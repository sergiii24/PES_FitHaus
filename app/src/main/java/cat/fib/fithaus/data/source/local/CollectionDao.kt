package cat.fib.fithaus.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cat.fib.fithaus.data.models.Collection


/**
 * Data Access Object for the collection table.
 */
@Dao
interface CollectionDao {
    /**
     * Select a collection by name.
     *
     * @param collectionName the name of the collection.
     * @return the name of collection.
     */
    @Query("SELECT * FROM collections WHERE name = :collectionName")
    fun getCollectionByName(collectionName: String): LiveData<Collection>

    /**
     * Insert a collection in the database. If the collection already exists, replace it.
     *
     * @param collection the collection to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCollection(collection: Collection)

}