package com.catnip.cospreadmap.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.catnip.cospreadmap.R
import com.catnip.cospreadmap.data.model.spread.local.LocalSpreadWrapper
import kotlinx.android.synthetic.main.item_local_spread.view.*

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class LocalSpreadListAdapter(private val itemClick: (LocalSpreadWrapper) -> Unit) :
    RecyclerView.Adapter<LocalSpreadListAdapter.LocalSpreadViewHolder>() {
    var items: List<LocalSpreadWrapper> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        LocalSpreadViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_local_spread, parent, false),
            itemClick
        )


    override fun onBindViewHolder(holder: LocalSpreadViewHolder, position: Int) {
        holder.bindView(items[position])
    }

    override fun getItemCount(): Int = items.size

    class LocalSpreadViewHolder(view: View, val itemClick: (LocalSpreadWrapper) -> Unit) :
        RecyclerView.ViewHolder(view) {
        fun bindView(item: LocalSpreadWrapper) {
            with(item) {
                itemView.setOnClickListener { itemClick(this) }
                itemView.tv_province_name?.text = this.province.name
                itemView.tv_positive_count?.text = this.localSpread?.positive.toString()
                itemView.tv_cured_count?.text = this.localSpread?.cured.toString()
                itemView.tv_death_count?.text = this.localSpread?.death.toString()
            }

        }
    }

}