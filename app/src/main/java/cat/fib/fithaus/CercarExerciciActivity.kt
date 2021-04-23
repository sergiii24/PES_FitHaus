package cat.fib.fithaus

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_cercar_exercici.*

/** Classe Cercar exercici
 *
 *  Activity on es mostren tots els exercicis i el popup del filtre d'aquests.
 *
 *  @author Oriol Prat.
 */
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

    /** Funció onCreate
     *
     *  Funció on es mostra la pantalla i s'inicia la configuració dels elements.
     *
     *  @param savedInstanceState
     *  @author Oriol Prat.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cercar_exercici)

        iniciBoto()
        initRecycler()
    }

    /** Funció iniciBoto
     *
     *  Funció on s'inicialitza el botó del filtre i es defineix el seu listener.
     *
     *  @author Oriol Prat.
     */
    private fun iniciBoto() {
        val button: FloatingActionButton = findViewById(R.id.btFiltre)
        button.setOnClickListener { createNewContactDialog() }
    }

    /** Funció initRecycler
     *
     *  Funció on s'inicialitza el recycler view dels exercicis.
     *
     *  @author Oriol Prat.
     */
    private fun initRecycler(){
        rvExerciciList.layoutManager = LinearLayoutManager(this)
        val adapter = ExerciciAdapter(exercicis)
        rvExerciciList.adapter = adapter
    }

    /** Funció createNewContactDialog
     *
     *  Funció on es gestiona el popup del filtre.
     *
     *  @author Oriol Prat.
     */
    private fun createNewContactDialog() {
        dialogBuilder = AlertDialog.Builder(this)
        val contactPopupView = layoutInflater.inflate(R.layout.pop_up_window, null)

        val nomExercici: TextView = contactPopupView.findViewById(R.id.etNomExercici)
        val btFiltrar: Button = contactPopupView.findViewById(R.id.btOkay)
        val btCancelar: Button = contactPopupView.findViewById(R.id.btCancel)

        dialogBuilder.setView(contactPopupView)
        dialog = dialogBuilder.create()
        dialog.show()
        btFiltrar.setOnClickListener {
            Toast.makeText(this, "Encara no s'ha implementat", Toast.LENGTH_LONG).show()
        }
        btCancelar.setOnClickListener { dialog.dismiss() }
    }

}