package me.jimmyshaw.politicalpositions.repo;

import android.content.Context;
import android.support.annotation.RawRes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import me.jimmyshaw.politicalpositions.R;
import me.jimmyshaw.politicalpositions.model.Issue;
import me.jimmyshaw.politicalpositions.model.Quote;

public class IssueLab {

    private static IssueLab sIssueLab;
    private List<Issue> mIssues;
    private List<Issue> mIssuesByClinton;
    private List<Issue> mIssuesByTrump;

    private IssueLab(Context context) {
        mIssues = new ArrayList<>();

        try {
            parseJSONFromRes(context);
            mIssuesByClinton = filterIssuesByCandidate("Clinton");
            mIssuesByTrump = filterIssuesByCandidate("Trump");
        }
        catch (IOException | JSONException ex) {
            ex.printStackTrace();
        }

    }

    public static IssueLab get(Context context) {
        if (sIssueLab == null) {
            sIssueLab = new IssueLab(context);
        }
        return sIssueLab;
    }

    public List<Issue> getIssues() {
        return mIssues;
    }

    public List<Issue> getIssuesByCandidate(String candidateName) {
        switch (candidateName) {
            case "none":
                return mIssues;
            case "Clinton":
                return mIssuesByClinton;
            case "Trump":
                return mIssuesByTrump;
            default:
                return mIssues;
        }
    }

    private List<Issue> filterIssuesByCandidate(String candidateName) {

        List<Issue> issuesFilteredByCandidate = new ArrayList<>();

        for (Issue issue : mIssues) {

            Issue issueWithFilteredQuotes = new Issue();
            issueWithFilteredQuotes.setId(issue.getId());
            issueWithFilteredQuotes.setTitle(issue.getTitle());

            for (Quote quote : issue.getQuotes()) {

                if (quote.getCandidate().equals(candidateName)) {

                    issueWithFilteredQuotes.getQuotes().add(quote);
                }
            }
            issuesFilteredByCandidate.add(issueWithFilteredQuotes);
        }
        return issuesFilteredByCandidate;
    }

    public Issue getIssue(String candidateName, int id) {
        switch (candidateName) {
            case "Clinton":
                for (Issue issue : mIssuesByClinton) {
                    if (issue.getId() == id) {
                        return issue;
                    }
                }
                return null;
            case "Trump":
                for (Issue issue : mIssuesByTrump) {
                    if (issue.getId() == id) {
                        return issue;
                    }
                }
                return null;
            default:
                for (Issue issue : mIssues) {
                    if (issue.getId() == id) {
                        return issue;
                    }
                }
                return null;
        }
    }

    private String readJSONFromRes(@RawRes int id, Context context) throws JSONException {
        StringBuilder builder = new StringBuilder();

        try {
            InputStream stream = context.getResources().openRawResource(id);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(stream));

            String line = "";

            while ((line = buffer.readLine()) != null) {
                builder.append(line);
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

        return builder.toString();
    }

    private void parseJSONFromRes(Context context) throws JSONException, IOException {
        String issuesJSONString = readJSONFromRes(R.raw.issues, context);

        JSONArray rootJSONArray = new JSONArray(issuesJSONString);

        for (int i = 0; i < rootJSONArray.length(); i++) {
            JSONObject issueJSONObject = rootJSONArray.getJSONObject(i);
            Issue issue = new Issue();
            issue.setId(i);
            issue.setTitle(issueJSONObject.getString("title"));

            ArrayList<Quote> quotes = new ArrayList<>();

            for (int j = 0; j < issueJSONObject.getJSONArray("quotes").length(); j++) {
                JSONObject quoteJSONObject = issueJSONObject.getJSONArray("quotes").getJSONObject(j);

                Quote quote = new Quote();
                quote.setId(j);
                quote.setCandidate(quoteJSONObject.getString("candidate"));
                quote.setQuote(quoteJSONObject.getString("quote"));
                quote.setDate(quoteJSONObject.getString("date"));
                quote.setSource(quoteJSONObject.getString("source"));

                quotes.add(quote);
            }

            issue.setQuotes(quotes);

            mIssues.add(issue);
        }
    }
}

