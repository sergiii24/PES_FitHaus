package cat.fib.fithaus.data.models

import com.google.gson.annotations.SerializedName

data class Exercise(
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("name")
    var name: String = "",
    @SerializedName("image")
    var image: String,
    @SerializedName("video")
    var video: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("muscle")
    var muscle: String,
    @SerializedName("age")
    var age: String,
    @SerializedName("difficulty")
    var difficulty: String,
    @SerializedName("category")
    var category: String,
    @SerializedName("duration")
    var duration: String,
    @SerializedName("imageMuscle")
    var imageMuscle: String
)