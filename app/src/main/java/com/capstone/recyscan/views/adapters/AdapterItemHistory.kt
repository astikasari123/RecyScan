package com.capstone.recyscan.views.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.recyscan.data.local.entity.EntityHistory
import com.capstone.recyscan.databinding.ItemHistoryBinding
import com.capstone.recyscan.views.ResultActivity

class AdapterItemHistory(
    private var listHistory: List<EntityHistory>,
    private val context: Context,
) : RecyclerView.Adapter<AdapterItemHistory.ListViewHolder>() {

    class ListViewHolder(val binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            ItemHistoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = listHistory.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        listHistory.reversed()[position].apply {
            with(holder.binding) {
                Glide.with(root).load(image).into(ivHistory)
                tvHistoryDesc.text = desc
                root.setOnClickListener {
                    it.context.startActivity(Intent(it.context, ResultActivity::class.java).apply {
                        putExtra(ResultActivity.TOOLBAR_TITLE, false)
                        putExtra(ResultActivity.IMAGE_URI_VALUE, image)
                    }
                    )
                }
            }
        }
    }
}