package udg.cuvalles.retrofit

import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName

data class CoffeeItem(
    @SerializedName("id")
    private val rawId: JsonElement? = null,
    val title: String? = "",
    val description: String? = "",
    @SerializedName("ingredients")
    private val rawIngredients: JsonElement? = null,
    val image: String? = ""
) {
    val id: String
        get() = try {
            when {
                rawId == null -> ""
                rawId.isJsonPrimitive -> rawId.asString
                else -> rawId.toString()
            }
        } catch (_: Exception) {
            ""
        }

    val ingredients: List<String>
        get() = try {
            when {
                rawIngredients == null -> emptyList()
                rawIngredients.isJsonArray -> rawIngredients.asJsonArray.mapNotNull { it?.asString }
                rawIngredients.isJsonPrimitive && rawIngredients.asJsonPrimitive.isString -> {
                    val str = rawIngredients.asString
                    if (str.isBlank()) emptyList() else str.split(",").map { it.trim() }
                }
                else -> emptyList()
            }
        } catch (_: Exception) {
            emptyList()
        }
}
