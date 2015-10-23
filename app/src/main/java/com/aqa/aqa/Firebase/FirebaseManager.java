package com.aqa.aqa.Firebase;

import android.content.Context;
import android.provider.Settings;

import com.firebase.client.Firebase;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;

import model.Classroom;
import model.Question;

/**
 * Created by trkeller on 10/22/15.
 */
public class FirebaseManager {
    private static final String TAG = FirebaseManager.class.getSimpleName();
    private static final String FIREBASE_BASE_URL =  "https://shining-fire-8522.firebaseio.com/";
    private static final String CLASSROOMS = "classrooms";
    private static final String QUESTIONS = "questions";
    private static final String ANSWER = "answer";

    private static FirebaseManager sFirebaseManager;

    private String deviceId;
    private Context mContext;
    private Firebase firebase;

    public static FirebaseManager get( Context context ){
        if( sFirebaseManager == null ){
            sFirebaseManager = new FirebaseManager(context);
        }

        return sFirebaseManager;
    }

    private FirebaseManager( Context context ){
        mContext = context;
        deviceId = Settings.Secure.getString(mContext.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        firebase = new Firebase(FIREBASE_BASE_URL);
    }

    public void postNewClassroom( String classroomTitle, String classRoomOwner,
                                  Firebase.CompletionListener completionListener){
        Classroom classroom = new Classroom( deviceId, classroomTitle, classRoomOwner);
        Firebase resolvedFirebasePath = firebase.child(CLASSROOMS).push();
        resolvedFirebasePath.setValue(classroom, completionListener);
    }

    public void listClassrooms( ValueEventListener listener ){
        Firebase resolvedClassroomRef = firebase.child(CLASSROOMS);
        resolvedClassroomRef.addListenerForSingleValueEvent(listener);
    }

    public void postQuestion(String question, String questioner, Classroom classroom,
                             Firebase.CompletionListener listener ){
        Question q = new Question( question, questioner);
        Firebase resolvedFirebasePath = firebase.child(QUESTIONS).child(classroom.getClassRoomFirebaseId()).push();
        resolvedFirebasePath.setValue(q, listener);
    }

    public void getQuestionsForClassroom( String classRoomId, ValueEventListener listener ){
        Firebase resolvedClassroomRef = firebase.child(QUESTIONS).child(classRoomId);
        resolvedClassroomRef.addListenerForSingleValueEvent(listener);
    }

    public void postAnswer( String fireBaseId, String classRoomId,  String answer,
                            Firebase.CompletionListener listener ){
        Firebase resolvedQuestionRef = firebase.child(QUESTIONS).child(classRoomId).child(fireBaseId);

        HashMap<String, Object> updateMap = new HashMap<>();
        updateMap.put( ANSWER, answer );

        resolvedQuestionRef.updateChildren( updateMap, listener);
    }

}
