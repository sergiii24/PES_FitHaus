package cat.fib.fithaus.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "program", indices = [Index(value = ["name", "difficulty"], unique = true)])
data class Program(
        @PrimaryKey @ColumnInfo(name = "id") @SerializedName("id")
        var id: Int = 0,
        @ColumnInfo(name = "name") @SerializedName("name")
        var name: String = "",
        @ColumnInfo(name = "description") @SerializedName("description")
        var description: String = "",
        @ColumnInfo(name = "difficulty") @SerializedName("difficulty")
        var difficulty: String = "",
        @ColumnInfo(name = "weeks") @SerializedName("weeks")
        var weeks: Int = 0,
        @ColumnInfo(name = "predef_routines") @SerializedName("predef_routines")
        var predef_routines: ArrayList<Int>
){
    constructor(name: String,
                description: String,
                difficulty: String,
                weeks: Int,
                predef_routines: ArrayList<Int>) : this(0, name, description, "B", 0, ArrayList())
}
