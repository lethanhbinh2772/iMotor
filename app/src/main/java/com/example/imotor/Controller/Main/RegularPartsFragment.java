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
import com.example.imotor.Controller.Main.Adapter.RecyclerViewRegularItemsAdapter;
import com.example.imotor.Model.GetPriceListMaterial;
import com.example.imotor.Model.GetPriceListMaterialFollow;
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
public class RegularPartsFragment extends Fragment {

    View viewRoot;
    RecyclerView rvRegularItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewRoot = inflater.inflate(R.layout.fragment_regular_parts, container, false);
        init();
        getData();
        return viewRoot;
    }
    private void getData() {
        GetRegularItems getAllItems = new GetRegularItems("28B1-0009", "", "1");
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
                    GetPriceListMaterialFollow getPriceListMaterialFollow = gson.fromJson(strJson, GetPriceListMaterialFollow.class);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                    rvRegularItems.setLayoutManager(linearLayoutManager);
                    RecyclerViewRegularItemsAdapter adapter = new RecyclerViewRegularItemsAdapter();
                    adapter.setContext(getContext());
                    adapter.setData(getPriceListMaterialFollow.getResult());
                    rvRegularItems.setAdapter(adapter);

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
        rvRegularItems = viewRoot.findViewById(R.id.rv_list_material_all);
    }

    class GetRegularItems {
        String bikeId, SearchKey, Page;

        public GetRegularItems(String bikeId, String searchKey, String page) {
            this.bikeId = bikeId;
            SearchKey = searchKey;
            Page = page;
        }
    }

}
