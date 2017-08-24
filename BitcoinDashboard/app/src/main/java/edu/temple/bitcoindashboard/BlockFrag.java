package edu.temple.bitcoindashboard;

//necessary imports for the library
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

//fragment to handle the block info
public class BlockFrag extends Fragment {

    //required empty constructor
    public BlockFrag() {
    }

    //super method calls
    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_block, container, false);
        return v;
    }

    //method to concatenate the url with the block number entered
    public String addNumUrl(int num) {
        String baseUrl;
        if (num > 0) {
            baseUrl = "http://btc.blockr.io/api/v1/block/info/" + num;
        } else {
            baseUrl = null;
        }
        return baseUrl;
    }

    //method to get the block data and try to send messages
    public void getBlockData(int num) {
        final String theURL = addNumUrl(num);
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    URL url = new URL(theURL);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
                    String temp= "";
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
                }
            }
        };
        thread.start();
    }

    //handler to convert the message to json and then retrieve needed info from the json data
    Handler responseHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            try {
                JSONObject blockObject = new JSONObject((String) msg.obj);
                JSONObject data = blockObject.getJSONObject("data");
                ((TextView) getView().findViewById(R.id.value3)).setText(String.valueOf(data.getString("nb")));
                ((TextView) getView().findViewById(R.id.value4)).setText(data.getString("hash"));
                ((TextView) getView().findViewById(R.id.value5)).setText(data.getString("version"));
                ((TextView) getView().findViewById(R.id.value6)).setText(data.getString("confirmations"));
                ((TextView) getView().findViewById(R.id.value7)).setText(data.getString("time_utc"));
                ((TextView) getView().findViewById(R.id.value8)).setText(data.getString("nb_txs"));
                ((TextView) getView().findViewById(R.id.value9)).setText(data.getString("merkleroot"));
                ((TextView) getView().findViewById(R.id.value10)).setText(data.getString("next_block_nb"));
                ((TextView) getView().findViewById(R.id.value11)).setText(data.getString("prev_block_nb"));
                ((TextView) getView().findViewById(R.id.value12)).setText(data.getString("next_block_hash"));
                ((TextView) getView().findViewById(R.id.value13)).setText(data.getString("prev_block_hash"));
                ((TextView) getView().findViewById(R.id.value14)).setText(data.getString("fee"));
                ((TextView) getView().findViewById(R.id.value15)).setText(data.getString("vout_sum"));
            } catch (JSONException e) {
            }
            return true;
        }
    });
}