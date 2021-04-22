package cat.fib.fithaus.data.models

import com.google.gson.annotations.SerializedName
import java.time.Instant
import java.util.*

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

val gson = Gson()

//convert a data class to a map
fun <T> T.serializeToMap(): HashMap<String, String> {
        return convert()
}

//convert a data class to a map
fun <T> T.serializeToString():  String {
        return convert()
}
        //convert a map to a data class
inline fun <reified T> HashMap<String, String>.toDataClass(): T {
        return convert()
}

//convert an object of type I to type O
inline fun <I, reified O> I.convert(): O {
        val json = gson.toJson(this)
        return gson.fromJson(json, object : TypeToken<O>() {}.type)
}

data class User(
        @SerializedName("id")
        var id: Int = 1000,
        @SerializedName("firstname")
        var firstname: String = "",
        @SerializedName("lastname")
        var lastname: String = "",
        @SerializedName("username")
        var username: String = "",
        @SerializedName("achievements")
        var achievements: String = "",
        @SerializedName("password")
        var password: String = "",
        @SerializedName("activitiesdone")
        var activitiesdone: Int = 0,
        @SerializedName("points")
        var points: Int = 0,
        @SerializedName("level")
        var level: Int = 0,
        @SerializedName("objectives")
        var objectives: Int = 0,
        @SerializedName("interestcategories")
        var interestcategories: String = "",
        @SerializedName("weight")
        var weight: Int = 0,
        @SerializedName("height")
        var height: Int = 0,
        @SerializedName("imc")
        var imc: Int = 0,
        @SerializedName("igc")
        var igc: Int = 0,
        @SerializedName("historic")
        var historic: Int = 0,
        @SerializedName("email")
        var email: String = "",
        @SerializedName("gender")
        var gender: String = "",
        @SerializedName("birthdate")
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
