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
import com.catnip.cospreadmap.data.model.spread.local.LocalSpreadWrapper
import com.catnip.cospreadmap.data.model.total.local.IndonesiaCount
import com.catnip.cospreadmap.ui.adapter.LocalSpreadListAdapter
import com.github.mikephil.charting.components.Legend
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
    private fun setChartData(data : IndonesiaCount){

    }


}
