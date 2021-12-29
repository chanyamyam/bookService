package com.app.part3.bookservice.api

import com.app.part3.bookservice.model.BestSellerDto
import com.app.part3.bookservice.model.SearchBookDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BookService {

    @GET("/api/search.api?output=json")
        fun getBookByName(
            @Query("key") apiKey: String,
            @Query("query") keyword: String
        ): Call<SearchBookDto>

    @GET("/api/bestSeller.api?output=json&categoryId=100")
        fun getBestSellerBooks(
            @Query("key") apiKey: String
        ): Call<BestSellerDto>
}