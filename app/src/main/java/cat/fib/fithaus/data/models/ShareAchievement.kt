package cat.fib.fithaus.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "shareachievement")
data class ShareAchievement(
    @PrimaryKey @ColumnInfo(name = "id") @SerializedName("id")
    var id: Int = 0,
    @ColumnInfo(name = "share") @SerializedName("share")
    var firstname: Boolean,
    @ColumnInfo(name = "user") @SerializedName("user")
    var lastname: Int,
    @ColumnInfo(name = "achievement") @SerializedName("achievement")
    var username: Int
){
    constructor(share: Boolean,
                user: Int,
                achievement: Int) : this(0, share, user, achievement)
}


