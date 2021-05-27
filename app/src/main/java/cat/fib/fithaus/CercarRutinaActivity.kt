package cat.fib.fithaus

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_cercar_rutina.*

/** Classe Cercar rutina
 *
 *  Activity on es mostren totes les rutines i el popup del filtre d'aquestes.
 *
 *  @author Oriol Prat.
 */
class CercarRutinaActivity : AppCompatActivity() {

    private lateinit var dialogBuilder: AlertDialog.Builder
    private lateinit var dialog: AlertDialog

    //TODO: agafar totes les rutines de l'app
    val rutines = listOf(
            Rutina("rutina1","Principiant", "imatgerutina1"),
            Rutina("rutina2","Intermedi", "imatgerutina2"),
            Rutina("rutina3","Principiant", "imatgerutina3"),
            Rutina("rutina4","Avançat", "imatgerutina4"),

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
        setContentView(R.layout.activity_cercar_rutina)

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
    private fun initRecyclerLinear(){
        rvRutinaList.layoutManager = LayoutManager(this)
        val adapter = RutinaAdapter(rutines)
        rvRutinaList.adapter = adapter
    }

    /** Funció createNewContactDialog
     *
     *  Funció on es gestiona el popup del filtre.
     *
     *  @author Oriol Prat.
     */
    private fun createNewContactDialog() {
        dialogBuilder = AlertDialog.Builder(this)
        val contactPopupView = layoutInflater.inflate(R.layout.pop_up_window_rutina, null)

        val nomRutina: TextView = contactPopupView.findViewById(R.id.etNomRutina)
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