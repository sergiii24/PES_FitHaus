package cat.fib.fithaus.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import cat.fib.fithaus.R
import cat.fib.fithaus.data.models.User
import cat.fib.fithaus.data.models.UserModelView

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentFisiques.newInstance] factory method to
 * create an instance of this fragment.
 */

class FragmentFisiques : Fragment(R.layout.fragment_fisiques) {

    lateinit var weight: TextView
    lateinit var height: TextView
    lateinit var imc: TextView
    lateinit var igc: TextView
    lateinit var historic: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v: View = inflater.inflate(R.layout.fragment_fisiques, container, false)
        weight = v.findViewById(R.id.Pes_bd)
        height = v.findViewById(R.id.Alçada_bd)
        imc = v.findViewById(R.id.Imc_bd)
        igc = v.findViewById(R.id.Igc_bd)
        historic = v.findViewById(R.id.Històric_bd)
        if(UserModelView.user != null)
            setUpData(UserModelView.getUser())
        return v
    }

    fun setUpData(userData: User) {
        weight.text = userData.weight.toString()
        height.text = userData.height.toString()
        imc.text = userData.imc.toString()
        igc.text = userData.igc.toString()
        historic.text = userData.historic.toString()
    }

}