package com.example.ChemPlan.data

class SampleItem(var factorName: String, var impact: String){}

class Sample(var samples: List<SampleItem> = listOf()){
    companion object {
        const val min: String = "min";
        const val max: String = "max";
        const val mid: String = "mid";
        var matrixMap: Map<Int, List<List<String>>> = mapOf(
            1 to listOf(
                listOf(min), listOf(mid), listOf(max)),
            2 to listOf(
                listOf(min, min), listOf(min, mid), listOf(min, max)
                , listOf(mid, min), listOf(mid, mid), listOf(mid, max)
                , listOf(max, min), listOf(max, mid), listOf(max, max)
            ),
            3 to listOf(
                listOf(min, min, mid), listOf(max, min, mid), listOf(min, max, mid), listOf(max, max, mid)
                , listOf(min, mid, min), listOf(max, mid, min), listOf(min, mid, max), listOf(max, mid, max)
                , listOf(mid, min, min), listOf(mid, max, min), listOf(mid, min, max), listOf(mid, max, max)
                , listOf(mid, mid, mid), listOf(mid, mid, mid), listOf(mid, mid, mid)
            ),
            4 to listOf(
                listOf(min, min, mid, mid), listOf(max, min, mid, mid), listOf(min, max, mid, mid), listOf(max, max, mid, mid)
                , listOf(mid, mid, min, min), listOf(mid, mid, max, min), listOf(mid, mid, min, max), listOf(mid, mid, max, max)
                , listOf(mid, mid, mid, mid)
                , listOf(min, mid, mid, min), listOf(max, mid, mid, min), listOf(min, mid, mid, max), listOf(max, mid, mid, max)
                , listOf(mid, min, min, mid), listOf(mid, max, min, mid), listOf(mid, min, max, mid), listOf(mid, max, max, mid)
                , listOf(mid, mid, mid, mid)
                , listOf(min, mid, min, mid), listOf(max, mid, min, mid), listOf(min, mid, max, mid), listOf(max, mid, max, mid)
                , listOf(mid, min, mid, min), listOf(mid, max, mid, min), listOf(mid, min, mid, max), listOf(mid, max, mid, max)
                , listOf(mid, mid, mid, mid)
            ),
        )
        var OriginalItems: MutableList<Sample> = mutableListOf();
        var Items: MutableList<Sample> = mutableListOf();
//        fun FillFactors(factors: List<Factor>): List<List<SampleItem>> {
//            if (factors.isEmpty()) { return emptyList() }
//            var currentFactor = factors.first();
//            val array: MutableList<List<SampleItem>> = mutableListOf();
//            var nextItems = FillFactors(factors.drop(1));
//            for (i in currentFactor.minVal..currentFactor.maxVal){
//                var currentSample = SampleItem(currentFactor.name, i);
//                if (nextItems.isNotEmpty()){
//                    for (x in nextItems) {
//                        array.add(listOf(currentSample).plus(x));
//                    }
//                } else {
//                    array.add(listOf(currentSample));
//                }
//            }
//            return array;
//        }

        fun FillFactors(factors: List<Factor>): List<List<SampleItem>> {
            var matrix = matrixMap[factors.size]!!.toMutableList();
            var result: MutableList<MutableList<SampleItem>> = mutableListOf();
            for (row in matrix){
                var elements: MutableList<SampleItem> = mutableListOf();
                for (factorIndex in factors.indices){
                    var col = row[factorIndex];
                    var factor = factors[factorIndex];
                    when(col) {
                        min -> elements.add(SampleItem(factor.name, factor.minVal));
                        mid -> elements.add(SampleItem(factor.name, factor.midVal));
                        max -> elements.add(SampleItem(factor.name, factor.maxVal));
                    }
                }
                result.add(elements);
            }
            return result;
        }

    }
}
