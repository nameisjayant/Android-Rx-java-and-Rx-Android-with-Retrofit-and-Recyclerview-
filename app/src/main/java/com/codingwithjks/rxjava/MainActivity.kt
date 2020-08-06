package com.codingwithjks.rxjava

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codingwithjks.rxjava.Adapater.FoodAdapter
import com.codingwithjks.rxjava.Model.Food
import com.codingwithjks.rxjava.Network.Retrofit
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var foodAdapter: FoodAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        foodAdapter= FoodAdapter(this,ArrayList<Food>())
        recyclerView=findViewById(R.id.recyclerView)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager= LinearLayoutManager(this@MainActivity)
            adapter=foodAdapter
        }

        val compositeDisposable= CompositeDisposable()
        compositeDisposable.add(getObservable().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({ response->getObserver(response as ArrayList<Food>)},
                {t->onFailure(t)}
            ))
    }


    private fun getObservable():Observable<List<Food>>
    {
        return Retrofit.api.getAllFoodList()
    }

    private fun getObserver(foodList:ArrayList<Food>)
    {
        if(foodList!= null && foodList.size!=0)
        {
            foodAdapter.setData(foodList)
        }
    }

    private fun onFailure(t:Throwable)
    {
        Log.d("main", "onFailure: "+t.message)
    }

}
