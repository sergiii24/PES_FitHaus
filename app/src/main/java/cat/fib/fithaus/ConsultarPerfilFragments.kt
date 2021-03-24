package cat.fib.fithaus

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import cat.fib.fithaus.ui.*

class ConsultarPerfilFragments : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consultar)

        val FragmentPersonals = FragmentPersonal()
        val FragmentEsportives = FragmentEsportives()
        val FragmentFisiques = FragmentFisiques()
        val btnFragmentPersonals: Button = findViewById(R.id.DadesPersonals)
        val btnFragmentEsportives: Button = findViewById(R.id.DadesEsportives)
        val btnFragmentFisiques : Button = findViewById(R.id.DadesFísiques)
        val btnTancarSessio : Button = findViewById(R.id.TancarSessio2)
        val btnModificarPerfil : Button = findViewById(R.id.ModificaPerfil)


        //posem el 1r fragment a la pantalla, necessitem el commit per fer efectiu el canvi
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, FragmentPersonals)
            commit()
        }

        //quan cliquem al botó del fragment 1 volem veure aquest fragment
        btnFragmentPersonals.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment, FragmentPersonals)
                addToBackStack(null)
                commit()
            }
        }

        //quan cliquem al botó del fragment 2 volem veure aquest fragment
        btnFragmentEsportives.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment, FragmentEsportives)
                addToBackStack(null)
                commit()
            }
        }

        //quan cliquem al botó del fragment 3 volem veure aquest fragment
        btnFragmentFisiques.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment, FragmentFisiques)
                addToBackStack(null)
                commit()
            }
        }



        //posem el 1r fragment a la pantalla, necessitem el commit per fer efectiu el canvi
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, FragmentPersonals)
            commit()
        }

        //quan cliquem al botó del fragment 1 volem veure aquest fragment
        btnFragmentPersonals.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment, FragmentPersonals)
                addToBackStack(null)
                commit()
            }
        }

        //quan cliquem al botó del fragment 2 volem veure aquest fragment
        btnFragmentEsportives.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment, FragmentEsportives)
                addToBackStack(null)
                commit()
            }
        }

        //quan cliquem al botó del fragment 3 volem veure aquest fragment
        btnFragmentFisiques.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment, FragmentFisiques)
                addToBackStack(null)
                commit()
            }
        }

        //quan cliquem al botó de tancar sessió tornem a la pantalla de Log In per iniciar-ne una altra
        btnTancarSessio.setOnClickListener {
            val intent = Intent(this@ConsultarPerfilFragments, LogInActivity::class.java)
            startActivity(intent)
        }

        //quan cliquem al botó de modificar anem a la pantalla d'aquesta activitat
        btnModificarPerfil.setOnClickListener {
            val intent = Intent(this@ConsultarPerfilFragments, ModificarPerfilActivity::class.java)
            startActivity(intent)
        }

    }
}