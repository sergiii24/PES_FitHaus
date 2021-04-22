package cat.fib.fithaus.models

import com.google.gson.annotations.SerializedName

class Exercise(
    @SerializedName("id")
    var id: Int = 1000,
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
) {
}