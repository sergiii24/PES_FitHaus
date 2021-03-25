package cat.fib.fithaus

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import cat.fib.fithaus.api.ApiServices
import cat.fib.fithaus.models.User
import cat.fib.fithaus.ui.*
import com.android.volley.Response
import com.google.android.gms.security.ProviderInstaller

class ConsultarPerfilActivity : AppCompatActivity() {

    var user : User = User()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consultar)

        ProviderInstaller.installIfNeeded(this)

        getUserData()

        val FragmentPersonals = FragmentPersonal()
        val FragmentEsportives = FragmentEsportives()
        val FragmentFisiques = FragmentFisiques()
        val btnFragmentPersonals: Button = findViewById(R.id.DadesPersonals)
        val btnFragmentEsportives: Button = findViewById(R.id.DadesEsportives)
        val btnFragmentFisiques: Button = findViewById(R.id.DadesFísiques)
        val btnTancarSessio: Button = findViewById(R.id.TancarSessio)
        val btnModificarPerfil: Button = findViewById(R.id.ModificarPerfil)


        //Posem el fragment de dades personals a la pantalla, necessitem el commit per fer efectiu el canvi
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, FragmentPersonals)
            commit()
        }

        //Es pot afegir el fragment a una pila per poder tornar-hi quan es tiri endarrere -->  addToBackStack(null)  <-- després de replace

        //Quan cliquem al botó de dades personals volem veure aquest fragment
        btnFragmentPersonals.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment, FragmentPersonals)
                commit()
            }
        }

        //Quan cliquem al botó de dades esportives volem veure aquest fragment
        btnFragmentEsportives.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment, FragmentEsportives)
                commit()
            }
        }

        //Quan cliquem al botó de dades físiques volem veure aquest fragment
        btnFragmentFisiques.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment, FragmentFisiques)
                commit()
            }
        }

        //Quan cliquem al botó de tancar sessió anem a la pantalla de Log In
        btnTancarSessio.setOnClickListener {
            val intent = Intent(this@ConsultarPerfilActivity, LogInActivity::class.java)
            startActivity(intent)
        }

        //Quan cliquem al botó de modificar perfil anem a la pantalla d'aquesta activitat
        btnModificarPerfil.setOnClickListener {
            val intent = Intent(this@ConsultarPerfilActivity, ModificarPerfilActivity::class.java)
            startActivity(intent)
        }

    }

    fun getUserData() {
        //TODO: get id user
        ApiServices.getUserInfo(1, this, listener(), errorListener() )
    }


    fun listener() : Response.Listener<User> {

        return Response.Listener { response ->

            user = response

            val name: TextView = findViewById(R.id.Nom_bd)
            name.text = response.firstname.plus("").plus(response.lastname)

            val user: TextView = findViewById(R.id.Usuari_bd)
            user.text = response.username

            val pass: TextView = findViewById(R.id.Contrasenya_bd)
            pass.text = response.password

        }

    }

    fun errorListener(): Response.ErrorListener {
        return Response.ErrorListener { it ->
            Log.println(Log.ERROR, "API", it.toString())
         }
        }
    }