package com.aqa.aqa.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.aqa.aqa.Firebase.FirebaseManager;
import com.aqa.aqa.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

import model.Classroom;
import model.Question;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = LoginActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_login);
        FirebaseManager.get(this).postNewClassroom("JT's Classroom", "Justin Timberlake", new Firebase.CompletionListener() {
            @Override
            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                Log.d(TAG, "classroom added");
            }
        });

        FirebaseManager.get(this).listClassrooms(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Iterable<DataSnapshot> snapshotClassrooms = snapshot.getChildren();

                ArrayList<Classroom> classrooms = new ArrayList<>();
                for (DataSnapshot classroomSnapshot : snapshotClassrooms) {
                    Classroom classroom = classroomSnapshot.getValue(Classroom.class);
                    classroom.setClassRoomFirebaseId(classroomSnapshot.getKey());
                    classrooms.add(classroom);
                }

                postQuestion(classrooms.get(0));

                Log.d(TAG, "Classrooms:" + classrooms.toString());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.e(TAG, "FirebaseError" + firebaseError);
            }
        });

    }

    private void postQuestion(final Classroom classroom) {
        FirebaseManager.get(this).postQuestion("Who is the President of the US", "Donald Trump", classroom,
                new Firebase.CompletionListener() {
                    @Override
                    public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                        //Get the list of questions
                        FirebaseManager.get(LoginActivity.this).getQuestionsForClassroom(classroom.getClassRoomFirebaseId(),
                                new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        Iterable<DataSnapshot> snapshotQuestions = dataSnapshot.getChildren();

                                        ArrayList<Question> questions = new ArrayList<>();
                                        for (DataSnapshot questionSnapShot : snapshotQuestions) {
                                            Question question = questionSnapShot.getValue(Question.class);
                                            question.setQuestionFireBaseId(questionSnapShot.getKey());
                                            questions.add(question);
                                        }

                                        postAnswer(classroom, questions.get(0));
                                    }

                                    @Override
                                    public void onCancelled(FirebaseError firebaseError) {
                                    }
                                });
                    }
                });
    }

    private void postAnswer(Classroom classroom, Question question) {
        FirebaseManager.get(this).postAnswer(question.getQuestionFireBaseId(), classroom.getClassRoomFirebaseId(),
                "Barack Obama", new Firebase.CompletionListener() {
            @Override
            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                Log.v(TAG, "Answer posted");
            }
        });
    }
}
