package com.example.sqldemo3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class statisticActivity extends AppCompatActivity {
    private LineChart mChart;
    private TextView first,last;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);

        first = findViewById(R.id.tv_firstyear);
        last = findViewById(R.id.tv_lastyear);

        String str = getIntent().getStringExtra("item");


        Toast.makeText(this, "" + str, Toast.LENGTH_SHORT).show();

        try {
            DataBaseStatistic x = new DataBaseStatistic(this);
            x.cleartable();
            x.csvcopy(this, str);
        } catch (Exception e) {
            Toast.makeText(this, "fehler", Toast.LENGTH_SHORT).show();
        }

        DataBaseStatistic x = new DataBaseStatistic(this);
        ArrayList<ProfileStats> all = x.profileSQL();


        ProfileStats d = all.get(all.size()-1);
        ProfileStats d2 = all.get(0);
        String date = d.getDate();
        String date11 = d2.getDate();
        first.setText(date);
        last.setText(date11);


        Toast.makeText(this, "Groeße:"+date, Toast.LENGTH_SHORT).show();


        mChart = ((LineChart) findViewById(R.id.linechart));

        mChart.setDragEnabled(true);
        mChart.getXAxis().setEnabled(false);
        ArrayList<Entry> yValues = new ArrayList<>();
        for(int i = 0; i < all.size(); i++){
            ProfileStats c = all.get(i);
            double wert = c.getKilo();

            yValues.add(new Entry(i, (float) wert));

        }


        LineDataSet set1 = new LineDataSet(yValues, "BENCHPRESS");
        set1.setFillAlpha(110);
        set1.setCircleColor(Color.RED);
        set1.setLineWidth(8f);
        set1.setValueTextSize(10f);


        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        LineData data = new LineData(dataSets);
        mChart.setData(data);



    }







}
