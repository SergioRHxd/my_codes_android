package udg.cuvalles.mylazylist

data class Persona(
    val nombre: String,
    val ocupacion: String,
    val likes: Int = 0,
    val foto: Int = R.drawable.ic_launcher_foreground,
)
