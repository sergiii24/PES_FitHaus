package cat.fib.fithaus

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import cat.fib.fithaus.viewmodels.UserViewModel
import kotlinx.android.synthetic.main.activity_log_in.*


/** Classe Activity LogIn
 *
 *  Activity on es troba tota la lògica per poder fer Log In de l'app i accedir al registre.
 *
 *  @constructor Crea un perfil d'usuari amb tots els camps amb valor nul.
 *  @author Adrià Espinola.
 */
class LogInActivity : AppCompatActivity() {

    private val viewModel by viewModels<UserViewModel>()


    /** Funció inicialitzadora
     *
     *  Funció que fa que es mostri la pantalla de logIn a l'usuari.
     *
     *  @param savedInstanceState
     *  @author Adrià Espinola.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
    }

    /** Funció signIn
     *
     *  Funció que comprova si els camps Email i Password són correctes per iniciar sessió.
     *
     *  @param view
     *  @author Adrià Espinola.
     */
    fun signIn(view: View) {
        var emailEmpty: Boolean = true
        var passwordEmpty = true
        var error: String
        var incorrectFormat: Boolean = true
        if (editTextTextEmailAddress.text.isNotEmpty()) emailEmpty = false
        if (editTextTextPassword.text.isNotEmpty()) passwordEmpty = false
        if (Patterns.EMAIL_ADDRESS.matcher(editTextTextEmailAddress.text).matches()) incorrectFormat = false
        if (emailEmpty or passwordEmpty or incorrectFormat) showAlert(emailEmpty, passwordEmpty, incorrectFormat)
        else login(editTextTextEmailAddress.text.toString(), editTextTextPassword.text.toString() )
    }

    fun login(
        email: String,
        pass: String
    ) {

        viewModel.login(email,pass)


    }

    /** Funció signUp
     *
     *  Funció que fa que es mostri la pantalla de CrearPerfilActivity (Crear Perfil) a l'usuari.
     *
     *  @param view
     *  @author Adrià Espinola.
     */
    fun signUp(view: View) {
        //Canviar MainActivity a CrearPerfilActivity quan es fagi el Merge
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    /** Funció showHome
     *
     *  Funció que fa que es mostri la pantalla MainActivity (Pantalla principal Home) a l'usuari un cop hagi iniciat sessió.
     *
     *  @param savedInstanceState
     *  @author Adrià Espinola.
     */
    private fun showHome() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    /** Funció showAlert
     *
     *  Funció que mostra un missatge d'error en format Toast segons els paràmetres d'entrada.
     *
     *  @param emailEmpty @param[passwordEmpty] @param[format]
     *  @author Adrià Espinola.
     */
    private fun showAlert(emailEmpty: Boolean, passwordEmpty: Boolean, format: Boolean) {
        when {
            emailEmpty and passwordEmpty -> Toast.makeText(this, "Els camps Email i Password estan buits", Toast.LENGTH_LONG).show()
            emailEmpty -> Toast.makeText(this, "El camp Email és buit", Toast.LENGTH_LONG).show()
            passwordEmpty -> Toast.makeText(this, "El camp Password és buit", Toast.LENGTH_LONG).show()
            format -> Toast.makeText(this, "El format del Email és incorrecte", Toast.LENGTH_LONG).show()
        }

    }

}