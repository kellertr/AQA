package com.aqa.aqa.Controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aqa.aqa.Firebase.FirebaseManager;
import com.aqa.aqa.R;

import java.util.ArrayList;

import model.Classroom;

/**
 * Created by trkeller on 10/23/15.
 */
public class ClassroomListFragment extends Fragment implements FirebaseManager.LoadingCompleteListener<Classroom>{
    public static final String TAG = ClassroomListFragment.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private ClassroomAdapter classroomAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.classroom_frag, container, false);

        mRecyclerView = (RecyclerView)view.findViewById( R.id.classroom_list_recycler );
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        classroomAdapter = new ClassroomAdapter(new ArrayList<Classroom>(), (AppCompatActivity)getActivity());
        mRecyclerView.setAdapter(classroomAdapter);

        FirebaseManager.get(getActivity()).listClassrooms( this );

        return view;
    }

    @Override
    public void handleResults(ArrayList<Classroom> results) {
        classroomAdapter.setClassroomList(results);
    }
}
