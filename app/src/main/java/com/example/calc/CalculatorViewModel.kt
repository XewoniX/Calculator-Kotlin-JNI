import androidx.lifecycle.ViewModel
import com.example.calc.NativeCalculator
import com.example.calc.Operator
import com.example.calc.Operator.*
import java.util.Stack


class CalculatorViewModel : ViewModel() {
    private val DIVIDE_BY_ZERO_ERROR: String = "Nie mozna dzielic przez 0"
    private val calculator = NativeCalculator()
    private fun String.toOperator(): Operator =
        Operator.entries.find { it.operator == this } ?: EMPTY

    fun calculate(expression: String): String {
        try {
            if (expression.isEmpty()) return "0"

            if (expression.last().toString().toOperator() in listOf(
                    PLUS,
                    MINUS,
                    MULTIPLY,
                    DIVIDE,
                    OPENBRACKET,
                )
            ) {
                throw IllegalArgumentException("Nieprawidłowy operator na końcu wyrażenia")
            }
            return performCalculation(expression)
        } catch (e: NumberFormatException) {
            return DIVIDE_BY_ZERO_ERROR
        } catch (e: Exception) {
            return "Błąd w wyrażeniu"
        }
    }

    private fun performCalculation(expression: String): String {
        fun getPrecedence(operator: Operator): Int = when (operator) {
            PLUS, MINUS -> 1
            MULTIPLY, DIVIDE -> 2
            else -> -1
        }

        fun applyOperation(num1: Double, num2: Double, operator: Operator): Double =
            when (operator) {
                PLUS -> calculator.add(num1, num2)
                MINUS -> calculator.subtract(num1, num2)
                MULTIPLY -> calculator.multiply(num1, num2)
                DIVIDE -> divide(num1, num2)
                else -> throw IllegalArgumentException("Nieznany operator: $operator")
            }

        val values = Stack<Double>()
        val ops = Stack<Operator>()

        var i = 0
        while (i < expression.length) {
            when {
                expression[i] == '-' && (i == 0 || expression[i - 1] in "+-*/(") -> {
                    var start = i
                    i++
                    while (i < expression.length && (expression[i].isDigit() || expression[i] == '.')) i++
                    values.push(
                        ("-" + expression.substring(
                            start + 1,
                            i
                        )).toDouble()
                    )
                    i--
                }

                expression[i].isDigit() || expression[i] == '.' -> {
                    val start = i
                    while (i < expression.length && (expression[i].isDigit() || expression[i] == '.')) i++
                    values.push(expression.substring(start, i).toDouble())
                    i--
                }

                expression[i] == '(' -> {
                    ops.push(OPENBRACKET)
                }

                expression[i] == ')' -> {
                    while (ops.isNotEmpty() && ops.peek() != OPENBRACKET) {
                        val b = values.pop()
                        val a = values.pop()
                        val operator = ops.pop()
                        values.push(applyOperation(a, b, operator))
                    }
                    ops.pop()

                }

                expression[i].toString().toOperator() in listOf(
                    PLUS,
                    MINUS,
                    MULTIPLY,
                    DIVIDE,
                ) -> {
                    val currentOp = expression[i].toString().toOperator()

                    if (currentOp == EMPTY) {
                        throw IllegalArgumentException("Nieznany operator: ${expression[i]}")
                    }

                    while (ops.isNotEmpty() && getPrecedence(ops.peek()) >= getPrecedence(currentOp)) {
                        val b = values.pop()
                        val a = values.pop()
                        val op = ops.pop()
                        values.push(applyOperation(a, b, op))
                    }
                    ops.push(currentOp)
                }

                else -> throw IllegalArgumentException("Nieprawidłowy znak: ${expression[i]}")
            }
            i++

        }
        while (ops.isNotEmpty()) {
            val b = values.pop()
            val a = values.pop()
            val op = ops.pop()
            val result = applyOperation(a, b, op)
            values.push(result)
            println("Przetworzono pozostały operator: $a ${op.operator} $b = $result")
        }

        return values.pop().toString()
    }

    private fun divide(num1: Double, num2: Double): Double {
        if (num2 == 0.0) {
            throw NumberFormatException(DIVIDE_BY_ZERO_ERROR)
        }
        return calculator.divide(num1, num2)
    }
}
