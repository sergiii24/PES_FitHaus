package cat.fib.fithaus.ui.home

import android.app.FragmentManager
import android.content.ClipData
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import cat.fib.fithaus.R
import cat.fib.fithaus.ui.CercarFiltrarClasseFragment
import cat.fib.fithaus.ui.CercarFiltrarExerciciFragment
import cat.fib.fithaus.ui.CercarFiltrarRutinaPredefinidaFragment
import com.google.android.material.bottomnavigation.BottomNavigationItemView

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    lateinit var botoRutines: Button
    lateinit var botoExercicis: Button
    lateinit var botoClasses: Button
    lateinit var boto: ClipData.Item

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        botoRutines = root.findViewById(R.id.button11)
        botoExercicis = root.findViewById(R.id.button12)
        botoClasses = root.findViewById(R.id.button13)

        setUpRutinesButton()
        setUpExercicisButton()
        setUpClassesButton()


        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    private fun setUpRutinesButton() {
        botoRutines.setOnClickListener {
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.nav_host_fragment, CercarFiltrarRutinaPredefinidaFragment())
                addToBackStack(null)
                commit()
            }
        }
    }

    private fun setUpExercicisButton() {
        botoExercicis.setOnClickListener {
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.nav_host_fragment, CercarFiltrarExerciciFragment())
                addToBackStack(null)
                commit()
            }
        }
    }

    private fun setUpClassesButton() {
        botoClasses.setOnClickListener {
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.nav_host_fragment, CercarFiltrarClasseFragment())
                addToBackStack(null)
                commit()
            }
        }
    }

}