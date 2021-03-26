package cat.fib.fithaus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
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
import com.google.firebase.auth.ktx.auth
import kotlinx.android.synthetic.main.activity_authentication_providers.*

/** Activity AuthenticationProviders
 *
 *  Activitat encarregada d'autenticar l'usuari amb els proveïdors d'accés Google i Facebook
 *
 *  @constructor Crea l'Activity AuthenticationProviders
 *  @author Albert Miñana Montecino
 */
class AuthenticationProviders : AppCompatActivity() {

    private val GOOGLE_SIGN_IN = 100                                // Constante de Google
    private val callbackManager = CallbackManager.Factory.create()  // Constante de Facebook

    /** Function onCreate
     *
     *  Funció encarregada d'establir el tema, mostrar i configurar el contingut de l'activitat
     *
     *  @param savedInstanceState
     *  @author Albert Miñana Montecino
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_FitHaus)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication_providers)
        setupGoogleFacebookButtons()
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
                                showHome(it.result?.user?.email ?: "")
                            }
                            else{
                                showAlert()
                            }
                        }
                    }
                }

                override fun onCancel() {}

                override fun onError(error: FacebookException?) {
                    showAlert()
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
                                showHome(account.email ?: "")
                            } else {
                                showAlert()
                            }
                        }

                }
            }
            catch(e: ApiException){
                showAlert()
            }
        }
    }

    /** Function showHome
     *
     *  Funció encarregada de canviar a una altra activitat
     *
     *  @param email
     *  @author Albert Miñana Montecino
     */
    private fun showHome(email: String){
        val homeIntent = Intent(this, MainActivity::class.java).apply {
            putExtra("email", email)
        }
        startActivity(homeIntent)
    }

    /** Function showAlert
     *
     *  Funció encarregada de mostrar un missatge d'error d'autenticació de l'usuari
     *
     *  @author Albert Miñana Montecino
     */
    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("S'ha produït un error autenticant l'usuari")
        builder.setPositiveButton("Acceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.create()
    }
}