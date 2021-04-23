package cat.fib.fithaus

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import cat.fib.fithaus.api.ApiServices
import cat.fib.fithaus.models.User
import cat.fib.fithaus.models.UserModelView
import cat.fib.fithaus.models.gson
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
        val btnEliminarPerfil: Button = findViewById(R.id.EliminarPerfil)

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

        //Quan cliquem al botó d'eliminar perfil anem a la pantalla d'inici de sessió
        btnEliminarPerfil.setOnClickListener {
            showAlert()
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

    /** Funció showAlert
     *
     *  Funció que treu una pantalla de confirmació per eliminar el perfil de l'usuari
     *
     *  @author Daniel Cárdenas.
     */
    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Confirmació")
        builder.setMessage("Estàs segur que vols eliminar el perfil?")
        builder.setPositiveButton("Acceptar"){ dialog, which ->
            val intent = Intent(this@ConsultarPerfilActivity, LogInActivity::class.java)
            startActivity(intent)
        }
        builder.setNegativeButton("Cancelar") { dialog, which ->
            val intent = Intent(this@ConsultarPerfilActivity, this::class.java)
            startActivity(intent)
        }
        val dialog: AlertDialog = builder.create()
        dialog.create()
    }



}