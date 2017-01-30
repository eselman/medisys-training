package com.eselman.medisys;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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


import com.eselman.medisys.adapters.DrawerMenuAdapter;
import com.eselman.medisys.helpers.RecyclerTouchListener;

public class PatientsLandingActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView drawerRecyclerView;
    private RecyclerView.Adapter drawerMenuAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private int lastSelectedPosition = 0;
    private View lastSelectedView;



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
                switch (position) {
                    //Patients Menu Option.
                    case 1: {
                        Fragment patientsLandingFragment = new PatientsLandingFragment();
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.content_patiens, patientsLandingFragment).commit();

                        // update selected item and title, then close the drawer
                        updateSelectedItem(view, position);
                        updateActionBarTitle(position);
                        drawerLayout.closeDrawer(drawerRecyclerView);
                    } case 2: {
                        updateSelectedItem(view, position);
                        updateActionBarTitle(position);
                    }
                    default:{
                        break;
                    }
                }
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

    /**
     * Updates the Action Bar Title with the selected menu option.
     *
     * @param position
     */
    private void updateActionBarTitle (int position) {
        getSupportActionBar().setTitle(getResources().getStringArray(R.array.menu_options)[position - 1]);
    }

    /**
     * Updates the layout for the selected menu option.
     *
     * @param view
     * @param position
     */
    private void updateSelectedItem (View view, int position) {
        if (position != lastSelectedPosition) {
            view.setSelected(true);

            if (lastSelectedView != null) {
                lastSelectedView.setSelected(false);
            }

            lastSelectedView = view;
            lastSelectedPosition = position;
        }

    }
}