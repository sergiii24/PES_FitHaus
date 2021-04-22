package cat.fib.fithaus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cat.fib.fithaus.exercise.ExerciseViewModel
import cat.fib.fithaus.ui.ConsultarExerciciFragment

class ConsultarExerciciActivity : AppCompatActivity() {


    private lateinit var exerciseViewModel: ExerciseViewModel

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