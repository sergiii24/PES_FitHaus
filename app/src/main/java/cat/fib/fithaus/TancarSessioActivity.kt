package cat.fib.fithaus

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/** Classe Tancar Sessió
 *
 *  Classe on es tanca la sessió d'un usuari.
 *
 *  @constructor Mostra el fragment de tancar sessió.
 *  @author Oriol Prat.
 */
class TancarSessioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tancar_sessio)
    }
}