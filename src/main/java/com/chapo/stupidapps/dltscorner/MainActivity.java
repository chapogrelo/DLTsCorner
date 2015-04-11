package com.chapo.stupidapps.dltscorner;

import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;

import com.google.samples.apps.iosched.ui.widget.ScrimInsetsFrameLayout;

/**
 * Created by chapo on 09/04/2015.
 */
public class MainActivity extends ActionBarActivity
            implements NavigationDrawerFragment.NavigationDrawerCallbacks{

    private Toolbar toolbar;
    private NavigationDrawerFragment mNavigationDrawerFragment;

    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

        mNavigationDrawerFragment.setUp(R.id.navigation_drawer,(DrawerLayout) findViewById(R.id.drawer_layout),toolbar);
    }

    @Override
    public void onNavigationDrawerSectionSelected(int position) {
        FragmentManager fragmentManager = getFragmentManager();
        Fragment fragment;
        switch(position){
            case (1):
                fragment = NewsFragment.newInstance("news","test");
                break;
            case (2):
                fragment = PlanningFragment.newInstance("news","test");
                break;
            default:
                fragment = NewsFragment.newInstance("test","test");
                break;
        }
        fragmentManager.beginTransaction()
                .replace(R.id.container,fragment )
                .commit();
    }
}
