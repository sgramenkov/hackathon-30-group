package com.example.putinder.ui.tinder

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.putinder.MainActivity
import com.example.putinder.R
import com.example.putinder.retrofit.Models.Photos
import com.example.putinder.retrofit.RetrofitFactory
import com.example.putinder.ui.adapters.TinderAdapter
import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import link.fls.swipestack.SwipeStack
import retrofit2.HttpException
import java.util.concurrent.Semaphore
import kotlin.math.abs

class TinderFragment : Fragment(), SwipeStack.SwipeStackListener, SwipeStack.SwipeProgressListener {
    //lateinit var adapter: TinderAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_tinder, container, false)
        val service = RetrofitFactory().makeRetrofitService()
        var data:List<Photos>?=null
            //mutableListOf(Photos(1, "https://cdn-st2.rtr-vesti.ru/vh/pictures/hd/201/278/9.jpg"))
        var swipeStack = root.findViewById<SwipeStack>(R.id.swipe_stack)

        swipeStack.setSwipeProgressListener(this)
        swipeStack.setListener(this)
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

    override fun onViewSwipedToLeft(position: Int) {

        Toast.makeText(context, "LEFT", Toast.LENGTH_SHORT).show()
    }

    override fun onViewSwipedToRight(position: Int) {
        Toast.makeText(context, "RIGHT", Toast.LENGTH_SHORT).show()

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



