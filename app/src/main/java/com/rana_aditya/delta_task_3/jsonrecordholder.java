package com.rana_aditya.delta_task_3;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface jsonrecordholder {

    @GET("forces")
    Call<List<Force>> getforces();
    @GET("forces/{name}")
    Call<spec_force> getspec_force(@Path("name") String name);
    @GET("crime-last-updated")
    Call<Lastupdated> getlastupdate();

    @GET("crimes-at-location")       //date=2017-02&lat=52.629729&lng=-1.131592
    Call<List<CrimeByLocation>> getcrimebylocation( @Query(value = "date",encoded = true) String date,@Query(value = "lat",encoded = true) String lat,@Query(value = "lng",encoded = true) String lng); //@Query("date")String date,@Query("lat") double latitude,@Query("lng") double longitude

    @GET("crimes-no-location")
    Call<List<CrimeByLocation>> getcrimenolocation(@Query(value = "category",encoded = true) String category,@Query(value = "force",encoded = true) String force,@Query(value = "date",encoded = true) String date);


}
