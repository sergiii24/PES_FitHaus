package cat.fib.fithaus

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import cat.fib.fithaus.models.User
import cat.fib.fithaus.ui.*
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

            //dades personals
            val name: TextView = findViewById(R.id.Nom_bd)
            name.text = response.firstname.plus(" ").plus(response.lastname)

            val user: TextView = findViewById(R.id.Usuari_bd)
            user.text = response.username

            val mai: TextView = findViewById(R.id.Mail_bd)
            mai.text = response.email

            val sex: TextView = findViewById(R.id.Sexe_bd)
            sex.text = response.gender

            val bdate: TextView = findViewById(R.id.DataNaixement_bd)
            bdate.text = response.birthdate

            //dades esportives
            val act: TextView = findViewById(R.id.NumActivitats_bd)
            act.text = response.activitiesdone

            val ass: TextView = findViewById(R.id.Assoliments_bd)
            ass.text = response.achievements

            val pts: TextView = findViewById(R.id.Punts_bd)
            pts.text = response.points

            val lvl: TextView = findViewById(R.id.Nivell_bd)
            lvl.text = response.level

            val obj: TextView = findViewById(R.id.Objectiu_bd)
            obj.text = response.objectives

            val cat: TextView = findViewById(R.id.CategoriesInterès_bd)
            cat.text = response.interestcategories

            //dades físiques
            val pes: TextView = findViewById(R.id.Pes_bd)
            pes.text = response.weight

            val alt: TextView = findViewById(R.id.Alçada_bd)
            alt.text = response.height

            val imc: TextView = findViewById(R.id.Imc_bd)
            imc.text = response.imc

            val igc: TextView = findViewById(R.id.Igc_bd)
            igc.text = response.igc

            val hist: TextView = findViewById(R.id.Històric_bd)
            hist.text = response.historic

        }

    }

    fun errorListener(): Response.ErrorListener {
        return Response.ErrorListener { it ->
            Log.println(Log.ERROR, "API", it.toString())
        }
    }
}