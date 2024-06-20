package com.capstone.recyscan.views.adapters

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.recyscan.data.local.entity.EntityHistory
import com.capstone.recyscan.databinding.ItemHistoryBinding
import com.capstone.recyscan.views.ResultActivity

class AdapterItemHistory(
    private val listener: HistoryAction,
) : ListAdapter<EntityHistory, AdapterItemHistory.ListViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            ItemHistoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), listener
        )
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ListViewHolder(val binding: ItemHistoryBinding, private val listener: HistoryAction) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(entityHistory: EntityHistory) {
            entityHistory.apply {
                with(binding) {
                    Glide.with(root).load(image).into(ivHistory)
                    tvHistoryDesc.text = "$scanResult \n $countResult \n"
                    root.setOnClickListener {
                        it.context.startActivity(
                            Intent(
                                it.context,
                                ResultActivity::class.java
                            ).apply {
                                putExtra(ResultActivity.TOOLBAR_TITLE, false)
                                putExtra(ResultActivity.IMAGE_URI_VALUE, image)
                                putExtra(ResultActivity.SCAN_RESULT, scanResult)
                                putExtra(ResultActivity.COUNT_RESULT, countResult)
                                putExtra(ResultActivity.RECOMMEND_RESULT, recommendResult)
                            }
                        )
                    }
                    btnDeleteHistory.setOnClickListener {
                        listener.onDeleteClicked(entityHistory)
                    }
                }
            }

        }

    }


    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<EntityHistory>() {
            override fun areItemsTheSame(
                oldItem: EntityHistory,
                newItem: EntityHistory,
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: EntityHistory,
                newItem: EntityHistory,
            ): Boolean = oldItem == newItem
        }
    }

    interface HistoryAction {
        fun onDeleteClicked(history: EntityHistory)
    }


}