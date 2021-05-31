package cat.fib.fithaus.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import cat.fib.fithaus.R
import cat.fib.fithaus.data.models.User
import cat.fib.fithaus.utils.Status
import cat.fib.fithaus.viewmodels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.lifecycle.Observer


/** Classe FragmentModificarEsportives
 *
 *  Classe on hi ha el fragment de les dades esportives.
 *
 *  @constructor Inicialitza els camps d'informació i captura les dades de l'usuari.
 *  @author Daniel Cárdenas.
 */

@AndroidEntryPoint
class FragmentModificarEsportives : Fragment(R.layout.fragment_modificar_esportives) {

    private val viewModel by viewModels<UserViewModel>()

    lateinit var btnGuardarModificacioEsportives: Button
    lateinit var activitats: TextView
    lateinit var assoliments: TextView
    lateinit var punts: TextView
    lateinit var nivell: EditText
    lateinit var objectiu: EditText
    lateinit var categoriainteres: EditText


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
        btnGuardarModificacioEsportives = v.findViewById(R.id.GuardarModificacioEsportives)
        activitats = v.findViewById(R.id.NumActivitats_bd)
        assoliments = v.findViewById(R.id.Assoliments_bd)
        punts = v.findViewById(R.id.Punts_bd)
        nivell = v.findViewById(R.id.nivell_bd)
        objectiu = v.findViewById(R.id.objectiu_bd)
        categoriainteres = v.findViewById(R.id.categoriainteres_bd)
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
    private fun configurarGuardar(user: User?) {
        btnGuardarModificacioEsportives.setOnClickListener {
            val userId = user?.id
            if (campsbuitsesportives()) {
                Toast.makeText(
                        this.context, "Els camps nivell, objectiu i/o categories d'interès s'han d'omplir", Toast.LENGTH_LONG).show()
            } else if (userId != null) {
                user.level = nivell.toString()
                var ArrayObjectius = ArrayList<String>()
                ArrayObjectius.add(objectiu.toString())
                var ArrayCategories = ArrayList<String>()
                ArrayCategories.add(objectiu.toString())
                user.objectives = ArrayObjectius
                user.categories = ArrayCategories
            }
            if (userId != null) {
                /*viewModel.updateUser(userId, user).observe(viewLifecycleOwner, Observer {
                    if (it.status == Status.SUCCESS) {
                        Toast.makeText(this.context, "Dades esportives modificades!", Toast.LENGTH_LONG).show()
                        val intent = Intent(activity, ConsultarPerfilActivity::class.java)
                        startActivity(intent)
                    } else if (it.status == Status.ERROR) Toast.makeText(this.context, "ERROR!", Toast.LENGTH_LONG).show()
                }) */
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
        activitats.text = userData?.activitiesdone.toString()
        assoliments.text = userData?.achievements.toString()
        punts.text = userData?.points.toString()
        nivell.hint = userData?.level.toString()
        objectiu.hint = userData?.objectives.toString()
        categoriainteres.hint = userData?.categories.toString()
    }

    /** Funció campsbuitsesportives
     *
     *  Funció que retorna cert si hi ha algun camp a omplir que no s'ha omplert.
     *
     *  @return Boolean
     *  @author Daniel Cárdenas.
     */
    fun campsbuitsesportives(): Boolean {
        if (nivell.text.isEmpty() || objectiu.text.isEmpty() || categoriainteres.text.isEmpty()) {
            return true
        }
        return false
    }

}