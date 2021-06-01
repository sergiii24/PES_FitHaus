/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cat.fib.fithaus.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import cat.fib.fithaus.data.models.Exercise
import cat.fib.fithaus.databinding.ListItemExerciseBinding
import cat.fib.fithaus.ui.main.ExerciseListFragmentDirections


/**
 * Adapter for the [RecyclerView] in [PlantListFragment].
 */
class ExerciseAdapter : ListAdapter<Exercise, RecyclerView.ViewHolder>(PlantDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ExerciseViewHolder(
            ListItemExerciseBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val exercise = getItem(position)
        (holder as ExerciseViewHolder).bind(exercise)
    }

    class ExerciseViewHolder(
        private val binding: ListItemExerciseBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.exercise?.let { exercise ->
                    navigateToExercise(exercise, it)
                }
            }
        }

        private fun navigateToExercise(
            exercise: Exercise,
            view: View
        ) {
            val direction =
                ExerciseListFragmentDirections.actionViewPagerFragmentToPlantDetailFragment(
                )
            view.findNavController().navigate(direction)
       }

        fun bind(item: Exercise) {
            binding.apply {
                exercise = item
                executePendingBindings()
            }
        }
    }


    private class PlantDiffCallback : DiffUtil.ItemCallback<Exercise>() {

        override fun areItemsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
            return oldItem == newItem
        }
    }
}
