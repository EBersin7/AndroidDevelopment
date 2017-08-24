package edu.temple.colorchanger;

import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class CanvasFragment extends Fragment {

    RelativeLayout relativeLayout;

    public CanvasFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_canvas, container, false);
        relativeLayout = (RelativeLayout) v.findViewById(R.id.fragment_canvas);
        return v;
    }

    public void changeBackgroundColor(String color){
        relativeLayout.setBackgroundColor(Color.parseColor(color));
    }
}
