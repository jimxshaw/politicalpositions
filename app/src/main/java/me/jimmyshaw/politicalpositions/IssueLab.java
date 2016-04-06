package me.jimmyshaw.politicalpositions;


import android.content.Context;

import java.util.ArrayList;
import java.util.List;

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
            if (issue.getId().equals(id)) {
                return issue;
            }
        }
        return null;
    }
}
