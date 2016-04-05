package me.jimmyshaw.politicalpositions;

import android.net.Uri;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Issue {

    private UUID mId;
    private String mTitle;
    private Date mDate;
    private String mQuote;
    private Uri mSource;

    public Issue() {
        // Generate unique identifier.
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
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
