package com.example.imotor.Controller.Main.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.imotor.Model.GetPriceListMaterialFollowResult;
import com.example.imotor.Model.GetPriceListMaterialResult;
import com.example.imotor.R;

import java.util.List;

public class RecyclerViewRegularItemsAdapter extends RecyclerView.Adapter<RecyclerViewRegularItemsAdapter.ViewHolder>{

    List<GetPriceListMaterialFollowResult> data;
    Context context;

    public void setData(List<GetPriceListMaterialFollowResult> data) {
        this.data = data;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.regular_items_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        GetPriceListMaterialFollowResult getPriceListMaterialFollowResult = data.get(i);
        viewHolder.tvPrice.setText(getPriceListMaterialFollowResult.getPrice().toString());
        viewHolder.tvMaterialName.setText(getPriceListMaterialFollowResult.getMaterialName());
        viewHolder.tvServiceLife.setText(getPriceListMaterialFollowResult.getMaxKm().toString());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMaterialName, tvPrice, tvServiceLife;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaterialName = itemView.findViewById(R.id.tv_material_name_regular);
            tvPrice = itemView.findViewById(R.id.tv_material_price_regular);
            tvServiceLife = itemView.findViewById(R.id.tv_material_service_life_regular);
        }
    }
}
