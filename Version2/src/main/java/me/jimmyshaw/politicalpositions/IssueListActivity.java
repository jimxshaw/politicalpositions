package me.jimmyshaw.politicalpositions;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class IssueListActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String EXTRA_CANDIDATE_NAME = "candidate_name";

    private DrawerLayout mDrawerLayout;

    private String mCandidateName;

    private Fragment mFragmentOriginal;
    private Fragment mFragmentNew;
    private FragmentManager mFragmentManager;

    private Toolbar mToolbar;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;

    public static Intent newIntent(Context packageContext, String candidateName) {
        Intent intent = new Intent(packageContext, IssueListActivity.class);
        intent.putExtra(EXTRA_CANDIDATE_NAME, candidateName);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue_list);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        mCollapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.CollapsingToolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(drawerToggle);
        // If the syncState method is not called then the drawer toggle icon AKA "Hamburger icon"
        // either won't synchronize with the drawer layout or the icon won't appear at all.
        drawerToggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        // Remove the gray color tint from nav menu item icons. Otherwise, the default gray
        // color would cover the entire icon image.
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);

        mFragmentManager = getSupportFragmentManager();
        mFragmentOriginal = mFragmentManager.findFragmentById(R.id.fragment_content_container);

        if (mFragmentOriginal == null) {

            if (getIntent().getStringExtra(EXTRA_CANDIDATE_NAME) != null) {
                mCandidateName = getIntent().getStringExtra(EXTRA_CANDIDATE_NAME);
            }
            else {
                mCandidateName = "none";
            }

            mFragmentOriginal = IssueListFragment.newInstance(mCandidateName);
            mFragmentManager.beginTransaction()
                    .add(R.id.fragment_content_container, mFragmentOriginal)
                    .commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_issue_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        super.onOptionsItemSelected(menuItem);

        switch (menuItem.getItemId()) {
            case R.id.action_about_page:
                startActivity(new Intent(this, AboutPageActivity.class));
                break;
            default:
                return false;
        }

        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        // Handle navigation view item clicks here.
        switch(menuItem.getItemId()) {
            case R.id.nav_drawer_menu_item_clinton:
                filterIssueListByCandidate(getResources().getString(R.string.candidate_clinton));
                break;
            case R.id.nav_drawer_menu_item_sanders:
                filterIssueListByCandidate(getResources().getString(R.string.candidate_sanders));
                break;
            case R.id.nav_drawer_menu_item_trump:
                filterIssueListByCandidate(getResources().getString(R.string.candidate_trump));
                break;
            default:
                filterIssueListByCandidate(getResources().getString(R.string.candidate_filter_none));
                break;
        }
        return true;
    }

    // Filter issue list by candidate. We instantiate a new IssueListFragment by taking in the user
    // selected candidate name. The fragment manager hides the original fragment and then starts up
    // the new fragment. We then designate the new fragment as the original fragment so that on
    // subsequent filtering that it can be hidden. Otherwise, the fragments will overlap on the UI.
    // Finally, the toolbar's title changes according to the candidate that's being filtered.
    private void filterIssueListByCandidate(String candidateName) {
        hideDrawer();
        mFragmentNew = IssueListFragment.newInstance(candidateName);
        mFragmentManager.beginTransaction()
                .hide(mFragmentOriginal)
                .addToBackStack("mFragmentNew")
                .add(R.id.fragment_content_container, mFragmentNew)
                .commit();
        mFragmentOriginal = mFragmentNew;
        changeToolbarText(candidateName);
    }

    // Change the tool bar's title if the user filters by a particular candidate.
    private void changeToolbarText(String candidateName) {
        if (!candidateName.equals("none")) {
            mCollapsingToolbarLayout.setTitle("Issues - " + candidateName);
        }
        else {
            mCollapsingToolbarLayout.setTitle(getResources().getString(R.string.app_name));
        }
    }

    // Show the navigation drawer.
    private void showDrawer() {
        mDrawerLayout.openDrawer(GravityCompat.START);
    }

    // Hide the navigation drawer.
    private void hideDrawer() {
        mDrawerLayout.closeDrawer(GravityCompat.START);
    }

    // When the user pushes the back button to close the app, if the drawer is open then hide the
    // drawer. Otherwise, perform the usual action with the back button press which is closing
    // the app.
    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            hideDrawer();
        }
        else {
            super.onBackPressed();
        }
    }
}
