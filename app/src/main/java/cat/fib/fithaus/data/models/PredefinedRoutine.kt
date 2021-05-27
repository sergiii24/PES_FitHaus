package cat.fib.fithaus.data.models

import androidx.collection.ArraySet
import android.media.Image
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.google.gson.internal.bind.ArrayTypeAdapter
import java.util.*


@Entity(tableName = "PredefinedRoutine")
data class PredefinedRoutine(
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
        @ColumnInfo(name = "age") @SerializedName("age")
        var age: String,
        @ColumnInfo(name = "level") @SerializedName("level")
        var level: String,
        @ColumnInfo(name = "equipment") @SerializedName("equipment")
        var equipment: ArrayList<String>,
        @ColumnInfo(name = "objective") @SerializedName("objective")
        var objective: String,
        @ColumnInfo(name = "impact") @SerializedName("impact")
        var impact: String,
        @ColumnInfo(name = "image") @SerializedName("image")
        var image: String,
        @ColumnInfo(name = "users") @SerializedName("users")
        var users: String,
)/*{

        constructor(id: Int, name: String, description: String, ) : this(1, "Prova", "Aquesta és una rutina de mostra" )

}*/