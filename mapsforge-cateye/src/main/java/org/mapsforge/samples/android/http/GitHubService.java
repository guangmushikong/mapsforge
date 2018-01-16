package org.mapsforge.samples.android.http;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by zhangdezhi1702 on 2018/1/8.
 */

public interface GitHubService {
    @GET("users/{user}/repos")
    Call<String> listRepos(@Path("user") String user);
    @POST("/service/man/userInfo/login")
    Call<String> login(@Body RequestBody body);
}
