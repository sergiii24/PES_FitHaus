package cat.fib.fithaus.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Immutable model class for a Exercise. In order to compile with Room, we can't use @JvmOverloads to
 * generate multiple constructors.
 *
 * @param id id of the exercise
 * @param name name of the exercise
 * @param image image of the exercise
 * @param video video of the exercise
 */

@Entity(tableName = "exercises")
data class Exercise(
    @PrimaryKey @ColumnInfo(name = "id") @SerializedName("id")
    var id: Int = 0,
    @ColumnInfo(name = "name") @SerializedName("name")
    var name: String = "",
    @ColumnInfo(name = "image") @SerializedName("image")
    var image: String,
    @ColumnInfo(name = "videoTutorial") @SerializedName("videoTutorial")
    var videoTutorial: String,
    @ColumnInfo(name = "videoExercise") @SerializedName("videoExercise")
    var videoExercise: String,
    @ColumnInfo(name = "description") @SerializedName("description")
    var description: String,
    @ColumnInfo(name = "muscle") @SerializedName("muscle")
    var muscle: String,
    @ColumnInfo(name = "age") @SerializedName("age")
    var age: String,
    @ColumnInfo(name = "difficulty") @SerializedName("difficulty")
    var difficulty: String,
    @ColumnInfo(name = "category") @SerializedName("category")
    var category: String,
    @ColumnInfo(name = "duration") @SerializedName("duration")
    var duration: String,
    @ColumnInfo(name = "imageMuscle") @SerializedName("imageMuscle")
    var imageMuscle: String
)