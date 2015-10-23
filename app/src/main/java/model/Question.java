package model;

/**
 * Created by trkeller on 10/22/15.
 */
public class Question {

    private String question = "";
    private String answer = "";
    private String questioner = "";
    private String questionFireBaseId = "";


    public Question(){
        //Needed for Firebase
    }

    public Question( String question, String answer, String questioner, String questionFireBaseId ){
        this.question = question;
        this.answer = answer;
        this.questioner = questioner;
        this.questionFireBaseId = questionFireBaseId;
    }

    public Question( String question, String questioner ){
        this.question = question;
        this.questioner = questioner;
    }


    public String getAnswer() {
        return answer;
    }
    public String getQuestioner(){
        return questioner;
    }

    public String getQuestion(){
        return question;
    }

    public void setAnswer( String answer ){
        this.answer = answer;
    }

    public String getQuestionFireBaseId(){
        return questionFireBaseId;
    }

    public void setQuestionFireBaseId(String fireBaseId){
        this.questionFireBaseId = fireBaseId;
    }

    @Override
    public String toString(){

        return new StringBuilder()
                .append("answer:" + answer)
                .append("questioner" + questioner)
                .append("question" + question)
                .toString();
    }

}
