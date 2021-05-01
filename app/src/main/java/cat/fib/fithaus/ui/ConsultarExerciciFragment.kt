package cat.fib.fithaus.ui

import android.media.MediaController2
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.MediaController
import android.widget.TextView
import android.widget.VideoView
import androidx.annotation.RequiresApi
import androidx.browser.customtabs.CustomTabsClient.getPackageName
import cat.fib.fithaus.BuildConfig
import cat.fib.fithaus.R
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_consultar_exercici.*

// Paràmetres d'inicialització del Fragment
private const val ARG_PARAM1 = "nomExercici"

/** Fragment ConsultarExercici
 *
 *  Fragment encarregat de consultar la informació completa d'un exercici
 *
 *  @constructor Crea el Fragment ConsultarExercici
 *  @author Albert Miñana Montecino
 */
class ConsultarExerciciFragment : Fragment() {

    private var nomIdentificadorExercici: String? = null // Nom de l'exercici

    lateinit var imatgeExercici: ImageView                  // ImageView amb la imatge de previsualització de l'exercici
    lateinit var nomExercici: TextView                      // TextView amb el nom de l'exercici
    lateinit var videotutorialExercici: VideoView           // VideoView amb el videotutorial de l'exercici
    lateinit var contingutDescripcioExercici: TextView      // TextView amb la descripció de l'exercici
    lateinit var contingutMusculTreballExercici: TextView   // TextView amb el múscul de treball de l'exercici
    lateinit var imatgeMusculTreballExercici: ImageView     // ImageView amb la imatge del múscul de treball de l'exercici
    lateinit var contingutEdatExercici: TextView            // TextView amb l'edat de l'exercici
    lateinit var contingutDificultatExercici: TextView      // TextView amb la dificultat de l'exercici
    lateinit var contingutDuracioExercici: TextView         // TextView amb la duració de l'exercici
    lateinit var contingutCategoriaExercici: TextView       // TextView amb la categoria de l'exercici

    /** Function onCreate
     *
     *  Funció encarregada de crear el fragment
     *
     *  @param savedInstanceState
     *  @author Albert Miñana Montecino
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            nomIdentificadorExercici = it.getString(ARG_PARAM1)
        }
    }

    /** Function onCreateView
     *
     *  Funció encarregada de configurar i mostrar el contingut del fragment
     *
     *  @param inflater
     *  @param container
     *  @param savedInstanceState
     *  @author Albert Miñana Montecino
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_consultar_exercici, container, false)

        imatgeExercici = view.findViewById(R.id.imatgeExercici)
        nomExercici = view.findViewById(R.id.nomExercici)
        videotutorialExercici = view.findViewById(R.id.videotutorialExercici)
        contingutDescripcioExercici = view.findViewById(R.id.contingutDescripcioExercici)
        contingutMusculTreballExercici = view.findViewById(R.id.contingutMusculTreballExercici)
        imatgeMusculTreballExercici = view.findViewById(R.id.imatgeMusculTreballExercici)
        contingutEdatExercici = view.findViewById(R.id.contingutEdatExercici)
        contingutDificultatExercici = view.findViewById(R.id.contingutDificultatExercici)
        contingutDuracioExercici = view.findViewById(R.id.contingutDuracioExercici)
        contingutCategoriaExercici = view.findViewById(R.id.contingutCategoriaExercici)

        setContent()

        return view
    }

    /** Function setContent
     *
     *  Funció encarregada d'establir el contingut amb la informació completa d'un exercici
     *
     *  @author Albert Miñana Montecino
     */

    private fun setContent(){
        // Demanar dades d'un exercici a Back

        imatgeExercici.setImageResource(R.drawable.exemple_exercici)
        nomExercici.text = "Genolls alts corrent en el seu lloc"

        val videoPath: String = "android.resource://" + BuildConfig.APPLICATION_ID + "/" + R.raw.high_knees_running_in_place
        val uri: Uri = Uri.parse(videoPath)
        videotutorialExercici.setVideoURI(uri)

        val mediaController = MediaController(activity)
        videotutorialExercici.setMediaController(mediaController)
        mediaController.setAnchorView(videotutorialExercici)
        videotutorialExercici.requestFocus()
        //videotutorialExercici.start()
        videotutorialExercici.seekTo(1)

        contingutDescripcioExercici.text = "Aquest exercici combina aixecaments de genolls amb el típic moviment de carrera. És un dels moviments més efectius per millorar la vostra forma de córrer, així com la vostra velocitat, flexibilitat i equilibri. Els genolls elevats també augmenten la freqüència cardíaca i crema calories, per la qual cosa és ideal per a aquells que busquen greix. És l’exercici perfecte per a un desafiant entrenament cardio que farà que el ritme cardíac continuï. Els principals músculs implicats són els quads, els vedells i els glutis. Aquest moviment també fa participar els abdominals i us pot ajudar a desfer-vos dels tiradors de l’amor."
        contingutMusculTreballExercici.text = "Quàdriceps"
        imatgeMusculTreballExercici.setImageResource(R.drawable.quadriceps)
        contingutEdatExercici.text = "Adult"
        contingutDificultatExercici.text = "Mitjana"
        contingutDuracioExercici.text = "30 segons"
        contingutCategoriaExercici.text = "Cardio"
    }

}