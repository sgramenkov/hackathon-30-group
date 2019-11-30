package com.example.putinder.ui.tinder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.putinder.R
import com.example.putinder.ui.adapters.TinderAdapter
import com.google.android.material.snackbar.Snackbar
import link.fls.swipestack.SwipeStack
import kotlin.math.abs

class TinderFragment : Fragment(), SwipeStack.SwipeStackListener, SwipeStack.SwipeProgressListener {
    lateinit var adapter: TinderAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_tinder, container, false)

        var swipeStack = root.findViewById<SwipeStack>(R.id.swipe_stack)
        var data: ArrayList<String> = arrayListOf(
            "dfl;jg;d",
            "dsF'dsF",
            "dsfdsfdsf",
            "dsF'dsF",
            "dsfdsfdsf"
        )
        var adapter = TinderAdapter(data)
        swipeStack.setSwipeProgressListener(this)
        swipeStack.setListener(this)

        swipeStack.adapter = adapter

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


        }    }


}



