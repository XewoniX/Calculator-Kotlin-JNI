import androidx.lifecycle.ViewModel
import com.example.calc.NativeCalculator
import com.example.calc.Operator
import com.example.calc.Operator.DIVIDE
import com.example.calc.Operator.MINUS
import com.example.calc.Operator.MULTIPLY
import com.example.calc.Operator.PLUS


class CalculatorViewModel : ViewModel() {
    private val DIVIDE_BY_ZERO_ERROR: String = "Nie mozna dzielic przez 0"
    private val calculator = NativeCalculator()

    fun calculate(input1: String, input2: String, operator: Operator): String {
        return try {
            val num1 = input1.toDouble()
            val num2 = input2.toDouble()
            val result = when (operator) {
                PLUS -> calculator.add(num1, num2)
                MINUS -> calculator.subtract(num1, num2)
                MULTIPLY -> calculator.multiply(num1, num2)
                DIVIDE -> divide(num1, num2)
                else -> 0.0
            }
            result.toString()
        } catch (e: NumberFormatException) {
            return e.message!!
        } catch (e: Exception) {
            "Error: ${e.message}"
        }
    }

    private fun divide(num1: Double, num2: Double): Double {
        if (num2 == 0.0) {
            throw NumberFormatException(DIVIDE_BY_ZERO_ERROR)
        }
        return calculator.divide(num1, num2)
    }
}
