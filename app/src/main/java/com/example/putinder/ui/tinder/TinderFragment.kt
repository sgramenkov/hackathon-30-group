package com.example.putinder.ui.tinder

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.putinder.R
import com.example.putinder.model.Sights
import com.example.putinder.retrofit.RetrofitFactory
import com.example.putinder.ui.adapters.TinderAdapter
<<<<<<< Updated upstream
import link.fls.swipestack.SwipeStack
import java.lang.Math.abs

=======
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import link.fls.swipestack.SwipeStack
import retrofit2.HttpException
import kotlin.math.abs
>>>>>>> Stashed changes

class TinderFragment : Fragment(), SwipeStack.SwipeStackListener, SwipeStack.SwipeProgressListener {

lateinit var data:ArrayList<Sights>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_tinder, container, false)
        var swipeStack = root.findViewById<SwipeStack>(R.id.swipe_stack)

    data= arrayListOf(Sights(0,1,"https://sun9-43.userapi.com/c850132/v850132279/1a8512/diTO1QI7NXE.jpg"))

        /*val service = RetrofitFactory().makeRetrofitService()
        Log.e("response",service.toString())

           try {
               val response = service.TransferToSightsActivity()

               if (response.isSuccessful) {
                   data = response.body()!!

               } else {
                   Toast.makeText(
                       context,
                       "${response.code()}",
                       Toast.LENGTH_SHORT
                   )
                       .show()
               }
           } catch (err:HttpException){
               Log.e("err",err.toString())
           }
*/


        swipeStack.setSwipeProgressListener(this)
        swipeStack.setListener(this)
<<<<<<< Updated upstream
        swipeStack.adapter = adapter
=======
        swipeStack.adapter = TinderAdapter(data)
>>>>>>> Stashed changes

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
        if (progress < 0 && progress > -0.6) {
            val dislikeView: View = view!!.findViewById(R.id.dislikeView)
            dislikeView.alpha = abs(progress * (1 / 0.6F))

        } else if (progress > 0 && progress < 0.6) {
            val likeView: View = view!!.findViewById(R.id.likeView)
            likeView.alpha = abs(progress * 1 / 0.6F)
<<<<<<< Updated upstream
        }    }
=======


        }
    }


}

>>>>>>> Stashed changes





