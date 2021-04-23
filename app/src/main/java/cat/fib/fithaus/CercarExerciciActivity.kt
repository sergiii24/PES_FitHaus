package cat.fib.fithaus

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_cercar_exercici.*


class CercarExerciciActivity : AppCompatActivity() {

    private lateinit var dialogBuilder: AlertDialog.Builder
    private lateinit var dialog: AlertDialog

    //TODO: agafar tots els exercicis de l'app
    val exercicis = listOf(
            Exercici("cames", 5, "fàcil", "imatgecames"),
            Exercici("braços", 10, "fàcil", "imatgebraços"),
            Exercici("peus", 8, "fàcil", "imatgepeus"),
            Exercici("ulls", 1, "fàcil", "imatgeulls")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cercar_exercici)



        iniciBoto()
        initRecycler()
    }


    private fun iniciBoto() {
        val button: FloatingActionButton = findViewById(R.id.btFiltre)
        button.setOnClickListener { createNewContactDialog() }
    }


    fun initRecycler(){
        rvExerciciList.layoutManager = LinearLayoutManager(this)
        val adapter = ExerciciAdapter(exercicis)
        rvExerciciList.adapter = adapter
    }


    fun createNewContactDialog() {
        dialogBuilder = AlertDialog.Builder(this)
        val contactPopupView = layoutInflater.inflate(R.layout.pop_up_window, null)

        val nomExercici: TextView = contactPopupView.findViewById(R.id.etNomExercici)
        val btFiltrar: Button = contactPopupView.findViewById(R.id.btOkay)
        val btCancelar: Button = contactPopupView.findViewById(R.id.btCancel)

        dialogBuilder.setView(contactPopupView)
        dialog = dialogBuilder.create()
        dialog.show()
        btFiltrar.setOnClickListener {

        }
        btCancelar.setOnClickListener { dialog.dismiss() }
    }

}