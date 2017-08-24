package edu.temple.bitcoindashboard;

//necessary imports for the activity

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

//main activity where fragments are created and managed
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //declaration of the actionbar and the pricefrag which will be used throughout main
    private ActionBar actionBar;
    private PriceFrag priceFrag;

    //overridden oncreate method for when the activity starts
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //creation of the toolbar and drawer so that they can be set
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //check if the app is first starting and set to the pricefrag first if so
        if (savedInstanceState == null) {
            priceFrag = new PriceFrag();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.mainFragment, priceFrag)
                    .commit();
        }
        actionBar = getSupportActionBar();
    }

    //overridden method to handle pressing back if inside of the drawer
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //overridden method to fill the menu with frag options
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //overridden method to determine how items should be selected from the actionbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //overridden method to determine which item was selected and what action to take
    //occurs within the navigation view
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        int id = item.getItemId();

        if (id == R.id.nav_bitcoin) {
            //shows the current bitcoin price
            priceFrag = new PriceFrag();
            transaction.replace(R.id.mainFragment, priceFrag);
            actionBar.setTitle(getString(R.string.app_name));
        } else if (id == R.id.nav_charts) {
            //shows a chart for the bitcoin prices over time
            ChartFrag chartFrag = new ChartFrag();
            transaction.replace(R.id.mainFragment, chartFrag);
            actionBar.setTitle(getString(R.string.bitcoinGraphs));
        } else if (id == R.id.nav_block) {
            //shows the info for a specific block
            NavigationFrag navigationFrag = new NavigationFrag();
            transaction.replace(R.id.mainFragment, navigationFrag);
            actionBar.setTitle(getString(R.string.blockInfo));
        } else if (id == R.id.nav_address) {
            //shows the current balance for a provided bitcoin address
            AddressFrag addrFrag = new AddressFrag();
            transaction.replace(R.id.mainFragment, addrFrag);
            actionBar.setTitle(getString(R.string.addrInfo));
        }
        transaction.addToBackStack(null);
        transaction.commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}