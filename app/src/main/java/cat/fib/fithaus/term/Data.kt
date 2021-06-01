package cat.fib.fithaus.term

import androidx.room.ColumnInfo
import cat.fib.fithaus.data.models.Exercise
import com.google.gson.annotations.SerializedName

class Data {

    companion object {

        fun getfun(): MutableList<Exercise> {
            val exercises: MutableList<Exercise> = mutableListOf()
            for (i in 1..100) {
                exercises.add(Exercise(i,"prova$i","null","null", "null", "null","null","null","null","null","null","null"))
            }
            return exercises
        }

    }

}