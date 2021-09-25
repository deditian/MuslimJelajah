package com.dedi.muslimjelajah.view.menu

import android.R
import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dedi.muslimjelajah.databinding.MenuLayoutBinding
import com.dedi.muslimjelajah.model.ItemPage


class MenuChildAdapter(private val item: List<ItemPage>, val context: Context) :
//        : RecyclerView.Adapter<RecyclerView.ViewHolder>()
        BaseAdapter() {

//    class MenuItemViewHolder(val binding: MenuLayoutBinding) : RecyclerView.ViewHolder(binding.root)

//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        return MenuItemViewHolder(MenuLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
//    }
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        (holder as MenuItemViewHolder).bind(item[position])
//    }
//
//    override fun getItemCount(): Int {
//        return item.size
//    }

//    private fun MenuItemViewHolder.bind(menus: ItemPage) {
//        binding.tvTitleMenu.text = menus.title_menu
//        val inputStream = binding.root.context.assets.open(menus.image_menu)
//        val ima = Drawable.createFromStream(inputStream, null)
//        binding.imgMenu.setImageDrawable(ima)
//    }

    override fun getCount(): Int {
       return item.size
    }

    override fun getItem(position: Int): Any? {
        return item[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
//        val sd = this.item[position]
//        var inflator = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//        var foodView = inflator.inflate(, null)
//        MenuLayoutBinding.bind(sd, viewGroup, false)
        val inflter = (LayoutInflater.from(context));

//        val view = inflter.inflate(R.layout.activity_list_item, null) // inflate the layout

//        val icon: ImageView = view.findViewById(R.id.icon) as ImageView // get the reference of ImageView

//        icon.setImageResource(logos.get(i)) // set logo images


        var holder: ViewHolder
            val itemBinding: MenuLayoutBinding = MenuLayoutBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
            holder = ViewHolder(itemBinding)
            holder.view = itemBinding.root
            holder.view.tag = holder
            holder.binding.tvTitleMenu.text = item[position].title_menu
            return holder.view




    }

    private class ViewHolder constructor(binding: MenuLayoutBinding) {
        var view: View = binding.root
        val binding: MenuLayoutBinding = binding

    }

}


