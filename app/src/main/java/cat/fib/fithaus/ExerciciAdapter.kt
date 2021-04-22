package cat.fib.fithaus

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_exercici.view.*

//monta la vista del recycler view
class ExerciciAdapter (val exercici:List<Exercici>):RecyclerView.Adapter<ExerciciAdapter.ExerciciHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciciHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ExerciciHolder(layoutInflater.inflate(R.layout.item_exercici, parent, false))
    }

    override fun getItemCount(): Int {
        return exercici.size
    }

    override fun onBindViewHolder(holder: ExerciciHolder, position: Int) {
        holder.render(exercici[position])
    }


    class ExerciciHolder(val view: View):RecyclerView.ViewHolder(view){

        fun render(exercici: Exercici){
            view.tvNom.text = exercici.nom
            view.tvDuracio.text = exercici.duracio.toString()
            view.tvDificultat.text = exercici.dificultat

            //TODO: canviar per setimage
            view.ivExercici.text = exercici.imatge

            //TODO: canviar per moure's a consultar exercici
            view.setOnClickListener { Toast.makeText(view.context, "Has seleccionat l'exercici ${exercici.nom}", Toast.LENGTH_SHORT).show() }
        }
    }


}