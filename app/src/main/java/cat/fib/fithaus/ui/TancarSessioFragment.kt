package cat.fib.fithaus.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import cat.fib.fithaus.LogInActivity
import cat.fib.fithaus.R

/** Classe Fragment Tancar Sessió
 *
 *  Classe on hi ha el fragment de tancar la sessió d'un usuari.
 *
 *  @author Oriol Prat.
 */
class TancarSessioFragment : Fragment(R.layout.fragment_tancar_sessio) {

    /** Funció onViewCreated
     *
     *  Funció que inicialitza la variable del botó i de la barra de progrés i treballa amb el listener del botó.
     *
     *  @param view
     *  @param savedInstanceState
     *  @author Oriol Prat.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tancar: Button = view.findViewById(R.id.TancarSessió)
        val progress: ProgressBar = view.findViewById(R.id.progressBar2)

        tancar.setOnClickListener {
            progress.visibility = View.VISIBLE
            tancar.isEnabled = false
            val intent = Intent(activity, LogInActivity::class.java)
            startActivity(intent)
            Toast.makeText(activity, "Fins aviat", Toast.LENGTH_SHORT).show()
        }
    }
}