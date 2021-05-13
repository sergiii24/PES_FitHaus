package cat.fib.fithaus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import cat.fib.fithaus.utils.Status
import cat.fib.fithaus.viewmodels.UserViewModel
import kotlinx.android.synthetic.main.activity_questionari_inicial.*
import cat.fib.fithaus.data.models.User
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONObject
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.math.exp

/** Classe Activty Qüestionari Inicial
 *
 *  Activity on es troba tota la lògica per poder fer el qüestionari inicial de l'usuari després de registrar-se.
 *
 *  @constructor Crea el qüestionari de l'usuari amb tots els camps sense seleccionar.
 *  @author Adrià Espinola.
 */
@AndroidEntryPoint
class QuestionariInicialActivity : AppCompatActivity() {

    private val viewModel by viewModels<UserViewModel>()

    /** Funció inicialitzadora
     *
     *  Funció que fa que es mostri la pantalla del qüestionari inicial.
     *
     *  @param savedInstanceState
     *  @author Adrià Espinola.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questionari_inicial)

        setupSendButton()
    }

    /** Funció setupSendButton
     *
     *  Funció encarregada de configurar el botó d'enviar el qüestionari.
     *
     *  @author Adrià Espinola.
     */
    private fun setupSendButton() {
        buttonEnviar.setOnClickListener {
            send()
        }
    }

    /** Funció send
     *
     *  Funció que comprova que el qüestionari sigui omplert correctament.
     *
     *  @author Adrià Espinola.
     */
    private fun send() {
        val experiencia: String? = experience()
        val categories: MutableList<String> = chosenCategories()
        val countC = categories.size
        val objectius: MutableList<String> = chosenObjectives()
        val countO = objectius.size

        if ((experiencia == null) || (countC == 0 || countC > 3) || (countO == 0 || countO > 3)) {
            if (experiencia == null)  Toast.makeText(this, "No has seleccionat experiència", Toast.LENGTH_LONG).show()
            if (countC == 0 || countC > 3) Toast.makeText(this, "Selecciona entre 1 i 3 categories", Toast.LENGTH_LONG).show()
            if (countO == 0 || countO > 3) Toast.makeText(this, "Selecciona entre 1 i 3 objectius", Toast.LENGTH_LONG).show()
        }
        else {

            //Toast.makeText(this, "Enviat correctament", Toast.LENGTH_LONG).show()
            viewModel.user.observe(this, Observer {
                if (it.status == Status.SUCCESS) {
                    updateU(it.data, objectius, categories, experiencia)
                }
                else Toast.makeText(this, "ERROR!", Toast.LENGTH_LONG).show()
            })

            //val intent = Intent(this, LogInActivity::class.java)
            //startActivity(intent)

            // Create JSON using JSONObject for back?
            /*
            val jsonObject = JSONObject()
            jsonObject.put("id", "1")
            jsonObject.put("categories", categories)
            jsonObject.put("objectius", objectius)
            println(jsonObject)
            // Convert JSONObject to String
            val jsonObjectString = jsonObject.toString()
            */
        }
    }

    /** Funció updateU
     *
     *  Funció que actualitza el objecte Usuari amb els objectius, categories i experiència seleccionades.
     *
     *  @author Adrià Espinola.
     */
    private fun updateU(user: User?, objectius: MutableList<String>, categories: MutableList<String>, experiencia: String) {
        val userId = user?.id
        if (user != null) {
            user.objectives = ArrayList(objectius)
            user.categories = ArrayList(categories)
            user.level = experiencia
        }
        if (userId != null) {
            viewModel.update(userId, user).observe(this, androidx.lifecycle.Observer {
                if(it.status == Status.SUCCESS) {
                    Toast.makeText(this, "Qüestionari enviat", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, LogInActivity::class.java)
                    startActivity(intent)
                } else if (it.status == Status.ERROR) Toast.makeText(this, "ERROR2!", Toast.LENGTH_LONG).show()
            })
        }
    }

    /** Funció experience
     *
     *  Funció que retorna el contingut del radio Button seleccionat.
     *
     *  @author Adrià Espinola.
     */
    private fun experience(): String? {
        return when {
            radioButtonPrincipiant?.isChecked == true -> "B"
            radioButtonIntermedi?.isChecked == true -> "I"
            radioButtonAvançat?.isChecked == true -> "A"
            else -> null
        }
    }

    /** Funció chosenObjectives
     *
     *  Funció que retorna una llista amb els objectius seleccionats.
     *
     *  @author Adrià Espinola.
     */
    private fun chosenObjectives(): MutableList<String> {
        val objectius: MutableList<String> = ArrayList()
        if (checkBoxSalut.isChecked) objectius.add("Salut")
        if (checkBoxForça2.isChecked) objectius.add("Força")
        if (checkBoxPerduaPes.isChecked) objectius.add("Pèrdua de pes")
        if (checkBoxFlexibilitat.isChecked) objectius.add("Flexibilitat")
        if (checkBoxResistència.isChecked) objectius.add("Resistència")
        if (checkBoxRecuperació.isChecked) objectius.add("Recuperació")
        if (checkBoxAgilitat.isChecked) objectius.add("Agilitat")
        return objectius
    }

    /** Funció chosenCategories
     *
     *  Funció que retorna una llista amb les categories seleccionades.
     *
     *  @author Adrià Espinola.
     */
    private fun chosenCategories(): MutableList<String> {
        val categories: MutableList<String> = ArrayList()
        if (checkBoxForça.isChecked) categories.add("Força")
        if (checkBoxCardio.isChecked) categories.add("Cardio")
        if (checkBoxIoga.isChecked) categories.add("Ioga")
        if (checkBoxEstiraments.isChecked) categories.add("Estiraments")
        if (checkBoxRehabilitació.isChecked) categories.add("Rehabilitació")
        if (checkBoxPilates.isChecked) categories.add("Pilates")
        return categories
    }
}