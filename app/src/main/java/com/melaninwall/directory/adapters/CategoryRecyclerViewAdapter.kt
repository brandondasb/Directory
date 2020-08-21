package com.melaninwall.directory.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.melaninwall.directory.R
import com.melaninwall.directory.interfaces.ListItemCategoryListener
import com.melaninwall.directory.model.Category
import com.melaninwall.directory.model.ListingItemData
import com.melaninwall.directory.viewHolder.CategoryViewHolder

class CategoryRecyclerViewAdapter(
    private val context: Context,
    private val itemListener: ListItemCategoryListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var categoryList: List<Category>? = listOf()
    private var listingItemDataList: List<ListingItemData>? = listOf()

    fun setData(listingItemData: List<ListingItemData>,dataList: List<Category>?) {
        this.categoryList = dataList
        this.listingItemDataList = listingItemData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.list_item_category_recycler, viewGroup, false)

        return CategoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categoryList!!.size //TODO  double bang might not be needed
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val categoryViewHolder: CategoryViewHolder = viewHolder as CategoryViewHolder
        val categoryList: Category =
            this.categoryList!![position] // TODO double bang again, cmon son
        val listingItemData:List<ListingItemData> = this.listingItemDataList!!

        categoryViewHolder.homeRootLayout.setOnClickListener {
            itemListener.launchCategoryFragment(listingItemData,categoryList.name)
        }

        val name = categoryList.name
        val imageUrl = categoryList.url

        categoryViewHolder.name.text = name

        Glide.with(context)
            .load(imageUrl)
            .into(categoryViewHolder.image)
    }
}
