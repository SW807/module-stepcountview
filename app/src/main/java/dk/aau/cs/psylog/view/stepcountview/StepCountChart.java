package dk.aau.cs.psylog.view.stepcountview;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import dk.aau.cs.psylog.module_lib.DBAccessContract;

/**
 * Created by Praetorian on 20-03-2015.
 */
public class StepCountChart extends BarChart {

    private ContentResolver contentResolver;
    private Uri uri;

    public StepCountChart(Context context) {
        super(context);
        uri = Uri.parse(DBAccessContract.DBACCESS_CONTENTPROVIDER + "STEPCOUNTER_steps");
        this.setData(getDBData());
        this.buildLayer();

    }


    public BarData getDBData()
    {
        ArrayList<String> xaxis = new ArrayList<>();
        ArrayList<BarEntry> yaxis = new ArrayList<>();

        Cursor cursor = contentResolver.query(uri, new String[]{"stepcount", "time"}, null, null, null);
        int i = 0;
        if(cursor.moveToFirst()) {
            do {
                int stepCount = cursor.getInt(cursor.getColumnIndex("stepcount"));
                String time = cursor.getString(cursor.getColumnIndex("time"));
                xaxis.add(time);
                yaxis.add(new BarEntry(stepCount, i));
                i++;
            }
            while (cursor.moveToNext());

        }
        ContentResolver cr = this.getContext().getContentResolver();
        BarDataSet d = new BarDataSet(yaxis, "stepcount dataset");
        d.setBarSpacePercent(20f);
        d.setColors(ColorTemplate.VORDIPLOM_COLORS);
        d.setBarShadowColor(Color.rgb(203, 203, 203));
        ArrayList<BarDataSet> sets = new ArrayList<BarDataSet>();
        sets.add(d);
        return new BarData(xaxis, sets);
    }
}
