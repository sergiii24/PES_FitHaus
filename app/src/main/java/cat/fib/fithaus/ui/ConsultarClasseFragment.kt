package cat.fib.fithaus.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import cat.fib.fithaus.R
import kotlinx.android.synthetic.main.fragment_consultar_classe.*
import org.w3c.dom.Text

// Paràmetres d'inicialització del Fragment
private const val ARG_PARAM1 = "nomClasse"

/** Fragment ConsultarClasse
 *
 *  Fragment encarregat de consultar la informació completa d'una classe
 *
 *  @constructor Crea el Fragment ConsultarClasse
 *  @author Albert Miñana Montecino
 */
class ConsultarClasseFragment : Fragment() {

    private var nomIdentificadorClasse: String? = null  // Nom de la classe

    lateinit var imatgeClasse: ImageView                // ImageView amb la imatge de previsualització de la classe
    lateinit var nomClasse: TextView                    // TextView amb el nom de la classe
    lateinit var contingutEntrenadorClasse: TextView    // TextView amb l'entrenador de la classe
    lateinit var contingutDescripcioClasse: TextView    // TextView amb la descripció de la classe
    lateinit var contingutAreaTreballClasse: TextView   // TextView amb l'àrea de treball de la classe
    lateinit var contingutEdatClasse: TextView          // TextView amb l'edat de la classe
    lateinit var contingutDificultatClasse: TextView    // TextView amb la dificultat de la classe
    lateinit var contingutDuracioClasse: TextView       // TextView amb la duració de la classe
    lateinit var contingutCategoriaClasse: TextView     // TextView amb la categoria de la classe

    /** Function onCreate
     *
     *  Funció encarregada de crear el fragment
     *
     *  @param savedInstanceState
     *  @author Albert Miñana Montecino
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            nomIdentificadorClasse = it.getString(ARG_PARAM1)
        }
    }

    /** Function onCreateView
     *
     *  Funció encarregada de configurar i mostrar el contingut del fragment
     *
     *  @param inflater
     *  @param container
     *  @param savedInstanceState
     *  @author Albert Miñana Montecino
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_consultar_classe, container, false)

        imatgeClasse = view.findViewById(R.id.imatgeClasse)
        nomClasse = view.findViewById(R.id.nomClasse)
        contingutEntrenadorClasse = view.findViewById(R.id.contingutEntrenadorClasse)
        contingutDescripcioClasse = view.findViewById(R.id.contingutDescripcioClasse)
        contingutAreaTreballClasse = view.findViewById(R.id.contingutAreaTreballClasse)
        contingutEdatClasse = view.findViewById(R.id.contingutEdatClasse)
        contingutDificultatClasse = view.findViewById(R.id.contingutDificultatClasse)
        contingutDuracioClasse = view.findViewById(R.id.contingutDuracioClasse)
        contingutCategoriaClasse = view.findViewById(R.id.contingutCategoriaClasse)

        setContent()

        return view
    }

    /** Function setContent
     *
     *  Funció encarregada d'establir el contingut amb la informació completa d'una classe
     *
     *  @author Albert Miñana Montecino
     */
    fun setContent(){
        // Demanar dades d'una classe a Back

        imatgeClasse.setImageResource(R.drawable.fitness_home)
        nomClasse.text = "Classe d'estiraments"
        contingutEntrenadorClasse.text = "Albert"
        contingutDescripcioClasse.text = "Es realitzen una gran varietat d'estiraments, pensats pel pre i post entrenament."
        contingutAreaTreballClasse.text = "Cos sencer"
        contingutEdatClasse.text = "Adult"
        contingutDificultatClasse.text = "Fàcil"
        contingutDuracioClasse.text = "15 minuts"
        contingutCategoriaClasse.text = "Estiraments"
    }

}