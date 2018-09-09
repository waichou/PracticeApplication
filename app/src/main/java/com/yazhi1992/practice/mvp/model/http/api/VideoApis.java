package com.yazhi1992.practice.mvp.model.http.api;

import com.yazhi1992.practice.mvp.model.bean.UserBean;
import com.yazhi1992.practice.mvp.model.http.response.VideoHttpResponse;

import retrofit2.http.GET;
import rx.Observable;

public interface VideoApis {
    public static final String GET_USER_URL =  "SessionExample/servlet/LoginServlet";

    /**
     * 首页
     *
     * @return
     */
    @GET(GET_USER_URL)
    Observable<VideoHttpResponse<UserBean>> getHomePage();
}
