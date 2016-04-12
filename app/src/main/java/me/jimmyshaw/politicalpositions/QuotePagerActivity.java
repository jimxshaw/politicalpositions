package me.jimmyshaw.politicalpositions;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.List;

// Every issue has a list of quotes. Our QuotePagerActivity will host a list of QuoteFragments and
// allows us to page through the quotes about the selected issue.
public class QuotePagerActivity extends FragmentActivity {

    private static final String EXTRA_ISSUE_ID = "issue_id";

    private int issueId;

    private ViewPager mViewPager;
    private List<Quote> mQuotes;

    public static Intent newInstance(Context packageContext, int issueId) {
        Intent intent = new Intent(packageContext, QuotePagerActivity.class);
        intent.putExtra(EXTRA_ISSUE_ID, issueId);
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote_pager);

        issueId = (int) getIntent().getSerializableExtra(EXTRA_ISSUE_ID);

        mViewPager = (ViewPager) findViewById(R.id.activity_quote_pager_view_pager);

        mQuotes = IssueLab.get(this).getIssue(issueId).getQuotes();

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
