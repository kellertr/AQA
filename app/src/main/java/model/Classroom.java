package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by trkeller on 10/22/15.
 */
public class Classroom {

    private String teacherId = "";
    private String classroomName = "";
    private String classRoomOwner = "";
    private String classRoomFirebaseId = "";

    public Classroom(){
        //Needed for Firebase
    }

    public Classroom( String teacherId, String classroomName, String classRoomOwner ){
        this.teacherId = teacherId;
        this.classroomName = classroomName;
        this.classRoomOwner = classRoomOwner;
    }

    public String getClassRoomOwner() {
        return classRoomOwner;
    }

    public String getClassroomName() {
        return classroomName;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public String getClassRoomFirebaseId() {
        return classRoomFirebaseId;
    }

    public void setClassRoomFirebaseId(String classRoomFirebaseId) {
        this.classRoomFirebaseId = classRoomFirebaseId;
    }

    @Override
    public String toString(){
        return new StringBuilder()
                .append("teacherId:" + teacherId)
                .append("classRoomFireBaseId:" + classRoomFirebaseId)
                .append("classRoomName:" + classroomName)
                .append("classRoomOwner:" + classRoomOwner)
                .toString();
    }
}
