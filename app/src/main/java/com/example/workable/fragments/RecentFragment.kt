package com.example.workable.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.workable.R
import com.google.firebase.firestore.FirebaseFirestore

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RecentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RecentFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        return inflater.inflate(R.layout.fragment_recent, container, false)
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

        val submitbtn: Button = view.findViewById(R.id.submitBtn)
        val tv1 : EditText = view.findViewById(R.id.ed1)
        val tv2 : EditText = view.findViewById(R.id.ed2)
        val tv3 : EditText = view.findViewById(R.id.ed3)
        val tv4 : EditText = view.findViewById(R.id.ed4)
        val tv5 : EditText = view.findViewById(R.id.ed5)
        // val radiogrp : RadioGroup = view.findViewById(R.id.radiogroup)
        // val radbtn: RadioButton = view.findViewById(radiogrp.checkedRadioButtonId)


        submitbtn.setOnClickListener {
            val title = tv1.text.toString()
            val phno = tv2.text.toString()
            val location= tv3.text.toString()
            val fee = tv4.text.toString()
            val time = tv5.text.toString()

            saveFirestore(title, phno , location, fee, time)// location, fee ,time

            tv1.setText("")
            tv2.setText("")
            tv3.setText("")
            tv4.setText("")
            tv5.setText("")

        }
    }

    private fun saveFirestore(
        title: String,
        phno: String,
        location: String,
        fee: String,
        time: String
    ) {//, location: String, fee: String, time: String

        val db = FirebaseFirestore.getInstance()
        //val work : MutableMap<String, Any> = HashMap()
        val test : MutableMap<String, Any> = HashMap()

        //   work["title"] = title
        //   work["phno"] = phno
        //   work["location"] = location
        //   work["fee"] = fee
        // work["time"] = time

        /* db.collection("work")
             .add(work)
             .addOnSuccessListener {
                 Toast.makeText(activity, "Record added" , Toast.LENGTH_SHORT).show()
             }*/

        test["title"] = title
        test["phno"] = phno
        test["location"] = location
        test["fee"] = fee
        test["time"]= time

        db.collection("workA")
            .add(test)
            .addOnSuccessListener {
                Toast.makeText(activity, "Record added", Toast.LENGTH_SHORT).show()
            }


    }
}