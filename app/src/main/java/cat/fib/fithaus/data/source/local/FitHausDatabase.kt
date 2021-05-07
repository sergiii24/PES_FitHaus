package cat.fib.fithaus.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import cat.fib.fithaus.data.models.Exercise
import cat.fib.fithaus.data.models.User

/**
 * The Room Database that contains the Exercise table.
 *
 * Note that exportSchema should be true in production databases.
 */

@Database(entities = [User::class, Exercise::class], version = 1, exportSchema = true)
abstract class FitHausDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun exerciseDao(): ExerciseDao
}