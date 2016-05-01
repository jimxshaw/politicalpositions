package me.jimmyshaw.politicalpositions;

import android.support.v4.app.Fragment;

public class AboutScreenActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new AboutScreenFragment();
    }
}
