package com.li.primary.main.mine;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.li.primary.R;
import com.li.primary.base.AppData;
import com.li.primary.base.BaseActivity;
import com.li.primary.base.bean.BaseResult;
import com.li.primary.base.bean.QuestionResult;
import com.li.primary.base.bean.vo.QuestionCommonVO;
import com.li.primary.base.bean.vo.QuestionJudgeVO;
import com.li.primary.base.bean.vo.QuestionMultipleVO;
import com.li.primary.base.bean.vo.QuestionSingleVO;
import com.li.primary.base.http.HttpService;
import com.li.primary.base.http.RetrofitUtil;
import com.li.primary.main.identify.ScanActivity;
import com.li.primary.util.UtilIntent;
import com.li.primary.util.UtilSPutil;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

import static rx.android.schedulers.AndroidSchedulers.mainThread;
import static rx.schedulers.Schedulers.io;

/**
 * Created by liu on 2017/6/7.
 */

public class ExamActivity extends BaseActivity implements View.OnClickListener {
    private TextView mTvType;
    private ImageView mIvBack;
    private ImageView mIvA;
    private ImageView mIvB;
    private ImageView mIvC;
    private ImageView mIvD;
    private TextView mTvDesc;
    private TextView mTvA;
    private TextView mTvB;
    private TextView mTvC;
    private TextView mTvD;
    private TextView mTvIndex;
    private Button mBtnOk;
    private LinearLayout mLlSingle;
    private LinearLayout mLlA;
    private LinearLayout mLlB;
    private LinearLayout mLlC;
    private LinearLayout mLlD;
    private LinearLayout mLlW;
    private LinearLayout mLlR;
    private ImageView mIvR;
    private ImageView mIvW;
    private TextView mTvState;
    private Button mBtnCommit;
    private Button mBtnAgain;
    private TextView mTvUp;
    private TextView mTvNext;
    private RelativeLayout mRlBottomLayout;
    private List<QuestionCommonVO> data = new ArrayList<>();
    private int currentIndex = 0;
    private int total = 0;
    private QuestionCommonVO commonVO;
    private List<String> answers = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        initView();
        getData(AppData.paperid);
    }

    private void getData(String paperid) {
        RetrofitUtil.getInstance().create(HttpService.class).getQuestionList(AppData.token, paperid).subscribeOn(io()).observeOn(mainThread()).subscribe(new Subscriber<QuestionResult>() {

            @Override
            public void onStart() {
                super.onStart();
                showProgressDialog();
            }

            @Override
            public void onCompleted() {
                removeProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                removeProgressDialog();
                e.printStackTrace();
            }

            @Override
            public void onNext(QuestionResult result) {
                if (result.isStatus()) {
                    mRlBottomLayout.setVisibility(View.VISIBLE);
                    initData(result);
                    updateUI(0);
                    AppData.paperid = result.getData().getPaperid();
                }
            }
        });
    }

    private void initView() {
        mTvType = (TextView) findViewById(R.id.tv_type);
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mIvA = (ImageView) findViewById(R.id.iv_a);
        mIvB = (ImageView) findViewById(R.id.iv_b);
        mIvC = (ImageView) findViewById(R.id.iv_c);
        mIvD = (ImageView) findViewById(R.id.iv_d);
        mTvDesc = (TextView) findViewById(R.id.tv_desc);
        mTvA = (TextView) findViewById(R.id.tv_a);
        mTvB = (TextView) findViewById(R.id.tv_b);
        mTvC = (TextView) findViewById(R.id.tv_c);
        mTvD = (TextView) findViewById(R.id.tv_d);
        mTvIndex = (TextView) findViewById(R.id.tv_index);
        mTvNext = (TextView) findViewById(R.id.tv_next);
        mTvNext.setOnClickListener(this);
        mIvBack.setOnClickListener(this);
        mLlSingle = (LinearLayout) findViewById(R.id.ll_single);
        mBtnOk = (Button) findViewById(R.id.btn_ok);
        mBtnOk.setOnClickListener(this);
        mLlA = (LinearLayout) findViewById(R.id.ll_a);
        mLlB = (LinearLayout) findViewById(R.id.ll_b);
        mLlC = (LinearLayout) findViewById(R.id.ll_c);
        mLlD = (LinearLayout) findViewById(R.id.ll_d);
        mLlA.setTag(0);
        mLlB.setTag(0);
        mLlC.setTag(0);
        mLlD.setTag(0);
        mLlA.setOnClickListener(this);
        mLlB.setOnClickListener(this);
        mLlC.setOnClickListener(this);
        mLlD.setOnClickListener(this);
        mTvState = (TextView) findViewById(R.id.tv_state);
        mBtnCommit = (Button) findViewById(R.id.btn_commit);
        mBtnAgain = (Button) findViewById(R.id.btn_again);
        mTvUp = (TextView) findViewById(R.id.tv_up);
        mBtnCommit.setOnClickListener(this);
        mBtnAgain.setOnClickListener(this);
        mTvUp.setOnClickListener(this);
        mLlR = (LinearLayout) findViewById(R.id.ll_r);
        mLlW = (LinearLayout) findViewById(R.id.ll_w);
        mIvR = (ImageView) findViewById(R.id.iv_r);
        mIvW = (ImageView) findViewById(R.id.iv_w);
        mLlR.setOnClickListener(this);
        mLlW.setOnClickListener(this);
        mRlBottomLayout = (RelativeLayout) findViewById(R.id.rl_bottom_layout);
    }

    private void initData(QuestionResult result) {
        if (result != null && result.getData() != null) {
            currentIndex = 0;
            data.clear();
            List<QuestionSingleVO> QuestionsingleselList = result.getData().getQuestionsingleselList();
            if (QuestionsingleselList != null && QuestionsingleselList.size() > 0) {
                for (int i = 0; i < QuestionsingleselList.size(); i++) {
                    QuestionSingleVO singleVO = QuestionsingleselList.get(i);
                    QuestionCommonVO vo = new QuestionCommonVO();
                    vo.setAnswer(singleVO.getAnswer());
                    vo.setChoicea(singleVO.getChoicea());
                    vo.setChoiceb(singleVO.getChoiceb());
                    vo.setChoicec(singleVO.getChoicec());
                    vo.setChoiced(singleVO.getChoiced());
                    vo.setQuestion(singleVO.getQuestion());
                    vo.setScore(singleVO.getScore());
                    vo.setType(0);
                    data.add(vo);
                }
            }
            List<QuestionMultipleVO> QuestionmultiselList = result.getData().getQuestionmultiselList();
            if (QuestionmultiselList != null && QuestionmultiselList.size() > 0) {
                for (int i = 0; i < QuestionmultiselList.size(); i++) {
                    QuestionMultipleVO multipleVO = QuestionmultiselList.get(i);
                    QuestionCommonVO vo = new QuestionCommonVO();
                    vo.setAnswer(multipleVO.getAnswer());
                    vo.setChoicea(multipleVO.getChoicea());
                    vo.setChoiceb(multipleVO.getChoiceb());
                    vo.setChoicec(multipleVO.getChoicec());
                    vo.setChoiced(multipleVO.getChoiced());
                    vo.setQuestion(multipleVO.getQuestion());
                    vo.setScore(multipleVO.getScore());
                    vo.setType(1);
                    data.add(vo);
                }
            }
            List<QuestionJudgeVO> QuestionrorwList = result.getData().getQuestionrorwList();
            if (QuestionrorwList != null && QuestionrorwList.size() > 0) {
                for (int i = 0; i < QuestionrorwList.size(); i++) {
                    QuestionJudgeVO judgeVO = QuestionrorwList.get(i);
                    QuestionCommonVO vo = new QuestionCommonVO();
                    vo.setAnswer(judgeVO.getAnswer());
                    vo.setQuestion(judgeVO.getQuestion());
                    vo.setScore(judgeVO.getScore());
                    vo.setType(2);
                    data.add(vo);
                }
            }
            total = data.size();
        }
    }

    private void updateUI(int type) {
        if(data.size() == 0){
            return;
        }
        mIvA.setImageResource(R.mipmap.a_unselect);
        mIvB.setImageResource(R.mipmap.b_unselect);
        mIvC.setImageResource(R.mipmap.c_unselect);
        mIvD.setImageResource(R.mipmap.d_unselect);
        mIvR.setImageResource(R.mipmap.r_unselect);
        mIvW.setImageResource(R.mipmap.w_unselect);
        mLlA.setTag(0);
        mLlB.setTag(0);
        mLlC.setTag(0);
        mLlD.setTag(0);
        mLlSingle.setVisibility(View.VISIBLE);
        answers.clear();
        commonVO = data.get(currentIndex);
        updateState();
        if (currentIndex == total - 1) {
            mTvNext.setVisibility(View.GONE);
        } else {
            mTvNext.setVisibility(View.VISIBLE);
        }
        if (currentIndex == 0) {
            mTvUp.setVisibility(View.GONE);
        } else {
            mTvUp.setVisibility(View.VISIBLE);
        }
        switch (type) {
            case 0:
                break;
            case 1: {
                ObjectAnimator animator = ObjectAnimator.ofFloat(mLlSingle, "x", 1080, 0);
                animator.setDuration(800);
                animator.start();
            }
            break;

            case 2: {
                ObjectAnimator animator = ObjectAnimator.ofFloat(mLlSingle, "x", -1080, 0);
                animator.setDuration(800);
                animator.start();
            }
            break;
        }

        mTvIndex.setText((currentIndex + 1) + "/" + total);
        mTvDesc.setText((currentIndex + 1) + "、" + commonVO.getQuestion());
        switch (commonVO.getType()) {
            case 0: {
                mTvType.setText("单选题");
                mBtnOk.setVisibility(View.GONE);
                mLlA.setVisibility(View.VISIBLE);
                mLlB.setVisibility(View.VISIBLE);
                mLlC.setVisibility(View.VISIBLE);
                mLlD.setVisibility(View.VISIBLE);
                mLlW.setVisibility(View.GONE);
                mLlR.setVisibility(View.GONE);
                setABCDValue();
            }
            break;

            case 1: {
                mTvType.setText("多选题");
                mLlA.setVisibility(View.VISIBLE);
                mLlB.setVisibility(View.VISIBLE);
                mLlC.setVisibility(View.VISIBLE);
                mLlD.setVisibility(View.VISIBLE);
                mLlW.setVisibility(View.GONE);
                mLlR.setVisibility(View.GONE);
                if(commonVO.getResult() != 2){
                    mBtnOk.setVisibility(View.GONE);
                }else{
                    mBtnOk.setVisibility(View.VISIBLE);
                }
                setABCDValue();
            }
            break;

            case 2: {
                mTvType.setText("判断题");
                mBtnOk.setVisibility(View.GONE);
                mLlA.setVisibility(View.GONE);
                mLlB.setVisibility(View.GONE);
                mLlC.setVisibility(View.GONE);
                mLlD.setVisibility(View.GONE);
                mLlW.setVisibility(View.VISIBLE);
                mLlR.setVisibility(View.VISIBLE);
            }
            break;
        }

    }

    private void setABCDValue() {
        mTvA.setText(commonVO.getChoicea());
        mTvB.setText(commonVO.getChoiceb());
        mTvC.setText(commonVO.getChoicec());
        mTvD.setText(commonVO.getChoiced());
    }

    private void updateState() {
        switch (commonVO.getResult()) {
            case 0: {
                mTvState.setVisibility(View.VISIBLE);
                mTvState.setText("恭喜您，回答正确！");
                mTvState.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.right, 0, 0, 0);
                mTvState.setTextColor(0xff00a456);
                if (commonVO.getType() == 0) {
                    if (commonVO.getReanswer().equals("A")) {
                        mIvA.setImageResource(R.mipmap.a_select);
                        mLlA.setTag(1);
                    } else if (commonVO.getReanswer().equals("B")) {
                        mIvB.setImageResource(R.mipmap.b_select);
                        mLlB.setTag(1);
                    } else if (commonVO.getReanswer().equals("C")) {
                        mIvC.setImageResource(R.mipmap.c_select);
                        mLlC.setTag(1);
                    } else if (commonVO.getReanswer().equals("D")) {
                        mIvD.setImageResource(R.mipmap.d_select);
                        mLlD.setTag(1);
                    }
                } else if (commonVO.getType() == 1) {
                    String[] answers = commonVO.getReanswer().split("-");
                    for (int i = 0; i < answers.length; i++) {
                        if (answers[i].equals("A")) {
                            mIvA.setImageResource(R.mipmap.a_select);
                            mLlA.setTag(1);
                        } else if (answers[i].equals("B")) {
                            mIvB.setImageResource(R.mipmap.b_select);
                            mLlB.setTag(1);
                        } else if (answers[i].equals("C")) {
                            mIvC.setImageResource(R.mipmap.c_select);
                            mLlC.setTag(1);
                        } else if (answers[i].equals("D")) {
                            mIvD.setImageResource(R.mipmap.d_select);
                            mLlD.setTag(1);
                        }
                    }
                }else if(commonVO.getType() == 2){
                    String answer = commonVO.getReanswer().toUpperCase();
                    if(answer.equals("W")){
                        mIvW.setImageResource(R.mipmap.w_select);
                    }else if(answer.equals("R")){
                        mIvR.setImageResource(R.mipmap.r_select);
                    }
                }
            }
            break;

            case 1: {
                mTvState.setVisibility(View.VISIBLE);
                String[] answers = commonVO.getAnswer().split("-");
                String temp = "";
                if (answers != null) {
                    for (int i = 0; i < answers.length; i++) {
                        temp += answers[i];
                    }
                }
                mTvState.setText("回答错误，再接再厉！正确答案：" + temp);
                mTvState.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.error, 0, 0, 0);
                mTvState.setTextColor(0xfff74b4f);
                if (commonVO.getType() == 0) {
                    if (commonVO.getReanswer().equals("A")) {
                        mIvA.setImageResource(R.mipmap.a_select);
                        mLlA.setTag(1);
                    } else if (commonVO.getReanswer().equals("B")) {
                        mIvB.setImageResource(R.mipmap.b_select);
                        mLlB.setTag(1);
                    } else if (commonVO.getReanswer().equals("C")) {
                        mIvC.setImageResource(R.mipmap.c_select);
                        mLlC.setTag(1);
                    } else if (commonVO.getReanswer().equals("D")) {
                        mIvD.setImageResource(R.mipmap.d_select);
                        mLlD.setTag(1);
                    }
                } else if (commonVO.getType() == 1) {
                    String[] answersTemp = commonVO.getReanswer().split("-");
                    for (int i = 0; i < answersTemp.length; i++) {
                        if (answersTemp[i].equals("A")) {
                            mIvA.setImageResource(R.mipmap.a_select);
                            mLlA.setTag(1);
                        } else if (answersTemp[i].equals("B")) {
                            mIvB.setImageResource(R.mipmap.b_select);
                            mLlB.setTag(1);
                        } else if (answersTemp[i].equals("C")) {
                            mIvC.setImageResource(R.mipmap.c_select);
                            mLlC.setTag(1);
                        } else if (answersTemp[i].equals("D")) {
                            mIvD.setImageResource(R.mipmap.d_select);
                            mLlD.setTag(1);
                        }
                    }
                }else if(commonVO.getType() == 2){
                    String answer = commonVO.getReanswer().toUpperCase();
                    if(answer.equals("W")){
                        mIvW.setImageResource(R.mipmap.w_select);
                    }else if(answer.equals("R")){
                        mIvR.setImageResource(R.mipmap.r_select);
                    }
                }
            }
            break;

            case 2:
                mTvState.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_next:
                currentIndex++;
                updateUI(1);
                break;

            case R.id.tv_up:
                currentIndex--;
                updateUI(2);
                break;

            case R.id.iv_back:
                onBackPressed();
                break;

            case R.id.ll_a: {
                String answer = commonVO.getAnswer().toUpperCase();
                if (commonVO.getResult() != 2) {
                    return;
                }
                if (commonVO.getType() == 0) {
                    if (answer.equals("A")) {
                        commonVO.setResult(0);
                    } else {
                        commonVO.setResult(1);
                    }
                    commonVO.setReanswer("A");
                    mIvA.setImageResource(R.mipmap.a_select);
                    updateState();
                    calculate(false);
                } else if (commonVO.getType() == 1) {
                    int mark = (int) v.getTag();
                    if (mark == 0) {
                        mLlA.setTag(1);
                        answers.add("A");
                        mIvA.setImageResource(R.mipmap.a_select);
                    } else {
                        mLlA.setTag(0);
                        answers.remove("A");
                        mIvA.setImageResource(R.mipmap.a_unselect);
                    }
                }
            }
            break;

            case R.id.ll_b: {
                String answer = commonVO.getAnswer().toUpperCase();
                if (commonVO.getResult() != 2) {
                    return;
                }
                if (commonVO.getType() == 0) {
                    if (answer.equals("B")) {
                        commonVO.setResult(0);
                    } else {
                        commonVO.setResult(1);
                    }
                    commonVO.setReanswer("B");
                    mIvB.setImageResource(R.mipmap.b_select);
                    updateState();
                    calculate(false);
                } else if (commonVO.getType() == 1) {
                    int mark = (int) v.getTag();
                    if (mark == 0) {
                        mLlB.setTag(1);
                        answers.add("B");
                        mIvB.setImageResource(R.mipmap.b_select);
                    } else {
                        mLlB.setTag(0);
                        answers.remove("B");
                        mIvB.setImageResource(R.mipmap.b_unselect);
                    }
                }
            }
            break;

            case R.id.ll_c: {
                String answer = commonVO.getAnswer().toUpperCase();
                if (commonVO.getResult() != 2) {
                    return;
                }
                if (commonVO.getType() == 0) {
                    if (answer.equals("C")) {
                        commonVO.setResult(0);
                    } else {
                        commonVO.setResult(1);
                    }
                    commonVO.setReanswer("C");
                    mIvC.setImageResource(R.mipmap.c_select);
                    updateState();
                    calculate(false);
                } else if (commonVO.getType() == 1) {
                    int mark = (int) v.getTag();
                    if (mark == 0) {
                        mLlC.setTag(1);
                        answers.add("C");
                        mIvC.setImageResource(R.mipmap.c_select);
                    } else {
                        mLlC.setTag(0);
                        answers.remove("C");
                        mIvC.setImageResource(R.mipmap.c_unselect);
                    }
                }
            }
            break;

            case R.id.ll_d: {
                String answer = commonVO.getAnswer().toUpperCase();
                if (commonVO.getResult() != 2) {
                    return;
                }
                if (commonVO.getType() == 0) {
                    if (answer.equals("D")) {
                        commonVO.setResult(0);
                    } else {
                        commonVO.setResult(1);
                    }
                    commonVO.setReanswer("D");
                    mIvD.setImageResource(R.mipmap.d_select);
                    updateState();
                    calculate(false);
                } else if (commonVO.getType() == 1) {
                    int mark = (int) v.getTag();
                    if (mark == 0) {
                        mLlD.setTag(1);
                        answers.add("D");
                        mIvD.setImageResource(R.mipmap.d_select);
                    } else {
                        mLlD.setTag(0);
                        answers.remove("D");
                        mIvD.setImageResource(R.mipmap.d_unselect);
                    }
                }
            }
            break;


            case R.id.ll_w:{
                String answer = commonVO.getAnswer().toUpperCase();
                if(commonVO.getResult() != 2){
                    return;
                }
                if(answer.equals("W")){
                    commonVO.setResult(0);
                }else{
                    commonVO.setResult(1);
                }
                commonVO.setReanswer("W");
                mIvW.setImageResource(R.mipmap.w_select);
                updateState();
                calculate(false);
            }
                break;

            case R.id.ll_r:{
                String answer = commonVO.getAnswer().toUpperCase();
                if(commonVO.getResult() != 2){
                    return;
                }
                if(answer.equals("R")){
                    commonVO.setResult(0);
                }else{
                    commonVO.setResult(1);
                }
                commonVO.setReanswer("R");
                mIvR.setImageResource(R.mipmap.r_select);
                updateState();
                calculate(false);
            }
                break;

            case R.id.btn_ok:{
                if(answers.size() == 0){
                    showToast("请选择您的答案");
                    return;
                }
                mBtnOk.setVisibility(View.GONE);
                String answerTemps[] = commonVO.getAnswer().split("-");
                String answerTemp = "";
                if(answers.size() == 1){
                    answerTemp += answers.get(0);
                }else if(answers.size() == 2){
                    answerTemp += answers.get(0) + "-" + answers.get(1);
                }else{
                    for (int i = 0; i < answers.size(); i++){
                        if(i == 0){
                            answerTemp += answers.get(i);
                        }else {
                            answerTemp += "-" + answers.get(i);
                        }
                    }
                }
                if(answers.size() != answerTemps.length){
                    commonVO.setResult(1);
                    commonVO.setReanswer(answerTemp);
                    mTvState.setVisibility(View.VISIBLE);
                    String[] answers = commonVO.getAnswer().split("-");
                    String temp = "";
                    if (answers != null) {
                        for (int i = 0; i < answers.length; i++) {
                            if(i == 0) {
                                temp += answers[i];
                            }else{
                                temp += "、" + answers[i];
                            }
                        }
                    }
                    mTvState.setText("回答错误，再接再厉！正确答案：" + temp);
                    mTvState.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.error, 0, 0, 0);
                    mTvState.setTextColor(0xfff74b4f);
                }else {
                    boolean isTrue = false;
                    int num = 0;
                    for (int i = 0; i < answers.size(); i++){
                        for (int j = 0; j < answerTemps.length; j++){
                            if(answers.get(i).equals(answerTemps[j])){
                                num++;
                            }
                        }
                    }
                    if(num == answers.size()){
                        commonVO.setResult(0);
                        commonVO.setReanswer(answerTemp);
                        mTvState.setVisibility(View.VISIBLE);
                        mTvState.setText("恭喜您，回答正确！");
                        mTvState.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.right, 0, 0, 0);
                        mTvState.setTextColor(0xff00a456);
                    }else{
                        commonVO.setResult(1);
                        commonVO.setReanswer(answerTemp);
                        mTvState.setVisibility(View.VISIBLE);
                        String[] answers = commonVO.getAnswer().split("-");
                        String temp = "";
                        if (answers != null) {
                            for (int i = 0; i < answers.length; i++) {
                                if(i == 0) {
                                    temp += answers[i];
                                }else{
                                    temp += "、" + answers[i];
                                }
                            }
                        }
                        mTvState.setText("回答错误，再接再厉！正确答案：" + temp);
                        mTvState.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.error, 0, 0, 0);
                        mTvState.setTextColor(0xfff74b4f);
                    }
                }
                calculate(false);
            }
                break;
            case R.id.btn_again:
                getData(AppData.paperid);
                break;

            case R.id.btn_commit:
                calculate(true);
                break;
        }
    }


    private void calculate(boolean b){
        int num = 0;
        for (int i = 0; i < data.size(); i++){
            if(data.get(i).getResult() != 2){
                num++;
            }
        }
        if(num != 0 && num % 14 == 0){
            UtilIntent.intentResultDIYLeftToRight(ExamActivity.this, ScanActivity.class,99);
            return;
        }
        if(!b) {
            if (num < total) {
                return;
            }
        }
        int score = 0;
        for (int i = 0; i < data.size(); i++){
            if(data.get(i).getResult() == 0){
                score += 2;
            }
        }
        uploadScore(String.valueOf(score));
        if (score >= 80)
            UtilSPutil.getInstance(this).setBoolean("ExamPassed", true);
        ScoreDialog dialog = new ScoreDialog(ExamActivity.this);
        dialog.setScore(score);
        dialog.show();
    }

    private void uploadScore(String score){
        RetrofitUtil.getInstance().create(HttpService.class).insExamRecord(AppData.token, score).subscribeOn(io()).observeOn(mainThread()).subscribe(new Subscriber<BaseResult>() {

            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onCompleted() {
                removeProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(BaseResult result) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            case 0:
                finish();
                break;

            case 1:

                break;
        }
    }
}
