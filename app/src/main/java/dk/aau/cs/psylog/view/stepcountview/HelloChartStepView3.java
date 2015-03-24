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
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.view.ColumnChartView;

/**
 * Created by Praetorian on 23-03-2015.
 */
public class HelloChartStepView3 extends ColumnChartView {

    ContentResolver contentResolver;
    public HelloChartStepView3(Context context) {
        super(context);
        contentResolver = context.getContentResolver();
        this.setInteractive(true);
        this.setZoomType(ZoomType.HORIZONTAL);
        this.setColumnChartData(setupChart());
    }

    public ColumnChartData setupChart() {
        ColumnChartData data = new ColumnChartData();
        data.setBaseValue(Float.NEGATIVE_INFINITY);
        data.setColumns(getDBData());
        List<AxisValue> axisValues = new ArrayList<AxisValue>();
        Axis axisX = new Axis(axisValues).setHasLines(true);
        axisX.setTextSize(8);

        Axis axisY = new Axis().setHasLines(true);
        axisX.setName("Tid");
        axisY.setName("Skridt Antal");
        data.setAxisXBottom(axisX);
        data.setAxisYLeft(axisY);

        return data;
    }

    public ArrayList<Column> getDBData()
    {
        try {
            ArrayList<Column> returnList = new ArrayList<>();
            Uri uri = Uri.parse(DBAccessContract.DBACCESS_CONTENTPROVIDER + "STEPCOUNTER_steps");
            Cursor cursor = contentResolver.query(uri, new String[]{"stepcount", "time"}, null, null, null);
            int i = 0;
            if (cursor.moveToFirst()) {
                do {
                    int stepCount = cursor.getInt(cursor.getColumnIndex("stepcount"));
                    String time = cursor.getString(cursor.getColumnIndex("time"));
                    Column c = new Column(new ArrayList<SubcolumnValue>(stepCount));
                    returnList.add(c);
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
