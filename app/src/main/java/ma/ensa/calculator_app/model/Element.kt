package ma.ensa.calculator_app.model

data class Element(
    val id: Int, // You might have an ID to uniquely identify elements
    val login: String,
    val password: String,
    val nom: String,
    val prenom: String,
    val role: String,
    val old_password: String
)