package me.jimmyshaw.politicalpositions;

import java.util.ArrayList;
import java.util.UUID;

public class Issue {

    private UUID mId;
    private String mTitle;
    private ArrayList<Quote> mQuotes;

    public Issue() {
        mQuotes = new ArrayList<>();
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
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
