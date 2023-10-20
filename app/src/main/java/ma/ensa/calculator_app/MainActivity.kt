package ma.ensa.calculator_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import ma.ensa.calculator_app.databinding.ActivityMainBinding
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener  {

    //menu
  //  private lateinit var toggle: ActionBarDrawerToggle // to controle the nav to open or close

    private var lastNumber = false
    private var stateError = false
    private var lastDot = false

    private lateinit var binding : ActivityMainBinding
    private lateinit var expression: Expression

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawer: DrawerLayout =findViewById(R.id.drawerLayout)
        val Navigation:NavigationView=findViewById(R.id.nav_view)
        val toolbar: Toolbar = findViewById(R.id.toolbar)

        //setSupportActionBar(toolbar)

        Navigation.bringToFront()
        val toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        Navigation.setNavigationItemSelectedListener(this)
        //menu

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.standard -> {
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }
            R.id.scientifique -> {
                val intent = Intent(this,MainActivity2::class.java)
                startActivity(intent)
            }

        }
        val drawer: DrawerLayout = findViewById(R.id.drawerLayout)
        drawer.closeDrawer(GravityCompat.START)

        return false
    }



    fun onOperatorClick(view: View) {
        if(!stateError && lastNumber){
            binding.dataTv.append((view as Button).text)
            lastDot = false
            lastNumber = false
            onEqual()
        }
    }

    fun onClearClick(view: View) {
        binding.dataTv.text = ""
        lastNumber = false

    }

    fun onBackClick(view: View) {
        binding.dataTv.text = binding.dataTv.text.toString().dropLast(1)

        try{
            val lastChar = binding.dataTv.text.toString().last()

            if (lastChar.isDigit()){
                onEqual()
            }
        }catch (e: Exception){
            binding.resultTv.text = ""
            binding.resultTv.visibility = View.GONE
            Log.e("last char error", e.toString())
        }
    }

    fun onDigitClick(view: View) {
        if(stateError){
            binding.dataTv.text = (view as Button).text
            stateError = false

        }else{
            binding.dataTv.append((view as Button).text)

        }
        lastNumber = true
        onEqual()
    }
    fun onAllclearClick(view: View) {
        binding.dataTv.text = ""
        binding.resultTv.text = ""

        stateError = false
        lastDot = false
        lastNumber = false
        binding.resultTv.visibility = View.GONE
    }

    fun onEqualClick(view: View) {
        onEqual()
        binding.dataTv.text = binding.resultTv.text.toString().drop(1)
    }

    fun onEqual() {
        if (lastNumber && !stateError){
            val txt= binding.dataTv.text.toString()

            expression = ExpressionBuilder(txt).build()

            try{
                val result = expression.evaluate()

                binding.resultTv.visibility = View.VISIBLE

                binding.resultTv.text = "=" + result.toString()


            }catch (ex: ArithmeticException){
                Log.e("evaluate error", ex.toString())
                binding.resultTv.text = "Error"
                stateError = true
                lastNumber = false
            }
        }
    }
}