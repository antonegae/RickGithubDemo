package com.rick.githubtour.view.adapter


import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rick.githubtour.R
import com.rick.githubtour.databinding.RvGithubuserItemBinding
import com.rick.githubtour.databinding.RvLoadingBinding
import com.rick.githubtour.model.UserListResponse
import com.rick.githubtour.view.main.ui.UserInfoActivity
import com.squareup.picasso.Picasso


class GithubUserAdapter( private val onDataUpdated: () -> Unit) :
    ListAdapter<UserListResponse, RecyclerView.ViewHolder>(DiffItemCallback()) {

    private var TYPE_NORMAL = 0
    private var TYPE_FOOTER = 1

    //是否顯示Footer  轉圈圈?
    private var showFooter: Boolean = true

    inner class UserAdapterViewHolder(var binding: RvGithubuserItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("CheckResult", "SetTextI18n")
        fun bind(model: UserListResponse) {
            Log.d("Rick", "fun bind(model: UserListResponse)")
            binding.tvUserLoginName.text = model.login
            //binding.tvSiteAdmin.text = "Badge status: "+model.siteAdmin.toString()
            if(model.siteAdmin == true){
                binding.tvSiteAdmin.text = "No."+model.id+" STAFF"
                binding.tvSiteAdmin.setTextColor(Color.BLUE)
            }else{
                binding.tvSiteAdmin.text = "No."+model.id+" non-STAFF"
                binding.tvSiteAdmin.setTextColor(Color.RED)
            }

            Picasso.get().load(model.avatarUrl)
                .placeholder(R.drawable.github)
                .into(binding.ivUserImage)

            //binding.githubUserMain.setOnClickListener {
                //itemClick(model)
            //}
        }
    }

    inner class FooterViewHolder(var binding: RvLoadingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.tvLoading.visibility = if (!showFooter) View.GONE else View.VISIBLE
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return if (TYPE_NORMAL == viewType) {
            val itemView =
                RvGithubuserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            UserAdapterViewHolder(itemView)
        } else {
            val itemView =
                RvLoadingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            FooterViewHolder(itemView)
        }
    }

    override fun getItemCount(): Int {

        val count = currentList.size
        return count

    }
    //override fun getItemCount(): Int = list.size ?: 0

    override fun getItemViewType(position: Int): Int {

        return if (position == itemCount) {
            TYPE_FOOTER
        } else {
            TYPE_NORMAL
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder.itemViewType) {

            TYPE_NORMAL -> {
                val item = currentList[position]
                item.let {
                    (holder as UserAdapterViewHolder).bind(it)
                }
                holder.itemView.setOnClickListener {
                    val tempLogin: String = item.login
                    val tempURL: String = item.avatarUrl
                    val i = Intent(holder.itemView.context, UserInfoActivity::class.java)
                    i.putExtra("txtname", tempLogin)
                    i.putExtra("picture", tempURL)
                    holder.itemView.context.startActivity(i)
                }
            }

            else -> {//TYPE_FOOTER
                (holder as FooterViewHolder).bind()
            }
        }
    }

        //資料疊加
        fun updateData(data: MutableList<UserListResponse>?) {

            val newItems = mutableListOf<UserListResponse>()
            newItems.addAll(currentList)
            data?.let { newItems.addAll(it) }
            submitList(newItems)

        }

        fun showFooter(show: Boolean) {

            if (currentList.isEmpty()) {

                showFooter = false

            } else if (showFooter != show) {

                showFooter = show
                submitList(currentList.toList())

            }

        }


    override fun onCurrentListChanged(
            previousList: MutableList<UserListResponse>,
            currentList: MutableList<UserListResponse>,
        ) {
            super.onCurrentListChanged(previousList, currentList)
            onDataUpdated() // 調用回調
        }
    }


    private class DiffItemCallback : DiffUtil.ItemCallback<UserListResponse>() {
        override fun areItemsTheSame(
            oldItem: UserListResponse,
            newItem: UserListResponse
        ): Boolean {

            return oldItem.id == newItem.id

        }

        override fun areContentsTheSame(
            oldItem: UserListResponse,
            newItem: UserListResponse
        ): Boolean {

            return oldItem == newItem

        }

    }




