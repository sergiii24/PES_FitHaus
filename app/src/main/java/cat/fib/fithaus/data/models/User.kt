package cat.fib.fithaus.data.models

import androidx.collection.ArraySet
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.google.gson.internal.bind.ArrayTypeAdapter
import java.time.Instant
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

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
        @ColumnInfo(name = "achievements") @SerializedName("achivements")
        var achievements: String = "",
        @ColumnInfo(name = "password") @SerializedName("password")
        var password: String = "",
        @ColumnInfo(name = "activitiesdone") @SerializedName("activitiesdone")
        var activitiesdone: Int = 0,
        @ColumnInfo(name = "points") @SerializedName("points")
        var points: Int = 0,
        @ColumnInfo(name = "level") @SerializedName("level")
        var level: String = "",
        @ColumnInfo(name = "objectives") @SerializedName("objectives")
        var objectives: ArrayList<String>,
        @ColumnInfo(name = "categories") @SerializedName("categories")
        var categories: ArrayList<String>,
        @ColumnInfo(name = "weight") @SerializedName("weight")
        var weight: Float = 0.0f,
        @ColumnInfo(name = "height") @SerializedName("height")
        var height: Float = 0.0f,
        @ColumnInfo(name = "imc") @SerializedName("imc")
        var imc: Float = 0.0f,
        @ColumnInfo(name = "igc") @SerializedName("igc")
        var igc: Float = 0.0f,
        @ColumnInfo(name = "updated") @SerializedName("updated")
        var updated: String = "",
        @ColumnInfo(name = "email") @SerializedName("email")
        var email: String = "",
        @ColumnInfo(name = "gender") @SerializedName("gender")
        var gender: String = "",
        @ColumnInfo(name = "birthdate") @SerializedName("birthdate")
        var birthdate: String = ""
){
        constructor(firstname: String,
                    lastname: String,
                username: String,
                password: String,
                email: String,
        gender: String,
        birthdate: String) : this(0, firstname, lastname, username, "", password, 0, 0, "", ArrayList(), ArrayList(), 0.0f, 0.0f, 0.0f, 0.0f, "0", email, gender, birthdate)

        constructor(firstname: String,
                    lastname: String,
                    username: String,
                    email: String) : this(0, firstname, lastname, username, "", "", 0, 0, "", ArrayList(), ArrayList(), 0.0f, 0.0f, 0.0f, 0.0f, "0", email, "", "")
}
