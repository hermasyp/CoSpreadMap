package com.catnip.cospreadmap.feature.main.globalspread

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.catnip.cateringlist.utils.result.ResultState
import com.catnip.cospreadmap.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class GlobalSpreadFragment : Fragment() {

    private val globalSpreadViewModel: GlobalSpreadViewModel by viewModel()
    private val TAG = GlobalSpreadFragment::class.java.simpleName

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_local, container, false)}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        globalSpreadViewModel.globalData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is ResultState.Progress -> {
                    Log.d(TAG, "On progress")
                }
                is ResultState.Success -> {
                    Log.d(TAG, it.data.toString())
                }
                is ResultState.Error -> {
                    Log.d(TAG, "Error")
                }
            }
        })
        globalSpreadViewModel.getData()
    }
}
