package com.atessam.mycocktails.ui.favourite

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.DiffResult.NO_POSITION
import androidx.recyclerview.widget.RecyclerView

import com.atessam.mycocktails.core.BaseViewHolder
import com.atessam.mycocktails.data.model.Cocktail
import com.atessam.mycocktails.databinding.MyRowBinding
import com.bumptech.glide.Glide

class FavouriteAdapter(
    private val context: Context,
    private val itemclickListener: OnCocktailClickListener
):RecyclerView.Adapter<BaseViewHolder<*>>() {

    private var cocktailList = listOf<Cocktail>()

    interface OnCocktailClickListener{
        fun onCocktailClick(cocktail: Cocktail,position: Int)

        fun onCocktailLongClick(cocktail: Cocktail,position: Int)
    }

    fun setCocktailList(cocktaillist:List<Cocktail>){
            this.cocktailList = cocktaillist
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
       val itembinding=MyRowBinding.inflate(LayoutInflater.from(context), parent, false)
        val holder = MainViewHolder(itembinding)

        holder.itemView.setOnClickListener {
            val position = holder.adapterPosition.takeIf { it != NO_POSITION }
                ?:return@setOnClickListener
            itemclickListener.onCocktailClick(cocktailList[position],position)
        }


      /*  holder.itemView.setOnLongClickListener {
            val position = holder.adapterPosition.takeIf { it != NO_POSITION }
                ?:return@setOnLongClickListener true

            itemclickListener.onCocktailLongClick(cocktailList[position],position)
        }*/
        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is MainViewHolder -> holder.bind(cocktailList[position])
        }
    }

    override fun getItemCount(): Int = cocktailList.size


    private inner class MainViewHolder(
        private val binding: MyRowBinding
    ):BaseViewHolder<Cocktail>(binding.root){
        override fun bind(item: Cocktail) = with(binding) {
           Glide.with(context)
               .load(item.image)
               .centerCrop()
               .into(imgCocktail)

                txtTitulo.text = item.name
                txtDescripcion.text = item.description
        }
    }
}