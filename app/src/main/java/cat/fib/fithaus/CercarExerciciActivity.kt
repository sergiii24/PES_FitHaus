package cat.fib.fithaus

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_cercar_exercici.*

class CercarExerciciActivity : AppCompatActivity() {

    val exercicis = listOf(
            Exercici("cames", 5, "fàcil","imatgecames"),
            Exercici("braços", 10, "fàcil","imatgebraços"),
            Exercici("peus", 8, "fàcil","imatgepeus"),
            Exercici("ulls", 1, "fàcil","imatgeulls")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cercar_exercici)

       // val btnFragmentEsportives: Button = findViewById(R.id.DadesEsportives)

        initRecycler()
    }

    fun initRecycler(){
        rvExerciciList.layoutManager = LinearLayoutManager(this)
        val adapter = ExerciciAdapter(exercicis)
        rvExerciciList.adapter = adapter
    }
}