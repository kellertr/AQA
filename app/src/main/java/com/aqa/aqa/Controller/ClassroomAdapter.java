package com.aqa.aqa.Controller;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aqa.aqa.R;

import java.util.List;

import model.Classroom;

/**
 * Created by trkeller on 10/23/15.
 */
public class ClassroomAdapter extends RecyclerView.Adapter<ClassroomAdapter.ClassRoomHolder> {

    private List<Classroom> classroomList;
    private AppCompatActivity mActivity;

    public ClassroomAdapter(List<Classroom> classrooms, AppCompatActivity activity) {
        classroomList = classrooms;
        mActivity = activity;
    }

    public void setClassroomList(List<Classroom> classrooms) {
        classroomList = classrooms;
        notifyDataSetChanged();
    }

    @Override
    public ClassRoomHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ClassRoomHolder(LayoutInflater.from(mActivity).inflate(R.layout.classroom_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ClassRoomHolder holder, int position) {
        holder.bindClassroom(classroomList.get(position));
    }

    @Override
    public int getItemCount() {
        return classroomList.size();
    }

    public class ClassRoomHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView owner;

        private Classroom classroom;

        public ClassRoomHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                FragmentTransaction transaction = mActivity.getSupportFragmentManager().beginTransaction();
                                                transaction.replace(R.id.main_container,
                                                        QuestionListFragment.newInstance(classroom.getClassRoomFirebaseId()));
                                                transaction.addToBackStack(null);
                                                transaction.commit();
                                            }

                                        }

            );

            title = (TextView) itemView.findViewById(R.id.classroom_name);
            owner = (TextView) itemView.findViewById(R.id.classroom_owner);
        }

        public void bindClassroom(Classroom classroom) {
            this.classroom = classroom;
            title.setText(classroom.getClassroomName());
            owner.setText(classroom.getClassRoomOwner());
        }
    }
}


