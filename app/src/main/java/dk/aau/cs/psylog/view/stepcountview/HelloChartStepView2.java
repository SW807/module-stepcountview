package dk.aau.cs.psylog.view.stepcountview;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

import dk.aau.cs.psylog.module_lib.DBAccessContract;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;


public class HelloChartStepView2 extends LineChartView {

    ContentResolver contentResolver;
    public HelloChartStepView2(Context context) {
        super(context);
        contentResolver = context.getContentResolver();
        this.setInteractive(true);
        this.setZoomType(ZoomType.HORIZONTAL);
        this.setLineChartData(setupChart());
    }

    public LineChartData setupChart() {
        Line line = new Line(getDBData()).setColor(Color.BLUE).setCubic(false);
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
            final int blockSize = 5;
            int index = 0;
            if (cursor.moveToFirst()) {
                int currentBlock = 0;
                do {
                    int stepCount = cursor.getInt(cursor.getColumnIndex("stepcount"));
                    String time = cursor.getString(cursor.getColumnIndex("time"));
                    currentBlock += stepCount;
                    if(i % blockSize == 0) {
                        returnList.add(new PointValue(index++, currentBlock).setLabel(time));
                        currentBlock = 0;
                    }
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
