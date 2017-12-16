package com.li.primary.base.bean.vo;

import java.util.List;

/**
 * Created by liu on 2017/6/7.
 */

public class QuestionVO {
    private List<QuestionSingleVO> QuestionsingleselList;
    private List<QuestionMultipleVO> QuestionmultiselList;
    private List<QuestionJudgeVO> QuestionrorwList;
    private String paperid;

    public List<QuestionSingleVO> getQuestionsingleselList() {
        return QuestionsingleselList;
    }

    public void setQuestionsingleselList(List<QuestionSingleVO> questionsingleselList) {
        QuestionsingleselList = questionsingleselList;
    }

    public List<QuestionMultipleVO> getQuestionmultiselList() {
        return QuestionmultiselList;
    }

    public void setQuestionmultiselList(List<QuestionMultipleVO> questionmultiselList) {
        QuestionmultiselList = questionmultiselList;
    }

    public List<QuestionJudgeVO> getQuestionrorwList() {
        return QuestionrorwList;
    }

    public void setQuestionrorwList(List<QuestionJudgeVO> questionrorwList) {
        QuestionrorwList = questionrorwList;
    }

    public String getPaperid() {
        return paperid;
    }
}
