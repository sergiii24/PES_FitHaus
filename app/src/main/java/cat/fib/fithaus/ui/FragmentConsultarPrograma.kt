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
import cat.fib.fithaus.ProgramRecyclerView
import cat.fib.fithaus.ProgramaAdapter
import cat.fib.fithaus.R
import cat.fib.fithaus.data.models.Program
import cat.fib.fithaus.utils.Status
import cat.fib.fithaus.viewmodels.ProgramViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_consultar_programa.*
import kotlinx.android.synthetic.main.programa_recyclerview.view.*

/** Classe FragmentConsultarPrograma
 *
 *  Classe on hi ha el fragment d'un programa
 *
 *  @constructor Inicialitza els camps d'informació amb les dades del programa consultat.
 *  @author Daniel Cárdenas.
 */

@AndroidEntryPoint
class FragmentConsultarPrograma : Fragment(R.layout.fragment_consultar_programa) {
    private val viewModel by viewModels<ProgramViewModel>()

    lateinit var nom_programa: TextView
    lateinit var descripcio: TextView
    lateinit var nivell_text: TextView
    lateinit var nivell_asignat: TextView
    lateinit var setmanes_text: TextView
    lateinit var setmanes_asignades: TextView
    lateinit var llistat_rutines_text: TextView
    lateinit var llistat_rutines: RecyclerView
    var num_predef_routines: Int = 0



    /** Funció inicialitzadora
     *
     *  Funció que inicialitza les variables amb el valor corresponent segons el programa consultat.
     *
     *  @param inflater, container, savedInstanceState
     *  @return View?
     *  @author Daniel Cárdenas.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v: View = inflater.inflate(R.layout.fragment_consultar_programa, container, false)

        nom_programa = v.findViewById(R.id.NomProgramaConsultat)
        descripcio = v.findViewById(R.id.descripcióProgramaConsultat)
        nivell_text = v.findViewById(R.id.NivellProgramaText)
        nivell_asignat = v.findViewById(R.id.nivellProgramaConsultat)
        setmanes_text = v.findViewById(R.id.SetmanesProgramaText)
        setmanes_asignades = v.findViewById(R.id.setmanesProgramaConsultat)
        llistat_rutines_text = v.findViewById(R.id.RutinesText)
        llistat_rutines = v.findViewById(R.id.LlistatRutines)
        viewModel.program.observe(viewLifecycleOwner, Observer {
            if (it.status == Status.SUCCESS) {
                setUpData(it.data)
                val routinesList = generateList(num_predef_routines)
                LlistatRutines.adapter = ProgramaAdapter(routinesList)
                LlistatRutines.layoutManager = LinearLayoutManager(this.context)
            }
            else Toast.makeText(this.context, "ERROR!", Toast.LENGTH_LONG).show()
        })
        return v
    }

    private fun generateList(size: Int): List<ProgramRecyclerView> {
        val list = ArrayList<ProgramRecyclerView>()

        for (i in 0 until size) {
            val imagen = list[i].imageResource
            val nombre = list[i].nametext

            val item = ProgramRecyclerView(imagen, nombre)
            list += item
        }
        return list
    }


    /** Funció setUpData
     *
     *  Funció que omple els camps del fragment amb les dades del programa corresponent.
     *
     *  @param userData
     *  @author Daniel Cárdenas.
     */
    fun setUpData(programData: Program?) {
        nom_programa.text = programData?.name.toString()
        descripcio.text = programData?.description.toString()
        nivell_asignat.text = programData?.difficulty.toString()
        setmanes_asignades.text = programData?.weeks.toString()
        //llistat_rutines = programData?.predef_routines.get()
        //num_predef_routines = programData?.predef_routines?.size!!
    }
}
