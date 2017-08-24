package edu.temple.webview;

//needed imports
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

//fragment definition
public class PageFragment extends Fragment {

    //declaration of WebView widget to load the pages
    private WebView webView;

    //required default constructor
    public PageFragment() {
    }

    //method to create the fragment view so it can be seen and set it to a new webviewclient
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_page, container, false);

        webView = (WebView) v.findViewById(R.id.webView);

        //allows the usage of javascript in the browser
        webView.getSettings().setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient());

        return v;
    }

    //method to load the address of the web site typed by the user
    public void loadSite(String address) {
        if(address != null) {
            //if the user did not type 'http://' then append it to the front of the address to prevent crash
            if (!address.contains("http://")) {
                address = "http://" + address;
                webView.loadUrl(address);
            } else {
                webView.loadUrl(address);
            }
        }
    }
}