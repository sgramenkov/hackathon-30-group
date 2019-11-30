package com.example.putinder.ui.tinder

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.putinder.MainActivity
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.putinder.FiltersActivity
import com.example.putinder.R
import com.example.putinder.retrofit.Models.Photos
import com.example.putinder.retrofit.RetrofitFactory
import com.example.putinder.ui.adapters.TinderAdapter
import kotlinx.coroutines.*
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.fragment_description.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import link.fls.swipestack.SwipeStack
import retrofit2.HttpException
import java.util.concurrent.Semaphore
import kotlin.math.abs

class TinderFragment : Fragment(), SwipeStack.SwipeStackListener, SwipeStack.SwipeProgressListener {
    //lateinit var adapter: TinderAdapter

    lateinit var realm: Realm
    var data:List<Photos>?=null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_tinder, container, false)
        val service = RetrofitFactory().makeRetrofitService()


        Realm.init(context)
        val config = RealmConfiguration.Builder()
            .name("PhotosDB.realm")
            .build()
        realm = Realm.getInstance(config)


        var swipeStack = root.findViewById<SwipeStack>(R.id.swipe_stack)

        swipeStack.setSwipeProgressListener(this)
        swipeStack.setListener(this)
        val filtersButton:ImageButton=root.findViewById(R.id.filter_button)
        filtersButton.setOnClickListener(){
            val intent=Intent(activity!!.applicationContext,FiltersActivity::class.java)
            startActivity(intent)
        }

        val sharedCounterLock = Semaphore(1)
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.TransferToPhotosActivity()
            try {
                withContext(Dispatchers.Default) {
                    if (response.isSuccessful) {
                        //recycler.adapter=PhotosAdapter(response.body()!!,this@PhotosActivity)




                        data = response.body()!!

                        Log.e("Retrofit", response.body().toString())
                        Log.e("context", context.toString())


                        sharedCounterLock.release()


                    } else {
                        //Toast.makeText(applicationContext,"${response.code()}", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (err: HttpException) {
                Log.e("Retrofit", "${err.printStackTrace()}")
            }
        }
        sharedCounterLock.acquire()


        sharedCounterLock.acquire()


        if (data!=null){
        var adapter = TinderAdapter(data!!, context!!)
        swipeStack.adapter = adapter}
        sharedCounterLock.release()
        /*var adapter = TinderAdapter(data!!, context!!)
        swipeStack.adapter = adapter*/



        return root
    }
    private fun saveData(photo:Photos) {
        /*val arrList: ArrayList<Photos> = arrayListOf()
        if (list.isNotEmpty()) {
//сначала все упакуем в один массив, а потом одной транзакцией отправим в БД
            list.forEach {
                val photo = Photos(it.id, it.albumId,it.url,it.title)
                /*photo.id=it.id
                photo.albumId = it.albumId
                photo.url = it.url
                photo.title=it.title*/
                //photo.img = Glide.with(this).load(it.url)
                arrList.add(photo)
            }
//одним траншем пишем в базу

        }*/

        realm.executeTransactionAsync({ bgRealm ->
            bgRealm.insertOrUpdate(photo) //сохраняем первый раз или обновляем уже имеющееся
        }, {
            Toast.makeText(context, "Success write", Toast.LENGTH_SHORT).show()
        }, {
            Toast.makeText(context, "Fail write", Toast.LENGTH_SHORT).show()
        })
    }
    override fun onViewSwipedToLeft(position: Int) {

        Toast.makeText(context, "LEFT", Toast.LENGTH_SHORT).show()
    }
    override fun onViewSwipedToRight(position: Int) {
        Toast.makeText(context, "RIGHT", Toast.LENGTH_SHORT).show()

        saveData(data!![position])

    }

    override fun onStackEmpty() {
        Toast.makeText(context, "EMPTY", Toast.LENGTH_SHORT).show()

    }
    override fun onSwipeEnd(position: Int) {
        val dislikeView: View = view!!.findViewById(R.id.dislikeView)
        dislikeView.alpha = 0.0F
        val likeView: View = view!!.findViewById(R.id.likeView)
        likeView.alpha = 0.0F
    }
    override fun onSwipeStart(position: Int) {

    }
    override fun onSwipeProgress(position: Int, progress: Float) {
        if (progress < 0 && progress > -0.6F) {
            val dislikeView: View = view!!.findViewById(R.id.dislikeView)
            dislikeView.alpha = abs(progress * (1 / 0.6F))
        } else if (progress > 0 && progress < 0.6F) {
            val likeView: View = view!!.findViewById(R.id.likeView)
            likeView.alpha = abs(progress * 1 / 0.6F)
        }
    }
}



