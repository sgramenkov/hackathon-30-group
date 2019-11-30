package com.example.putinder.ui.tinder

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.putinder.R

import com.example.putinder.retrofit.RetrofitFactory
import com.example.putinder.retrofit.Models.Photos
import com.example.putinder.ui.adapters.TinderAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import link.fls.swipestack.SwipeStack
import retrofit2.HttpException
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
        var data: List<Photos>
        var swipeStack = root.findViewById<SwipeStack>(R.id.swipe_stack)
        swipeStack.setSwipeProgressListener(this)
        swipeStack.setListener(this)

        CoroutineScope(Dispatchers.IO).launch {


            val response = service.TransferToPhotosActivity()
            try {
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        //recycler.adapter=PhotosAdapter(response.body()!!,this@PhotosActivity)
                        data = response.body()!!
                        Log.e("Retrofit",response.body().toString())
                        var adapter = TinderAdapter(data, context!!)
                        swipeStack.adapter = adapter

                    } else {
                        Toast.makeText(context,"${response.code()}", Toast.LENGTH_SHORT).show()
                    }
                }

            } catch (err: HttpException) {
                Log.e("Retrofit", "${err.printStackTrace()}")
            }
        }




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



