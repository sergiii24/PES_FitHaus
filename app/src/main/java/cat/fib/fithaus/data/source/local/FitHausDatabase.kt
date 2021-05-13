package cat.fib.fithaus.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import cat.fib.fithaus.data.models.User
import cat.fib.fithaus.data.models.Class
import cat.fib.fithaus.data.models.Converters

/**
 * The Room Database that contains the Exercise table.
 *
 * Note that exportSchema should be true in production databases.
 */
@Database(entities = [User::class, Class::class], version = 1, exportSchema = true)
@TypeConverters(Converters::class)
abstract class FitHausDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun classDao(): ClassDao

}