package com.example.ChemPlan.adapters
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.example.ChemPlan.data.SampleItem
import com.example.ChemPlan.databinding.SampleItemBinding

class RowRecyclerViewAdapter(var samples: List<SampleItem>) : RecyclerView.Adapter<RowRecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            SampleItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = samples[position]
        holder.factorImpact.text = item.impact.toString();
    }

    override fun getItemCount(): Int = samples.size

    class ViewHolder(binding: SampleItemBinding) : RecyclerView.ViewHolder(binding.root) {
        var factorImpact: TextView = binding.factorImpact;

        override fun toString(): String {
            return super.toString() + " '" //+ contentView.text + "'"
        }
    }

}