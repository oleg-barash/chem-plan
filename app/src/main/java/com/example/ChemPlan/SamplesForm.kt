package com.example.ChemPlan

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Environment.*
import android.provider.CalendarContract.Colors
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TableRow
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.ChemPlan.adapters.FactorRecyclerViewAdapter
import com.example.ChemPlan.adapters.ItemRecyclerViewAdapter
import com.example.ChemPlan.data.Factor
import com.example.ChemPlan.data.Sample
import com.example.ChemPlan.databinding.SampleFormBinding
import org.apache.poi.ss.usermodel.Color
import org.apache.poi.ss.usermodel.FillPatternType
import org.apache.poi.ss.usermodel.IndexedColors
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileOutputStream
import java.nio.file.Paths.*


class SamplesForm : Fragment() {

    private var _binding: SampleFormBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.sample_form, container, false);
        var sampleList = view.findViewById<RecyclerView>(R.id.samples);
        var sampleListAdapter = ItemRecyclerViewAdapter(Sample.Items);
        sampleList.adapter = sampleListAdapter;
        var factorList = view.findViewById<RecyclerView>(R.id.factor_list);
        factorList.adapter = FactorRecyclerViewAdapter(Factor.Items)
        _binding = SampleFormBinding.inflate(inflater, container, false)
        var randomButton = view.findViewById<Button>(R.id.button_random);
        randomButton.setOnClickListener {
            Sample.Items.shuffle();
            sampleListAdapter.notifyDataSetChanged()
        }
        var backButton = view.findViewById<Button>(R.id.button_back);
        backButton.setOnClickListener {
            findNavController().navigate(R.id.action_ResultsFragment_to_FirstFragment)
        }

        var buttonExport = view.findViewById<Button>(R.id.button_export);
        buttonExport.setOnClickListener {
            val workbook = XSSFWorkbook();
            val sheet: XSSFSheet = workbook.createSheet("Experiment results")
            var rownum = 0

            var header = Sample.Items.first()
            val headerRow: Row = sheet.createRow(rownum++)
            var headerStyle = workbook.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.BLUE_GREY.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            var cellnum = 0
            val cell = headerRow.createCell(cellnum++)
            cell.setCellValue("№")
            cell.cellStyle = headerStyle
            for (item in header.samples) {
                val headerCell = headerRow.createCell(cellnum++)
                headerCell.setCellValue(item.factorName)
                headerCell.cellStyle = headerStyle
            }
            for (sample in Sample.Items) {
                var viewItem = sampleList.get(rownum-1)
                val row: Row = sheet.createRow(rownum++)
                var rowCellNum = 0
                val numCell = row.createCell(rowCellNum++)
                numCell.setCellValue((rownum-1).toString())
                for (item in sample.samples) {
                    val rowCell = row.createCell(rowCellNum++)
                    rowCell.setCellValue(item.impact)
                }
                val resultCell = row.createCell(rowCellNum++)
                var res = ((viewItem as TableRow).getVirtualChildAt(2) as AppCompatEditText).text.toString()
                resultCell.setCellValue(res)
            }

            try {
                val dir = File(getExternalStorageDirectory().toString() + "/Download")
                dir.mkdirs()
                var externalPath = dir.path + "/Experiment_results.xlsx"
                var file = File(externalPath)
                file.createNewFile()
                val out = FileOutputStream(file)
                workbook.write(out)

                out.close()
                println(
                    "Experiment_results.xlsx written successfully on disk."
                )
            }
            catch (e: Exception) {
                e.printStackTrace()
            }
        }

        return view
    }
}