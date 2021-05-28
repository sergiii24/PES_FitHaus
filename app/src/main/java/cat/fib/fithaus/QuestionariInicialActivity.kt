package cat.fib.fithaus

import android.content.Context
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

    private var identificadorUsuari: String? = null   // Identificador de l'usuari

    /** Funció inicialitzadora
     *
     *  Funció que fa que es mostri la pantalla del qüestionari inicial.
     *
     *  @param savedInstanceState
     *  @author Adrià Espinola.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
        identificadorUsuari = prefs.getString("userId", null)
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

            identificadorUsuari?.let {
                viewModel.getUser(it)
            }

            viewModel.user.observe(this, Observer {
                if (it.status == Status.SUCCESS) {
                    updateUser(it.data, objectius, categories, experiencia)
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
    private fun updateUser(user: User?, objectius: MutableList<String>, categories: MutableList<String>, experiencia: String) {
        val userId = user?.id
        if (user != null) {
            user.objectives = ArrayList(objectius)
            user.categories = ArrayList(categories)
            user.level = experiencia
        }
        if (userId != null) {
            viewModel.updateUser(userId, user)
            viewModel.user.observe(this, Observer {
                if(it.status == Status.SUCCESS) {
                    val intent = Intent(this, MainActivity::class.java)
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
        if (checkBoxSalut.isChecked) objectius.add("S")
        if (checkBoxForça2.isChecked) objectius.add("Fr")
        if (checkBoxPerduaPes.isChecked) objectius.add("P")
        if (checkBoxFlexibilitat.isChecked) objectius.add("Fl")
        if (checkBoxResistència.isChecked) objectius.add("Rs")
        if (checkBoxRecuperació.isChecked) objectius.add("Rc")
        if (checkBoxAgilitat.isChecked) objectius.add("A")
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
        if (checkBoxForça.isChecked) categories.add("S")
        if (checkBoxCardio.isChecked) categories.add("C")
        if (checkBoxIoga.isChecked) categories.add("Y")
        if (checkBoxEstiraments.isChecked) categories.add("E")
        if (checkBoxRehabilitació.isChecked) categories.add("R")
        if (checkBoxPilates.isChecked) categories.add("P")
        return categories
    }
}