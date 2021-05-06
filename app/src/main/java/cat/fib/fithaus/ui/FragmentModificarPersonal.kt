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
import cat.fib.fithaus.data.models.User
import cat.fib.fithaus.data.models.UserModelView


/** Classe FragmentModificarPersonal
 *
 *  Classe on hi ha el fragment de les dades personals.
 *
 *  @constructor Inicialitza els camps d'informació i captura les dades de l'usuari.
 *  @author Daniel Cárdenas.
 */
class FragmentModificarPersonal : Fragment(R.layout.fragment_modificar_personal) {

    lateinit var name: EditText
    lateinit var username: EditText
    lateinit var gender: RadioGroup
    lateinit var email: TextView
    lateinit var birthdate: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v: View = inflater.inflate(R.layout.fragment_modificar_personal, container, false)
        name = v.findViewById(R.id.nom)
        username = v.findViewById(R.id.usuari)
        gender = v.findViewById(R.id.SexeSelection)
        email = v.findViewById(R.id.mail)
        birthdate = v.findViewById(R.id.dataNaixement)
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
        name.hint = userData.firstname.toString() + userData.lastname.toString()
        username.hint = userData.username.toString()
        if (userData.gender.toString() == "M") gender.check(R.id.Sexe_Home)
        else if (userData.gender.toString() == "W") gender.check(R.id.Sexe_Dona)
        else gender.check(R.id.Sexe_Altre)
        email.text = userData.email.toString()
        birthdate.text = userData.birthdate.toString()
    }

}