package cat.fib.fithaus.ui

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import cat.fib.fithaus.ConsultarPerfilActivity
import cat.fib.fithaus.R
import cat.fib.fithaus.data.models.User
import cat.fib.fithaus.utils.Status
import cat.fib.fithaus.viewmodels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

/** Classe FragmentModificarFisiques
 *
 *  Classe on hi ha el fragment de les dades físiques.
 *
 *  @constructor Inicialitza els camps d'informació i captura les dades de l'usuari.
 *  @author Daniel Cárdenas.
 */

@AndroidEntryPoint
class FragmentModificarFisiques : Fragment(R.layout.fragment_modificar_fisiques) {

    private val viewModel by viewModels<UserViewModel>()

    lateinit var btnGuardarModificacionsFisiques: Button
    lateinit var weight: EditText
    lateinit var height: EditText
    lateinit var imc: TextView
    lateinit var igc: TextView
    lateinit var historic: TextView


    /** Funció inicialitzadora
     *
     *  Funció que inicialitza les variables amb el valor corresponent segons l'usuari.
     *
     *  @param inflater, container, savedInstanceState
     *  @return View?
     *  @author Daniel Cárdenas.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v: View = inflater.inflate(R.layout.fragment_modificar_fisiques, container, false)
        btnGuardarModificacionsFisiques = v.findViewById(R.id.GuardarModificacionsFisiques)
        weight = v.findViewById(R.id.PesIntroduit)
        height = v.findViewById(R.id.AlcadaIntroduida)
        imc = v.findViewById(R.id.imctotal)
        igc = v.findViewById(R.id.igctotal)
        historic = v.findViewById(R.id.historictotal)
        viewModel.user.observe(viewLifecycleOwner, Observer {
            if (it.status == Status.SUCCESS) {
                setUpData(it.data)
                configurarGuardar(it.data)
            }
            else Toast.makeText(this.context, "ERROR!", Toast.LENGTH_LONG).show()
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
        btnGuardarModificacionsFisiques.setOnClickListener {
            val userId = user?.id
            if (campsbuitsfisiques()) {
                Toast.makeText(
                        this.context, "Els camps pes i/o alçada s'han d'omplir", Toast.LENGTH_LONG).show()
            } else if (userId != null) {
                user.weight = weight.text.toString().toFloat()
                user.height = height.text.toString().toFloat()
            }
            if (userId != null) {
                viewModel.update(userId, user).observe(viewLifecycleOwner, Observer {
                    if (it.status == Status.SUCCESS) {
                        Toast.makeText(this.context, "Dades físiques modificades!", Toast.LENGTH_LONG).show()
                        val intent = Intent(activity, ConsultarPerfilActivity::class.java)
                        startActivity(intent)
                    } else if (it.status == Status.ERROR) Toast.makeText(this.context, "ERROR!", Toast.LENGTH_LONG).show()
                })
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
        weight.hint = userData?.weight.toString()
        height.hint = userData?.height.toString()
        imc.text = userData?.imc.toString()
        igc.text = userData?.igc.toString()
        historic.text = userData?.historic.toString()
    }


    /** Funció campsbuitsfisiques
     *
     *  Funció que retorna cert si hi ha algun camp a omplir que no s'ha omplert.
     *
     *  @return Boolean
     *  @author Daniel Cárdenas.
     */
    fun campsbuitsfisiques(): Boolean {
        if (weight.text.isEmpty() || height.text.isEmpty()) {
            return true
        }
        return false
    }

}




