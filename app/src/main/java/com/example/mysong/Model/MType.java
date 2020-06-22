package com.example.mysong.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MType implements Serializable {

    @SerializedName("idType")
    @Expose
    private String idType;
    @SerializedName("idTheme")
    @Expose
    private String idTheme;
    @SerializedName("typeName")
    @Expose
    private String typeName;
    @SerializedName("typePhoto")
    @Expose
    private String typePhoto;

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdTheme() {
        return idTheme;
    }

    public void setIdTheme(String idTheme) {
        this.idTheme = idTheme;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypePhoto() {
        return typePhoto;
    }

    public void setTypePhoto(String typePhoto) {
        this.typePhoto = typePhoto;
    }

}
