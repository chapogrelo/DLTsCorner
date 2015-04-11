package com.chapo.stupidapps.dltscorner;



import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.samples.apps.iosched.ui.widget.ScrimInsetsFrameLayout;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment {

    private static final String STATE_SELECTED_POSITION = "selected_navigation_drawer_position";
    private static final String PREF_USER_LEARNED_DRAWER = "navigation_drawer_learned";


    int SECTIONS[] = {R.string.title_news, R.string.title_planning, R.string.title_incoming, R.string.title_members};
    int ICONS[] = {R.drawable.ic_newspaper_white_24dp, R.drawable.ic_timetable_white_24dp, R.drawable.ic_directions_white_24dp, R.drawable.ic_account_multiple_white_24dp};

    String nickName = "Chapogrelo";
    String mojo = "Vers l'infini et au dela !";
    int profilePic = R.drawable.darkvatar;

    private NavigationDrawerCallbacks mCallbacks;

    private DrawerLayout mDrawerLayout;
    private View mFragmentContainerView;
    private ActionBarDrawerToggle mDrawerToggle;

    public ScrimInsetsFrameLayout scrimInsetsFrameLayout;
    private RecyclerView mRecyclerView;
    private DrawerAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    final GestureDetector mGestureDetector = new GestureDetector(getActivity(),new GestureDetector.SimpleOnGestureListener(){
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return true;
        }
    });

    private int mCurrentSelectedPosition = 1;
    private boolean mFromSavedInstanceState;
    private boolean mUserLearnedDrawer;


    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mUserLearnedDrawer = sp.getBoolean(PREF_USER_LEARNED_DRAWER, false);

        if (savedInstanceState != null) {
            mCurrentSelectedPosition = savedInstanceState.getInt(STATE_SELECTED_POSITION);
            mFromSavedInstanceState = true;
        }
        mCallbacks.onNavigationDrawerSectionSelected(mCurrentSelectedPosition);
        // Select either the default item (0) or the last selected item.
        //selectItem(mCurrentSelectedPosition);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        scrimInsetsFrameLayout = (ScrimInsetsFrameLayout) inflater.inflate(R.layout.fragment_navigation_drawer,container,false);
        mRecyclerView  = (RecyclerView) scrimInsetsFrameLayout.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        List<DrawerRow> drawerRows = new ArrayList<>();
        drawerRows.add(new DrawerRowHeader(nickName,mojo,profilePic));
        for (int i = 0 ; i < SECTIONS.length ; i++){
            Drawable icon = getActivity().getResources().getDrawable(ICONS[i]);
            drawerRows.add(new DrawerRowSection(icon,SECTIONS[i]));
        }
        drawerRows.add(new DrawerRowDivider());
        Drawable icon = getActivity().getResources().getDrawable(R.drawable.ic_settings_white_24dp);
        drawerRows.add(new DrawerRowSection(icon,R.string.title_settings));

        mAdapter = new DrawerAdapter(getActivity(),drawerRows,mCurrentSelectedPosition);
        mLayoutManager = new LinearLayoutManager(getActivity());

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener(){

            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                View child = rv.findChildViewUnder(e.getX(),e.getY());

                if(child != null && mGestureDetector.onTouchEvent(e) && child.isClickable()){
                    int position = mRecyclerView.getChildAdapterPosition(child);
                    mAdapter.setSelected(child,position);
                    mDrawerLayout.closeDrawers();

                    if (mCallbacks != null) {
                        mCallbacks.onNavigationDrawerSectionSelected(position);
                    }

                    Toast.makeText(getActivity(), "The Item Clicked is: " + position, Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }
        });

        return scrimInsetsFrameLayout;
    }

    public void setUp(int fragmentId, DrawerLayout drawerLayout, Toolbar toolbar){
        mFragmentContainerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(
                getActivity(),
                mDrawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        ){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (!isAdded()) {
                    return;
                }

                getActivity().invalidateOptionsMenu(); // calls onPrepareOptionsMenu()
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!isAdded()) {
                    return;
                }

                if (!mUserLearnedDrawer) {
                    // The user manually opened the drawer; store this flag to prevent auto-showing
                    // the navigation drawer automatically in the future.
                    mUserLearnedDrawer = true;
                    SharedPreferences sp = PreferenceManager
                            .getDefaultSharedPreferences(getActivity());
                    sp.edit().putBoolean(PREF_USER_LEARNED_DRAWER, true).apply();
                }

                getActivity().invalidateOptionsMenu(); // calls onPrepareOptionsMenu()
            }
        };


        if (!mUserLearnedDrawer && !mFromSavedInstanceState) {
            mDrawerLayout.openDrawer(mFragmentContainerView);
        }
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }
    private ActionBar getActionBar() {
        return ((ActionBarActivity) getActivity()).getSupportActionBar();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallbacks = (NavigationDrawerCallbacks) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement NavigationDrawerCallbacks.");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_SELECTED_POSITION, mAdapter.getSelectedView());
    }


    public static interface NavigationDrawerCallbacks {

        void onNavigationDrawerSectionSelected(int position);
    }
}
