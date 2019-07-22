package com.example.imotor.Controller.Main;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.imotor.Controller.Main.Adapter.RecyclerViewAllItemsAdapter;
import com.example.imotor.Model.GetPriceListMaterial;
import com.example.imotor.R;
import com.example.imotor.iMotorService.IMotorService;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllPartsFragment extends Fragment {

    View viewRoot;
    RecyclerView rvAllItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewRoot = inflater.inflate(R.layout.fragment_all_parts, container, false);
        init();
        getData();
        return viewRoot;
    }
    private void getData() {
        GetAllItems getAllItems = new GetAllItems("28B1-0009", "", "1");
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://45.118.144.19:1904/api/")
                .build();
        retrofit.create(IMotorService.class).getPriceListMaterial(getAllItems).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String strJson = response.body().string();
                    Gson gson = new Gson();
                    GetPriceListMaterial getPriceListMaterial = gson.fromJson(strJson, GetPriceListMaterial.class);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                    rvAllItems.setLayoutManager(linearLayoutManager);
                    RecyclerViewAllItemsAdapter adapter = new RecyclerViewAllItemsAdapter();
                    adapter.setContext(getContext());
                    adapter.setData(getPriceListMaterial.getResult());
                    rvAllItems.setAdapter(adapter);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getContext(), "He thong khong the resolve", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void init() {
        rvAllItems = viewRoot.findViewById(R.id.rv_list_material_all);
    }

    class GetAllItems {
        String bikeId, SearchKey, Page;

        public GetAllItems(String bikeId, String searchKey, String page) {
            this.bikeId = bikeId;
            SearchKey = searchKey;
            Page = page;
        }
    }

}
