package dk.aau.cs.psylog.view.stepcountview;

import android.app.ActionBar;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.view.ViewGroup;

import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import dk.aau.cs.psylog.module_lib.DBAccessContract;
import lecho.lib.hellocharts.formatter.AxisValueFormatter;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * Created by Praetorian on 23-03-2015.
 */
public class HelloChartStepView extends LineChartView {

    ContentResolver contentResolver;
    public HelloChartStepView(Context context) {
        super(context);
        contentResolver = context.getContentResolver();
        this.setInteractive(true);
        this.setZoomType(ZoomType.HORIZONTAL);
/*
        List<PointValue> values = new ArrayList<PointValue>();
        values.add(new PointValue(0, 2));
        values.add(new PointValue(1, 4));
        values.add(new PointValue(2, 3));
        values.add(new PointValue(3, 4));*/

        //In most cased you can call data model methods in builder-pattern-like manner.




      //  LineChartView chart = new LineChartView(context);
    //    chart.setLineChartData(data);
        this.setLineChartData(setupChart());
    }

    public LineChartData setupChart() {
        Line line = new Line(getDBData()).setColor(Color.BLUE).setCubic(true);
        List<Line> lines = new ArrayList<Line>();
        lines.add(line);

        LineChartData data = new LineChartData();
        data.setBaseValue(Float.NEGATIVE_INFINITY);
        data.setLines(lines);
        List<AxisValue> axisValues = new ArrayList<AxisValue>();
        int i = 0;
        for(PointValue p : line.getValues()) {
            if(i % 5 == 0) {
                String label = String.valueOf(p.getLabelAsChars());
                axisValues.add(new AxisValue(i).setLabel(label));
            }

            i++;
        }
        Axis axisX = new Axis(axisValues).setHasLines(true);
        axisX.setTextSize(8);

        Axis axisY = new Axis().setHasLines(true);
        axisX.setName("Tid");
        axisY.setName("Skridt Antal");
        data.setAxisXBottom(axisX);
        data.setAxisYLeft(axisY);

        return data;
    }

    public ArrayList<PointValue> getDBData()
    {
        try {
            ArrayList<PointValue> returnList = new ArrayList<>();
            Uri uri = Uri.parse(DBAccessContract.DBACCESS_CONTENTPROVIDER + "STEPCOUNTER_steps");

            Cursor cursor = contentResolver.query(uri, new String[]{"stepcount", "time"}, null, null, null);
            int i = 0;

            if (cursor.moveToFirst()) {
                do {
                    int stepCount = cursor.getInt(cursor.getColumnIndex("stepcount"));
                    String time = cursor.getString(cursor.getColumnIndex("time"));
                    returnList.add(new PointValue(i, stepCount).setLabel(time));
                    i++;
                }
                while (cursor.moveToNext());

            }
            return returnList;
        }
        catch (Exception e)
        {
            return new ArrayList<>();
        }
    }
}
