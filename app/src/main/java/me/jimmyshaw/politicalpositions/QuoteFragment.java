package me.jimmyshaw.politicalpositions;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class QuoteFragment extends Fragment {

    private static final String ARG_ISSUE_ID = "issue_id";
    private static final String ARG_QUOTE_ID = "quote_id";

    private Issue mIssue;
    private Quote mQuote;

    private ImageView mQuoteCandidateImage;
    private TextView mQuoteCandidate;
    private TextView mQuoteText;
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

        mQuoteCandidateImage = (ImageView) view.findViewById(R.id.fragment_quote_card_image);

        switch (mQuote.getCandidate()) {
            case "Clinton":
                mQuoteCandidateImage.setImageResource(R.drawable.img_clinton);
                break;
            case "Sanders":
                mQuoteCandidateImage.setImageResource(R.drawable.img_sanders);
                break;
            case "Trump":
                mQuoteCandidateImage.setImageResource(R.drawable.img_trump);
                break;
            case "Cruz":
                mQuoteCandidateImage.setImageResource(R.drawable.img_cruz);
                break;
            default:
                break;
        }

        mQuoteCandidate = (TextView) view.findViewById(R.id.fragment_quote_candidate);
        mQuoteCandidate.setText(mQuote.getCandidate());

        mQuoteText = (TextView) view.findViewById(R.id.fragment_quote_card_text);
        mQuoteText.setText(mQuote.getQuote());

//
//        mQuoteBody = (TextView) view.findViewById(R.id.issue_quote_body);
//        mQuoteBody.setText(mQuote.getQuote());
//
//        mQuoteSource = (TextView) view.findViewById(R.id.issue_quote_source);
//        mQuoteSource.setText(R.string.issue_quote_source_label);
//        mQuoteSource.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse(mQuote.getSource().toString()));
//                startActivity(intent);
//            }
//        });
//
//        mQuoteDate = (TextView) view.findViewById(R.id.issue_quote_date);
//        String rawDate = mQuote.getDate().toString();
//        String date = rawDate.substring(4, 10) + "," + rawDate.substring(23);
//        mQuoteDate.setText(date);


        return view;
    }

}
