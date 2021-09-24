package com.dedi.muslimjelajah.view.menu

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.dedi.muslimjelajah.R
import com.dedi.muslimjelajah.model.ItemPage
import com.dedi.muslimjelajah.model.MenuItem
import com.dedi.muslimjelajah.model.MenuPage


class MenuAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var menusList = mutableListOf<MenuItem>()
//    private var menusList : List<MenuItem> = emptyList()

//    private val menusList: Collection<MenuPage>? = null

    companion object {
        private const val ITEM_HEADER = 0
        private const val ITEM_MENU = 1
    }

    override fun getItemViewType(position: Int): Int {
        return menusList[position].type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder{
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            ITEM_HEADER -> MenuHeaderViewHolder(inflater.inflate(R.layout.header_layout, parent, false))
            ITEM_MENU -> MenuItemViewHolder(inflater.inflate(R.layout.menu_layout, parent, false))
            else -> throw IllegalArgumentException("Unsupported layout") // in case populated with a model we don't know how to display.
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            ITEM_HEADER -> {
                val headerHolder = holder as MenuHeaderViewHolder
                Log.e("TAG", "onBindViewHolder MenuHeaderViewHolder: ${menusList[position].menu}")
                headerHolder.bind(menusList[position].menu as String)
            }
            ITEM_MENU -> {
                val itemHolder = holder as MenuItemViewHolder
                Log.e("TAG", "onBindViewHolder MenuItemViewHolder : ${menusList[position].item}")
                itemHolder.bind(menusList[position].item as ItemPage)
            }
            else -> throw IllegalArgumentException("Undefined view type")
        }
    }

    override fun getItemCount(): Int {
        return menusList.size
    }

    fun setList(menusList: MutableList<MenuItem>) {
        this.menusList = menusList
        notifyDataSetChanged()
    }

    inner class MenuHeaderViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
//        private var image: ImageView = binding.imgMenu
        private var title: TextView = itemView.findViewById(R.id.txtCategoryTitleMenu)
//        private var cardView: CardView = itemView.findViewById(R.id.cardView)
//        private var onClick = binding.cardView

        fun bind(menus: String){




            title.text = menus
            Log.i("TAG", "bind: MenuPage ${menus} ")
//            val ims: InputStream = getAssets().open("avatar.jpg")

//            val resID: Int = getResources().getIdentifier(
//                drawableName,
//                "drawable",
//                VerifyAccess.getPackageName()
//            )
//            iw.setImageResource(resID)
//            image.setImageDrawable(drawableNew(menus.image_menu))
//            Log.i("TAG", "bind: menus.image_menu ${menus.image_menu}")
//            title.text = menus.title_menu
//            onClick.setOnClickListener {
//
//            }

        }
    }

    inner class MenuItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var image: ImageView = itemView.findViewById(R.id.imgMenu)
        private var title: TextView = itemView.findViewById(R.id.tvTitleMenu)
//        private var onClick = binding.cardView

        fun bind(itemMenus: ItemPage){
            Log.i("TAG", "bind: ItemPage ${itemMenus}")
            image.setImageDrawable(drawableNew(itemMenus.image_menu))
//            title.text = itemMenus.title_menu
//            onClick.setOnClickListener {
//
//            }

        }
    }

    private fun drawableNew(filename: String) : Drawable{
        val inputStream = this.context.assets.open(filename)
        return Drawable.createFromStream(inputStream, null)
    }

}