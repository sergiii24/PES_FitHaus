package cat.fib.fithaus.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import cat.fib.fithaus.R
import cat.fib.fithaus.data.models.User
import cat.fib.fithaus.utils.Status
import cat.fib.fithaus.viewmodels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentEsportives.newInstance] factory method to
 * create an instance of this fragment.
 */

class FragmentEsportives : Fragment(R.layout.fragment_esportives) {

    private val viewModel by viewModels<UserViewModel>()

    lateinit var activitiesdone: TextView
    lateinit var achievements: TextView
    lateinit var points: TextView
    lateinit var level: TextView
    lateinit var objectives: TextView
    lateinit var interestcategories: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v: View = inflater.inflate(R.layout.fragment_esportives, container, false)
        activitiesdone = v.findViewById(R.id.NumActivitats_bd)
        achievements = v.findViewById(R.id.Assoliments_bd)
        points = v.findViewById(R.id.Punts_bd)
        level = v.findViewById(R.id.Nivell_bd)
        objectives = v.findViewById(R.id.Objectiu_bd)
        interestcategories = v.findViewById(R.id.CategoriesInterès_bd)
        viewModel.user.observe(viewLifecycleOwner, Observer {
            if (it.status == Status.SUCCESS)
                setUpData(it.data)
        })

        return v
    }

    fun setUpData(userData: User?) {
        activitiesdone.text = userData?.activitiesdone.toString()
        achievements.text = userData?.achievements
        points.text = userData?.points.toString()
        level.text = userData?.level.toString()
        objectives.text = userData?.objectives.toString()
        interestcategories.text = userData?.interestcategories
    }

}