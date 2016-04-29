package me.jimmyshaw.politicalpositions;

import android.support.v4.app.Fragment;

//TODO: Extend AppCompatActivity.

public class IssueListActivity extends SingleFragmentActivity {

    //TODO: Add new intent method that accepts a candidate name string.

    //TODO: Replace createFragment with onCreate lifecycle method that returns a new filtered IssueListFragment.

    @Override
    protected Fragment createFragment() {
        return new IssueListFragment();
    }

}
