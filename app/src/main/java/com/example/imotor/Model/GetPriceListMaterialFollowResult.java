package com.example.imotor.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetPriceListMaterialFollowResult {

    @SerializedName("materialID")
    @Expose
    private String materialID;
    @SerializedName("materialName")
    @Expose
    private String materialName;
    @SerializedName("unit")
    @Expose
    private String unit;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("maxKm")
    @Expose
    private Double maxKm;

    public String getMaterialID() {
        return materialID;
    }

    public void setMaterialID(String materialID) {
        this.materialID = materialID;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getMaxKm() {
        return maxKm;
    }

    public void setMaxKm(Double maxKm) {
        this.maxKm = maxKm;
    }

}

