package models;

import java.util.ArrayList;

public class QuestionList {
    private ArrayList<Question> questions = new ArrayList<Question>();

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }
    public void appendQuestion(Question question){
        this.questions.add(question);
    }
}
