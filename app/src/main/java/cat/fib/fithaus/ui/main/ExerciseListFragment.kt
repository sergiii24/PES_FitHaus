package cat.fib.fithaus.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import cat.fib.fithaus.adapters.ExerciseAdapter
import cat.fib.fithaus.databinding.MainFragmentBinding
import cat.fib.fithaus.viewmodels.ExerciseViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExerciseListFragment : Fragment() {

    private val viewModel: ExerciseViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = MainFragmentBinding.inflate(inflater, container, false)
        context ?: return binding.root

        val adapter = ExerciseAdapter()
        binding.photoList.adapter = adapter
        subscribeUi(adapter)

        setHasOptionsMenu(true)
        return binding.root
    }


    private fun subscribeUi(adapter: ExerciseAdapter) {

        viewModel.exercises.observe(viewLifecycleOwner) { exercise ->
            adapter.submitList(exercise.data)
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

}