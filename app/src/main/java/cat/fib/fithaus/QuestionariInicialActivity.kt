package cat.fib.fithaus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import cat.fib.fithaus.api.ApiServices
import kotlinx.android.synthetic.main.activity_questionari_inicial.*
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException
import cat.fib.fithaus.questionari.QuestionariViewModel

class QuestionariInicialActivity : AppCompatActivity() {

    private lateinit var questionariViewModel: QuestionariViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questionari_inicial)

        val appContainer = (application as Application).appContainer
        questionariViewModel = appContainer.questionariViewModelFactory.create()

        setupSendButton()
    }

    private fun setupSendButton() {
        buttonEnviar.setOnClickListener {
            send()
        }
    }

    private fun send() {
        val experiencia: String? = experience()
        val categories: MutableList<String> = chosencategories()
        val countC = categories.size
        val objectius: MutableList<String> = chosenobjectives()
        val obj: String = objectius.toString()
        val countO = objectius.size

        if ((experiencia == null) || (countC == 0 || countC > 3) || (countO == 0 || countO > 3)) {
            if (experiencia == null)  Toast.makeText(this, "No has seleccionat experiència", Toast.LENGTH_LONG).show()
            //else Toast.makeText(this, experiencia, Toast.LENGTH_LONG).show()
            if (countC == 0 || countC > 3) Toast.makeText(this, "Selecciona entre 1 i 3 categories", Toast.LENGTH_LONG).show()
            if (countO == 0 || countO > 3) Toast.makeText(this, "Selecciona entre 1 i 3 objectius", Toast.LENGTH_LONG).show()
        }
        else {
            Toast.makeText(this, countO.toString(), Toast.LENGTH_LONG).show()
            Toast.makeText(this, experiencia, Toast.LENGTH_LONG).show()
            println(categories)
            println(obj)

            //questionariViewModel.patchQuestionari("1", experiencia, categories, objectius)
            // Create JSON using JSONObject
            /*
            val jsonObject = JSONObject()
            jsonObject.put("id", "1")
            jsonObject.put("categories", categories)
            jsonObject.put("objectius", objectius)

            // Convert JSONObject to String
            val jsonObjectString = jsonObject.toString()
            */

        }
    }

    private fun experience(): String? {
        return when {
            radioButtonPrincipiant?.isChecked == true -> "Princpiant"
            radioButtonIntermedi?.isChecked == true -> "Intermedi"
            radioButtonAvançat?.isChecked == true -> "Avançat"
            else -> null
        }
    }

    private fun chosenobjectives(): MutableList<String> {
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

    private fun chosencategories(): MutableList<String> {
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