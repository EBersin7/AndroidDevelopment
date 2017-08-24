package edu.temple.bitcoindashboard;

//necessary imports for the fragment

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

//fragment which handles the display of the current bitcoin price
public class PriceFrag extends Fragment {

    //string which sets the fullURL to access current price
    final String fullURL = "http://btc.blockr.io/api/v1/coin/info";

    //required empty constructor
    public PriceFrag() {
    }

    //required method to create the viewable fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_price, container, false);
        getCurrentPrice();
        return v;
    }

    //method which is called in the onCreateView, this accesses the internet and stores the info in string
    public void getCurrentPrice() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    URL url = new URL(PriceFrag.this.fullURL);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
                    String temp = "";
                    String response = "";
                    while (temp != null) {
                        response.concat(temp);
                        response = response + temp;
                        temp = reader.readLine();
                    }
                    Message msg = Message.obtain();
                    msg.obj = response;
                    responseHandler.sendMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        };
        thread.start();
    }

    //the message from the previous method is translated into the json object and the appropriate field is used
    //to assign the value into the text view
    Handler responseHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            try {
                JSONObject blockObject = new JSONObject((String) msg.obj);
                JSONObject data = blockObject.getJSONObject("data");
                JSONObject markets = data.getJSONObject("markets");
                JSONObject btce = markets.getJSONObject("btce");
                ((TextView) getView().findViewById(R.id.bitcoinCurrentPrice)).setText("The Current Price Is:\n$" + btce.getString("value"));
            } catch (JSONException e) {
            }
            return true;
        }
    });
}