package com.codingwithjks.rxjava.Network

import com.codingwithjks.rxjava.Model.Food
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET


interface Api {

    @GET("/food.php")
    fun getAllFoodList():Observable<List<Food>>
}