package com.example.imotor.Controller.Main.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.imotor.Model.GetPriceListMaterialResult;
import com.example.imotor.R;

import java.util.List;

public class RecyclerViewAllItemsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    List<Object> data;
    Context context;

    private final int VIEW_TYPE_ITEM = 0, VIEW_TYPE_LOADING = 1;

    public RecyclerViewAllItemsAdapter(List<Object> data, Context context) {
        this.data = data;
        this.context = context;
    }

    public RecyclerViewAllItemsAdapter() {

    }

    public void setData(List<Object> data) {
        this.data = data;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(context).inflate(R.layout.all_items_layout, parent, false);
            return new ItemViewHolder(view);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(context).inflate(R.layout.progress_bar_layout, parent, false);
            return new ItemViewHolder(view);
        }
        return null;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        if (viewHolder instanceof ItemViewHolder) {
            ItemViewHolder itemViewHolder = (ItemViewHolder) viewHolder;
            GetPriceListMaterialResult getPriceListMaterialResult = new GetPriceListMaterialResult();
            itemViewHolder.tvPrice.setText((getPriceListMaterialResult.getPrice().toString()));
            itemViewHolder.tvMaterialName.setText(getPriceListMaterialResult.getMaterialName());
            itemViewHolder.tvServiceLife.setText(getPriceListMaterialResult.getMaxKm().toString());

        } else if (viewHolder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) viewHolder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }
    }



    @Override
    public int getItemCount() {
        return data.size();
    }


    class LoadingViewHolder extends RecyclerView.ViewHolder {

        ProgressBar progressBar;

        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progress_bar);
        }
    }
    class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvMaterialName, tvPrice, tvServiceLife;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaterialName = itemView.findViewById(R.id.tv_material_name_all);
            tvPrice = itemView.findViewById(R.id.tv_material_price_all);
            tvServiceLife = itemView.findViewById(R.id.tv_material_service_life_all);
        }
    }
}
