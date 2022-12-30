package com.e.frutas_api

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class FruitAdapter(var fruta: MutableList<Fruit>):RecyclerView.Adapter<FruitAdapter.ViewHolder>() {
    inner class ViewHolder( view:View):RecyclerView.ViewHolder(view){
        val carbohidratos:TextView=view.findViewById(R.id.textViewCarbo)
        val proteinas:TextView=view.findViewById(R.id.textViewPro)
        val nombre:TextView=view.findViewById(R.id.textViewFruit)
        val calorias:TextView=view.findViewById(R.id.textViewCal)
        val id:TextView=view.findViewById(R.id.textViewID)
        val family:TextView=view.findViewById(R.id.textViewFamily)
        val foto:ImageView=view.findViewById(R.id.imageView2)
        val imagen=foto.toString()
        fun onBind(comida:Fruit){
            carbohidratos.text=comida.carbohydrates.toString()
           proteinas.text=comida.protein.toString()
            nombre.text=comida.name
            calorias.text=comida.calories.toString()
            id.text=comida.id.toString()
            family.text=comida.family
           Picasso.get().load(imagen).into(foto)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val myView:View=LayoutInflater.from(parent.context).inflate(R.layout.item_task,parent,false)
        return ViewHolder(myView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val posicionFruta= fruta[position]
        holder.onBind(posicionFruta)
    }


    override fun getItemCount(): Int {
      return  fruta.size

    }
}