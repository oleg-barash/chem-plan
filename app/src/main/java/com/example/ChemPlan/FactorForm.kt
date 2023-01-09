package com.example.ChemPlan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.ChemPlan.data.Factor
import com.example.ChemPlan.data.Sample
import com.example.ChemPlan.data.Sample.Companion.FillFactors
import com.example.ChemPlan.databinding.FragmentSecondBinding

class FactorForm : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    private val binding get() = _binding!!
    private var totalFactors = 0;

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        Factor.Items.clear();
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            val requiredFactors = requireArguments().getInt("factorsCount")
            Factor.Items.add(
                Factor(this.binding.factorName.text.toString(),
                this.binding.minFactor.text.toString(),
                this.binding.middleFactor.text.toString(),
                this.binding.maxFactor.text.toString()
            ))
            this.binding.minFactor.setText("");
            this.binding.minFactor.setText("");
            this.binding.middleFactor.setText("");
            this.binding.maxFactor.setText("");
            totalFactors ++;
            if (totalFactors >= requiredFactors) {
                Sample.OriginalItems.clear();
                val items = FillFactors(Factor.Items);
                for (i in items) {
                    Sample.OriginalItems.add(Sample(i));
                }
                Sample.Items = Sample.OriginalItems;
                findNavController().navigate(R.id.action_SecondFragment_to_ResultsFragment)
            }
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}