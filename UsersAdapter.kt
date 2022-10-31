package com.app.myorisapptest.views.activities.users

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.myorisapptest.R
import com.app.myorisapptest.databinding.ContentUsersItemsBinding
import com.app.myorisapptest.models.roomDatabase.User
import com.app.myorisapptest.views.activities.userDetails.UserDetailsActivity

class UsersAdapter(
    private val data: List<User>,
    private val userActivity: UserActivity,
) : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersAdapter.ViewHolder {

        val binding : ContentUsersItemsBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.content_users_items,
            parent,
            false)

        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: UsersAdapter.ViewHolder, position: Int) {

        holder.binding.txtvwId.text = data.get(position).id.toString()
        holder.binding.txtvwFirstName.text = data.get(position).first_name
        holder.binding.txtvwLastName.text = data.get(position).last_name

        holder.binding.cardviewItems.setOnClickListener {

            val intent = Intent(userActivity, UserDetailsActivity::class.java)
            intent.putExtra("id", data.get(position).id.toString())

            userActivity.startActivity(intent)

        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder (val binding: ContentUsersItemsBinding)  : RecyclerView.ViewHolder(binding.root)

}