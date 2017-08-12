package com.skgroup4.android.storekarrier;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Seo on 2017-08-12.
 */

public interface GetSpot {
    public static final String spot_url = "http://169.56.68.139:9010";
    @FormUrlEncoded
    @POST("spot")
    Call<ResponseBody> getSpot(@Field("ID") String ID , @Field("LAT") String LAT , @Field("LONG") String LONG);
}
