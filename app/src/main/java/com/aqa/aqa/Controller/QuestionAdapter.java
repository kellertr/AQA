package com.aqa.aqa.Controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aqa.aqa.R;

import java.util.List;

import model.Question;

/**
 * Created by trkeller on 10/23/15.
 */
public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder> {

    private List<Question> questions;
    private Context mContext;

    public QuestionAdapter( List<Question> questions, Context context ){
        this.questions = questions;
        this.mContext = context;
    }

    @Override
    public QuestionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new QuestionViewHolder(LayoutInflater.from(mContext).inflate( R.layout.question_item_holder,
                parent,false));
    }

    @Override
    public void onBindViewHolder(QuestionViewHolder holder, int position) {
        holder.bindQuestionView( questions.get(position) );
    }

    public void setQuestions( List<Question> questions ){
        this.questions = questions;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public class QuestionViewHolder extends RecyclerView.ViewHolder{

        private TextView questionTextView;
        private TextView answer;
        private TextView questioner;

        public QuestionViewHolder(View itemView) {
            super(itemView);

            questionTextView = (TextView) itemView.findViewById(R.id.question_text );
            answer = (TextView) itemView.findViewById( R.id.answer_text );
            questioner = (TextView) itemView.findViewById(R.id.questioner);
        }

        public void bindQuestionView( Question question ){
            questionTextView.setText( question.getQuestion() );

            if( question.getQuestioner() == null || question.getQuestioner().equals("") ){
                questioner.setText( mContext.getString(R.string.anonymous) );
            }

            if( question.getAnswer() != null && question.getAnswer().length() > 0 ){
                answer.setVisibility(View.VISIBLE);
                answer.setText( question.getAnswer() );
            }
        }
    }
}
