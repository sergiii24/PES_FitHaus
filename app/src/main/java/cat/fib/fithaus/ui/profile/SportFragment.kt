package cat.fib.fithaus.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import cat.fib.fithaus.AuthenticationProviders
import cat.fib.fithaus.PreferencesActivity
import cat.fib.fithaus.R
import cat.fib.fithaus.data.models.User
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_sport.*

/**
 * A simple [Fragment] subclass.
 * Use the [SportFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class SportFragment : Fragment(R.layout.fragment_sport) {

    //private val viewModel by viewModels<UserViewModel>()

    lateinit var level: TextView
    lateinit var interestcategories: TextView
    lateinit var objectives: TextView
    lateinit var routinesdone: TextView
    lateinit var points: TextView
    lateinit var achievements: TextView
    lateinit var preferencesButton: Button


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v: View = inflater.inflate(R.layout.fragment_sport, container, false)
        routinesdone = v.findViewById(R.id.NumRutines)
        achievements = v.findViewById(R.id.Assoliments)
        points = v.findViewById(R.id.Punts)
        level = v.findViewById(R.id.Nivell)
        objectives = v.findViewById(R.id.Objectiu)
        interestcategories = v.findViewById(R.id.CategoriesInterès)
        preferencesButton = v.findViewById(R.id.ActualitzarPreferencesPerfilButton)
        setUpButtonPreferences()
        /*viewModel.user.observe(viewLifecycleOwner, Observer {
            if (it.status == Status.SUCCESS)
                setUpData(it.data)
        }) */

        return v
    }

    fun setUpData(userData: User?) {
        routinesdone.text = userData?.activitiesdone.toString()
        achievements.text = userData?.achievements
        points.text = userData?.points.toString()
        level.text = userData?.level.toString()
        objectives.text = userData?.objectives.toString()
        interestcategories.text = userData?.categories.toString()
    }

    fun setUpButtonPreferences() {
        preferencesButton.setOnClickListener {
            val intent = Intent(activity, PreferencesActivity::class.java)
            startActivity(intent)
        }
    }

}