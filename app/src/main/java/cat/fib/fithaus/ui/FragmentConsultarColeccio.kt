package cat.fib.fithaus.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cat.fib.fithaus.RecyclerViewAdapter
import cat.fib.fithaus.CardViewItem
import cat.fib.fithaus.R
import cat.fib.fithaus.data.models.Collection
import cat.fib.fithaus.utils.Status
import cat.fib.fithaus.viewmodels.CollectionViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_consultar_coleccio.*

/** Classe FragmentConsultarColeccio
 *
 *  Classe on hi ha el fragment d'una col·lecció
 *
 *  @constructor Inicialitza els camps d'informació amb les dades de la col·lecció consultada.
 *  @author Daniel Cárdenas.
 */

@AndroidEntryPoint
class FragmentConsultarColeccio : Fragment(R.layout.fragment_consultar_coleccio), RecyclerViewAdapter.OnItemClickListener {
    private val viewModel by viewModels<CollectionViewModel>()

    lateinit var nom_coleccio: TextView
    lateinit var descripcio_coleccio: TextView
    lateinit var llistat_rutines_text: TextView
    lateinit var llistat_rutines: ArrayList<CardViewItem>
    lateinit var recyclerView: RecyclerView


    /** Funció inicialitzadora
     *
     *  Funció que inicialitza les variables amb el valor corresponent segons la col·lecció consultada.
     *
     *  @param inflater, container, savedInstanceState
     *  @return View?
     *  @author Daniel Cárdenas.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v: View = inflater.inflate(R.layout.fragment_consultar_coleccio, container, false)

        nom_coleccio = v.findViewById(R.id.NomColleccioConsultada)
        descripcio_coleccio = v.findViewById(R.id.descripcióColleccioConsultada)
        llistat_rutines_text = v.findViewById(R.id.RutinesText)
        recyclerView = v.findViewById(R.id.recycler_view)

        /*viewModel.collection.observe(viewLifecycleOwner, Observer {
            if (it.status == Status.SUCCESS) {
                setUpData(it.data)
                /*val routinesList = generateList(num_predef_routines)
                LlistatRutines.adapter = RecyclerViewAdapter(routinesList)
                LlistatRutines.layoutManager = LinearLayoutManager(this.context)*/
            }
            else Toast.makeText(this.context, "ERROR!", Toast.LENGTH_LONG).show()
        })*/
        setExampleContent()
        return v
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(context, "Item $position clicked", Toast.LENGTH_SHORT).show()
        val clickedItem = llistat_rutines[position]
        clickedItem.text = "Item $position modified"
        recyclerView.adapter?.notifyItemChanged(position)
    }

    /*private fun generateList(size: Int): List<CardViewItem> {
        val list = ArrayList<CardViewItem>()

        for (i in 0 until size) {
            val imagen = list[i].imageResource
            val nombre = list[i].nametext

            val item = CardViewItem(imagen, nombre)
            list += item
        }
        return list
    }*/


    /** Funció setUpData
     *
     *  Funció que omple els camps del fragment amb les dades de la col·lecció corresponent.
     *
     *  @param collectionData
     *  @author Daniel Cárdenas.
     */
    /*fun setUpData(collectionData: Collection?) {
        nom_coleccio.text = collectionData?.name.toString()
        descripcio_coleccio.text = collectionData?.description.toString()
        //llistat_rutines = collectionData?.predef_routines.get()

    }*/


    private fun setExampleContent(){
        nom_coleccio.text = "Col·lecció d'exemple"
        descripcio_coleccio.text = "Descripció de la col·lecció d'exemple"

        llistat_rutines = ArrayList<CardViewItem>()
        for (i in 0 until 500) {
            val imatge = when (i % 3) {
                0 -> "http://simpleicon.com/wp-content/uploads/android.png"
                1 -> "https://upload.wikimedia.org/wikipedia/commons/thumb/f/fa/Apple_logo_black.svg/647px-Apple_logo_black.svg.png"
                else -> "https://icons-for-free.com/iconfiles/png/512/microsoft+windows+icon-1320186681671871370.png"
            }
            val item = CardViewItem(imatge, "Item $i")
            llistat_rutines.plusAssign(item)
        }
        recyclerView.adapter = RecyclerViewAdapter(llistat_rutines, this)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)
    }
}
