package me.jimmyshaw.politicalpositions;

import android.net.Uri;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Quote {
    private int mId;
    private String mCandidate;
    private String mQuote;
    private Date mDate;
    private Uri mSource;

    public Quote() {
        mDate = new Date();
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getCandidate() {
        return mCandidate;
    }

    public void setCandidate(String candidate) {
        mCandidate = candidate;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(String dateString) {
        // Converts a String date input into a Date object and then set it
        // to our mDate field.
        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy");

        try {
            mDate = formatter.parse(dateString);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getQuote() {
        return mQuote;
    }

    public void setQuote(String quote) {
        mQuote = quote;
    }

    public Uri getSource() {
        return mSource;
    }

    public void setSource(String uriString) {
        Uri uri = Uri.parse(uriString);

        mSource = uri;
    }
}
