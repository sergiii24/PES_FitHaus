package cat.fib.fithaus.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "classes")
data class Class(
        @PrimaryKey @ColumnInfo(name = "id") @SerializedName("id")
        var id: Int = 0,
        @ColumnInfo(name = "name") @SerializedName("name")
        var name: String,
        @ColumnInfo(name = "description") @SerializedName("description")
        var description: String,
        @ColumnInfo(name = "age") @SerializedName("age")
        var age: String,
        @ColumnInfo(name = "difficulty") @SerializedName("difficulty")
        var difficulty: String,
        @ColumnInfo(name = "duration") @SerializedName("duration")
        var duration: String,
        @ColumnInfo(name = "category") @SerializedName("category")
        var category: String,
        @ColumnInfo(name = "image") @SerializedName("image")
        var image: String,
        @ColumnInfo(name = "area") @SerializedName("area")
        var area: String,
        @ColumnInfo(name = "trainer") @SerializedName("trainer")
        var trainer: String,
        @ColumnInfo(name = "video") @SerializedName("video")
        var video: String,
)