/*
 Name: ProducerDashboardActivity.java
 Written by: Charles Bein
 Description: Splash screen for logged in users; allows browsing, creation of new events, and managing of existing ones
 NOTE: Managing existing events is currently impossible
 */

package com.i4i.hcvb.route26;

import android.app.LoaderManager;
import android.app.SearchManager;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import io.swagger.client.ApiException;
import io.swagger.client.api.MemberApi;
import io.swagger.client.model.MemberPublic;

public class ProducerDashboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, LoaderManager.LoaderCallbacks<MemberPublic> {

    MemberPublic mMember;
    long mMemberId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producer_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("Route 26 Artbeat");

        SharedPreferences preferences = getSharedPreferences("loginCredentials", MODE_PRIVATE);
        String uName = preferences.getString("username", null);

        Bundle args = new Bundle();
        args.putString("name", uName);

        getLoaderManager().initLoader(3, args, this).forceLoad();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Button browseButton = findViewById(R.id.pro_browse);
        browseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ResultsActivity.class);
                startActivity(intent);
            }
        });

        Button createButton = findViewById(R.id.create_event);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CreateEventActivity.class);
                startActivity(intent);
            }
        });

        ListView manageList = findViewById(R.id.manage_list);
        //TODO: Modify API so that events can return host IDs, allowing identifcation of owned events
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.producer_dashboard, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        final SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(getApplicationContext(), ResultsActivity.class);
                startActivity(intent);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        };

        searchView.setOnQueryTextListener(queryTextListener);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_settings) {
            Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(intent);
            return (true);
        } else if (id == R.id.nav_logout) {
            SharedPreferences preferences = getSharedPreferences("logState", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("loggedIn", false);
            editor.apply();

            SharedPreferences loginPrefs = getSharedPreferences("loginCredentials", MODE_PRIVATE);
            SharedPreferences.Editor loginEditor = loginPrefs.edit();
            loginEditor.clear();
            loginEditor.apply();

            Intent intent = new Intent(getApplicationContext(), ConsumerDashboard.class);
            startActivity(intent);
            return (true);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public Loader<MemberPublic> onCreateLoader(int id, Bundle args) {
        return new GetMember(ProducerDashboardActivity.this, args.getString("name"));
    }

    @Override
    public void onLoadFinished(Loader<MemberPublic> loader, MemberPublic data) {
        mMember = data;
    }

    @Override
    public void onLoaderReset(Loader<MemberPublic> loader) {
    }

    private static class GetMember extends AsyncTaskLoader<MemberPublic> {
        MemberPublic mMember;
        String mUsername;

        GetMember(Context context, String inName) {
            super(context);
            mUsername = inName;
        }

        @Override
        public MemberPublic loadInBackground() {
            try {
                MemberApi memberApi = new MemberApi();
                mMember = memberApi.getMemberByName(mUsername);

            } catch (ApiException e) {
                System.err.println("Exception when calling MemberApi.getMemberByName");
                e.printStackTrace();
            }
            return mMember;
        }
    }
}
