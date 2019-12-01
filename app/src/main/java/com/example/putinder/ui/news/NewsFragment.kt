package com.example.putinder.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.putinder.R
import com.example.putinder.ui.adapters.NewsCategoryAdapter
import com.example.putinder.ui.adapters.NewsRVAdapter

class NewsFragment:Fragment() {
    lateinit var newsList:ArrayList<String>
    lateinit var categoriesList:ArrayList<String>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_news, container, false)
        categoriesList= arrayListOf()

        categoriesList.add("Новые")
        categoriesList.add("Интересные")
        categoriesList.add("Рядом")
        categoriesList.add("Бесплатные")
        val newsCategoriesRV:RecyclerView=root.findViewById(R.id.news_category_rv)
        val categoryLM=LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        newsCategoriesRV.layoutManager=categoryLM
        newsCategoriesRV.adapter=NewsCategoryAdapter(categoriesList)
        newsList= arrayListOf()
        newsList
        for (i in 1..100){
            newsList.add(i.toString()).toString()
        }
        val newsRV:RecyclerView=root.findViewById(R.id.news_rv)
        val lm=LinearLayoutManager(context)
        newsRV.layoutManager=lm
        newsRV.adapter=NewsRVAdapter(newsList)
        return root

    }
}