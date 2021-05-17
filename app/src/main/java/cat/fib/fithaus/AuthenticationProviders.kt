package cat.fib.fithaus

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import cat.fib.fithaus.data.models.User
import cat.fib.fithaus.utils.Status
import cat.fib.fithaus.viewmodels.UserViewModel
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_authentication_providers.*
import kotlinx.android.synthetic.main.activity_authentication_providers.editTextTextEmailAddress
import kotlinx.android.synthetic.main.activity_authentication_providers.editTextTextPassword
import kotlin.random.Random

/** Activity AuthenticationProviders
 *
 *  Activitat encarregada d'autenticar l'usuari amb el correu i la contrasenya, amb els proveïdors d'accés Google o Facebook,
 *  o amb registre.
 *
 *  @constructor Crea l'Activity AuthenticationProviders
 *  @author Albert Miñana Montecino i Adrià Espinola Garcia
 */
@AndroidEntryPoint
class AuthenticationProviders : AppCompatActivity() {

    private val viewModel by viewModels<UserViewModel>()            //ViewModel de l'usuari
    private val GOOGLE_SIGN_IN = 100                                // Constante de Google
    private val callbackManager = CallbackManager.Factory.create()  // Constante de Facebook

    /** Function onCreate
     *
     *  Funció encarregada d'establir el tema, mostrar i configurar el contingut de l'activitat
     *
     *  @param savedInstanceState
     *  @author Albert Miñana Montecino i Adrià Espinola Garcia
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_FitHaus)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication_providers)
        setupSignInButton()
        setupGoogleFacebookButtons()
        setupSignUpButton()
    }

    /** Function setupSignInButton
     *
     *  Funció que comprova si els camps Email i Password són correctes per iniciar sessió.
     *
     *  @author Adrià Espinola Garcia, Albert Miñana Montecino
     */
    private fun setupSignInButton() {
        signInButton.setOnClickListener {
            val validateEmail = validateEmail()
            val validatePassword = validatePassword()
            if (validateEmail && validatePassword) {
                viewModel.login(editTextTextEmailAddress.text.toString(), editTextTextPassword.text.toString() )
                viewModel.user.observe(this, Observer {
                    if (it.status == Status.SUCCESS) {
                        //guarda id
                        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
                        prefs.putString("userId", it.data?.id.toString())
                        prefs.putString("provider", "FitHaus")
                        prefs.apply()
                        showHome()
                    }
                    else
                        Toast.makeText(this, "ERROR!", Toast.LENGTH_LONG).show()
                })
            }
        }
    }

    /** Function validateEmail
     *
     *  Funció que comprova si el camp Email és correcte.
     *
     *  @return Retorna cert si és correcte, fals en cas contrari.
     *  @author Adrià Espinola Garcia, Albert Miñana Montecino
     */
    private fun validateEmail(): Boolean {
        val email = editTextTextEmailAddress?.text.toString()
        if (email.isEmpty()) {
            editTextTextEmailAddress?.setError("El camp no pot ser buit")
            return false
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextTextEmailAddress?.setError("Format incorrecte")
            return false
        }
        else {
            editTextTextEmailAddress?.setError(null)
            return true
        }
    }

    /** Function validatePassword
     *
     *  Funció que comprova si el camp Contrasenya és correcte.
     *
     *  @return Retorna cert si és correcte, fals en cas contrari.
     *  @author Adrià Espinola Garcia, Albert Miñana Montecino
     */
    private fun validatePassword(): Boolean {
        val password = editTextTextPassword?.text.toString()
        if (password.isEmpty()) {
            editTextTextPassword?.setError("El camp no pot ser buit")
            return false
        }
        else {
            editTextTextPassword?.setError(null)
            return true
        }
    }

    /** Function setupGoogleFacebookButtons
     *
     *  Funció encarregada de configurar els botons d'accés amb Google i Facebook
     *
     *  @author Albert Miñana Montecino
     */
    private fun setupGoogleFacebookButtons(){
        googleButton.setOnClickListener() {
            // Configuració d'accés amb Google
            val googleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

            // Creació del client d'autenticació amb Google
            val googleClient = GoogleSignIn.getClient(this, googleConf)

            // Elimina el compte de Google autenticat en aquest moment (per si es disposa de varis comptes de Google al mateix dispositiu)
            googleClient.signOut()

            // Inicia l'activitat d'accés amb Google
            startActivityForResult(googleClient.signInIntent, GOOGLE_SIGN_IN)
        }

        facebookButton.setOnClickListener(){
            // Elimina el compte de Facebook autenticat en aquest moment
            LoginManager.getInstance().logOut()

            // Inicia l'activitat d'accés amb Facebook
            LoginManager.getInstance().logInWithReadPermissions(this, listOf("email"))

            // Operacions de resposta un cop finalizat l'accés amb Facebook
            LoginManager.getInstance().registerCallback(callbackManager, object: FacebookCallback<LoginResult>{
                override fun onSuccess(result: LoginResult?) {
                    result?.let {
                        // Mostra l'accés amb Google a la consola de Firebase
                        val token = it.accessToken
                        val credential = FacebookAuthProvider.getCredential(token.token)
                        FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener(){
                            if (it.isSuccessful){
                                //Pendent de la funcionalitat del Back
                                val fullName = it.result?.user?.displayName
                                val email = it.result?.user?.email
                                val uid = it.result?.user?.uid
                                viewModel.getUserByEmail(email.toString())
                                viewModel.user.observe(this@AuthenticationProviders, Observer {
                                    if (it.status == Status.SUCCESS) {
                                        //guarda id
                                        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
                                        prefs.putString("userId", it.data?.id.toString())
                                        prefs.putString("provider", "Facebook")
                                        prefs.apply()
                                        showHome()
                                    } else {
                                        val index = fullName?.indexOf(" ", 0, false)
                                        val firstName = index?.let { it1 -> fullName?.substring(0, it1) }
                                        val lastName = index?.plus(1)?.let { it1 -> fullName?.substring(it1) }
                                        val indexEmail = email?.indexOf("@", 0, false)
                                        val emailName = indexEmail?.let { it1 -> email?.substring(0, it1) }
                                        val username = generateUsername(emailName)
                                        val user = User(firstName.toString(), lastName.toString(), username, email.toString())
                                        viewModel.createUser(user)
                                        viewModel.user.observe(this@AuthenticationProviders, Observer {
                                            if (it.status == Status.SUCCESS) {
                                                //guarda id
                                                val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
                                                prefs.putString("userId", it.data?.id.toString())
                                                prefs.putString("provider", "Facebook")
                                                prefs.apply()
                                                showSurvey()
                                            }
                                            else {
                                                Toast.makeText(this@AuthenticationProviders, "ERROR!", Toast.LENGTH_LONG).show()
                                            }
                                        })
                                    }
                                })
                            }
                            else{
                                showAlertGoogleFacebook()
                            }
                        }
                    }
                }

                override fun onCancel() {}

                override fun onError(error: FacebookException?) {
                    showAlertGoogleFacebook()
                }
            })
        }
    }

    /** Function onActivityResult
     *
     *  Funció encarregada de tractar les activitats d'accés amb Google i Facebook
     *
     *  @param requestCode
     *  @param resultCode
     *  @param data
     *  @author Albert Miñana Montecino
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Cas de tractar l'activitat d'accés amb Facebook
        callbackManager.onActivityResult(requestCode, resultCode, data)

        // Cas de tractar l'activitat d'accés amb Google
        if (requestCode == GOOGLE_SIGN_IN){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                if (account != null) {
                    // Mostra l'accés amb Google a la consola de Firebase
                    val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                    FirebaseAuth.getInstance().signInWithCredential(credential)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                //Pendent de la funcionalitat del Back
                                val fullName = it.result?.user?.displayName
                                val email = it.result?.user?.email
                                val uid = it.result?.user?.uid
                                viewModel.getUserByEmail(email.toString())
                                viewModel.user.observe(this, Observer {
                                    if (it.status == Status.SUCCESS) {
                                        //guarda id
                                        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
                                        prefs.putString("userId", it.data?.id.toString())
                                        prefs.putString("provider", "Google")
                                        prefs.apply()
                                        showHome()
                                    } else {
                                        val index = fullName?.indexOf(" ", 0, false)
                                        val firstName = index?.let { it1 -> fullName?.substring(0, it1) }
                                        val lastName = index?.plus(1)?.let { it1 -> fullName?.substring(it1) }
                                        val indexEmail = email?.indexOf("@", 0, false)
                                        val emailName = indexEmail?.let { it1 -> email?.substring(0, it1) }
                                        val username = generateUsername(emailName)
                                        val user = User(firstName.toString(), lastName.toString(), username, email.toString())
                                        viewModel.createUser(user)
                                        viewModel.user.observe(this, Observer {
                                            if (it.status == Status.SUCCESS) {
                                                //guarda id
                                                val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
                                                prefs.putString("userId", it.data?.id.toString())
                                                prefs.putString("provider", "Google")
                                                prefs.apply()
                                                showSurvey()
                                            }
                                            else {
                                                Toast.makeText(this, "ERROR!", Toast.LENGTH_LONG).show()
                                            }
                                        })
                                    }
                                })
                            } else {
                                showAlertGoogleFacebook()
                            }
                        }

                }
            }
            catch(e: ApiException){
                showAlertGoogleFacebook()
            }
        }
    }

    /** Function setupSignUpButton
     *
     *  Funció que fa que es mostri la pantalla de CrearPerfilActivity (Crear Perfil) a l'usuari.
     *
     *  @author Adrià Espinola Garcia
     */
    fun setupSignUpButton() {
        signUpButton.setOnClickListener {
            val intent = Intent(this, CrearPerfilActivity::class.java)
            startActivity(intent)
        }
    }


    /** Function showHome
     *
     *  Funció encarregada de canviar a l'activitat principal.
     *
     *  @author Albert Miñana Montecino i Adrià Espinola Garcia
     */
    private fun showHome(){
        val homeIntent = Intent(this, MainActivity::class.java)
        startActivity(homeIntent)
    }

    /** Function showSurvey
     *
     *  Funció encarregada de canviar a l'activitat del qüestionari.
     *
     *  @author Albert Miñana Montecino i Adrià Espinola Garcia
     */
    private fun showSurvey(){
        val homeIntent = Intent(this, QuestionariInicialActivity::class.java)
        startActivity(homeIntent)
    }

    /** Function showAlertGoogleFacebook
     *
     *  Funció encarregada de mostrar un missatge d'error d'autenticació de l'usuari
     *
     *  @author Albert Miñana Montecino
     */
    private fun showAlertGoogleFacebook(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("S'ha produït un error autenticant l'usuari")
        builder.setPositiveButton("Acceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.create()
    }

    /** Function showAlertSignIn
     *
     *  Funció que mostra un missatge d'error en format Toast segons els paràmetres d'entrada.
     *
     *  @param emailEmpty
     *  @param passwordEmpty
     *  @param format
     *  @author Adrià Espinola Garcia
     */
    private fun showAlertSignIn(emailEmpty: Boolean, passwordEmpty: Boolean, format: Boolean) {
        when {
            emailEmpty and passwordEmpty -> Toast.makeText(this, "Els camps Email i Password estan buits", Toast.LENGTH_LONG).show()
            emailEmpty -> Toast.makeText(this, "El camp Email és buit", Toast.LENGTH_LONG).show()
            passwordEmpty -> Toast.makeText(this, "El camp Password és buit", Toast.LENGTH_LONG).show()
            format -> Toast.makeText(this, "El format del Email és incorrecte", Toast.LENGTH_LONG).show()
        }

    }


    /** Function generateUsername
     *
     *  Funció que genera un username a partir del nom que conté un email.
     *
     *  @param emailName
     *  @return Retorna el username generat.
     *  @author Albert Miñana Montecino i Adrià Espinola Garcia
     */
    private fun generateUsername(emailName: String?) : String {
        var numberText = StringBuilder()
        val numberArray = intArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)

        val totalNumbers = 3

        for (index in 0 until totalNumbers) {
            numberText.append(numberArray[Random.nextInt(numberArray.size)]);
        }
        return "$emailName#$numberText"
    }
}