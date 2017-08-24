package edu.temple.webview;

//needed imports
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.ArrayList;

//main activity definition
public class MainActivity extends AppCompatActivity {

    //declaration and initialization of objects needed for the activity
    //a pager, button, edittext, string for web address, array to hold all the frags, an adapter,
    //and index variables to help manage where at in the array the needed frag is
    EditText editText;
    Button button;
    ViewPager pager;
    String address;
    ArrayList<PageFragment> fragArray = new ArrayList<>();
    int numFrags = 1;
    int arrayIndex = 0;
    int index;
    PageFragment currentFrag;
    PagerAdapter pagerAdapter;

    //main function for when the app is created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //find and set the edittext + button from the layout
        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);

        //setup for the pager with the adapter
        pager = (ViewPager) findViewById(R.id.viewPager);
        pager.setAdapter(new PageAdapter(getSupportFragmentManager()));

        //check for an intent
        final Uri data = getIntent().getData();

        //load the address with the data possibly passed as an intent above
        if (data != null) {
            address = data.toString();
            currentFrag.loadSite(address);
        }

        //method to handle the button click
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //turn the edittext text into a string, change the current visible frag, pass the current frag
                //the string to load its web page address
                address = editText.getText().toString();
                index = pager.getCurrentItem();
                currentFrag = fragArray.get(index);
                currentFrag.loadSite(address);
            }
        });
    }

    //definition of pager adapter class designed to work with the pager
    private class PageAdapter extends FragmentPagerAdapter {

        //call to the super method
        public PageAdapter(FragmentManager fm) {
            super(fm);
        }

        //method to create a new frag and add it to the array
        @Override
        public Fragment getItem(int pos) {
            switch(pos) {
                default:
                    PageFragment defaultFrag = new PageFragment();
                    fragArray.add(arrayIndex, defaultFrag);
                    arrayIndex++;
                    return defaultFrag;
            }
        }

        //return the number of frags in the array
        @Override
        public int getCount() {
            return numFrags;
        }
    }

    //method to inflate the menu so that the user can see the prev, next, and new page buttons
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //method to get the menu item selected and then perform the selected action
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.prevTab:
                //makes the previous page the visible one when the user clicks the previous button
                pager.setCurrentItem(pager.getCurrentItem()-1);
                return true;
            case R.id.newTab:
                //creates a new fragment for a page when the user clicks the new button
                PageFragment fragment = new PageFragment();
                fragArray.add(arrayIndex, fragment);
                numFrags++;

                //must notify the adapter of changes
                pagerAdapter = pager.getAdapter();
                pagerAdapter.notifyDataSetChanged();

                //makes the current fragment visible by making it the current item
                currentFrag = fragment;
                pager.setCurrentItem(arrayIndex);
                return true;
            case R.id.nextTab:
                //makes the next page the visible one when the user clicks the next button
                pager.setCurrentItem(pager.getCurrentItem()+1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}