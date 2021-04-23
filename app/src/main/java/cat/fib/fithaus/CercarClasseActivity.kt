package cat.fib.fithaus

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_cercar_classe.*

/** Classe Cercar classe
 *
 *  Activity on es mostren totes les classes i el popup del filtre d'aquestes.
 *
 *  @author Oriol Prat.
 */
class CercarClasseActivity : AppCompatActivity() {

    private lateinit var dialogBuilder: AlertDialog.Builder
    private lateinit var dialog: AlertDialog

    //TODO: agafar totes les classes de l'app
    val classes = listOf(
            Classe("classe1", 5, "fàcil", "imatgeclasse1"),
            Classe("classe2", 10, "fàcil", "imatgeclasse2"),
            Classe("classe3", 8, "fàcil", "imatgeclasse3"),
            Classe("classe4", 1, "fàcil", "imatgeclasse4")
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
        setContentView(R.layout.activity_cercar_classe)

        iniciBotoFiltre()
        initRecycler()
    }

    /** Funció iniciBoto
     *
     *  Funció on s'inicialitza el botó del filtre i es defineix el seu listener.
     *
     *  @author Oriol Prat.
     */
    private fun iniciBotoFiltre() {
        val button: FloatingActionButton = findViewById(R.id.btFiltre)
        button.setOnClickListener { createNewContactDialog() }
    }

    /** Funció initRecycler
     *
     *  Funció on s'inicialitza el recycler view de les classes.
     *
     *  @author Oriol Prat.
     */
    private fun initRecycler(){
        rvClasseList.layoutManager = LinearLayoutManager(this)
        val adapter = ClasseAdapter(classes)
        rvClasseList.adapter = adapter
    }

    /** Funció createNewContactDialog
     *
     *  Funció on es gestiona el popup del filtre.
     *
     *  @author Oriol Prat.
     */
    private fun createNewContactDialog() {
        dialogBuilder = AlertDialog.Builder(this)
        val contactPopupView = layoutInflater.inflate(R.layout.pop_up_window_classe, null)

        val nomClasse: TextView = contactPopupView.findViewById(R.id.etNomClasse)
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