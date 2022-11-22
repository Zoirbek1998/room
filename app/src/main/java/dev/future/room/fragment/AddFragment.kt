package dev.future.room.fragment

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import dev.future.room.R
import dev.future.room.databinding.FragmentAddBinding
import dev.future.room.model.User
import dev.future.room.viewModel.UserViewModel


class AddFragment : Fragment() {

    lateinit var binding: FragmentAddBinding
    lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding = FragmentAddBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAdd.setOnClickListener {
            insertDataToDataBase()
        }

    }

    private fun insertDataToDataBase() {
        val fristName = binding.addFristnameEt.text.toString()
        val lastName = binding.addLastNameEt.text.toString()
        val age = binding.addAgeEt.text

        if (inputChacked(fristName,lastName,age)){
            val user= User(0,fristName,lastName,Integer.parseInt(age.toString()))
            mUserViewModel.userAdd(user)
            Toast.makeText(requireActivity(), "SuccessFully added!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireActivity(), "Please fill out all fields.", Toast.LENGTH_SHORT).show()
        }

    }

    private fun inputChacked(fristName: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(fristName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }


}