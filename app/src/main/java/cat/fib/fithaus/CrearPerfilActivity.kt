package cat.fib.fithaus

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import cat.fib.fithaus.data.models.User
import cat.fib.fithaus.ui.dialog.DatePickerFragment
import cat.fib.fithaus.utils.Status
import cat.fib.fithaus.viewmodels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_crear_perfil.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter


/** Classe CrearPerfil
 *
 *  Classe on es crea el perfil d'un usuari amb els camps principals necessaris.
 *
 *  @constructor Crea un perfil d'usuari amb tots els camps amb valor nul.
 *  @author Daniel Cárdenas.
*/
@AndroidEntryPoint
class CrearPerfilActivity : AppCompatActivity() {
    var Nom: EditText? = null
    var Cognoms: EditText? = null
    var NomUsuari: EditText? = null
    var CorreuElectronic: EditText? = null
    var Contrasenya: EditText? = null
    var DataNaixement: EditText? = null
    var Sexe_Dona: RadioButton? = null
    var Sexe_Home: RadioButton? = null
    var Sexe_Altre: RadioButton? = null


    private val viewModel by viewModels<UserViewModel>()

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
        Cognoms = findViewById (R.id.Cognoms)
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
        setupSendButton()
    }

        /** Function setupSendButton
     *
     *  Funció que comprova si els camps són correctes per crear un usuari.
     *
     *  @author Adrià Espinola Garcia, Albert Miñana Montecino, Daniel Cárdenas Rafael
     */
    fun setupSendButton() {
        ActualitzarUsuariButton.setOnClickListener {
            val validateName = validateName()
            val validateLastName = validateLastName()
            val validateUsername = validateUsername()
            val validateEmail = validateEmail()
            val validatePassword = validatePassword()
            val validateBirthday = validateBirthday()
            val validateGender = validateGender()
            if (validateName && validateLastName && validateUsername && validateEmail && validatePassword && validateBirthday && validateGender) {
                val name = Nom?.text.toString()
                val lastname = Cognoms?.text.toString()
                val username = NomUsuari?.text.toString()
                val email = CorreuElectronic?.text.toString()
                val password = Contrasenya?.text.toString()
                val birthday = DataNaixement?.text.toString()
                var gender: String
                when {
                    Sexe_Home?.isChecked == true -> gender = "M"
                    Sexe_Dona?.isChecked == true -> gender = "W"
                    else -> gender = "X"
                }
                val user = User(name, lastname, username, password, email, gender,
                        LocalDate.parse(birthday, DateTimeFormatter.ofPattern("dd/MM/yyyy")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString())
                viewModel.createUser(user)
                viewModel.user.observe(this, Observer {
                    if (it.status == Status.SUCCESS) {
                        //guarda id
                        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
                        prefs.putString("userId", it.data?.id.toString())
                        prefs.putString("provider", "FitHaus")
                        prefs.apply()
                        showSurvey()
                    } else if (it.status == Status.ERROR) {
                        Toast.makeText(this, "ERROR!", Toast.LENGTH_LONG).show()
                    }
                })
            }
        }
    }

    /** Function validateName
     *
     *  Funció que comprova si el camp Nom és correcte.
     *
     *  @return Retorna cert si és correcte, fals en cas contrari.
     *  @author Adrià Espinola Garcia, Albert Miñana Montecino, Daniel Cárdenas Rafael
     */
    private fun validateName(): Boolean {
        val name = Nom?.text.toString()
        if (name.isEmpty()) {
            textInputLayoutNom?.setError("El camp no pot ser buit")
            return false
        }
        else {
            textInputLayoutNom?.setError(null)
            return true
        }
    }

    /** Function validateLastName
     *
     *  Funció que comprova si el camp Cognoms és correcte.
     *
     *  @return Retorna cert si és correcte, fals en cas contrari.
     *  @author Adrià Espinola Garcia, Albert Miñana Montecino, Daniel Cárdenas Rafael
     */
    private fun validateLastName(): Boolean {
        val lastName = Cognoms?.text.toString()
        if (lastName.isEmpty()) {
            textInputLayoutCognoms?.setError("El camp no pot ser buit")
            return false
        }
        else {
            textInputLayoutCognoms?.setError(null)
            return true
        }
    }

    /** Function validateUserName
     *
     *  Funció que comprova si el camp Nom Usuari és correcte.
     *
     *  @return Retorna cert si és correcte, fals en cas contrari.
     *  @author Adrià Espinola Garcia, Albert Miñana Montecino, Daniel Cárdenas Rafael
     */
    private fun validateUsername(): Boolean {
        val username = NomUsuari?.text.toString()
        if (username.isEmpty()) {
            textInputLayoutNomUsuari?.setError("El camp no pot ser buit")
            return false
        }
        else {
            textInputLayoutNomUsuari?.setError(null)
            return true
        }
    }

    /** Function validateEmail
     *
     *  Funció que comprova si el camp Correu Electrònic és correcte.
     *
     *  @return Retorna cert si és correcte, fals en cas contrari.
     *  @author Adrià Espinola Garcia, Albert Miñana Montecino, Daniel Cárdenas Rafael
     */
    private fun validateEmail(): Boolean {
        val email = CorreuElectronic?.text.toString()
        if (email.isEmpty()) {
            textInputLayoutCorreuElectronic?.setError("El camp no pot ser buit")
            return false
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            textInputLayoutCorreuElectronic?.setError("Format incorrecte")
            return false
        }
        else {
            textInputLayoutCorreuElectronic?.setError(null)
            return true
        }
    }

    /** Function validatePassword
     *
     *  Funció que comprova si el camp Contrasenya és correcte.
     *
     *  @return Retorna cert si és correcte, fals en cas contrari.
     *  @author Adrià Espinola Garcia, Albert Miñana Montecino, Daniel Cárdenas Rafael
     */
    private fun validatePassword(): Boolean {
        val password = Contrasenya?.text.toString()
        if (!ContrasenyaValida(password)) {
            textInputLayout?.setError("Mínim 8 caràcters, 1 majúscula, 1 minúscula, 1 número, 1 símbol")
            return false
        }
        else {
            textInputLayout?.setError(null)
            return true
        }
    }

    /** Function validateBirthday
     *
     *  Funció que comprova si el camp Data Naixement és correcte.
     *
     *  @return Retorna cert si és correcte, fals en cas contrari.
     *  @author Adrià Espinola Garcia, Albert Miñana Montecino, Daniel Cárdenas Rafael
     */
    private fun validateBirthday(): Boolean {
        val birthday = DataNaixement?.text.toString()
        if (birthday.isEmpty()) {
            textInputLayoutDataNaixement?.setError("El camp no pot ser buit")
            return false
        }
        else {
            textInputLayoutDataNaixement?.setError(null)
            return true
        }
    }

    /** Function validateGender
     *
     *  Funció que comprova la selecció del camp Sexe.
     *
     *  @return Retorna cert si és correcte, fals en cas contrari.
     *  @author Adrià Espinola Garcia, Albert Miñana Montecino, Daniel Cárdenas Rafael
     */
    private fun validateGender(): Boolean {
        val genderNotChecked = (Sexe_Dona?.isChecked == false && Sexe_Home?.isChecked == false && Sexe_Altre?.isChecked == false)
        if (genderNotChecked) {
            textInputLayoutSexe?.setError("El camp no està seleccionat")
            Sexe_Altre?.setError("El camp no està seleccionat")
            return false
        }
        else {
            textInputLayoutSexe?.setError(null)
            Sexe_Altre?.setError(null)
            return true
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


    /** Function showSurvey
     *
     *  Funció encarregada de canviar a l'activitat del qüestionari.
     *
     *  @author Albert Miñana Montecino i Adrià Espinola Garcia
     */
    private fun showSurvey(){
        val homeIntent = Intent(this, PreferencesActivity::class.java)
        startActivity(homeIntent)
    }

}