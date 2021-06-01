package cat.fib.fithaus.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cat.fib.fithaus.CardViewItem
import cat.fib.fithaus.R
import cat.fib.fithaus.RecyclerViewAdapter
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_consultar_rutina_predefinida.*

// Paràmetres d'inicialització del Fragment
private const val ARG_PARAM1 = "identificadorRutinaPredefinida"

/** Fragment ConsultarRutinaPredefinida
 *
 *  Fragment encarregat de consultar la informació completa d'una rutina predefinida
 *
 *  @constructor Crea el Fragment ConsultarRutinaPredefinida
 *  @author Albert Miñana Montecino
 */
@AndroidEntryPoint
class ConsultarRutinaPredefinidaFragment : Fragment(), RecyclerViewAdapter.OnItemClickListener {

    //private val viewModel by viewModels<DefaultRoutineViewModel>()    // ViewModel de la rutina predefinida

    private var identificadorRutinaPredefinida: String? = null   // Identificador de la rutina predefinida

    lateinit var imatgeRutinaPredefinida: ImageView                 // ImageView amb la imatge de previsualització de la rutina predefinida
    lateinit var nomRutinaPredefinida: TextView                     // TextView amb el nom de la rutina predefinida
    lateinit var contingutDescripcioRutinaPredefinida: TextView     // TextView amb la descripció de la rutina predefinida
    lateinit var contingutCategoriaRutinaPredefinida: TextView      // TextView amb la categoria de la rutina predefinida
    lateinit var contingutTempsRutinaPredefinida: TextView          // TextView amb el temps de la rutina predefinida
    lateinit var contingutEdatRutinaPredefinida: TextView           // TextView amb l'edat de la rutina predefinida
    lateinit var contingutNivellRutinaPredefinida: TextView         // TextView amb el nivell de la rutina predefinida
    lateinit var contingutEquipamentRutinaPredefinida: TextView     // TextView amb l'equipament de la rutina predefinida
    lateinit var contingutObjectiuRutinaPredefinida: TextView       // TextView amb l'objectiu de la rutina predefinida
    lateinit var contingutImpacteRutinaPredefinida: TextView        // TextView amb l'impacte de la rutina predefinida

    lateinit var recyclerView: RecyclerView
    lateinit var llistat: ArrayList<CardViewItem>

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
            identificadorRutinaPredefinida = it.getString(ARG_PARAM1)
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
        val view: View = inflater.inflate(R.layout.fragment_consultar_rutina_predefinida, container, false)

        imatgeRutinaPredefinida = view.findViewById(R.id.imatgeRutinaPredefinida)
        nomRutinaPredefinida = view.findViewById(R.id.nomRutinaPredefinida)
        contingutDescripcioRutinaPredefinida = view.findViewById(R.id.contingutDescripcioRutinaPredefinida)
        contingutCategoriaRutinaPredefinida = view.findViewById(R.id.contingutCategoriaRutinaPredefinida)
        contingutTempsRutinaPredefinida = view.findViewById(R.id.contingutTempsRutinaPredefinida)
        contingutEdatRutinaPredefinida = view.findViewById(R.id.contingutEdatRutinaPredefinida)
        contingutNivellRutinaPredefinida = view.findViewById(R.id.contingutNivellRutinaPredefinida)
        contingutEquipamentRutinaPredefinida = view.findViewById(R.id.contingutEquipamentRutinaPredefinida)
        contingutObjectiuRutinaPredefinida = view.findViewById(R.id.contingutObjectiuRutinaPredefinida)
        contingutImpacteRutinaPredefinida = view.findViewById(R.id.contingutImpacteRutinaPredefinida)


        recyclerView = view.findViewById(R.id.recycler_view)

        /*
        identificadorExercici?.let {
            viewModel.getExercise(it)
        }

        viewModel.exercise.observe(viewLifecycleOwner, Observer {
            if (it.status == Status.SUCCESS)
                setContent(it.data)
            else
                Toast.makeText(activity, "ERROR!", Toast.LENGTH_LONG).show()
        })*/

        setExampleContent()

        return view
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(context, "Item $position clicked", Toast.LENGTH_SHORT).show()
        val clickedItem = llistat[position]
        clickedItem.text = "Item $position modified"
        recyclerView.adapter?.notifyItemChanged(position)
    }

    private fun setExampleContent(){
        Picasso.get().load("https://mens.laradiomovil.com/wp-content/uploads/2019/02/img_msanoja_20160504-144748_imagenes_lv_getty_gettyimages-546127037_2-k7r-U401526493710pEB-992x558@LaVanguardia-Web.jpg").into(imatgeRutinaPredefinida)
        nomRutinaPredefinida.text = "Rutina predefinida d'exemple"
        contingutDescripcioRutinaPredefinida.text = "Descripció de la rutina predefinida d'exemple"
        contingutCategoriaRutinaPredefinida.text = "Força, Càrdio, Estiraments"
        contingutTempsRutinaPredefinida.text = "Fixat"
        contingutEdatRutinaPredefinida.text = "Adult"
        contingutNivellRutinaPredefinida.text = "Intermedi"
        contingutEquipamentRutinaPredefinida.text = "Material de casa"
        contingutObjectiuRutinaPredefinida.text = "Salut"
        contingutImpacteRutinaPredefinida.text = "Moderat"

        llistat = ArrayList<CardViewItem>()
        for (i in 0 until 500) {
            val imatge = when (i % 3) {
                0 -> "http://simpleicon.com/wp-content/uploads/android.png"
                1 -> "https://upload.wikimedia.org/wikipedia/commons/thumb/f/fa/Apple_logo_black.svg/647px-Apple_logo_black.svg.png"
                else -> "https://icons-for-free.com/iconfiles/png/512/microsoft+windows+icon-1320186681671871370.png"
            }
            val item = CardViewItem(imatge, "Item $i")
            llistat.plusAssign(item)
        }
        recyclerView.adapter = RecyclerViewAdapter(llistat, this)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)
    }
}