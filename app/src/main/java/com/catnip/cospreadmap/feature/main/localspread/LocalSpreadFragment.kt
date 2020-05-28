package com.catnip.cospreadmap.feature.main.localspread

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.catnip.cateringlist.utils.result.ResultState
import com.catnip.cospreadmap.R
import org.koin.androidx.viewmodel.ext.android.viewModel


class LocalSpreadFragment : Fragment() {

    private val localSpreadViewModel: LocalSpreadViewModel by viewModel()
    private val TAG = LocalSpreadFragment::class.java.simpleName


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_local, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        localSpreadViewModel.localData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is ResultState.Progress -> {
                    Log.d(TAG, "On progress")
                }
                is ResultState.Success -> {

                }
                is ResultState.Error -> {
                    Log.d(TAG, "Error")
                }
            }
        })
        localSpreadViewModel.getData()
    }

}
