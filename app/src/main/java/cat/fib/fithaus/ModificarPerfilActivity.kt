package cat.fib.fithaus

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import cat.fib.fithaus.ui.FragmentEsportives
import cat.fib.fithaus.ui.FragmentFisiques
import cat.fib.fithaus.ui.FragmentModificarFisiques
import cat.fib.fithaus.ui.FragmentPersonal
import java.io.IOException
import com.google.android.gms.security.ProviderInstaller
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_fisiques.*
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response

/** Classe Modificar perfil
 *
 *  Classe on es mostra la pantalla de modificar perfil.
 *
 *  @constructor Indica a l'usuari que es troba a la pantalla de modificar perfil.
 *  @author Daniel Cárdenas.
 */

@AndroidEntryPoint
class ModificarPerfilActivity : AppCompatActivity() {


    /** Funció inicialitzadora
     *
     *  Funció que fa que es mostri la pantalla a l'usuari i que inicialitza totes les variables.
     *
     *  @param savedInstanceState
     *  @author Daniel Cárdenas.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modificar_perfil)

        ProviderInstaller.installIfNeeded(this)

       // val fragmentModificarPersonal = FragmentModificarPersonal()
       // val fragmentModificarEsportives = FragmentModificarEsportives()
        val fragmentModificarFisiques = FragmentModificarFisiques()

        val btnFragmentModificarPersonals: Button = findViewById(R.id.DadesPersonalsModificar)
        val btnFragmentModificarEsportives: Button = findViewById(R.id.DadesEsportivesModificar)
        val btnFragmentModificarFisiques: Button = findViewById(R.id.DadesFísiquesModificar)


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

        //Posem el fragment de dades personals a la pantalla, necessitem el commit per fer efectiu el canvi
       /* supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragmentPersonal)
            commit()
        }*/
    }
}