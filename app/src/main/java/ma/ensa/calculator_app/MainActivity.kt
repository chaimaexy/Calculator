package ma.ensa.calculator_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.app.ActionBarDrawerToggle
import ma.ensa.calculator_app.databinding.ActivityMainBinding
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    //menu
    private lateinit var toggle: ActionBarDrawerToggle // to controle the nav to open or close

    private var lastNumber = false
    private var stateError = false
    private var lastDot = false


    private lateinit var expression: Expression

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //menu
        binding.apply {
            toggle = ActionBarDrawerToggle( this@MainActivity, drawerLayout, R.string.open, R.string.close )
            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()


            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            navView.setNavigationItemSelectedListener {
                when(it.itemId){
                    R.id.firstItem->{
                        val intent = Intent(this@MainActivity, MainActivity::class.java)
                        startActivity(intent);
                    }
                    R.id.secondItem->{
                        val intent = Intent(this@MainActivity, MainActivity2::class.java)
                        startActivity(intent);
                    }

                }
                true
            }

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            true
        }
        return super.onOptionsItemSelected(item)

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