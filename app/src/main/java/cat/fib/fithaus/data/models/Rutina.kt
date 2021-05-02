package cat.fib.fithaus.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "rutina")
data class Rutina(
        @PrimaryKey @ColumnInfo(name = "id") @SerializedName("id")
        var id: Int = 0,
        @ColumnInfo(name = "name") @SerializedName("name")
        var name: String = "",
        @ColumnInfo(name = "categories") @SerializedName("categories")
        var categories: String,
        @ColumnInfo(name = "description") @SerializedName("description")
        var description: String,
        @ColumnInfo(name = "time") @SerializedName("time")
        var time: Int,
        @ColumnInfo(name = "age") @SerializedName("age")
        var age: Int,
        @ColumnInfo(name = "level") @SerializedName("level")
        var level: String,
        @ColumnInfo(name = "equipment") @SerializedName("equipment")
        var equipment: String,
        @ColumnInfo(name = "objective") @SerializedName("objective")
        var objective: String,
        @ColumnInfo(name = "impact") @SerializedName("impact")
        var impact: String,
        @ColumnInfo(name = "impactPre") @SerializedName("impactPre")
        var impactPre: String,
)