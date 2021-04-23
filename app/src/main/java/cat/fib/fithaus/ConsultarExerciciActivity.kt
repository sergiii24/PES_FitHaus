package cat.fib.fithaus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cat.fib.fithaus.exercise.ExerciseViewModel
import cat.fib.fithaus.ui.ConsultarExerciciFragment

/** Activity ConsultarExercici
 *
 *  Activitat encarregada de consultar la informació completa d'un exercici
 *
 *  @constructor Crea l'Activity ConsultarExercici
 *  @author Albert Miñana Montecino
 */
class ConsultarExerciciActivity : AppCompatActivity() {

    private lateinit var exerciseViewModel: ExerciseViewModel   // ViewModel de l'Activity ConsultarExercici

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

        val appContainer = (application as Application).appContainer
        exerciseViewModel = appContainer.loginViewModelFactory.create()

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        val fragment = ConsultarExerciciFragment()
        fragmentTransaction.add(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }

}