package com.example.paginatedrv2.api;

import com.example.paginatedrv2.models.responses.MangaListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MangaService {
    @GET("manga")
    Call<MangaListResponse> getMangaList(
            @Query("page") int page
    );
}
