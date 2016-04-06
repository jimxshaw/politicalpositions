package me.jimmyshaw.politicalpositions;


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
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class IssueLab {

    private static IssueLab sIssueLab;
    private List<Issue> mIssues;

    private IssueLab(Context context) {
        mIssues = new ArrayList<>();
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

    public Issue getIssue(int id) {
        for (Issue issue : mIssues) {
            if (issue.getId() == id) {
                return issue;
            }
        }
        return null;
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
        catch(IOException ex) {
            ex.printStackTrace();
        }

        return builder.toString();
    }

    private void parseJSONFromRes(Context context) throws JSONException, IOException {
        String issuesJSONString = readJSONFromRes(R.raw.issues2, context);

        JSONArray rootJSONObject = new JSONArray(issuesJSONString);

        for (int i = 0; i < rootJSONObject.length(); i++) {
            Issue issue = new Issue();


        }
    }
}
