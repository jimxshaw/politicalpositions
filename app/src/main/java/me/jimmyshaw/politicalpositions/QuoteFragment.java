package me.jimmyshaw.politicalpositions;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;

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
        args.putInt(ARG_ISSUE_ID, issueId);
        args.putInt(ARG_QUOTE_ID, quoteId);
        QuoteFragment quoteFragment = new QuoteFragment();
        quoteFragment.setArguments(args);
        return quoteFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int issueId = getArguments().getInt(ARG_ISSUE_ID, 0);
        int quoteId = getArguments().getInt(ARG_QUOTE_ID, 0);
        mIssue = IssueLab.get(getActivity()).getIssue(issueId);
        mQuote = mIssue.getQuote(quoteId);
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
        String rawDate = mQuote.getDate().toString();
        String date = rawDate.substring(4, 10) + "," + rawDate.substring(23);
        mQuoteDate.setText(date);


        return view;
    }
}
