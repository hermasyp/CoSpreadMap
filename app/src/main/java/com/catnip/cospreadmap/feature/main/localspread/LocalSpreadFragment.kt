package com.catnip.cospreadmap.feature.main.localspread

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.catnip.cateringlist.utils.result.ResultState
import com.catnip.cospreadmap.R
import com.catnip.cospreadmap.ui.adapter.LocalSpreadListAdapter
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

}
