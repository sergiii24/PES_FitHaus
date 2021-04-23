package cat.fib.fithaus.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import cat.fib.fithaus.data.models.Exercise

/**
 * The Room Database that contains the Exercise table.
 *
 * Note that exportSchema should be true in production databases.
 */
@Database(entities = [Exercise::class], version = 1, exportSchema = false)
abstract class FitHausDatabase : RoomDatabase() {

    abstract fun exerciseDao(): ExerciseDao

}