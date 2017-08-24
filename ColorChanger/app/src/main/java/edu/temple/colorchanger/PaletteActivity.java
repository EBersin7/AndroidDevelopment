package edu.temple.colorchanger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PaletteActivity extends AppCompatActivity implements PaletteFragment.SenderInterface {

    CanvasFragment receiver;
    boolean bothFrags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palette);

        bothFrags = (findViewById(R.id.receiverFrag)!= null);

        PaletteFragment sender = new PaletteFragment();
        getFragmentManager()
                .beginTransaction()
                .add(R.id.senderFrag, sender)
                .commit();

        receiver = new CanvasFragment();

        if(bothFrags){
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.receiverFrag, receiver)
                    .commit();
        }
    }

    private void doTransition(){
        getFragmentManager()
                .beginTransaction()
                .add(R.id.senderFrag, receiver)
                .addToBackStack(null)
                .commit();
        getFragmentManager().executePendingTransactions();
    }

    @Override
    public void sendColor(String color){
        if (!bothFrags){
            doTransition();
        }
        receiver.changeBackgroundColor(color);
    }
}