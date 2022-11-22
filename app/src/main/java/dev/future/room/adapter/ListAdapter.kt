package dev.future.room.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController

import androidx.recyclerview.widget.RecyclerView
import dev.future.room.databinding.CustomRowBinding
import dev.future.room.fragment.ListFragmentDirections
import dev.future.room.model.User

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var userList = emptyList<User>()

    class MyViewHolder(val binding: CustomRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            CustomRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = userList[position]

        holder.binding.apply {
            tvFristName.text = item.fristName
            tvLastname.text = item.lastName
            tvId.text = item.id.toString()
            tvAge.text = item.age.toString()

            rowLayout.setOnClickListener {
                val action = ListFragmentDirections.actionListFragmentToUpdateFragment(item)
                holder.itemView.findNavController().navigate(action)
            }
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }


    fun setData(user:List<User>){
        this.userList = user
        notifyDataSetChanged()
    }
}