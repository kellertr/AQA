package com.aqa.aqa.Controller;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.aqa.aqa.Firebase.FirebaseManager;
import com.aqa.aqa.R;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;

import model.Classroom;
import model.Question;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Firebase.setAndroidContext(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.main_container,
                new ClassroomListFragment(), ClassroomListFragment.TAG);
        transaction.addToBackStack(null);
        transaction.commit();

    }

    private void postQuestion(final Classroom classroom) {
        FirebaseManager.get(this).postQuestion("Who is the President of the US", "Donald Trump", classroom,
                new Firebase.CompletionListener() {
                    @Override
                    public void onComplete(FirebaseError firebaseError, Firebase firebase) {
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
