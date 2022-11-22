package dev.future.room.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dev.future.room.R
import dev.future.room.databinding.FragmentUpdateBinding
import dev.future.room.model.User
import dev.future.room.viewModel.UserViewModel


class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    lateinit var binding: FragmentUpdateBinding
    lateinit var mUserViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        binding = FragmentUpdateBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addFristnameEt.setText(args.currentUser.fristName)
        binding.addLastNameEt.setText(args.currentUser.lastName)
        binding.addAgeEt.setText(args.currentUser.age.toString())


        binding.btnAdd.setOnClickListener {
            update()
        }
        setHasOptionsMenu(true)
    }

    private fun update() {
        val fristName = binding.addFristnameEt.text.toString()
        val lastName = binding.addLastNameEt.text.toString()
        val age = binding.addAgeEt.text

        if (inputChacked(fristName, lastName, age)) {

            val user =
                User(args.currentUser.id, fristName, lastName, Integer.parseInt(age.toString()))
            mUserViewModel.updateDate(
                user
            )
            Toast.makeText(requireActivity(), "SuccessFully added!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireActivity(), "Please fill out all fields.", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun inputChacked(fristName: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(fristName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.menu_delete) {
            deleteUser()
        }

        return super.onOptionsItemSelected(item)

    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireActivity())
        builder.setPositiveButton("Yes") { _, _ ->
            mUserViewModel.deleteUser(args.currentUser)
            Toast.makeText(requireActivity(), "Successfully removed ${args.currentUser.fristName}", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }

        builder.setNegativeButton("No") { _, _ ->

        }

        builder.setTitle("Delete ${args.currentUser.fristName}")
        builder.setMessage("Are you soure want to delete ${args.currentUser.fristName}")
        builder.create().show()
    }

}