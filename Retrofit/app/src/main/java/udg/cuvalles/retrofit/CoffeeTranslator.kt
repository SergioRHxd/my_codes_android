package udg.cuvalles.retrofit

object CoffeeTranslator {
    fun translateTitle(title: String?): String {
        val t = title?.trim()?.lowercase() ?: ""
        return when {
            t.contains("caramel latte") -> "Latte de Caramelo"
            t.contains("iced mocha latte") -> "Latte Mocha Helado"
            t.contains("iced latte") -> "Latte Helado"
            t.contains("latte") -> "Café Latte"
            t.contains("cappuccino") -> "Capuchino"
            t.contains("americano") -> "Café Americano"
            t.contains("espresso") -> "Espresso"
            t.contains("macchiato") -> "Macchiato"
            t.contains("blended caramel coffee") -> "Café Frappé de Caramelo"
            t.contains("blended mocha coffee") -> "Café Frappé de Moca"
            t.contains("mocha") -> "Moca"
            t.contains("hot chocolate") -> "Chocolate Caliente"
            t.contains("chai latte") -> "Chai Latte"
            t.contains("matcha latte") -> "Matcha Latte"
            t.contains("seasonal brew") -> "Café de Temporada"
            t.contains("black tea") -> "Té Negro"
            t.contains("orange juice") -> "Jugo de Naranja"
            t.contains("frozen lemonade") -> "Limonada Congelada"
            t.contains("lemonade") -> "Limonada"
            t.contains("black coffee") || t.contains("black coffeetest") -> "Café Negro"
            else -> title ?: ""
        }
    }

    fun translateDescription(title: String?, description: String?): String {
        val t = title?.trim()?.lowercase() ?: ""
        return when {
            t.contains("caramel latte") -> "Un delicioso café latte elaborado con espresso, leche vaporizada y un toque de dulce jarabe de caramelo."
            t.contains("iced mocha latte") -> "Un refrescante café latte helado con espresso, leche fría, jarabe de chocolate y hielo."
            t.contains("iced latte") -> "Café latte servido frío sobre hielo con espresso y leche."
            t.contains("latte") -> "Bebida de café muy popular elaborada con un trago de espresso y leche vaporizada, con un toque de espuma. Se puede pedir solo o con sabores como vainilla."
            t.contains("cappuccino") -> "Un espresso clásico cubierto con partes iguales de leche vaporizada y espuma densa, a menudo espolvoreado con cacao en polvo o canela."
            t.contains("americano") -> "Se prepara añadiendo agua caliente a un espresso, lo que le da una fuerza similar al café de filtro tradicional."
            t.contains("espresso") -> "Un café concentrado y de cuerpo completo elaborado forzando agua caliente a través de granos de café finamente molidos."
            t.contains("macchiato") -> "Un espresso 'manchado' con una pequeña cantidad de leche vaporizada o espuma en la parte superior."
            t.contains("blended caramel coffee") -> "Una bebida de café mezclada con hielo y rica salsa de caramelo, tipo frappé."
            t.contains("blended mocha coffee") -> "Una bebida de café mezclada con hielo y chocolate, tipo frappé."
            t.contains("mocha") -> "Una deliciosa combinación de espresso, leche vaporizada y jarabe de chocolate dulce, a menudo cubierto con crema batida."
            t.contains("hot chocolate") -> "Una bebida cálida y reconfortante hecha con chocolate fundido o cacao y leche caliente, ideal para días fríos."
            t.contains("chai latte") -> "Té negro especiado mezclado con leche vaporizada y un toque de dulzura."
            t.contains("matcha latte") -> "Té verde matcha finamente molido combinado con leche vaporizada cremosa."
            t.contains("seasonal brew") -> "Una mezcla de café especial elaborada por tiempo limitado con granos de temporada."
            t.contains("black tea") -> "Té negro infusionado, aromático y lleno de sabor."
            t.contains("orange juice") -> "Jugo de naranja natural recién exprimido, lleno de vitamina C."
            t.contains("frozen lemonade") -> "Limonada granizada y refrescante, perfecta para los días calurosos."
            t.contains("lemonade") -> "Limonada clásica, ácida y refrescante con un toque dulce."
            t.contains("black coffee") || t.contains("black coffeetest") -> "Café negro simple y directo, sin leche ni endulzantes añadidos."
            else -> description ?: ""
        }
    }

    fun translateIngredients(ingredients: List<String>?): List<String> {
        return ingredients?.map { ingredient ->
            when (ingredient.trim().lowercase()) {
                "espresso" -> "Espresso"
                "steamed milk" -> "Leche vaporizada"
                "milk" -> "Leche"
                "foam" -> "Espuma"
                "water" -> "Agua"
                "chocolate syrup" -> "Jarabe de chocolate"
                "caramel syrup" -> "Jarabe de caramelo"
                "whipped cream" -> "Crema batida"
                "ice" -> "Hielo"
                "whisky" -> "Whisky"
                "sugar" -> "Azúcar"
                "cocoa powder" -> "Cacao en polvo"
                "cinnamon" -> "Canela"
                "tea" -> "Té"
                "oranges" -> "Naranjas"
                "lemons" -> "Limones"
                "matcha" -> "Matcha"
                "chai" -> "Chai"
                else -> ingredient
            }
        } ?: emptyList()
    }
}
