package cat.fib.fithaus

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.programa_recyclerview.view.*

class ProgramaAdapter(private val routinesList: List<ProgramRecyclerView>) : RecyclerView.Adapter<ProgramaAdapter.ProgramaViewHolder>() {

    class ProgramaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.image_routine_program
        val textView: TextView = itemView.name_routine_program
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgramaViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.programa_recyclerview, parent, false)

        return ProgramaViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProgramaViewHolder, position: Int) {
        val currentItem = routinesList[position]

        holder.imageView.setImageResource(currentItem.imageResource)
        holder.textView.text = currentItem.nametext
    }

    override fun getItemCount() = routinesList.size

}