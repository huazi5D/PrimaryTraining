package com.li.primary.base.bean.vo;

/**
 * Created by liu on 2017/6/7.
 */

public class QuestionSingleVO {

    /**
     * questionid : 1
     * question : 关于货车防盗措施的说法不正确的是___。
     * choicea : 给车辆安装防盗装置
     * choiceb : 合理装载、苫盖严实
     * choicec : 在安全地点停车
     * choiced : 驾驶员短暂离开可不锁车门
     * answer : A
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
