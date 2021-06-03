package cat.fib.fithaus.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import cat.fib.fithaus.data.models.*

/**
 * The Room Database that contains the Exercise table.
 *
 * Note that exportSchema should be true in production databases.
 */

@Database(entities = [User::class, Exercise::class, Class::class, PredefinedRoutine::class, HealthData::class, Program::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class FitHausDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun exerciseDao(): ExerciseDao

    abstract fun classDao(): ClassDao

    abstract fun predefinedRoutineDao(): PredefinedRoutineDao

    abstract fun healthDataDao(): HealthDataDao

    abstract fun programDao(): ProgramDao

}