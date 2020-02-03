package com.ateam.insulinpumpsimulation.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ateam.insulinpumpsimulation.R;
import com.ateam.insulinpumpsimulation.utils.Utils;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Random;

import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.start_btn)
    ImageView startButton;
    @BindView(R.id.stop_btn)
    ImageView stopButton;
    LineGraphSeries<DataPoint> series;
    LineGraphSeries<DataPoint> series2;
    private Unbinder unbinder;
    private GraphView graph;
    private Handler graphHandler = new Handler();
    private double lastPoint = 3;
    private double lastPoint2 = 5;
    private Random random = new Random();

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);

        startButton.setOnClickListener(this);
        stopButton.setOnClickListener(this);

        graph = (GraphView) view.findViewById(R.id.graph);
        series = new LineGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });

        series.setDrawDataPoints(true);
        series.setDrawBackground(true);

        graph.addSeries(series);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(10);
        graph.getViewport().setMaxY(130);
        graph.getViewport().setMinY(0);
        graph.getViewport().setXAxisBoundsManual(true);

//        graph.getGridLabelRenderer().setLabelVerticalWidth(100);
        graph.getGridLabelRenderer().setHighlightZeroLines(false);
        graph.getGridLabelRenderer().setHorizontalAxisTitle("Times");
        graph.getGridLabelRenderer().setVerticalAxisTitle("Glucose Level (mg/dl)");

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start_btn:
                Utils.showToast(this.getActivity(), "Machine has started", false);
                // TODO: 1/30/20 Write method for showing initial Graph Data
                addRandomDataPoint();
                break;
            case R.id.stop_btn:
                Utils.showToast(this.getActivity(), "Machine is stopped", false);
                // TODO: 1/30/20 Stop the real time graph data update
                break;
        }
    }

    private void addRandomDataPoint() {
        graphHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                lastPoint++;
                series.appendData(new DataPoint(lastPoint, random.nextInt(78)), false, 100);
                addRandomDataPoint();
            }
        }, 1500);
    }
}
