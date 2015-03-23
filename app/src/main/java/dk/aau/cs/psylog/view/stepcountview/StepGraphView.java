package dk.aau.cs.psylog.view.stepcountview;

import android.content.Context;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

/**
 * Created by Praetorian on 20-03-2015.
 */
public class StepGraphView extends GraphView{
    public StepGraphView(Context context) {
        super(context);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        this.addSeries(series);

    }
}
