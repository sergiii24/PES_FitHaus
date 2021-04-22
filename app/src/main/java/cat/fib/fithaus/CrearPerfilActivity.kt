package cat.fib.fithaus

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import cat.fib.fithaus.data.api.ApiServices
import cat.fib.fithaus.data.models.User
import cat.fib.fithaus.ui.dialog.DatePickerFragment
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException
import java.util.*


/** Classe CrearPerfil
 *
 *  Classe on es crea el perfil d'un usuari amb els camps principals necessaris.
 *
 *  @constructor Crea un perfil d'usuari amb tots els camps amb valor nul.
 *  @author Daniel Cárdenas.
*/
class CrearPerfilActivity : AppCompatActivity() {
    var Nom: EditText? = null
    var PrimerCognom: EditText? = null
    var SegonCognom: EditText? = null
    var NomUsuari: EditText? = null
    var CorreuElectronic: EditText? = null
    var Contrasenya: EditText? = null
    var DataNaixement: EditText? = null
    var Sexe_Dona: RadioButton? = null
    var Sexe_Home: RadioButton? = null
    var Sexe_Altre: RadioButton? = null

    /** Funció inicialitzadora
     *
     *  Funció que fa que es mostri la pantalla a l'usuari i que inicialitza totes les variables.
     *
     *  @param savedInstanceState
     *  @author Daniel Cárdenas.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_perfil)
        //Asignem els valors de cada component de la interfície a les variables
        Nom = findViewById (R.id.Nom)
        PrimerCognom = findViewById (R.id.PrimerCognom)
        SegonCognom = findViewById (R.id.SegonCognom)
        NomUsuari = findViewById (R.id.NomUsuari)
        CorreuElectronic = findViewById (R.id.CorreuElectronic)
        Contrasenya = findViewById (R.id.Contrasenya)
        DataNaixement = findViewById (R.id.DataNaixement)
        DataNaixement?.setOnClickListener {
            showDatePickerDialog()
        }
        Sexe_Dona = findViewById (R.id.Sexe_Dona)
        Sexe_Home = findViewById (R.id.Sexe_Home)
        Sexe_Altre = findViewById (R.id.Sexe_Altre)
    }

    /** Funció Enviar
     *
     *  Funció que comprova tots els possibles errors dels camps a omplir.
     *
     *  @param view
     *  @author Daniel Cárdenas.
     */
    //Definim un nou mètode per executar-lo al prémer el botó "Crear perfil"
    fun Enviar(view: View) {

        if (Nom?.text.toString().isEmpty()) Toast.makeText(this, "El camp Nom està buit", Toast.LENGTH_LONG).show();
        else {
            if (PrimerCognom?.text.toString().isEmpty()) Toast.makeText(this, "El camp Primer Cognom està buit", Toast.LENGTH_LONG).show()
            else {
                if (SegonCognom?.text.toString().isEmpty()) Toast.makeText(this, "El camp Segon Cognom està buit", Toast.LENGTH_LONG).show()
                else {
                    if (NomUsuari?.text.toString().isEmpty()) Toast.makeText(this, "El camp Nom Usuari està buit", Toast.LENGTH_LONG).show()
                    else {
                        if (CorreuElectronic?.text.toString().isEmpty()) Toast.makeText(this, "El camp Correu Electrònic està buit", Toast.LENGTH_LONG).show()
                        else {
                            if (!ContrasenyaValida(Contrasenya?.text.toString())) Toast.makeText(this, "La contrasenya ha de contenir com a mínim 8 caràcters amb una lletra mayúscula, una minúscula, un número i un símbol", Toast.LENGTH_LONG).show()
                            else {
                                if (Sexe_Dona?.isChecked == false && Sexe_Home?.isChecked == false && Sexe_Altre?.isChecked == false) Toast.makeText(this, "El Sexe no està seleccionat", Toast.LENGTH_LONG).show()
                                else {
                                    if (DataNaixement?.text.toString().isEmpty()) Toast.makeText(this, "El camp Data Naixement està buit", Toast.LENGTH_LONG).show()
                                    else {
                                        if (NomUsuari?.text.toString().isNotEmpty() && NomUsuari?.text.toString().length < 4) Toast.makeText(this, "El nom d'usuari ha de tenir 4 caràcters com a mínim", Toast.LENGTH_LONG).show()
                                        else {
                                            if (!Patterns.EMAIL_ADDRESS.matcher(CorreuElectronic?.text).matches()) Toast.makeText(this, "El correu electrònic no té el format correcte (fit@fithaus.com)", Toast.LENGTH_LONG).show()
                                            else {
                                                Toast.makeText(this, "Formulari complet!", Toast.LENGTH_LONG).show()
                                                var usuari = NomUsuari?.text.toString();
                                                var lastname = PrimerCognom?.text.toString()+SegonCognom?.text.toString()
                                                var name = Nom?.text.toString()
                                                var email = CorreuElectronic?.text.toString()
                                                var la = DataNaixement?.text.toString()
                                                var g : String = "M"
                                                if(Sexe_Dona?.isChecked == true) g = "W"
                                                if(Sexe_Altre?.isChecked == true) g = "X"
                                                val user = User(name, lastname, usuari, Contrasenya?.text.toString(),  email, g, Date(la) )


                                                ApiServices.postUserInfo(user, object : Callback {

                                                    override fun onResponse(call: Call, response: Response) {
                                                        println("User created.")
                                                    }

                                                    override fun onFailure(call: Call, e: IOException) {
                                                        println("Request Failure.")
                                                    }
                                                })

                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    /** Funció ContrasenyaValida
     *
     *  Funció que comprova si una contrasenya és vàlida o no, comprovant que tingui com a mínim 8 caràcters amb una lletra majúscula, una minúscula, un número i un símbol.
     *
     *  @param password
     *  @return Retorna el booleà valida si es compleixen les condicions
     *  @author Daniel Cárdenas.
     */
    fun ContrasenyaValida (password: String): Boolean {
        var valida: Boolean = true;
        var minuscula: Int = 0
        var mayuscula: Int = 0
        var numero: Int = 0
        var caracterespecial: Int = 0

        if (password.length < 8) return false
        var i: Int = 0
        while (i < password.length) {
            var c = password[i].toChar()
            if (c <= ' ' || c > '~') {
                valida = false
                break
            }
            if ((c > ' ' && c < '0') || (c >= ':' && c < 'A') || (c >= '[' && c < 'a') || (c >= '{' && c < 127.toChar())) {
                caracterespecial++;
            }
            if (c >= '0' && c < ':') numero++;
            if (c >= 'A' && c < '[') mayuscula++;
            if (c >= 'a' && c < '{') minuscula++;
            i++
        }
        valida = valida && caracterespecial > 0 && numero > 0 && minuscula > 0 && mayuscula > 0;
        return valida;
    }


    /** Funció showDatePickerDialog
     *
     *  Funció que selecciona la data marcada en el fragment del calendari i l'assigna a la data de naixement de l'usuari.
     *
     *  @author Daniel Cárdenas.
     */
    private fun showDatePickerDialog() {
        val newFragment = DatePickerFragment.newInstance(DatePickerDialog.OnDateSetListener { _, year, month, day ->
            val dayStr = day.twoDigits()
            val monthStr = (month + 1).twoDigits() // +1 because January is zero
            val selectedDate = "$dayStr/$monthStr/$year"
            DataNaixement?.setText(selectedDate)
        })

        newFragment.show(supportFragmentManager, "datePicker")
    }

    /** Funció twoDigits
     *
     *  Funció que afegeix el dígit '0' davant d'un nombre menor que 10.
     *
     *  @author Daniel Cárdenas.
     */
    fun Int.twoDigits() = if (this <= 9) "0$this" else this.toString()

    /** Funció Enrere
     *
     *  Funció que mostra la interfície d'inici de sessió.
     *
     *  @param view
     *  @author Daniel Cárdenas.
     */
    /* fun Enrere(view: View) {
         //Anar a la pantalla d'iniciar sessió sense omplir el formulari
         val intent = Intent(this, LogInActivity::class.java).apply
         startActivity(intent)
     }*/

}