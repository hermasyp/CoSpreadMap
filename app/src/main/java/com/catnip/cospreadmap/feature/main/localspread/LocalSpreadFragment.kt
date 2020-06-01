package com.catnip.cospreadmap.feature.main.localspread

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.catnip.cateringlist.utils.result.ResultState
import com.catnip.cospreadmap.R
import com.catnip.cospreadmap.data.model.total.local.IndonesiaCount
import com.catnip.cospreadmap.ui.adapter.LocalSpreadListAdapter
import com.catnip.cospreadmap.utils.ext.fromFormattedToNumber
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import kotlinx.android.synthetic.main.fragment_local.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class LocalSpreadFragment : Fragment() {

    private val localSpreadViewModel: LocalSpreadViewModel by viewModel()
    private val TAG = LocalSpreadFragment::class.java.simpleName
    private lateinit var listAdapter: LocalSpreadListAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_local, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupChart()
        listAdapter = LocalSpreadListAdapter {
            Toast.makeText(context, it.province.name, Toast.LENGTH_SHORT).show()
        }
        rv_local_spread?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            setHasFixedSize(true)
        }
        localSpreadViewModel.localData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is ResultState.Progress -> {
                    Log.d(TAG, "On progress")
                }
                is ResultState.Success -> {
                    it.data.spreadData?.let { list ->
                        listAdapter.items = list
                    }
                    it.data.spreadCount?.let {spCount ->
                        setChartData(spCount)
                    }
                }
                is ResultState.Error -> {
                    Log.d(TAG, "Error")
                }
            }
        })
        localSpreadViewModel.getData()
    }

    private fun setupChart() {
        chart_view?.apply {
            description.isEnabled = true
            isDrawHoleEnabled = true
            isHighlightPerTapEnabled = true
            setUsePercentValues(true)
            setHoleColor(R.color.md_white_1000)
            setEntryLabelColor(R.color.md_white_1000)
            setExtraOffsets(5f, 10f, 5F, 5F)
            setEntryLabelTextSize(12f)
            holeRadius = 58F
            transparentCircleRadius = 61F

        }
        val legend = chart_view?.legend
        legend?.apply {
            verticalAlignment = Legend.LegendVerticalAlignment.TOP
            horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
            orientation = Legend.LegendOrientation.VERTICAL
            setDrawInside(false)
            xEntrySpace = 7f
            yEntrySpace = 0f
            yOffset = 0f
        }
    }

    private fun setChartData(data: IndonesiaCount) {
        val pos = data.positive?.fromFormattedToNumber()!!
        val cur = data.cured?.fromFormattedToNumber()!!
        val dth = data.death?.fromFormattedToNumber()!!

        val pieEntries = arrayListOf<PieEntry>()
        pieEntries.add(PieEntry(pos.toFloat(), "Positive"))
        pieEntries.add(PieEntry(cur.toFloat(), "Cured"))
        pieEntries.add(PieEntry(dth.toFloat(), "Death"))

        val dataSet = PieDataSet(pieEntries, "Indonesia")
        val colors = intArrayOf(R.color.md_red_500, R.color.md_green_500, R.color.md_grey_500)
        dataSet.setColors(colors,context)
        val data = PieData(dataSet)
        chart_view?.let {
            data.setValueFormatter(PercentFormatter(chart_view))
        }
        data.setValueTextSize(11f)
        data.setValueTextColor(R.color.md_white_1000)
        chart_view?.data = data
        chart_view?.highlightValue(null)
        chart_view?.invalidate()
    }


}
