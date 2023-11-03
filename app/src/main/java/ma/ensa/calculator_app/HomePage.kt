package ma.ensa.calculator_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        var isLayoutVisible = false
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        var add_button: Button = findViewById<Button>(R.id.add_button)
        var layout_add: LinearLayout = findViewById<LinearLayout>(R.id.addLayout)
        var layout_remove: LinearLayout = findViewById<LinearLayout>(R.id.removeLayout)
        var addElement: Button = findViewById<Button>(R.id.add_User)
        var removeElement: Button = findViewById<Button>(R.id.remove_User)

        add_button.setOnClickListener {
            isLayoutVisible = !isLayoutVisible
            layout_add.visibility = if(isLayoutVisible) View.VISIBLE else View.GONE
            layout_remove.visibility = if(isLayoutVisible) View.VISIBLE else View.GONE
        }
        addElement.setOnClickListener{
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }
        removeElement.setOnClickListener{
            val intent = Intent(this, RemoveActivity::class.java)
            startActivity(intent)
        }
    }
}