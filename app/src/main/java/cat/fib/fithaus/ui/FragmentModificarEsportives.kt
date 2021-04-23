package cat.fib.fithaus.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import cat.fib.fithaus.R
import cat.fib.fithaus.models.User
import cat.fib.fithaus.models.UserModelView


/** Classe FragmentModificarEsportives
 *
 *  Classe on hi ha el fragment de les dades esportives.
 *
 *  @constructor Inicialitza els camps d'informació i captura les dades de l'usuari.
 *  @author Daniel Cárdenas.
 */
class FragmentModificarEsportives : Fragment(R.layout.fragment_modificar_esportives) {

    lateinit var activitats: TextView
    lateinit var assoliments: TextView
    lateinit var punts: TextView
    lateinit var nivell: EditText
    lateinit var objectiu: EditText
    lateinit var categoriainteres: EditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v: View = inflater.inflate(R.layout.fragment_modificar_personal, container, false)
        activitats = v.findViewById(R.id.NumActivitats)
        assoliments = v.findViewById(R.id.Assoliments)
        punts = v.findViewById(R.id.Punts)
        nivell = v.findViewById(R.id.Nivell)
        objectiu = v.findViewById(R.id.Objectiu)
        categoriainteres = v.findViewById(R.id.CategoriesInterès)
        if(UserModelView.user != null)
            setUpData(UserModelView.getUser())
        return v
    }

    /** Funció setUpData
     *
     *  Funció que omple els camps del fragment amb les dades d'usuari corresponents.
     *
     *  @param userData
     *  @author Daniel Cárdenas.
     */
    fun setUpData(userData: User) {
        activitats.text = userData.activitiesdone.toString()
        assoliments.text = userData.achievements.toString()
        punts.text = userData.points.toString()
        nivell.hint = userData.level.toString()
        objectiu.hint = userData.objectives.toString()
        categoriainteres.hint = userData.interestcategories.toString()
    }

}