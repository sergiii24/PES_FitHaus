package cat.fib.fithaus.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import cat.fib.fithaus.R
import cat.fib.fithaus.models.User
import cat.fib.fithaus.models.UserModelView

/** Classe Fragment esportives
 *
 *  Classe on hi ha el fragment de les dades esportives.
 *
 *  @constructor Inicialitza els camps d'informació i captura les dades de l'usuari.
 *  @author Oriol Prat.
 */

class FragmentEsportives : Fragment(R.layout.fragment_esportives) {

    lateinit var activitiesdone: TextView
    lateinit var achievements: TextView
    lateinit var points: TextView
    lateinit var level: TextView
    lateinit var objectives: TextView
    lateinit var interestcategories: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v: View = inflater.inflate(R.layout.fragment_esportives, container, false)
        activitiesdone = v.findViewById(R.id.NumActivitats_bd)
        achievements = v.findViewById(R.id.Assoliments_bd)
        points = v.findViewById(R.id.Punts_bd)
        level = v.findViewById(R.id.Nivell_bd)
        objectives = v.findViewById(R.id.Objectiu_bd)
        interestcategories = v.findViewById(R.id.CategoriesInterès_bd)
        if(UserModelView.user != null)
            setUpData(UserModelView.getUser())
        return v
    }

    /** Funció setUp
     *
     *  Funció que omple els camps del fragment amb les dades d'usuari corresponents.
     *
     *  @param userData
     *  @author Oriol Prat.
     */
    fun setUpData(userData: User) {
        activitiesdone.text = userData.activitiesdone.toString()
        achievements.text = userData.achievements
        points.text = userData.points.toString()
        level.text = userData.level.toString()
        objectives.text = userData.objectives.toString()
        interestcategories.text = userData.interestcategories
    }

}