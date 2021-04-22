package cat.fib.fithaus

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_cercar_exercici.*


class CercarExerciciActivity : AppCompatActivity() {

    private lateinit var dialogBuilder: AlertDialog.Builder
    private lateinit var dialog: AlertDialog

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
        val nom1 = contactPopupView.findViewById<View>(R.id.etnom1) as EditText
        val nom2 = contactPopupView.findViewById<View>(R.id.etnom2) as EditText
        val nom3 = contactPopupView.findViewById<View>(R.id.etnom3) as EditText
        val boto1 = contactPopupView.findViewById<View>(R.id.bt1) as Button
        val boto2 = contactPopupView.findViewById<View>(R.id.bt2) as Button
        dialogBuilder.setView(contactPopupView)
        dialog = dialogBuilder.create()
        dialog.show()
        boto1.setOnClickListener { }
        boto2.setOnClickListener { }
    }

}