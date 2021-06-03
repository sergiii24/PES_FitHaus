package cat.fib.fithaus.viewmodels

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cat.fib.fithaus.CardViewItem
import cat.fib.fithaus.ConsultarClasseActivity
import cat.fib.fithaus.data.models.Class
import cat.fib.fithaus.R
import cat.fib.fithaus.RecyclerViewAdapter
import cat.fib.fithaus.utils.Status
import dagger.hilt.android.AndroidEntryPoint

// Paràmetres d'inicialització del Fragment
private const val EXTRA_MESSAGE = "cat.fib.fithaus.MESSAGE"

/** Fragment CercarFiltrarClasse
 *
 *  Fragment encarregat de cercar una classe mitjançant un filtre
 *
 *  @constructor Crea el Fragment CercarFiltrarClasse
 *  @author Albert Miñana Montecino
 */
@AndroidEntryPoint
class CercarFiltrarClasseFragment : Fragment(), RecyclerViewAdapter.OnItemClickListener {

    private val viewModel by viewModels<ClassViewModel>()       // ViewModel de les classes

    private var llistatClasses: List<Class>? = null             // Llistat del model classe

    lateinit var recyclerView: RecyclerView                     // RecyclerView de CardViewItems que contenen la imatge i el nom de tot el conjunt de classes
    lateinit var list: ArrayList<CardViewItem>                  // Llistat de CardViewItems que contenen la imatge i el nom de tot el conjunt de classes

    /** Function onCreate
     *
     *  Funció encarregada de crear el fragment
     *
     *  @param savedInstanceState
     *  @author Albert Miñana Montecino
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        val view: View = inflater.inflate(R.layout.fragment_cercar_filtrar_classe, container, false)

        recyclerView = view.findViewById(R.id.recycler_view)
        list = ArrayList<CardViewItem>()

        viewModel.getClasses()
        viewModel.classes?.observe(viewLifecycleOwner, Observer {
            if (it.status == Status.SUCCESS) {
                llistatClasses = it.data
                setContent()
            }
            else if (it.status == Status.ERROR)
                Toast.makeText(activity, "ERROR!", Toast.LENGTH_LONG).show()
        })

        return view
    }

    /** Function setContent
     *
     *  Funció encarregada de generar la llista de CardViewItems amb la imatge i el nom de tot el conjunt de classes
     *
     *  @author Albert Miñana Montecino
     */
    private fun setContent(){
        for (i in llistatClasses!!) {
            val imatge = cat.fib.fithaus.data.api.Configuration.urlServer + i.pre
            val nom = i.name
            val item = CardViewItem(imatge, nom)
            list.plusAssign(item)
        }
        recyclerView.adapter = RecyclerViewAdapter(list, this)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)
    }

    /** Function onItemClick
     *
     *  Funció encarregada de configurar el comportament del clic en un CardViewItem del RecyclerView
     *
     *  @param position
     *  @author Albert Miñana Montecino
     */
    override fun onItemClick(position: Int) {
        val nomIdentificadorClasse = llistatClasses!![position].name
        val intent = Intent(activity, ConsultarClasseActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, nomIdentificadorClasse)
        }
        startActivity(intent)
    }
}