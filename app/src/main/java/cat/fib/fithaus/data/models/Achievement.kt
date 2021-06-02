package cat.fib.fithaus.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "achievement")
data class Achievement(
        @PrimaryKey @ColumnInfo(name = "id") @SerializedName("id")
        var id: Int = 0,
        @ColumnInfo(name = "achievement") @SerializedName("achievement")
        var firstname: String,
        @ColumnInfo(name = "quantity") @SerializedName("quantity")
        var lastname: Int,
        @ColumnInfo(name = "points") @SerializedName("points")
        var username: Int
){
    constructor(achievement: String,
                quantity: Int,
                points: Int) : this(0, achievement, quantity, points)
}