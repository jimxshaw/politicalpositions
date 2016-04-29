package me.jimmyshaw.politicalpositions;

import java.util.ArrayList;

public class Issue {

    private int mId;
    private String mTitle;
    private ArrayList<Quote> mQuotes;

    public Issue() {
        mQuotes = new ArrayList<>();
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Quote getQuote(int quoteId) {
        for (Quote quote : mQuotes) {
            if (quote.getId() == quoteId) {
                return quote;
            }
        }
        return null;
    }

    public Quote getQuoteByCandidate(String candidateName) {
        for (Quote quote : mQuotes) {
            if (quote.getCandidate().equals(candidateName)) {
                return quote;
            }
        }
        return null;
    }

    public ArrayList<Quote> getQuotes() {
        return mQuotes;
    }

    public void setQuotes(ArrayList<Quote> quotes) {
        mQuotes = quotes;
    }

    public int quotesCount() {
        return mQuotes.size();
    }

}
