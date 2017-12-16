package com.li.primary.base.bean.vo;

/**
 * Created by liu on 2017/6/7.
 */

public class QuestionCommonVO {
    private int questionid;
    private String question;
    private String choicea;
    private String choiceb;
    private String choicec;
    private String choiced;
    private String answer;
    private int score;
    //题的类型0：单选，1：多选，2判断
    private int type;
    //0是正确，1是错误，2是没有回答
    private int result = 2;
    //用户回答的答案
    private String reanswer = "";

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getReanswer() {
        return reanswer;
    }

    public void setReanswer(String reanswer) {
        this.reanswer = reanswer;
    }
}
