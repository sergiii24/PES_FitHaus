package cat.fib.fithaus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import cat.fib.fithaus.exercise.ExerciseViewModel
import cat.fib.fithaus.ui.ConsultarExerciciFragment
import cat.fib.fithaus.viewmodels.ExercisesViewModel
import dagger.hilt.android.AndroidEntryPoint

/** Activity ConsultarExercici
 *
 *  Activitat encarregada de consultar la informació completa d'un exercici
 *
 *  @constructor Crea l'Activity ConsultarExercici
 *  @author Albert Miñana Montecino
 */
@AndroidEntryPoint
class ConsultarExerciciActivity : AppCompatActivity() {

    private val viewModel by viewModels<ExercisesViewModel>()


    /** Function onCreate
     *
     *  Funció encarregada de crear, configurar i mostrar el contingut de l'activitat mitjançant el Fragment ConsultarExercici
     *
     *  @param savedInstanceState
     *  @author Albert Miñana Montecino
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consultar_exercici)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        val fragment = ConsultarExerciciFragment()
        fragmentTransaction.add(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }


}