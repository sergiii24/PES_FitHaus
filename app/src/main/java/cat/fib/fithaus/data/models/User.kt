package cat.fib.fithaus.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.time.Instant
import java.util.*

@Entity(tableName = "user")
data class User(
        @PrimaryKey @ColumnInfo(name = "id") @SerializedName("id")
        var id: Int = 0,
        @ColumnInfo(name = "firstname") @SerializedName("firstname")
        var firstname: String = "",
        @ColumnInfo(name = "lastname") @SerializedName("lastname")
        var lastname: String = "",
        @ColumnInfo(name = "username") @SerializedName("username")
        var username: String = "",
        @ColumnInfo(name = "achievements") @SerializedName("achievements")
        var achievements: String = "",
        @ColumnInfo(name = "password") @SerializedName("password")
        var password: String = "",
        @ColumnInfo(name = "activitiesdone") @SerializedName("activitiesdone")
        var activitiesdone: Int = 0,
        @ColumnInfo(name = "points") @SerializedName("points")
        var points: Int = 0,
        @ColumnInfo(name = "level") @SerializedName("level")
        var level: Int = 0,
        @ColumnInfo(name = "objectives") @SerializedName("objectives")
        var objectives: Int = 0,
        @ColumnInfo(name = "interestcategories") @SerializedName("interestcategories")
        var interestcategories: String = "",
        @ColumnInfo(name = "weight") @SerializedName("weight")
        var weight: Int = 0,
        @ColumnInfo(name = "height") @SerializedName("height")
        var height: Int = 0,
        @ColumnInfo(name = "imc") @SerializedName("imc")
        var imc: Int = 0,
        @ColumnInfo(name = "igc") @SerializedName("igc")
        var igc: Int = 0,
        @ColumnInfo(name = "historic") @SerializedName("historic")
        var historic: Int = 0,
        @ColumnInfo(name = "email") @SerializedName("email")
        var email: String = "",
        @ColumnInfo(name = "gender") @SerializedName("gender")
        var gender: String = "",
        @ColumnInfo(name = "birthdate") @SerializedName("birthdate")
        var birthdate: Date = Date.from(Instant.now())
){
        constructor(firstname: String,
                    lastname: String,
                username: String,
                password: String,
                email: String,
        gender: String,
        birthdate: Date) : this(1000, firstname, lastname, username, "", password, 0, 0, 0, 0, "", 0, 0, 0, 0, 0, email, gender, birthdate

        )
}
