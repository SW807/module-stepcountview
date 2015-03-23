package dk.aau.cs.psylog.view.stepcountview;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.listener.OnChartGestureListener;

/**
 * Created by Praetorian on 20-03-2015.
 */
public class BarChartView extends View implements OnChartGestureListener {

    private BarChart chart;
    public BarChartView(Context context) {
        super(context);
        chart = new StepCountChart(context);
        chart.setDescription("");
        chart.setOnChartGestureListener(this);

    }

    @Override
    public void onChartLongPressed(MotionEvent me) {
        Log.i("LongPress", "Chart longpressed.");
    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {
        Log.i("DoubleTap", "Chart double-tapped.");
    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {
        Log.i("SingleTap", "Chart single-tapped.");
    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
        Log.i("Fling", "Chart flinged. VeloX: " + velocityX + ", VeloY: " + velocityY);
    }
}
