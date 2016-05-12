package me.jimmyshaw.politicalpositions;

import android.support.v4.app.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class QuoteFragment extends Fragment {

    private static final String ARG_CANDIDATE_NAME = "candidate_name";
    private static final String ARG_ISSUE_ID = "issue_id";
    private static final String ARG_QUOTE_ID = "quote_id";

    private String mCandidateName;
    private Issue mIssue;
    private Quote mQuote;

    private ImageView mQuoteCandidateImage;
    private TextView mQuoteCandidateName;
    private TextView mQuoteText;
    private TextView mQuoteSource;
    private TextView mQuoteDate;

    private double mRandomNumber;

    public static Fragment newInstance(String candidateName, int issueId, int quoteId) {
        Bundle args = new Bundle();
        args.putString(ARG_CANDIDATE_NAME, candidateName);
        args.putInt(ARG_ISSUE_ID, issueId);
        args.putInt(ARG_QUOTE_ID, quoteId);
        QuoteFragment quoteFragment = new QuoteFragment();
        quoteFragment.setArguments(args);
        return quoteFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRandomNumber = Math.random();

        mCandidateName = getArguments().getString(ARG_CANDIDATE_NAME);
        int issueId = getArguments().getInt(ARG_ISSUE_ID, 0);
        int quoteId = getArguments().getInt(ARG_QUOTE_ID, 0);
        mIssue = IssueLab.get(getActivity()).getIssue(mCandidateName, issueId);
        mQuote = mIssue.getQuote(quoteId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quote, container, false);

        mQuoteCandidateImage = (ImageView) view.findViewById(R.id.fragment_quote_image);

        randomizeCandidateImages(mQuote.getCandidate());

        mQuoteCandidateName = (TextView) view.findViewById(R.id.fragment_quote_candidate_name);
        mQuoteCandidateName.setText(mQuote.getCandidate());

        mQuoteText = (TextView) view.findViewById(R.id.fragment_quote_text);
        mQuoteText.setText(mQuote.getQuote());

        mQuoteSource = (TextView) view.findViewById(R.id.fragment_quote_source);
        mQuoteSource.setText(R.string.fragment_quote_source_text);
        mQuoteSource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(mQuote.getSource().toString()));
                startActivity(intent);
            }
        });

        mQuoteDate = (TextView) view.findViewById(R.id.fragment_quote_date);
        String rawDate = mQuote.getDate().toString();
        String date = rawDate.substring(4, 10) + "," + rawDate.substring(23);
        mQuoteDate.setText(date);

        return view;
    }

    private void randomizeCandidateImages(String candidateName) {

        switch (candidateName) {
            case "Clinton":
                if (mRandomNumber > 0.5) {
                    mQuoteCandidateImage.setImageResource(R.drawable.img_clinton01);
                }
                else {
                    mQuoteCandidateImage.setImageResource(R.drawable.img_clinton02);
                }
                break;
            case "Sanders":
                if (mRandomNumber > 0.5) {
                    mQuoteCandidateImage.setImageResource(R.drawable.img_sanders01);
                }
                else {
                    mQuoteCandidateImage.setImageResource(R.drawable.img_sanders02);
                }
                break;
            case "Trump":
                if (mRandomNumber > 0.5) {
                    mQuoteCandidateImage.setImageResource(R.drawable.img_trump01);
                }
                else {
                    mQuoteCandidateImage.setImageResource(R.drawable.img_trump02);
                }
                break;
            default:
                break;
        }
    }
}
