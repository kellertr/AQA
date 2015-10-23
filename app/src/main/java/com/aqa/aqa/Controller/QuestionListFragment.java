package com.aqa.aqa.Controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aqa.aqa.Firebase.FirebaseManager;
import com.aqa.aqa.R;

import java.util.ArrayList;

import model.Question;

/**
 * Created by trkeller on 10/23/15.
 */
public class QuestionListFragment extends Fragment implements FirebaseManager.LoadingCompleteListener<Question> {
    public static final String TAG = QuestionListFragment.class.getSimpleName();
    private static final String CLASSROOM_ID = "classroom_id";

    private RecyclerView mRecyclerView;
    private QuestionAdapter adapter;
    private String classRoomId;

    public static QuestionListFragment newInstance( String classRoomId ){
        QuestionListFragment questionListFragment = new QuestionListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(CLASSROOM_ID, classRoomId);
        questionListFragment.setArguments(bundle);
        return questionListFragment;
    }

    @Override
    public void onCreate( Bundle savedInstanceState ){
        super.onCreate(savedInstanceState);
        classRoomId = getArguments().getString(CLASSROOM_ID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.question_frag, container, false );

        mRecyclerView = (RecyclerView) view.findViewById( R.id.question_list_recycler );
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        adapter = new QuestionAdapter( new ArrayList<Question>(), getActivity());

        mRecyclerView.setAdapter( adapter );

        FirebaseManager.get( getActivity() ).getQuestionsForClassroom( classRoomId, this );

        return view;
    }

    @Override
    public void handleResults(ArrayList<Question> results) {
        adapter.setQuestions(results);
    }
}
