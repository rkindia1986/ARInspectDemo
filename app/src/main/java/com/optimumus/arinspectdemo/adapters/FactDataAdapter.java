package com.optimumus.arinspectdemo.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.optimumus.arinspectdemo.R;
import com.optimumus.arinspectdemo.models.FactData;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by INSPRION on 31-05-2019 for Recycleview data binding.
 */

public class FactDataAdapter extends RecyclerView.Adapter<FactDataAdapter.CustomVIewHolder> {
    private Context mcontext;
    private List<FactData> mfactDatalist;

    public FactDataAdapter(Context context, List<FactData> factData) {
        mcontext = context;
        mfactDatalist = factData;
    }

    @NonNull
    @Override
    public CustomVIewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.fact_list_item, parent, false);
        CustomVIewHolder holder = new CustomVIewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomVIewHolder holder, int position) {
        FactData factData = mfactDatalist.get(position);
        holder.txtTitle.setText(factData.getTitle());
        holder.txtDesc.setText(factData.getDescription());
        Glide.with(mcontext)
                .load(factData.getImageHref())
                .into(holder.imgHref);
    }

    @Override
    public int getItemCount() {
        return mfactDatalist.size();
    }

    public class CustomVIewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.fact_txt_title)
        TextView txtTitle;
        @BindView(R.id.fact_txt_desc)
        TextView txtDesc;
        @BindView(R.id.fact_img_href)
        ImageView imgHref;

        public CustomVIewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
