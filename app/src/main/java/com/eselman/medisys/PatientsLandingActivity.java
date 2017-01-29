package com.eselman.medisys;

import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.eselman.medisys.adapters.DrawerMenuAdapter;
import com.eselman.medisys.helpers.RecyclerTouchListener;

public class PatientsLandingActivity extends AppCompatActivity {
    private Toolbar toolbar;
    RecyclerView drawerRecyclerView;
    RecyclerView.Adapter drawerMenuAdapter;
    RecyclerView.LayoutManager layoutManager;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients_landing);

        // Assinging the toolbar object to the view and setting the Action bar to the toolbar
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        // Assigning the RecyclerView Object to the xml View
        drawerRecyclerView = (RecyclerView) findViewById(R.id.drawerRecyclerView);

        // Letting the system know that the list objects are of fixed size
        drawerRecyclerView.setHasFixedSize(true);

        // Creating Adapter
        drawerMenuAdapter = new DrawerMenuAdapter(getResources().getStringArray(R.array.menu_options), getResources().obtainTypedArray(R.array.menu_options_icons));

        // Setting the adapter to RecyclerView
        drawerRecyclerView.setAdapter(drawerMenuAdapter);

        // Creating Layout Manager
        layoutManager = new LinearLayoutManager(this);

        // Setting Layout Manager to RecyclerView
        drawerRecyclerView.setLayoutManager(layoutManager);

        // Set click events to Drawer Recycler view.
        drawerRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(this,
                drawerRecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(PatientsLandingActivity.this, "Position clicked: " + position, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}