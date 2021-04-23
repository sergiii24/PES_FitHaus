package cat.fib.fithaus

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_rutina.view.*

/** Classe RutinaAdapter
 *
 *  Classe que gestiona la vista del recycler view.
 *
 *  @param rutines
 *  @author Oriol Prat.
 */

class RutinaAdapter (val rutines:List<Rutina>):RecyclerView.Adapter<RutinaAdapter.RutinaHolder>() {

    /** Funció onCreateViewHolder
     *
     *  Funció on es genera la pantalla del recycler view.
     *
     *  @param parent
     *  @param viewType
     *  @author Oriol Prat.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RutinaHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return RutinaHolder(layoutInflater.inflate(R.layout.item_rutina, parent, false))
    }

    /** Funció getItemCount
     *
     *  Funció que retorna el nombre d'elements a renderitzar.
     *
     *  @author Oriol Prat.
     */
    override fun getItemCount(): Int {
        return rutines.size
    }

    /** Funció onBindViewHolder
     *
     *  Funció on es renderitza un cert element al recycler view.
     *
     *  @param holder
     *  @param position
     *  @author Oriol Prat.
     */
    override fun onBindViewHolder(holder: RutinaHolder, position: Int) {
        holder.render(rutines[position])
    }

    /** Classe RutinaHolder
     *
     *  Classe que gestiona la renderització dels elements al recycler view.
     *
     *  @author Oriol Prat.
     */
    class RutinaHolder(val view: View):RecyclerView.ViewHolder(view){

        /** Funció render
         *
         *  Funció on es gestionen els components d'un element del recycler view.
         *
         *  @param rutina
         *  @author Oriol Prat.
         */
        fun render(rutina: Rutina){
            view.tvNom.text = rutina.nom
            view.tvNivell.text = rutina.nivell

            //TODO: canviar per setimage
            view.ivRutina.text = rutina.imatge

            //TODO: canviar per moure's a consultar rutina
            view.setOnClickListener {
                Toast.makeText(view.context, "Has seleccionat la rutina ${rutina.nom}", Toast.LENGTH_SHORT).show()
            }

            view.btAfegirACalendari.setOnClickListener {
                Toast.makeText(view.context, "Encara no s'ha implementat", Toast.LENGTH_LONG).show()
            }

        }
    }

}