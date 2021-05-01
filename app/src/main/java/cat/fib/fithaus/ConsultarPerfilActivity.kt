package cat.fib.fithaus

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import cat.fib.fithaus.data.api.ApiServices
import cat.fib.fithaus.data.models.User
import cat.fib.fithaus.data.models.UserModelView
import cat.fib.fithaus.data.models.gson
import cat.fib.fithaus.ui.*
import com.google.android.gms.security.ProviderInstaller
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

class ConsultarPerfilActivity : AppCompatActivity() {

    var userData: User = User()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consultar)

        ProviderInstaller.installIfNeeded(this)
        getUserData()

        val fragmentPersonal = FragmentPersonal()
        val fragmentEsportives = FragmentEsportives()
        val fragmentFisiques = FragmentFisiques()

        val btnFragmentPersonals: Button = findViewById(R.id.DadesPersonals)
        val btnFragmentEsportives: Button = findViewById(R.id.DadesEsportives)
        val btnFragmentFisiques: Button = findViewById(R.id.DadesFísiques)
        val btnModificarPerfil: Button = findViewById(R.id.ModificarPerfil)


        //Es pot afegir el fragment a una pila per poder tornar-hi quan es tiri endarrere -->  addToBackStack(null)  <-- després de replace

        //Quan cliquem al botó de dades personals volem veure aquest fragment
        btnFragmentPersonals.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment, fragmentPersonal)
                commit()
            }
        }

        //Quan cliquem al botó de dades esportives volem veure aquest fragment
        btnFragmentEsportives.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment, fragmentEsportives)
                commit()
            }
        }

        //Quan cliquem al botó de dades físiques volem veure aquest fragment
        btnFragmentFisiques.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment, fragmentFisiques)
                commit()
            }
        }

        //Quan cliquem al botó de modificar perfil anem a la pantalla d'aquesta activitat
        btnModificarPerfil.setOnClickListener {
            val intent = Intent(this@ConsultarPerfilActivity, ModificarPerfilActivity::class.java)
            startActivity(intent)
        }

        //Posem el fragment de dades personals a la pantalla, necessitem el commit per fer efectiu el canvi
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragmentPersonal)
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