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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ConsultarClasseFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ConsultarClasseFragment : Fragment() {
    /*
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    */

    lateinit var imatgeClasse: ImageView
    lateinit var nomClasse: TextView
    lateinit var contingutEntrenadorClasse: TextView
    lateinit var contingutDescripcioClasse: TextView
    lateinit var contingutAreaTreballClasse: TextView
    lateinit var contingutEdatClasse: TextView
    lateinit var contingutDificultatClasse: TextView
    lateinit var contingutDuracioClasse: TextView
    lateinit var contingutCategoriaClasse: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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

        setup()

        return view
    }

    fun setup(){
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

    /*
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ConsultarClasseFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ConsultarClasseFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    */
}