package edu.temple.colorchanger;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

public class PaletteFragment extends Fragment {

    SenderInterface senderInterface;
    final String[] my_colors = {"Blue", "Green", "Purple", "Red", "Yellow",};
    boolean check = false;

    public PaletteFragment(){
    }

    @Override
    public void onAttach(Activity c) {
        super.onAttach(c);
        senderInterface = (SenderInterface) c;
    }

    @Override
    public void onDetach() {
        senderInterface = null;
        super.onDetach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.fragment_palette, container, false);
        Spinner spinner = (Spinner) v.findViewById(R.id.spinnerFrag);

        Resources res = getResources();
        String[] colors = res.getStringArray(R.array.color_labels_array);

        CustomAdapter customAdapter = new CustomAdapter(v.getContext(), android.R.layout.simple_spinner_dropdown_item, colors);
        spinner.setAdapter(customAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(check) {
                    String color = parent.getSelectedItem().toString();
                    senderInterface.sendColor(my_colors[position]);
                }
                check = true;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        return v;
    }

    public interface SenderInterface{
        public void sendColor(String color);
    }
}