package com.example.trungqltc.ThongKe;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.trungqltc.R;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class PieChart extends AppCompatActivity {
    TextView id1,id2;
    com.github.mikephil.charting.charts.PieChart pieChart;
    DecimalFormat integerFormatter = new DecimalFormat("#,###.###", new DecimalFormatSymbols(Locale.US));



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pie_chart);
        pieChart = findViewById(R.id.piechar);
        id1 = findViewById(R.id.id1);
        id2 = findViewById(R.id.id2);

        pieChart.setRotationEnabled(true);
        pieChart.setHoleRadius(35f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText("Thong Ke");
        pieChart.setCenterTextSize(20);
        pieChart.setEntryLabelTextSize(20);
        pieChart.setTransparentCircleRadius(30f);
        pieChart.setDrawEntryLabels(true);


         Intent intent = getIntent();
         int tongthu = intent.getIntExtra("thu",0);
         int tongchi = intent.getIntExtra("chi",0);
         int sum = tongchi+tongthu;
         float phantram1 = (tongthu/sum);

        id1.setText("THU: "+String.valueOf(integerFormatter.format(tongthu))+ "vnđ");
        id2.setText("CHI: "+String.valueOf(integerFormatter.format(tongchi))+ "vnđ");


        List<PieEntry> value = new ArrayList<>();
        value.add(new PieEntry(tongthu,"Tổng thu"));
        value.add(new PieEntry(tongchi,"Tổng chi"));

        PieDataSet pieDataset = new PieDataSet(value,"");
        PieData pieData1 = new PieData(pieDataset);
        pieChart.setData(pieData1);
        pieDataset.setValueTextSize(20);
        pieDataset.setValueTextColor(Color.BLUE);

        ArrayList<Integer> colors=new ArrayList<>();
        colors.add(Color.GREEN);
        colors.add(Color.RED);

        pieDataset.setColors(colors);
        pieChart.animateXY(1400,1400);


     }

}
