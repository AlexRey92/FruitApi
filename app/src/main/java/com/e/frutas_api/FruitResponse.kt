package com.e.frutas_api

data class FruitResponse(
    val fruits:List<Fruits>
)

data class Fruits (
    val genus:String,
    val name:String,
    val id:Int,
    val family:String,
    val order:String,
    val nutritions:Nutritions
        )

data class Nutritions(
    val carbohydrates:Int,
    val protein: Int,
    val fat:Float,
    val calories:Int,
    val sugar:Float,

)




