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

import java.util.ArrayList;
import java.util.List;


public class PieChart extends AppCompatActivity {
    TextView id1,id2;
    com.github.mikephil.charting.charts.PieChart pieChart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pie_chart);
        pieChart = findViewById(R.id.piechar);
        id1 = findViewById(R.id.id1);
        id2 = findViewById(R.id.id2);




        Description des = new Description();
        des.setText("biểu đồ thống kê");
        des.setTextSize(30f);

        pieChart.setRotationEnabled(true);
        pieChart.setHoleRadius(35f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText("PieChart");
        pieChart.setCenterTextSize(20);
        pieChart.setDescription(des);
        pieChart.setTransparentCircleRadius(50f);
        pieChart.setDrawEntryLabels(true);


         Intent intent = getIntent();
         int tongthu = intent.getIntExtra("thu",0);
         int tongchi = intent.getIntExtra("chi",0);
         int sum = tongchi+tongthu;
         float phantram1 = (tongthu/sum);

         id1.setText("THU: "+tongthu+ "vnđ");
         id2.setText("CHI: "+tongchi+"vnđ");

        List<PieEntry> value = new ArrayList<>();
        value.add(new PieEntry(tongthu,"Tổng thu"));
        value.add(new PieEntry(tongchi,"Tổng chi"));

        PieDataSet pieDataset = new PieDataSet(value,"Thống kê");
        PieData pieData1 = new PieData(pieDataset);
        pieChart.setData(pieData1);

        ArrayList<Integer> colors=new ArrayList<>();
        colors.add(Color.CYAN);
        colors.add(Color.MAGENTA);

        pieDataset.setColors(colors);
        pieChart.animateXY(1400,1400);


     }

}
