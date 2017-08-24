package edu.temple.colorchanger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class CanvasActivity extends AppCompatActivity {

    final String[] colors = {"Blue", "Green", "Purple", "Red", "Yellow",};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);

        Intent receivedIntent = getIntent();

        //now use the position to set corresponding to the colors array
        int pos = receivedIntent.getIntExtra("The new color screen", 0);

        RelativeLayout myLayout = (RelativeLayout) findViewById(R.id.activity_canvas);

        myLayout.setBackgroundColor(Color.parseColor(colors[pos]));

    }
}
