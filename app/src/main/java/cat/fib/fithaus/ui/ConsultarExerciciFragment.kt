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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ConsultarExerciciFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ConsultarExerciciFragment : Fragment() {
    // TODO: Rename and change types of parameters
    /*private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
*/



    lateinit var imatgeExercici: ImageView
    lateinit var nomExercici: TextView
    lateinit var videotutorialExercici: VideoView
    lateinit var contingutDescripcioExercici: TextView
    lateinit var contingutMusculTreballExercici: TextView
    lateinit var imatgeMusculTreballExercici: ImageView
    lateinit var contingutEdatExercici: TextView
    lateinit var contingutDificultatExercici: TextView
    lateinit var contingutDuracioExercici: TextView
    lateinit var contingutCategoriaExercici: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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

        setup()

        return view
    }

    private fun setup(){
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
/*
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ConsultarExerciciFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ConsultarExerciciFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }*/
}