package me.jimmyshaw.politicalpositions;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

// IssueListActivity will host this IssueListFragment that contains a RecyclerView with its two usual
// utility classes, a ViewHolder and an Adapter. This Fragment's purpose to show our list of issues
// retrieved from IssueLab. A ViewHolder holds on to a view. An Adapter creates ViewHolders. It's a
// controller object that sits between the RecyclerView and the data set that the RecyclerView
// should display. Its responsibilities are creating the necessary ViewHolders and binding
// ViewHolders to data from the model layer.
public class IssueListFragment extends Fragment {

    private RecyclerView mIssueRecyclerView;
    private IssueAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_issue_list, container, false);

        mIssueRecyclerView = (RecyclerView) view.findViewById(R.id.issue_recycler_view);
        mIssueRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private void updateUI(){
        IssueLab issueLab = IssueLab.get(getActivity());

        List<Issue> issues = issueLab.getIssues();

        mAdapter = new IssueAdapter(issues);
        mIssueRecyclerView.setAdapter(mAdapter);
    }

    private class IssueHolder extends RecyclerView.ViewHolder
                                implements View.OnClickListener {
        private Issue mIssue;
        private TextView mTitleTextView;

        public IssueHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_issue_title_text_view);
        }

        public void bindIssue(Issue issue) {
            mIssue = issue;
            mTitleTextView.setText(issue.getTitle());
        }

        // When a particular issue is clicked from our list of issues, an intent is fired to start
        //
        @Override
        public void onClick(View v) {
            Intent intent = QuotePagerActivity.newIntent(getActivity(), mIssue.getId());
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
            View view = layoutInflater.inflate(R.layout.list_item_issue, parent, false);
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
