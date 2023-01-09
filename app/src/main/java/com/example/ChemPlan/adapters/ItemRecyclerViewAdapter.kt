package com.example.ChemPlan.adapters
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ChemPlan.data.Sample
import com.example.ChemPlan.databinding.SampleListBinding

class ItemRecyclerViewAdapter(var items: MutableList<Sample>) : RecyclerView.Adapter<ItemRecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            SampleListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.number.text = (position + 1).toString();
        val subItemRecyclerViewAdapter = RowRecyclerViewAdapter(item.samples)
        holder.sampleItems.layoutManager = LinearLayoutManager(holder.itemView.context, LinearLayoutManager.HORIZONTAL,false)
        holder.sampleItems.adapter = subItemRecyclerViewAdapter
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(binding: SampleListBinding) : RecyclerView.ViewHolder(binding.root) {
        val number: TextView = binding.number;
        var sampleItems: RecyclerView = binding.factorList;

        override fun toString(): String {
            return super.toString() + " '" //+ contentView.text + "'"
        }
    }

}