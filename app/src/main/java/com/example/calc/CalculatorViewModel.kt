import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.calc.NativeCalculator

class CalculatorViewModel : ViewModel() {
    private val calculator = NativeCalculator()

    fun calculate(input1: String, input2: String, operation: String): String {
        return try {
            val num1 = input1.toDouble()
            val num2 = input2.toDouble()
            val result = when (operation) {
                "+" -> calculator.add(num1, num2)
                "-" -> calculator.subtract(num1, num2)
                "*" -> calculator.multiply(num1, num2)
                "/" -> if(num2 != 0.0)
                { calculator.divide(num1, num2)
                } else
                {
                    "Nie mozna dzielic przez 0"
                }
                else -> 0.0
            }
            result.toString()
        } catch (e: Exception) {
            "Error: ${e.message}"
        }
    }
}
