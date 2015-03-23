package dk.aau.cs.psylog.view.stepcountview;

import android.content.Context;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.util.AttributeSet;

import com.db.chart.Tools;
import com.db.chart.model.ChartSet;
import com.db.chart.model.LineSet;
import com.db.chart.model.Point;
import com.db.chart.view.LineChartView;
import com.db.chart.view.XController;
import com.db.chart.view.YController;
import com.db.chart.view.animation.style.DashAnimation;

import java.text.DecimalFormat;

/**
 * Created by Praetorian on 20-03-2015.
 */
public class WilliamStep extends LineChartView {

    public WilliamStep(Context context) {
        super(context, null);

        LineSet set = new LineSet();
        set.addPoint(new Point("p1", 1.0f));
        set.addPoint(new Point("p2", 2.0f));
        set.addPoint(new Point("p1", 1.0f));
        set.addPoint(new Point("p2", 2.0f));
        set.addPoint(new Point("p1", 1.0f));
        set.addPoint(new Point("p2", 2.0f));
        set.addPoint(new Point("p1", 1.0f));
        set.addPoint(new Point("p2", 2.0f));
        set.addPoint(new Point("p1", 1.0f));
        set.addPoint(new Point("p2", 2.0f));
        set.addPoint(new Point("p1", 1.0f));
        set.addPoint(new Point("p2", 2.0f));
        set.addPoint(new Point("p1", 1.0f));
        set.addPoint(new Point("p2", 2.0f));
        set.addPoint(new Point("p1", 1.0f));
        set.addPoint(new Point("p2", 2.0f));
        set.addPoint(new Point("p1", 1.0f));
        set.addPoint(new Point("p2", 2.0f));
        set.setLineColor(context.getResources().getColor(R.color.accent_material_dark))
                .setLineThickness(Tools.fromDpToPx(3))
                .setSmooth(true)
                .setDashed(true);
        this.addData(set);
        Paint mLineGridPaint = new Paint();
        mLineGridPaint.setColor(context.getResources().getColor(R.color.accent_material_light));
        mLineGridPaint.setPathEffect(new DashPathEffect(new float[] {5,5}, 0));
        mLineGridPaint.setStyle(Paint.Style.STROKE);
        mLineGridPaint.setAntiAlias(true);
        mLineGridPaint.setStrokeWidth(Tools.fromDpToPx(.75f));
        this.setBorderSpacing(Tools.fromDpToPx(4))
                .setGrid(LineChartView.GridType.HORIZONTAL,mLineGridPaint)
                .setXAxis(false)
                .setXLabels(XController.LabelPosition.OUTSIDE)
                .setYAxis(false)
                .setYLabels(YController.LabelPosition.OUTSIDE)
                .setAxisBorderValues(-10, 10, 5)
                .setLabelsFormat(new DecimalFormat("##'u'"))
                .show();
        //this.animateSet(1, new DashAnimation());

    }
}