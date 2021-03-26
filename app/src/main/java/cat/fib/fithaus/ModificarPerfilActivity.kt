package cat.fib.fithaus

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/** Classe Modificar perfil
 *
 *  Classe on es mostra la pantalla de modificar perfil.
 *
 *  @constructor Indica a l'usuari que es troba a la pantalla de modificar perfil.
 *  @author Oriol Prat.
 */
class ModificarPerfilActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modificar_perfil)
    }
}