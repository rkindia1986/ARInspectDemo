package com.optimumus.arinspectdemo.factdatascreeen;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.optimumus.arinspectdemo.R;
import com.optimumus.arinspectdemo.models.FactDataList;
import com.optimumus.arinspectdemo.retrofit.ApiService;
import com.optimumus.arinspectdemo.retrofit.RetroClient;
import com.optimumus.arinspectdemo.utils.InternetConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by INSPRION on 31-05-2019.
 */

public class FactDataPresImpl implements FactDataPres {

    private static final String TAG = "FactDataPresImpl";
    private Context context;
    private FactDataView factDataView;

    //Initilize with view
    public FactDataPresImpl(Context context, FactDataView dataView) {
        this.context = context;
        this.factDataView = dataView;
    }

    //code to send data request
    @Override
    public void refreshData() {

        if (InternetConnection.checkConnection(context)) {
            ApiService apiService = RetroClient.getApiService();
            Call<String> call = apiService.getFactdata();
            Log.e(TAG, "request ongetData url :" + call.request().url());
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Log.e(TAG, "response : ongetData :" + response.body());
                    parseResponse(response.body());
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Log.e(TAG, "onFailure ongetData : " + t.getMessage());
                }
            });
        } else {
            factDataView.displayMessage(context.getString(R.string.nonetwork), false);
        }
    }

    //parse JSON response and if response valid then display data in listview
    private void parseResponse(String body) {
        Gson gson = new Gson();
        try {
            FactDataList factDataList = gson.fromJson(body, FactDataList.class);
            if (!factDataList.title.isEmpty() || (factDataList.factDataArrayList != null && factDataList.factDataArrayList.size() > 0)) {
                factDataView.UpdateListView(factDataList);
            } else {
                factDataView.displayMessage(context.getString(R.string.nodata), false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            factDataView.displayMessage(context.getString(R.string.nodata), false);
        }
    }
}
