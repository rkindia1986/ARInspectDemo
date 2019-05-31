package com.optimumus.arinspectdemo.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by INSPRION on 31-05-2019.
 */

public interface ApiService {
    //ROOT_URL of RETROFIT appended with get URL for webservice call
    @GET("facts.json?")
    Call<String> getFactdata();
}
