package cat.fib.fithaus

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import cat.fib.fithaus.api.ApiServices
import cat.fib.fithaus.models.User
import cat.fib.fithaus.models.UserModelView
import cat.fib.fithaus.ui.*
import com.android.volley.Response
import com.google.android.gms.security.ProviderInstaller

/** Classe Consultar perfil
 *
 *  Classe que mostra les dades del perfil d'un usuari.
 *
 *  @constructor Inicialitza totes les variables de la pantalla i gestiona els listeners.
 *  @author Oriol Prat.
 */
class ConsultarPerfilActivity : AppCompatActivity() {

    var userData : User = User()

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

    /** Funció getUserData
     *
     *  Funció que agafa totes les dades d'un usuari.
     *
     *  @author Oriol Prat.
     */
    fun getUserData() {
        //TODO: get id user
        ApiServices.getUserInfo(1, this, listener(), errorListener() )
    }

    /** Funció listener
     *
     *  Funció que retorna la resposta d'un listener per usuaris.
     *
     *  @return Response.Listener<User>
     *  @author Oriol Prat.
     */
    fun listener() : Response.Listener<User> {

        return Response.Listener { response ->
            UserModelView.setUser(response)
        }
    }

    /** Funció errorListener
     *
     *  Funció que retorna la resposta d'un listener d'errors.
     *
     *  @return Response.ErrorListener
     *  @author Oriol Prat.
     */
    fun errorListener(): Response.ErrorListener {
        return Response.ErrorListener { it ->
            Log.println(Log.ERROR, "API", it.toString())
        }
    }

}