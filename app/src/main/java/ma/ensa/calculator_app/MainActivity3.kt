package ma.ensa.calculator_app

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity3 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        var isLayoutVisible = false
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        var add_button: FloatingActionButton
        var layout1: FrameLayout
        var addElement: FloatingActionButton
        var removeElement: FloatingActionButton

        add_button = findViewById<FloatingActionButton>(R.id.add_button)

        layout1 = findViewById<FrameLayout>(R.id.layout1) // Replace with the actual ID of your layout
        addElement = findViewById<FloatingActionButton>(R.id.add_User)
        removeElement = findViewById<FloatingActionButton>(R.id.remove_User)

        add_button.setOnClickListener {
            isLayoutVisible = !isLayoutVisible
            layout1.visibility = if (isLayoutVisible) View.VISIBLE else View.GONE
        }
        addElement.setOnClickListener{
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }
    }
}