package me.jimmyshaw.politicalpositions;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AboutScreenFragment extends Fragment {

    private TextView mAboutScreenBody;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about_screen, container, false);

        mAboutScreenBody = (TextView) view.findViewById(R.id.about_screen_body_text_view);
        mAboutScreenBody.setText(R.string.about_screen_body);

        return view;
    }
}
