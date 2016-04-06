package me.jimmyshaw.politicalpositions;

import android.support.v4.app.Fragment;


public class IssueListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new IssueListFragment();
    }

}
