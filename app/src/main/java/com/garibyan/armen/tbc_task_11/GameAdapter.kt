package com.garibyan.armen.tbc_task_11

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.garibyan.armen.tbc_task_11.databinding.FootballItemBinding
import com.garibyan.armen.tbc_task_11.databinding.HockeyItemBinding

class GameAdapter : ListAdapter<Game, RecyclerView.ViewHolder>(UserCallBack()) {

    var onDeleteClickListener: ((Game) -> Unit)? = null
    var onEditClickListener: ((Int) -> Unit)? = null

    inner class FootballViewHolder(private val binding: FootballItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(game: Game) = with(binding) {
            tvFirstTeam.text = game.firstTeam
            tvFirstTeamScore.text = game.firstTeamScore.toString()
            tvSecondTeam.text = game.secondTeam
            tvSecondTeamScore.text = game.secondTeamScore.toString()
            btnDelete.setOnClickListener { onDeleteClickListener?.invoke(game) }
            btnEdit.setOnClickListener { onEditClickListener?.invoke(adapterPosition) }
        }
    }

    inner class HockeyViewHolder(private val binding: HockeyItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(game: Game) = with(binding) {
            tvFirstTeam.text = game.firstTeam
            tvFirstTeamScore.text = game.firstTeamScore.toString()
            tvSecondTeam.text = game.secondTeam
            tvSecondTeamScore.text = game.secondTeamScore.toString()
            btnDelete.setOnClickListener { onDeleteClickListener?.invoke(game) }
            btnEdit.setOnClickListener { onEditClickListener?.invoke(adapterPosition) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ViewTypes.FOOTBALL -> FootballViewHolder(
                FootballItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            else -> HockeyViewHolder(
                HockeyItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is FootballViewHolder -> holder.bind(getItem(position))
            is HockeyViewHolder -> holder.bind(getItem(position))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).gameType
    }

    class UserCallBack : DiffUtil.ItemCallback<Game>() {
        override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean {
            return oldItem == newItem
        }
    }
}