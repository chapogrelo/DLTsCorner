package com.chapo.stupidapps.dltscorner;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

/**
 * Created by chapo on 09/04/2015.
 * Activité principale de l'application
 * met en place le navigation drawer et affiche le fragment concerné au choix d'une section.
 */
public class MainActivity extends ActionBarActivity
            implements NavigationDrawerFragment.NavigationDrawerCallbacks{

    private Toolbar toolbar;
    private NavigationDrawerFragment mNavigationDrawerFragment;


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
                fragmentManager.beginTransaction()
                        .replace(R.id.container,fragment )
                        .commit();
                break;
            case (2):
                fragment = PlanningFragment.newInstance("news","test");
                fragmentManager.beginTransaction()
                        .replace(R.id.container,fragment )
                        .commit();
                break;
            default:
                Toast.makeText(this,"Coming soon",Toast.LENGTH_SHORT);
                break;
        }
    }
}
