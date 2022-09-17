package com.monika.productlist.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.monika.productlist.data.model.ProductResponse
import com.monika.productlist.databinding.ProductListBinding
import com.monika.productlist.presentation.ui.productlist.ProductsFragmentDirections

class ProductsAdapter :
    RecyclerView.Adapter<ProductsAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: ProductListBinding) : RecyclerView.ViewHolder(binding.root)

    private var userList = emptyList<ProductResponse>()

    fun setUserList(userList: List<ProductResponse>) {
        val diffCallBack = MyDiffCallBack(this.userList, userList)
        val diffResult = DiffUtil.calculateDiff(diffCallBack)
        diffResult.dispatchUpdatesTo(this)
        this.userList = userList
    }

    class MyDiffCallBack(
        private val oldList: List<ProductResponse>,
        private val newList: List<ProductResponse>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }


    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder) {
            with(userList[position]) {
                binding.title.text = this.title
                val price = "₹ ${this.price}/-"
                binding.price.text = price
                binding.description.text = this.description
                binding.category.text = this.category
                Glide.with(holder.itemView.context)
                    .load(this.image)
                    .into(binding.productImage)
                binding.productCard.setOnClickListener {
                    holder.itemView.findNavController()
                        .navigate(ProductsFragmentDirections.openDetails(this))
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ProductListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

}