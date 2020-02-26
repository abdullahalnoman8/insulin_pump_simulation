package com.ateam.insulinpumpsimulation.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.ateam.insulinpumpsimulation.R;
import com.ateam.insulinpumpsimulation.utils.Utils;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Random;

import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.start_btn)
    ImageView startButton;
    @BindView(R.id.stop_btn)
    ImageView stopButton;
    @BindView(R.id.edt_blood_glucose)
    EditText insulin;
    @BindView(R.id.machine_status)
    TextView machine_status;
    @BindView(R.id.edt_carbohydrate)
    EditText glucagon;
    @BindView(R.id.frame_layout)
    FrameLayout frameLayout;

    @BindView(R.id.btn_glucagon_push)
    Button btnGlucagon;

    LineGraphSeries<DataPoint> series;
    LineGraphSeries<DataPoint> singleLineSeries;
    private Unbinder unbinder;
    private GraphView graph;
    private Handler graphHandler = new Handler();
    private double lastPoint = 3;
    private double lastPoint2 = 5;
    private Random random = new Random();
    private int low = 85, max = 95;
    private Runnable mTimer;


    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            lastPoint++;
            lastPoint2++;

            series.appendData(new DataPoint(lastPoint, random.nextInt(max - low) + low), false, 100);
            singleLineSeries.appendData(new DataPoint(lastPoint2, 90), false, 100);
            graphHandler.postDelayed(this, 1000);
        }
    });

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);

        startButton.setOnClickListener(this);
        stopButton.setOnClickListener(this);
        btnGlucagon.setOnClickListener(this);

        graph = (GraphView) view.findViewById(R.id.graph);
        series = new LineGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(0, 91),
                new DataPoint(1, 82),
                new DataPoint(2, 91),
                new DataPoint(3, 85),
                new DataPoint(4, 93)
        });

        singleLineSeries = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(0, 90),
                new DataPoint(1, 90),
                new DataPoint(2, 90),
                new DataPoint(3, 90),
                new DataPoint(4, 90),
                new DataPoint(5, 90)
        });

        // Safe zone graph
        graph.addSeries(singleLineSeries);
        singleLineSeries.setDrawDataPoints(true);
//        singleLineSeries.setDrawBackground(true);

        //  Insulin Graph
        series.setDrawDataPoints(true);
        series.setDrawBackground(true);
        graph.addSeries(series);

//        graph.getViewport().setScrollable(true); // enables horizontal scrolling
//        graph.getViewport().setScrollableY(true); // enables vertical scrolling
//        graph.getViewport().setScalable(true); // enables horizontal zooming and scrolling
//        graph.getViewport().setScalableY(true); // enables vertical zooming and scrolling

        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(20);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setScalable(true);


        graph.getViewport().setMaxY(130);
        graph.getViewport().setMinY(60);
//        graph.getViewport().setXAxisBoundsManual(true);


        graph.getGridLabelRenderer().setNumHorizontalLabels(20);
        graph.getGridLabelRenderer().setNumVerticalLabels(5);

        graph.getGridLabelRenderer().setLabelVerticalWidth(100);

        graph.getGridLabelRenderer().setHighlightZeroLines(false);
        graph.getGridLabelRenderer().setHorizontalAxisTitle("Times");
        graph.getGridLabelRenderer().setVerticalAxisTitle("Glucose Level (mg/dl)");

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start_btn:
                Utils.snackbarMessage(frameLayout, "Machine has started!!!", false);
                machine_status.setText("Machine is running...");
                // TODO: 1/30/20 Write method for showing initial Graph Data
                addRandomDataPoint();
                break;
            case R.id.stop_btn:
                Utils.snackbarMessage(frameLayout, "Machine is stopped", false);
                machine_status.setText("Machine is stopped...!!!");
                // TODO: 1/30/20 Stop the real time graph data update
                graphHandler.removeCallbacksAndMessages(mTimer);
                graphHandler.removeCallbacks(mTimer);
                break;
            case R.id.btn_glucagon_push:
                if (glucagon.getText().toString().trim().equals("")) {
                    Utils.snackbarMessage(frameLayout, "Please Enter Glucagon Amount", true);
                } else {
                    // TODO: 2/10/20 Update the data in plot
                }
                break;
        }
    }

    @OnClick(R.id.btn_insulin_push)
    public void onInsulinPushButtonClick() {
        if (insulin.getText().toString().trim().equals("")) {
            Utils.snackbarMessage(frameLayout, "Please Enter the Insulin Amount", true);
        } else {
            // TODO: 2/10/20 update the data in graph
        }
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    private void addRandomDataPoint() {

        mTimer = new Runnable() {
            @Override
            public void run() {
                lastPoint++;
                lastPoint2++;
//
//                Calendar cal = Calendar.getInstance();
//                Date date = cal.getTime();
//
//
//                long millis = System.currentTimeMillis();
//                double x = (new Long(millis)).doubleValue();
//                mCalendar.setTimeInMillis((long) x);
//

                series.appendData(new DataPoint(lastPoint, random.nextInt(max - low) + low), false, 100);
                singleLineSeries.appendData(new DataPoint(lastPoint2, 90), false, 100);
                graphHandler.postDelayed(this, 1000);
//                addRandomDataPoint();
            }
        };
        graphHandler.postDelayed(mTimer, 1500);
    }

    @Override
    public void onPause() {
        graphHandler.removeCallbacks(mTimer);
        super.onPause();
    }
}
