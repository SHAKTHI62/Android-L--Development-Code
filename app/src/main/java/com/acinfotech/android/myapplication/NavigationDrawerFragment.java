package com.acinfotech.android.myapplication;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment {


    public static final String FILE_NAME = "testperf";
    public static final String KEY_USER_LEARNED_DRAWER = "user_learned_drawer";
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;

    private boolean mUserLearnedDrawer;
    private boolean mfromSavedInstanceState;

    private View containerView;

    private RecyclerView mRecyclerView;

    private ViewAdapter adapter;


    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       mUserLearnedDrawer = Boolean.valueOf(readFromPreference(getActivity(), KEY_USER_LEARNED_DRAWER, "false"));
       if(savedInstanceState != null)
           mfromSavedInstanceState = true;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ArrayList<String> dataValue = new ArrayList<>();
        dataValue.add("velan");
        dataValue.add("velan");
        dataValue.add("velan");

        // Inflate the layout for this fragment
        View mlayout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
         mRecyclerView = (RecyclerView) mlayout.findViewById(R.id.drawer_list);

        adapter = new ViewAdapter(getActivity(), getData());
        mRecyclerView.setAdapter(adapter);


        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return mlayout;
    }


    public List<Information> getData() {
        List<Information> data = new ArrayList<>();
        int [] icon = {R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher};
        String [] title = {"You", "Me", "Collect"};
        for (int i=0; i<title.length && i<icon.length; i++) {

            Information current = new Information();
            current.mItemId = icon[i];
            current.mTitle = title[i];
            data.add(current);

        }
        return data;
    }

    public void setUp(int fragementID, DrawerLayout drawerLayout, final Toolbar mToolbar) {

        containerView = getActivity().findViewById(fragementID);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, mToolbar,R.string.drawer_open,R.string.drawer_close ) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if(!mUserLearnedDrawer) {
                    mUserLearnedDrawer = true;
                    savedToPreference(getActivity(), KEY_USER_LEARNED_DRAWER, mUserLearnedDrawer+"");
                }
                getActivity().invalidateOptionsMenu();
//
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();

            }

//            Third Method --- like Play store


            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
//                super.onDrawerSlide(drawerView, slideOffset);
//                Log.d("VVV","Offset--"+ slideOffset);
                if(slideOffset < 0.6) {
                    mToolbar.setAlpha(1 - slideOffset);
                }
            }
        };

        if(!mUserLearnedDrawer && !mfromSavedInstanceState) {
            mDrawerLayout.openDrawer(containerView);
        }


    mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

    }

    public static void savedToPreference(Context mContext, String perferName, String perferValue) {

        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(perferName, perferValue);
        editor.apply();
    }

    public static String readFromPreference(Context mContext, String perferName, String perferValue) {

        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);

        return mSharedPreferences.getString(perferName, perferValue);

    }
}
