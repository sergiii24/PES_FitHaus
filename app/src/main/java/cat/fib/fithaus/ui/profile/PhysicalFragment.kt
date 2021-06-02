package cat.fib.fithaus.ui.profile

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import cat.fib.fithaus.R
import cat.fib.fithaus.data.models.User
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.LegendRenderer
import com.jjoe64.graphview.series.BarGraphSeries
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass.
 * Use the [PhysicalFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class PhysicalFragment : Fragment(R.layout.fragment_physical) {

    //private val viewModel by viewModels<UserViewModel>()

    lateinit var weight: TextView
    lateinit var height: TextView
    lateinit var imc: TextView
    lateinit var igc: TextView
    lateinit var updated: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v: View = inflater.inflate(R.layout.fragment_physical, container, false)
        weight = v.findViewById(R.id.Pes)
        height = v.findViewById(R.id.Alçada)
        imc = v.findViewById(R.id.IMC)
        igc = v.findViewById(R.id.IGC)
        createExampleGraph(v)
        //updated = v.findViewById(R.id.Historic)
        /*viewModel.user.observe(viewLifecycleOwner, Observer {
            if (it.status == Status.SUCCESS)
                setUpData(it.data)
        }) */
        return v
    }

    fun setUpData(userData: User?) {
        weight.text = userData?.weight.toString()
        height.text = userData?.height.toString()
        imc.text = userData?.imc.toString()
        igc.text = userData?.igc.toString()
        //updated.text = userData?.updated.toString()
    }

    fun createExampleGraph(view: View) {
        val graph: GraphView = view.findViewById(R.id.graph)
        val series1: LineGraphSeries<DataPoint> = LineGraphSeries<DataPoint>( arrayOf(
            DataPoint(0.0, -2.0),
            DataPoint(1.0, 5.0),
            DataPoint(2.0, 3.0),
            DataPoint(3.0, 2.0),
            DataPoint(4.0, 6.0)
        ))
        series1.color = Color.DKGRAY
        graph.addSeries(series1)

        val series2: LineGraphSeries<DataPoint> = LineGraphSeries<DataPoint>( arrayOf(
            DataPoint(0.0, 3.0),
            DataPoint(1.0, 3.0),
            DataPoint(2.0, 6.0),
            DataPoint(3.0, 2.0),
            DataPoint(4.0, 5.0)
        ))
        series2.color = Color.LTGRAY
        graph.addSeries(series2)

        series1.title = "Pes"
        series2.title = "Alçada"
        graph.legendRenderer.isVisible = true
        graph.legendRenderer.align = LegendRenderer.LegendAlign.BOTTOM
    }

}