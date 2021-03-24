package cat.fib.fithaus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast

class TancarSessioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tancar_sessio)
    }

    val btnLogOut: Button = findViewById(R.id.TancarSessio)
    val brProgress: ProgressBar = findViewById(R.id.progressBar)

    fun signOut(){
        btnLogOut.setOnClickListener{
            brProgress.visibility = View.VISIBLE
            btnLogOut.isEnabled = false
            AuthUI.getInstance().signOut(this).addOnSuccesListener{
                startActivity(Intent(this, LogInActivity::class.java))
                Toast.makeText(this, "Fins aviat", Toast.LENGTH_SHORT).show()
                finish()
            }.addOnFailureListener{
                btnLogOut.isEnabled = true
                brProgress.visibility = View.GONE
                Toast.makeText(this, "Hi ha hagut un error ${it.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}