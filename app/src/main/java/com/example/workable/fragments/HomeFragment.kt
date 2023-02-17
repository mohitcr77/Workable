package com.example.workable.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.workable.R
import com.example.workable.adapter
import com.example.workable.work_data

import com.google.firebase.firestore.*
import org.w3c.dom.Document


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RecentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    //custom reference variables
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: adapter
    private lateinit var worklist: ArrayList<work_data>
    private lateinit var db : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RecentFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RecentFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

      // val delbtn:Button = view.findViewById(R.id.delbtn)
      //  val no: TextView = view.findViewById(R.id.feeview)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager= LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        worklist= arrayListOf()

        adapter = adapter(worklist)

        recyclerView.adapter=adapter

        EventChangeListener()
        // Setting the Adapter with the recyclerview
//        recyclerView.adapter = adapter

     /*  delbtn.setOnClickListener {



               db =FirebaseFirestore.getInstance()

               db.collection("workA").document("123")
                   .delete()
                   .addOnSuccessListener {
                       Toast.makeText(activity, "Congratulation for applying", Toast.LENGTH_SHORT).show()
                   }


      //      val num = no.text.toString()

           // delfirestore( num )
       } */

    }



    private fun EventChangeListener()
    {
        db = FirebaseFirestore.getInstance()
        db.collection("workA").
        addSnapshotListener(object : EventListener<QuerySnapshot>{
            override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                for(dc : DocumentChange in value?.documentChanges!!)
                {
                    if(dc.type == DocumentChange.Type.ADDED){
                        worklist.add(dc.document.toObject(work_data::class.java))
                    }
                }

                adapter.notifyDataSetChanged()
            }

        })
    }




}



