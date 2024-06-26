package com.mastercoding.bakalaurinis.view.menus;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mastercoding.bakalaurinis.R;
import com.mastercoding.bakalaurinis.data.LevelsData;
import com.mastercoding.bakalaurinis.view.questions.QuestionActivity;

import java.util.List;


public class TopicsListFragment extends ListFragment {

    private List<String> topics;
    private String levelName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (getArguments() != null) {
            levelName = getArguments().getString("levelName", "");
        }
        LevelsData levelsData = new LevelsData();
        topics = levelsData.getTopicsForLevel(levelName);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                inflater.getContext(), R.layout.topics_list,
                topics);
        setListAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView listView = getListView();
        listView.setPadding(100, 0, 100, 20);
        listView.setClipToPadding(false);
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent intent = new Intent(getActivity(), QuestionActivity.class);
        Bundle args = new Bundle();
        if (getArguments() != null) {
            levelName = getArguments().getString("levelName", "");
        }
        String topicName = topics.get(position);
        args.putString("levelName", levelName); //Passing to Question Activity selected level and topic
        args.putString("topicName", topicName);
        intent.putExtras(args);
        startActivity(intent);
    }
}