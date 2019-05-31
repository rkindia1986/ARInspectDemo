package com.optimumus.arinspectdemo;

import android.content.DialogInterface;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.optimumus.arinspectdemo.adapters.FactDataAdapter;
import com.optimumus.arinspectdemo.factdatascreeen.FactDataPresImpl;
import com.optimumus.arinspectdemo.factdatascreeen.FactDataView;
import com.optimumus.arinspectdemo.models.FactDataList;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements FactDataView {
    public static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.fact_RecyclerView)
    RecyclerView mRecycleview;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mswiperefreshlayout;
    FactDataPresImpl factDataPresimpl;
    FactDataAdapter factDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        factDataPresimpl = new FactDataPresImpl(this, this);
        mRecycleview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecycleview.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        mswiperefreshlayout.setOnRefreshListener(mRefreshListener);
        mswiperefreshlayout.setColorSchemeColors(
                ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark)
        );
        mswiperefreshlayout.setRefreshing(true);
        factDataPresimpl.refreshData();
    }

    //on swipe refresh again call the request data
    private SwipeRefreshLayout.OnRefreshListener mRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            if (!mswiperefreshlayout.isRefreshing()) {
                mswiperefreshlayout.setRefreshing(true);

            }
            factDataPresimpl.refreshData();
        }
    };

    //Display content code
    @Override
    public void UpdateListView(FactDataList f) {
        mswiperefreshlayout.setRefreshing(false);
        if (f.factDataArrayList == null) {
            f.factDataArrayList = new ArrayList<>();
        }
        if(factDataAdapter==null){
            factDataAdapter = new FactDataAdapter(MainActivity.this, f.factDataArrayList);
            mRecycleview.setAdapter(factDataAdapter);
        }
        factDataAdapter.notifyDataSetChanged();
        if (!f.title.isEmpty())
            DisplayTitle(f.title);
        else
            DisplayTitle(getString(R.string.app_name));
    }

    //Display alert dialog
    @Override
    public void displayMessage(String s, final boolean isfinish) {
        mswiperefreshlayout.setRefreshing(false);
        AlertDialog.Builder alertdialog = new AlertDialog.Builder(MainActivity.this);
        alertdialog.setMessage(s);
        alertdialog.setCancelable(false);
        alertdialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (isfinish)
                    finish();
                else
                    dialogInterface.dismiss();
            }
        });
        alertdialog.show();
    }

    //Display title text in actionbar
    private void DisplayTitle(String title) {
        getSupportActionBar().setTitle(title);
    }
}
