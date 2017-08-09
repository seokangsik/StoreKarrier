package com.skgroup4.android.storekarrier;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Seo on 2017-08-09.
 */

public interface GetHouse {
    public static final String house_url = "http://169.56.68.139:9000";
    @FormUrlEncoded
    @POST("house")
    Call<ResponseBody> getLocation(@Field("ID") String ID , @Field("LAT") String LAT , @Field("LONG") String LONG);
}