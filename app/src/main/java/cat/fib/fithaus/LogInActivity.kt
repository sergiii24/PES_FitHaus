package cat.fib.fithaus

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/** Classe Log In
 *
 *  Classe on es mostra la pantalla de log in.
 *
 *  @constructor Indica a l'usuari que es troba a la pantalla de log in.
 *  @author Oriol Prat.
 */
class LogInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
    }
}