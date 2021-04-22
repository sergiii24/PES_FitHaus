package cat.fib.fithaus

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import cat.fib.fithaus.api.ApiServices
import cat.fib.fithaus.models.User
import cat.fib.fithaus.models.UserModelView
import cat.fib.fithaus.models.gson
import cat.fib.fithaus.ui.FragmentEsportives
import cat.fib.fithaus.ui.FragmentFisiques
import cat.fib.fithaus.ui.FragmentModificarFisiques
import cat.fib.fithaus.ui.FragmentPersonal
import java.io.IOException
import com.google.android.gms.security.ProviderInstaller
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response


class ModificarPerfilActivity : AppCompatActivity() {

    var userData: User = User()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modificar_perfil)

        ProviderInstaller.installIfNeeded(this)
        getUserData()

       // val fragmentModificarPersonal = FragmentModificarPersonal()
       // val fragmentModificarEsportives = FragmentModificarEsportives()
        val fragmentModificarFisiques = FragmentModificarFisiques()

        val btnFragmentModificarPersonals: Button = findViewById(R.id.DadesPersonalsModificar)
        val btnFragmentModificarEsportives: Button = findViewById(R.id.DadesEsportivesModificar)
        val btnFragmentModificarFisiques: Button = findViewById(R.id.DadesFísiquesModificar)
        val btnGuardarModificacions: Button = findViewById(R.id.GuardarModificacions)


        //Es pot afegir el fragment a una pila per poder tornar-hi quan es tiri endarrere -->  addToBackStack(null)  <-- després de replace

        //Quan cliquem al botó de dades personals volem veure aquest fragment
       /* btnFragmentModificarPersonals.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment, fragmentModificarPersonal)
                commit()
            }
        }

        //Quan cliquem al botó de dades esportives volem veure aquest fragment
        btnFragmentEsportives.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment, fragmentEsportives)
                commit()
            }
        }*/

        //Quan cliquem al botó de dades físiques volem veure aquest fragment
        btnFragmentModificarFisiques.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment, fragmentModificarFisiques)
                commit()
            }
        }

        //Quan cliquem al botó de guardar anem a la pantalla de consultar les dades del perfil d'un usuari
        btnGuardarModificacions.setOnClickListener {
            val intent = Intent(this, ConsultarPerfilActivity::class.java)
            startActivity(intent)
        }

        //Posem el fragment de dades personals a la pantalla, necessitem el commit per fer efectiu el canvi
       /* supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragmentPersonal)
            commit()
        }*/
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