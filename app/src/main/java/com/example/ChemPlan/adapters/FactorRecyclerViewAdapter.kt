package com.example.ChemPlan.adapters
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.example.ChemPlan.data.Factor
import com.example.ChemPlan.databinding.FactorItemBinding

class FactorRecyclerViewAdapter(var factors: List<Factor>) : RecyclerView.Adapter<FactorRecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FactorItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = factors[position]
        holder.factor.text = item.name;
    }

    override fun getItemCount(): Int = factors.size

    class ViewHolder(binding: FactorItemBinding) : RecyclerView.ViewHolder(binding.root) {
        var factor: TextView = binding.factor;

        override fun toString(): String {
            return super.toString() + " '" //+ contentView.text + "'"
        }
    }

}