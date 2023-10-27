package ma.ensa.calculator_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText


class AddActivity : AppCompatActivity() {
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var nom: EditText
    private lateinit var prenom: EditText
    private lateinit var valider: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        nom = findViewById(R.id.nom)
        prenom = findViewById(R.id.prenom)
        valider = findViewById(R.id.Valider)

        valider.setOnClickListener(View.OnClickListener {
            val myDB = MyDatabaseHelper(this)
            myDB.addAuth(
                email.text.toString().trim(),
                password.text.toString().trim(),
                nom.text.toString().trim(),
                prenom.text.toString().trim(),

            )
        })
    }
}