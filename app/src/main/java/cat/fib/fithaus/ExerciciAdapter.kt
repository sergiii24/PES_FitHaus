package cat.fib.fithaus

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_exercici.view.*

/** Classe ExerciciAdapter
 *
 *  Classe que gestiona la vista del recycler view.
 *
 *  @param exercicis
 *  @author Oriol Prat.
 */

class ExerciciAdapter (val exercicis:List<Exercici>):RecyclerView.Adapter<ExerciciAdapter.ExerciciHolder>() {

    /** Funció onCreateViewHolder
     *
     *  Funció on es genera la pantalla del recycler view.
     *
     *  @param parent
     *  @param viewType
     *  @author Oriol Prat.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciciHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ExerciciHolder(layoutInflater.inflate(R.layout.item_exercici, parent, false))
    }

    /** Funció getItemCount
     *
     *  Funció que retorna el nombre d'elements a renderitzar.
     *
     *  @author Oriol Prat.
     */
    override fun getItemCount(): Int {
        return exercicis.size
    }

    /** Funció onBindViewHolder
     *
     *  Funció on es renderitza un cert element al recycler view.
     *
     *  @param holder
     *  @param position
     *  @author Oriol Prat.
     */
    override fun onBindViewHolder(holder: ExerciciHolder, position: Int) {
        holder.render(exercicis[position])
    }

    /** Classe ExerciciHolder
     *
     *  Classe que gestiona la renderització dels elements al recycler view.
     *
     *  @author Oriol Prat.
     */
    class ExerciciHolder(val view: View):RecyclerView.ViewHolder(view){

        /** Funció render
         *
         *  Funció on es gestionen els components d'un element del recycler view.
         *
         *  @param exercici
         *  @author Oriol Prat.
         */
        fun render(exercici: Exercici){
            view.tvNom.text = exercici.nom
            view.tvDuracio.text = exercici.duracio.toString()
            view.tvDificultat.text = exercici.dificultat

            //TODO: canviar per setimage
            view.ivExercici.text = exercici.imatge

            //TODO: canviar per moure's a consultar exercici
            view.setOnClickListener {
                Toast.makeText(view.context, "Has seleccionat l'exercici ${exercici.nom}", Toast.LENGTH_SHORT).show()
            }

            view.btAfegeixARutina.setOnClickListener {
                Toast.makeText(view.context, "Encara no s'ha implementat", Toast.LENGTH_LONG).show()
            }

        }
    }

}