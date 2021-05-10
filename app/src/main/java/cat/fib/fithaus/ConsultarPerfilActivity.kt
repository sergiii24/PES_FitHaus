package cat.fib.fithaus

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import cat.fib.fithaus.ui.*
import cat.fib.fithaus.utils.Status
import cat.fib.fithaus.viewmodels.UserViewModel
import com.google.android.gms.security.ProviderInstaller
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConsultarPerfilActivity : AppCompatActivity() {

    private val viewModel by viewModels<UserViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consultar)

        ProviderInstaller.installIfNeeded(this)

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

    private fun showAlert() {
       /* val builder = AlertDialog.Builder(this)
        builder.setTitle("Confirmació")
        builder.setMessage("Estàs segur que vols eliminar el perfil?")
        builder.setPositiveButton("Acceptar") { dialog, which ->*/
            viewModel.deleteUser(15).observe(this, Observer {
                if (it.status == Status.SUCCESS) {
                    val intent = Intent(this@ConsultarPerfilActivity, LogInActivity::class.java)
                    startActivity(intent)
                } else Toast.makeText(this, "ERROR!", Toast.LENGTH_LONG).show()
            })
        //}
       /* builder.setNegativeButton("Cancelar") { dialog, which ->
            val intent = Intent(this@ConsultarPerfilActivity, this::class.java)
            startActivity(intent)
        }
        val dialog: AlertDialog = builder.create()
        dialog.create()*/
    }
}
