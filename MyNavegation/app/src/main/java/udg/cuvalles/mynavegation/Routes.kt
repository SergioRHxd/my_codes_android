package udg.cuvalles.mynavegation

import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
data class Profile(
    val userId: String,
    val userName: String
)
