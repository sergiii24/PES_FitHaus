package cat.fib.fithaus

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_classe.view.*

/** Classe ClasseAdapter
 *
 *  Classe que gestiona la vista del recycler view.
 *
 *  @param classes
 *  @author Oriol Prat.
 */

class ClasseAdapter (val classes:List<Classe>):RecyclerView.Adapter<ClasseAdapter.ClasseHolder>() {

    /** Funció onCreateViewHolder
     *
     *  Funció on es genera la pantalla del recycler view.
     *
     *  @param parent
     *  @param viewType
     *  @author Oriol Prat.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClasseHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ClasseHolder(layoutInflater.inflate(R.layout.item_classe, parent, false))
    }

    /** Funció getItemCount
     *
     *  Funció que retorna el nombre d'elements a renderitzar.
     *
     *  @author Oriol Prat.
     */
    override fun getItemCount(): Int {
        return classes.size
    }

    /** Funció onBindViewHolder
     *
     *  Funció on es renderitza un cert element al recycler view.
     *
     *  @param holder
     *  @param position
     *  @author Oriol Prat.
     */
    override fun onBindViewHolder(holder: ClasseHolder, position: Int) {
        holder.render(classes[position])
    }

    /** Classe ExerciciHolder
     *
     *  Classe que gestiona la renderització dels elements al recycler view.
     *
     *  @author Oriol Prat.
     */
    class ClasseHolder(val view: View):RecyclerView.ViewHolder(view){

        /** Funció render
         *
         *  Funció on es gestionen els components d'un element del recycler view.
         *
         *  @param classe
         *  @author Oriol Prat.
         */
        fun render(classe: Classe){
            view.tvNom.text = classe.nom
            view.tvDuracio.text = classe.duracio.toString()
            view.tvDificultat.text = classe.dificultat

            //TODO: canviar per setimage
            view.ivClasse.text = classe.imatge

            //TODO: canviar per moure's a consultar classe
            view.setOnClickListener {
                Toast.makeText(view.context, "Has seleccionat la classe ${classe.nom}", Toast.LENGTH_SHORT).show()
            }

            view.btAfegeixARutina.setOnClickListener {
                Toast.makeText(view.context, "Encara no s'ha implementat", Toast.LENGTH_LONG).show()
            }

        }
    }

}