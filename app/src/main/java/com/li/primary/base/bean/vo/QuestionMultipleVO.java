package com.li.primary.base.bean.vo;

/**
 * Created by liu on 2017/6/7.
 */

public class QuestionMultipleVO {
    /**
     * questionid : 38
     * question : 问题问题问题
     * choicea : 选项一
     * choiceb : 选项二
     * choicec : 选项三
     * choiced : 选项四
     * answer : AD
     * score : 2
     */

    private int questionid;
    private String question;
    private String choicea;
    private String choiceb;
    private String choicec;
    private String choiced;
    private String answer;
    private int score;

    public int getQuestionid() {
        return questionid;
    }

    public void setQuestionid(int questionid) {
        this.questionid = questionid;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getChoicea() {
        return choicea;
    }

    public void setChoicea(String choicea) {
        this.choicea = choicea;
    }

    public String getChoiceb() {
        return choiceb;
    }

    public void setChoiceb(String choiceb) {
        this.choiceb = choiceb;
    }

    public String getChoicec() {
        return choicec;
    }

    public void setChoicec(String choicec) {
        this.choicec = choicec;
    }

    public String getChoiced() {
        return choiced;
    }

    public void setChoiced(String choiced) {
        this.choiced = choiced;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
