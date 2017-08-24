package edu.temple.bitcoindashboard;

//necessary imports for the fragment

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.os.Handler;
import android.os.Message;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;

import com.squareup.picasso.Picasso;

//fragment which handles the display of the charts over time
public class ChartFrag extends Fragment {

    //declaration of variables to hold the view, spinner, and adapter
    ImageView chart;
    Spinner spinner;
    CustomAdapter adapter;

    //declaration of the arrays used to display options in the spinner
    String times[] = {"1 day", "5 days", "1 month", "6 months", "1 year", "2 years"};
    String days[] = {"1d", "5d", "30d", "180d", "365d", "730d"};
    int pos = 0;
    Thread thread;

    //required empty constructor
    public ChartFrag() {
    }

    //onCreateView method is adjusted to handle the creation of the chart, spinner, and adapter
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_chart, container, false);
        chart = (ImageView) v.findViewById(R.id.graph);
        spinner = (Spinner) v.findViewById(R.id.spinner);
        adapter = new CustomAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, times);
        spinner.setAdapter(adapter);
        updateChart();

        //spinner's method which handles selection of an item
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Picasso.with(chart.getContext()).load("https://chart.yahoo.com/z?s=BTCUSD=X&thread=" + days[position]).into(chart);
                pos = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return v;
    }

    //method where a thread runs to try and update the chart every 15 seconds, not complete
    public void updateChart() {
        thread = new Thread() {
            @Override
            public void run() {
                try {
                    while (!thread.isInterrupted()) {
                        int i = 0;
                        i++;
                        Thread.sleep(15000);
                        responseHandler.sendEmptyMessage(0);
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        thread.start();
    }

    //the handler draws the chart
    Handler responseHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            try {
                Picasso.with(chart.getContext()).invalidate("https://chart.yahoo.com/z?s=BTCUSD=X&thread=" + days[pos]);
            } catch (Exception e) {
            }
            return true;
        }
    });

    //method to handle no longer viewing frag and stop the thread from running anymore
    @Override
    public void onDetach() {
        super.onDetach();
        if (thread != null) {
            thread.interrupt();
        }
    }
}