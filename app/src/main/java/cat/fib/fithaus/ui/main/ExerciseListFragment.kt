package cat.fib.fithaus.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ToggleButton
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import cat.fib.fithaus.R
import cat.fib.fithaus.adapters.ExerciseAdapter
import cat.fib.fithaus.databinding.MainFragmentBinding
import cat.fib.fithaus.viewmodels.ExerciseViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.app_bar_main.*
import java.util.*

@AndroidEntryPoint
class ExerciseListFragment : Fragment() {

    private val viewModel: ExerciseViewModel by viewModels()
    private lateinit var dialogBuilder: AlertDialog.Builder
    private lateinit var dialog: AlertDialog
    val adapter = ExerciseAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = MainFragmentBinding.inflate(inflater, container, false)
        context ?: return binding.root

        binding.photoList.adapter = adapter
        subscribeUi(adapter)
        binding.toolbar.setOnClickListener { e->
            run {
                createNewContactDialog(e)
            }
        }
        setHasOptionsMenu(true)
        return binding.root
    }


    private fun subscribeUi(adapter: ExerciseAdapter) {

        viewModel.ex_filtered.observe(viewLifecycleOwner) { exercise ->
            adapter.submitList(exercise)
        }
    }

    private fun updateData() {
        /*with(viewModel) {
            if (isFiltered()) {
                clearGrowZoneNumber()
            } else {
                setGrowZoneNumber(9)
            }
        }*/
    }

    private fun createNewContactDialog(v : View) {
        dialogBuilder = AlertDialog.Builder(v.context)
        val contactPopupView = layoutInflater.inflate(R.layout.filter_exercise, null)

        val btFiltrar: Button = contactPopupView.findViewById(R.id.btOkay)
        val btCancelar: Button = contactPopupView.findViewById(R.id.btCancel)

        dialogBuilder.setView(contactPopupView)
        dialog = dialogBuilder.create()
        dialog.show()
        btFiltrar.setOnClickListener {
            val diffculty : MutableList<String> = mutableListOf()
            val facil: ToggleButton = contactPopupView.findViewById(R.id.tbFacil)
            if (facil.isChecked) diffculty.add("facil")
            val mitja: ToggleButton = contactPopupView.findViewById(R.id.tbMitja)
            if (mitja.isChecked) diffculty.add("mitja")
            val dificil: ToggleButton = contactPopupView.findViewById(R.id.tbDificil)
            if (dificil.isChecked) diffculty.add("dificil")
            viewModel.setFilter(diffculty)
            adapter.notifyDataSetChanged()
            dialog.dismiss()
        }
        btCancelar.setOnClickListener { dialog.dismiss() }
    }



}