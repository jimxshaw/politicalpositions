package me.jimmyshaw.politicalpositions;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class QuoteFragment extends Fragment {

    private static final String ARG_ISSUE_ID = "issue_id";
    private static final String ARG_QUOTE_ID = "quote_id";

    private Issue mIssue;
    private Quote mQuote;

    public static QuoteFragment newInstance(int issueId, int quoteId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_ISSUE_ID, issueId);
        args.putSerializable(ARG_QUOTE_ID, quoteId);
        QuoteFragment quoteFragment = new QuoteFragment();
        quoteFragment.setArguments(args);
        return quoteFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int issueId = (int) getArguments().getSerializable(ARG_ISSUE_ID);
        int quoteId = (int) getArguments().getSerializable(ARG_QUOTE_ID);
        mQuote = IssueLab.get(getActivity()).getIssue(issueId).getQuote(quoteId);
    }
}
