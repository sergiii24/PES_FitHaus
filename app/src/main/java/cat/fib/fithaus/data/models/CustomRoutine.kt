package cat.fib.fithaus.data.models

import androidx.collection.ArraySet
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.google.gson.internal.bind.ArrayTypeAdapter
import kotlin.collections.ArrayList


@Entity(tableName = "CustomRoutine")
data class CustomRoutine(
    @PrimaryKey @ColumnInfo(name = "id") @SerializedName("id")
    var id: Int = 0,
    @ColumnInfo(name = "name") @SerializedName("name")
    var name: String = "",
    @ColumnInfo(name = "description") @SerializedName("description")
    var description: String,
    @ColumnInfo(name = "time") @SerializedName("time")
    var time: String,
    @ColumnInfo(name = "categories") @SerializedName("categories")
    var categories: ArrayList<String>,
    @ColumnInfo(name = "exercises") @SerializedName("exercises")
    var exercises: ArrayList<String>,
    @ColumnInfo(name = "classes") @SerializedName("classes")
    var classes: ArrayList<String>,
    @ColumnInfo(name = "public") @SerializedName("public")
    var publicad: Boolean,
    @ColumnInfo(name = "user") @SerializedName("user")
    var user: String
)/*{

        constructor(id: Int,
                    name: String,
                    description: String,
                    time: String,
                    categories: String,
                    exercises: String,
                    classes: String,
                    public: Boolean,
                    user: String) : this(1, name, description, time, categories, exercises, classes, public, user)
}*/