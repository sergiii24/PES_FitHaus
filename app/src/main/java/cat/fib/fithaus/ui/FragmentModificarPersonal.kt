package cat.fib.fithaus.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import cat.fib.fithaus.R
import cat.fib.fithaus.data.models.User
import cat.fib.fithaus.utils.Status
import cat.fib.fithaus.viewmodels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_modificar_personal.*


/** Classe FragmentModificarPersonal
 *
 *  Classe on hi ha el fragment de les dades personals.
 *
 *  @constructor Inicialitza els camps d'informació i captura les dades de l'usuari.
 *  @author Daniel Cárdenas.
 */

@AndroidEntryPoint
class FragmentModificarPersonal : Fragment(R.layout.fragment_modificar_personal) {

    private val viewModel by viewModels<UserViewModel>()
    lateinit var btnGuardarModificacioPersonal: Button
    lateinit var name: EditText
    lateinit var username: EditText
    lateinit var gender: RadioGroup
    lateinit var email: TextView
    lateinit var birthdate: TextView


    /** Funció inicialitzadora
     *
     *  Funció que inicialitza les variables amb el valor corresponent segons l'usuari.
     *
     *  @param inflater, container, savedInstanceState
     *  @return View?
     *  @author Daniel Cárdenas.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v: View = inflater.inflate(R.layout.fragment_modificar_personal, container, false)
        btnGuardarModificacioPersonal = v.findViewById(R.id.GuardarModificacionsPersonals)
        name = v.findViewById(R.id.nomedit)
        username = v.findViewById(R.id.usuariedit)
        gender = v.findViewById(R.id.SexeSelection)
        email = v.findViewById(R.id.mail_bd)
        birthdate = v.findViewById(R.id.datanaixement_bd)
        viewModel.user.observe(viewLifecycleOwner, Observer {
            if (it.status == Status.SUCCESS) {
                setUpData(it.data)
                configurarGuardar(it.data)
            }
        })



        return v

    }

    /** Funció configurarGuardar
     *
     *  Funció que comproba si els camps a modificar són buits i que envia la petició d'actualització a back amb els camps actualitzats
     *  tornant a la pantalla de consultar el perfil de l'usuari.
     *
     *  @param user
     *  @author Daniel Cárdenas.
     */
    //Quan cliquem al botó de guardar comprobem que els camps a omplir no siguin buits i anem a la pantalla de consultar les dades del perfil d'un usuari.
    private fun configurarGuardar(user: User?) {
        val userId = user?.id
        btnGuardarModificacioPersonal.setOnClickListener {
            if (campsbuitspersonals()) Toast.makeText(this.context, "Els camps nom, usuari i/o sexe s'han d'omplir",
                    Toast.LENGTH_LONG
            ).show()
            else if (userId != null) {
                user.firstname = name.toString()
                user.username = username.toString()
                user.gender = gender.toString()
            }
            if (userId != null) {
                if (user != null) { /*
                    viewModel.updateUser(userId, user).observe(viewLifecycleOwner, Observer {
                        if (it.status == Status.SUCCESS) {
                            Toast.makeText(this.context, "Dades personals modificades!", Toast.LENGTH_LONG).show()
                            val intent = Intent(activity, ConsultarPerfilActivity::class.java)
                            startActivity(intent)
                        } else if (it.status == Status.ERROR) Toast.makeText(this.context, "ERROR!", Toast.LENGTH_LONG).show()
                    }) */
                }
            }
        }
    }

    /** Funció setUpData
     *
     *  Funció que omple els camps del fragment amb les dades d'usuari corresponents.
     *
     *  @param userData
     *  @author Daniel Cárdenas.
     */
    fun setUpData(userData: User?) {
        name.hint = userData?.firstname.plus(" ").plus(userData?.lastname)
        username.hint = userData?.username.toString()
        if (userData?.gender.toString() == "M") gender.check(R.id.Sexe_Home)
        else if (userData?.gender.toString() == "W") gender.check(R.id.Sexe_Dona)
        else gender.check(R.id.Sexe_Altre)
        email.text = userData?.email.toString()
        birthdate.text = userData?.birthdate.toString()
    }

    /** Funció campsbuitspersonals
     *
     *  Funció que retorna cert si hi ha algun camp a omplir que no s'ha omplert.
     *
     *  @return Boolean
     *  @author Daniel Cárdenas.
     */
    fun campsbuitspersonals(): Boolean {
        val sexe: String? = experience()
        if (name.text.isEmpty() || username.text.isEmpty() || sexe == null) {
            return true
        }
        return false
    }

    /** Funció experience
     *
     *  Funció que retorna un string depenent del radiobutton del sexe seleccionat.
     *
     *  @return String?
     *  @author Daniel Cárdenas.
     */
    fun experience(): String? {
        return when {
            Sexe_Home?.isChecked == true -> "M"
            Sexe_Dona?.isChecked == true -> "W"
            Sexe_Altre?.isChecked == true -> "X"
            else -> null
        }
    }

}