package com.example.ChemPlan.data

class Factor(var name: String, var minVal: String, var midVal: String, var maxVal: String){
    companion object {
        var Items: MutableList<Factor> = mutableListOf();
    }
}
