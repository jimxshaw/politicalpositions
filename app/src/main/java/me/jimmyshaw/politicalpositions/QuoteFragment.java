package me.jimmyshaw.politicalpositions;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class QuoteFragment extends Fragment {

    private static final String ARG_ISSUE_ID = "issue_id";
    private static final String ARG_QUOTE_ID = "quote_id";

    private Issue mIssue;
    private Quote mQuote;

    private TextView mQuoteTitle;
    private TextView mQuoteCandidate;
    private TextView mQuoteBody;
    private TextView mQuoteSource;
    private TextView mQuoteDate;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_quote, container, false);

        mQuoteTitle = (TextView) view.findViewById(R.id.issue_quote_title);
        mQuoteTitle.setText(mIssue.getTitle());

        mQuoteCandidate = (TextView) view.findViewById(R.id.issue_quote_candidate);
        mQuoteCandidate.setText(mQuote.getCandidate());

        mQuoteBody = (TextView) view.findViewById(R.id.issue_quote_body);
        mQuoteBody.setText(mQuote.getQuote());

        // TODO: Refactor the string source into an actual URL.
        mQuoteSource = (TextView) view.findViewById(R.id.issue_quote_source);
        mQuoteSource.setText(mQuote.getSource().toString());

        mQuoteDate = (TextView) view.findViewById(R.id.issue_quote_date);
        mQuoteDate.setText(mQuote.getDate().toString());

        
        return view;
    }
}
