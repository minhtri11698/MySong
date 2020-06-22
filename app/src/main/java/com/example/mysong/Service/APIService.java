package com.example.mysong.Service;

public class APIService {

    private static String base_url = "https://triss116.000webhostapp.com/server/";

    public static DataService getService(){
        return APIRetrofitClient.getClient(base_url).create(DataService.class);
    }
}
