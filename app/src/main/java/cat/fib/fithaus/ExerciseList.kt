package cat.fib.fithaus

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import cat.fib.fithaus.databinding.MainFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExerciseList : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView<MainFragmentBinding>(this, R.layout.exercise_list_activity)
    }

}