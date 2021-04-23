package cat.fib.fithaus

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import cat.fib.fithaus.api.ApiServices
import cat.fib.fithaus.models.User
import cat.fib.fithaus.models.UserModelView
import cat.fib.fithaus.models.gson
import cat.fib.fithaus.ui.FragmentModificarEsportives
import cat.fib.fithaus.ui.FragmentModificarPersonal
import cat.fib.fithaus.ui.FragmentPersonal
import java.io.IOException
import com.google.android.gms.security.ProviderInstaller
import kotlinx.android.synthetic.main.activity_crear_perfil.*
import kotlinx.android.synthetic.main.fragment_modificar_personal.*
import kotlinx.android.synthetic.main.fragment_modificar_personal.Sexe_Altre
import kotlinx.android.synthetic.main.fragment_modificar_personal.Sexe_Dona
import kotlinx.android.synthetic.main.fragment_modificar_personal.Sexe_Home
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response


/** Classe ModificarPerfil
 *
 *  Classe on es mostra la pantalla de modificar perfil.
 *
 *  @constructor Indica a l'usuari que es troba a la pantalla de modificar perfil.
 *  @author Daniel Cárdenas.
 */
class ModificarPerfilActivity : AppCompatActivity() {

    var userData: User = User()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modificar_perfil)

        ProviderInstaller.installIfNeeded(this)
        getUserData()

        val fragmentModificarPersonal = FragmentModificarPersonal()
        val fragmentModificarEsportives = FragmentModificarEsportives()
        //val fragmentModificarFisiques = FragmentModificarFisiques()

        val btnFragmentModificarPersonals: Button = findViewById(R.id.DadesPersonalsModificar)
        val btnFragmentModificarEsportives: Button = findViewById(R.id.DadesEsportivesModificar)
        val btnFragmentModificarFisiques: Button = findViewById(R.id.DadesFísiquesModificar)
        val btnGuardarModificacions: Button = findViewById(R.id.GuardarModificacions)


        /** Funció experience
         *
         *  Funció que retorna un string depenent del radiobutton del sexe seleccionat.
         *
         *  @return String?
         *  @author Daniel Cárdenas.
         */
        fun experience(): String? {
            return when {
                fragmentModificarPersonal.Sexe_Home?.isChecked == true -> "Home"
                fragmentModificarPersonal.Sexe_Dona?.isChecked == true -> "Dona"
                fragmentModificarPersonal.Sexe_Altre?.isChecked == true -> "Altre"
                else -> null
            }
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
            if (fragmentModificarPersonal.name.text.isEmpty() || fragmentModificarPersonal.username.text.isEmpty() || sexe == null) {
                return true
            }
            return false
        }

        /** Funció campsbuitsesportives
         *
         *  Funció que retorna cert si hi ha algun camp a omplir que no s'ha omplert.
         *
         *  @return Boolean
         *  @author Daniel Cárdenas.
         */
        fun campsbuitsesportives(): Boolean {
            if (fragmentModificarEsportives.nivell.text.isEmpty() || fragmentModificarEsportives.objectiu.text.isEmpty() || fragmentModificarEsportives.categoriainteres.text.isEmpty()) {
                return true
            }
            return false
        }

        //Es pot afegir el fragment a una pila per poder tornar-hi quan es tiri endarrere -->  addToBackStack(null)  <-- després de replace

        //Quan cliquem al botó de dades personals volem veure aquest fragment
        btnFragmentModificarPersonals.setOnClickListener {
             supportFragmentManager.beginTransaction().apply {
                 replace(R.id.flFragment, fragmentModificarPersonal)
                 commit()
             }
         }
         //Quan cliquem al botó de dades esportives volem veure aquest fragment
         btnFragmentModificarEsportives.setOnClickListener {
             supportFragmentManager.beginTransaction().apply {
                 replace(R.id.flFragment, fragmentModificarEsportives)
                 commit()
             }
         }

        //Quan cliquem al botó de dades físiques volem veure aquest fragment
        /*btnFragmentModificarFisiques.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment, fragmentModificarFisiques)
                commit()
            }
        }*/

        //Quan cliquem al botó de guardar comprobem que els camps a omplir no siguin buits i anem a la pantalla de consultar les dades del perfil d'un usuari.
        btnGuardarModificacions.setOnClickListener {
            if (campsbuitspersonals()) Toast.makeText(this, "Els camps nom, usuari i/o sexe s'han d'omplir", Toast.LENGTH_LONG).show()
            else if (campsbuitsesportives()) Toast.makeText(this, "Els camps nivell, objectiu i/o categories d'interès s'han d'omplir", Toast.LENGTH_LONG).show()
            else {
                val intent = Intent(this, ConsultarPerfilActivity::class.java)
                startActivity(intent)
            }
        }

        //Posem el fragment de dades personals a la pantalla, necessitem el commit per fer efectiu el canvi
         supportFragmentManager.beginTransaction().apply {
             replace(R.id.flFragment, fragmentModificarPersonal)
             commit()
         }


    }




    fun getUserData() {
        //TODO: get id user
        ApiServices.getUserInfo(1, object : Callback {

            override fun onResponse(call: Call, response: Response) {
                val responseData = response.body?.string()
                runOnUiThread {
                    UserModelView.setUser(gson.fromJson(responseData, User::class.java))
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                println("Request Failure.")
            }
        })
    }
}