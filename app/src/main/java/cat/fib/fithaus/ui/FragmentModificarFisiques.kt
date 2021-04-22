package cat.fib.fithaus.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import cat.fib.fithaus.R
import cat.fib.fithaus.models.User
import cat.fib.fithaus.models.UserModelView

class FragmentModificarFisiques : Fragment(R.layout.fragment_modificar_fisiques) {

    lateinit var weight: EditText
    lateinit var height: EditText
    lateinit var imc: TextView
    lateinit var igc: TextView
    lateinit var historic: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v: View = inflater.inflate(R.layout.fragment_modificar_fisiques, container, false)
        weight = v.findViewById(R.id.PesIntroduit)
        height = v.findViewById(R.id.AlcadaIntroduida)
        imc = v.findViewById(R.id.imctotal)
        igc = v.findViewById(R.id.igctotal)
        historic = v.findViewById(R.id.historictotal)
        if(UserModelView.user != null)
            setUpData(UserModelView.getUser())
        return v
    }

    fun setUpData(userData: User) {
        weight.hint = userData.weight.toString()
        height.hint = userData.height.toString()
        imc.text = userData.imc.toString()
        igc.text = userData.igc.toString()
        historic.text = userData.historic.toString()
    }

}