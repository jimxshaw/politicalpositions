package me.jimmyshaw.politicalpositions;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import java.util.List;


public class QuotePagerActivity extends AppCompatActivity {

    private static final String EXTRA_ISSUE_ID = "issue_id";
    private int issueId;
    private ViewPager mViewPager;
    private List<Quote> mQuotes;

    public static Intent newIntent(Context packageContext, int issueId) {
        Intent intent = new Intent(packageContext, QuotePagerActivity.class);
        intent.putExtra(EXTRA_ISSUE_ID, issueId);
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote_view_pager);

        issueId = getIntent().getIntExtra(EXTRA_ISSUE_ID, 0);

        mViewPager = (ViewPager) findViewById(R.id.activity_quote_view_pager);

        Issue mIssue = IssueLab.get(this).getIssue("Trump", issueId);

        mQuotes = mIssue.getQuotes();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(mIssue.getTitle());
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Quote quote = mQuotes.get(position);
                return QuoteFragment.newInstance(issueId, quote.getId());
            }

            @Override
            public int getCount() {
                return mQuotes.size();
            }
        });
    }

}
