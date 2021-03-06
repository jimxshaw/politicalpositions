package me.jimmyshaw.politicalpositions.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import me.jimmyshaw.politicalpositions.R;
import me.jimmyshaw.politicalpositions.activity.QuotePagerActivity;
import me.jimmyshaw.politicalpositions.model.Issue;
import me.jimmyshaw.politicalpositions.repo.IssueLab;
import me.jimmyshaw.politicalpositions.utilities.DividerItemDecoration;

public class IssueListFragment extends Fragment {

    private static final String ARG_CANDIDATE_NAME = "candidate_name";

    private RecyclerView mIssueListRecyclerView;
    private IssueAdapter mAdapter;

    private List<Issue> mIssues;

    private String mCandidateName;

    public static Fragment newInstance(String candidateName) {
        Bundle args = new Bundle();
        args.putString(ARG_CANDIDATE_NAME, candidateName);
        IssueListFragment issueListFragment = new IssueListFragment();
        issueListFragment.setArguments(args);

        return issueListFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCandidateName = getArguments().getString(ARG_CANDIDATE_NAME);
        mIssues = IssueLab.get(getActivity()).getIssuesByCandidate(mCandidateName);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_issue_list, container, false);

        mIssueListRecyclerView = (RecyclerView) view.findViewById(R.id.fragment_issue_list_recycler_view);
        mIssueListRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        mIssueListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private void updateUI() {
        mAdapter = new IssueAdapter(mIssues);
        mIssueListRecyclerView.setAdapter(mAdapter);
    }


    private class IssueHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {

        private Issue mIssue;
        private TextView mTitleTextView;
        private ImageView mIconImageView;

        public IssueHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mTitleTextView = (TextView) itemView.findViewById(R.id.fragment_issue_list_item_title);
            mIconImageView = (ImageView) itemView.findViewById(R.id.fragment_issue_list_item_icon);
        }

        public void bindIssue(Issue issue) {
            mIssue = issue;
            mTitleTextView.setText(issue.getTitle());

            switch (issue.getTitle()) {
                case "Energy":
                    mIconImageView.setImageResource(R.drawable.ic_issues_energy);
                    break;
                case "Veterans":
                    mIconImageView.setImageResource(R.drawable.ic_issues_veterans);
                    break;
                case "Taxes":
                    mIconImageView.setImageResource(R.drawable.ic_issues_taxes);
                    break;
                case "Defense":
                    mIconImageView.setImageResource(R.drawable.ic_issues_defense);
                    break;
                case "Health Care":
                    mIconImageView.setImageResource(R.drawable.ic_issues_health_care);
                    break;
                case "Foreign Policy":
                    mIconImageView.setImageResource(R.drawable.ic_issues_foreign_policy);
                    break;
                case "Civil Liberties":
                    mIconImageView.setImageResource(R.drawable.ic_issues_civil_liberties);
                    break;
                case "Education":
                    mIconImageView.setImageResource(R.drawable.ic_issues_education);
                    break;
                case "Crime & Safety":
                    mIconImageView.setImageResource(R.drawable.ic_issues_crime_safety);
                    break;
                case "Environment":
                    mIconImageView.setImageResource(R.drawable.ic_issues_environment);
                    break;
                case "Immigration":
                    mIconImageView.setImageResource(R.drawable.ic_issues_immigration);
                    break;
                case "Economy":
                    mIconImageView.setImageResource(R.drawable.ic_issues_economy);
                    break;
                case "Abortion":
                    mIconImageView.setImageResource(R.drawable.ic_issues_abortion);
                    break;
                default:
                    break;
            }
        }

        @Override
        public void onClick(View v) {
            Intent intent = QuotePagerActivity.newIntent(getActivity(), mCandidateName, mIssue.getId());
            startActivity(intent);
        }
    }


    private class IssueAdapter extends RecyclerView.Adapter<IssueHolder> {

        private List<Issue> mIssues;

        public IssueAdapter(List<Issue> issues) {
            mIssues = issues;
        }

        @Override
        public IssueHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.fragment_issue_list_item, parent, false);
            return new IssueHolder(view);
        }

        @Override
        public void onBindViewHolder(IssueHolder holder, int position) {
            Issue issue = mIssues.get(position);
            holder.bindIssue(issue);
        }

        @Override
        public int getItemCount() {
            return mIssues.size();
        }
    }
}
