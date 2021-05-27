package cat.fib.fithaus.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import cat.fib.fithaus.data.models.CustomRoutine
import cat.fib.fithaus.data.models.PredefinedRoutine
import androidx.room.TypeConverters
import cat.fib.fithaus.data.models.Converters


/**
 * The Room Database that contains the Routine table.
 *
 * Note that exportSchema should be true in production databases.
 */
@Database(entities = [CustomRoutine::class, PredefinedRoutine::class], version = 1, exportSchema = true)
@TypeConverters(Converters::class)
abstract class FitHausDatabase : RoomDatabase() {

    abstract fun customRoutineDao(): CustomRoutineDao
    abstract fun predefinedRoutineDao(): PredefinedRoutineDao
}