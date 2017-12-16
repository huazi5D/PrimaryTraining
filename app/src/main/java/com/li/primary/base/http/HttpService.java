package com.li.primary.base.http;

import com.li.primary.base.bean.AreaResult;
import com.li.primary.base.bean.BaseResult;
import com.li.primary.base.bean.ChapterResult;
import com.li.primary.base.bean.ExamRecordResult;
import com.li.primary.base.bean.FaceResult;
import com.li.primary.base.bean.LoginResult;
import com.li.primary.base.bean.StudyRecord;
import com.li.primary.base.bean.StudyResult;
import com.li.primary.base.bean.CityResult;
import com.li.primary.base.bean.HomeResult;
import com.li.primary.base.bean.InfoResult;
import com.li.primary.base.bean.QuestionResult;
import com.li.primary.base.bean.UploadStudyResult;
import com.li.primary.base.bean.vo.FaceActionResult;
import com.li.primary.base.bean.vo.IdResult;
import com.li.primary.base.bean.vo.SystemResult;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import rx.Observable;

/**
 * Created by liu on 2017/6/7.
 */

public interface HttpService {
    @FormUrlEncoded
    @POST("question/getQuestionList")
    Observable<QuestionResult> getQuestionList(@Field("token") String param, @Field("paperid") String paperid);

    @POST("login/homePagePre")
    Observable<HomeResult> homePagePre();

    @FormUrlEncoded
    @POST("login/getDataFrDB")
    Observable<IdResult> getDataFrDB(@Field("paperscode") String param);

    @POST("login/getCode2ByCode1")
    Observable<CityResult> getCode2ByCode1();

    @FormUrlEncoded
    @POST("login/getCode3ByCode2")
    Observable<AreaResult> getCode3ByCode2(@Field("code2") String param);


    @Multipart
    @POST("login/register")
    Observable<BaseResult> register(@PartMap Map<String, RequestBody> params);

    @FormUrlEncoded
    @POST("login/resetPasswd")
    Observable<BaseResult> resetPasswd(@Field("userid") String id, @Field("password") String password);

    @FormUrlEncoded
    @POST("login/signin")
    Observable<LoginResult> signin(@Field("userid") String id, @Field("password") String password);

    @FormUrlEncoded
    @POST("edu/getCode1Title1List")
    Observable<StudyResult> getCode1Title1List(@Field("token") String token);

    @FormUrlEncoded
    @POST("edu/getCode2Title2List")
    Observable<ChapterResult> getCode2Title2List(@Field("token") String token, @Field("code1") String code1);

    @FormUrlEncoded
    @POST("user/getUserInfo")
    Observable<InfoResult> getUserInfo(@Field("token") String token);

    @FormUrlEncoded
    @POST("user/insUsersuggest")
    Observable<BaseResult> insUsersuggest(@Field("token") String token, @Field("suggestarea") String suggestarea);


    @FormUrlEncoded
    @POST("login/signout")
    Observable<BaseResult> signout(@Field("token") String token);

    @FormUrlEncoded
    @POST("user/getLearnrecordList")
    Observable<StudyRecord> getLearnrecordList(@Field("token") String token, @Field("pagenum") String pagenum);

    @FormUrlEncoded
    @POST("edu/insEduRecord")
    Observable<UploadStudyResult> insEduRecord(@Field("token") String token, @Field("eduJsonStr") String eduJsonStr, @Field("sessionid") String sessionid);


    @FormUrlEncoded
    @POST("api/faceangle")
    Observable<FaceActionResult> faceangle(@Field("faceimage") String faceimage);

    @FormUrlEncoded
    @POST("question/insExamRecord")
    Observable<BaseResult> insExamRecord(@Field("token") String token, @Field("score") String score);

    @Multipart
    @POST("login/url2url")
    Observable<FaceResult> url2url(@PartMap Map<String, RequestBody> params);


    @Multipart
    @POST("user/updUserImg")
    Observable<BaseResult> updUserImg(@PartMap Map<String, RequestBody> params);

    @FormUrlEncoded
    @POST("user/getExamrecordList")
    Observable<ExamRecordResult> getExamrecordList(@Field("token") String token, @Field("pagenum") String pagenum);


    @POST("login/getSysParam")
    Observable<SystemResult> getSysParam();

//    @Multipart
//    @POST("login/url2url")
//    Observable<FaceResult> url2url(@PartMap Map<String, RequestBody> params);
}