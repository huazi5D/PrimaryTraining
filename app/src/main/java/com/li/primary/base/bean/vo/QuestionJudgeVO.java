package com.li.primary.base.bean.vo;

/**
 * Created by liu on 2017/6/7.
 */

public class QuestionJudgeVO {
    /**
     * questionid : 41
     * question : 问题问题问题
     * answer : R
     * score : 2
     */

    private int questionid;
    private String question;
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
