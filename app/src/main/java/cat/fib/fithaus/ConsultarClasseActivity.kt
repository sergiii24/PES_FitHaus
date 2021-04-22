package cat.fib.fithaus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cat.fib.fithaus.ui.ConsultarClasseFragment

class ConsultarClasseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consultar_classe)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        val fragment = ConsultarClasseFragment()
        fragmentTransaction.add(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }
}