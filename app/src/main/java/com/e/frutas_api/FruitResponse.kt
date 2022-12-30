package com.e.frutas_api

data class FruitResponse(
    val genus:String,
    val name:String,
    val id:Int,
    val family:String,
    val order:String,
    val nutritions:Nutritions
)

data class Nutritions(
    val carbohydrates:Double,
    val protein: Double,
    val fat:Double,
    val calories:Double,
    val sugar:Double,
)





