package me.jimmyshaw.politicalpositions;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class QuoteFragment extends Fragment {

    private static final String ARG_QUOTE_ID = "quote_id";

    public static QuoteFragment newInstance(int quoteId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_QUOTE_ID, quoteId);
        QuoteFragment quoteFragment = new QuoteFragment();
        quoteFragment.setArguments(args);
        return quoteFragment;
    }

}
