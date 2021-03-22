package cat.fib.fithaus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_log_in.*

class LogInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
    }

    fun signIn(view: View) {
        var emailEmpty: Boolean = true
        var passwordEmpty = true
        var error: String
        var incorrectFormat: Boolean = true
        if (editTextTextEmailAddress.text.isNotEmpty()) emailEmpty = false
        if (editTextTextPassword.text.isNotEmpty()) passwordEmpty = false
        if (Patterns.EMAIL_ADDRESS.matcher(editTextTextEmailAddress.text).matches()) incorrectFormat = false
        if (emailEmpty or passwordEmpty or incorrectFormat) showAlert(emailEmpty, passwordEmpty, incorrectFormat)
        else showHome()
    }

    fun signUp(view: View) {
        //Canviar MainActivity a CrearPerfilActivity quan es fagi el Merge
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun showHome() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun showAlert(emailEmpty: Boolean, passwordEmpty: Boolean, format: Boolean) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        when {
            emailEmpty and passwordEmpty -> builder.setMessage("Els camps Email i Password estan buits")
            emailEmpty -> builder.setMessage("El camp Email és buit")
            passwordEmpty -> builder.setMessage("El camp Password és buit")
            format -> builder.setMessage("El format del Email és incorrecte")
        }
        //builder.setMessage("S'ha produït un error d'autenticació")
        builder.setPositiveButton("Acceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}