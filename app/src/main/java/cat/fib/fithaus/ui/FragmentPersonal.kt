package cat.fib.fithaus.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import cat.fib.fithaus.R
import cat.fib.fithaus.data.models.User
import cat.fib.fithaus.utils.Status
import cat.fib.fithaus.viewmodels.UserViewModel
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint


/**
 * A simple [Fragment] subclass.
 * Use the [FragmentPersonal.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentPersonal : Fragment(R.layout.fragment_personals) {

    private val viewModel by viewModels<UserViewModel>()

    lateinit var nom_bd: TextView
    lateinit var usuari: TextView
    lateinit var mail: TextView
    lateinit var sexe: TextView
    lateinit var data: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v: View = inflater.inflate(R.layout.fragment_personals, container, false)
        nom_bd = v.findViewById(R.id.Nom_bd)
        usuari = v.findViewById(R.id.Usuari_bd)
        mail = v.findViewById(R.id.Mail_bd)
        sexe = v.findViewById(R.id.Sexe_bd)
        data = v.findViewById(R.id.DataNaixement_bd)
        viewModel.user.observe(viewLifecycleOwner, Observer {
            if (it.status == Status.SUCCESS)
                setUpPersonal(it.data)
        })
        return v
    }

    fun setUpPersonal(userData: User?) {
        nom_bd.text = userData?.firstname.plus(" ").plus(userData?.lastname)
        usuari.text = userData?.username
        mail.text = userData?.email
        sexe.text = userData?.gender
        data.text = userData?.birthdate.toString()
    }


}