import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calc.Operator
import com.example.calc.Operator.DIVIDE
import com.example.calc.Operator.EMPTY
import com.example.calc.Operator.MINUS
import com.example.calc.Operator.MULTIPLY
import com.example.calc.Operator.PLUS

@Composable
fun CalcScreen(viewModel: CalculatorViewModel = viewModel()) {

    var operator by remember { mutableStateOf<Operator>(EMPTY) }
    var input1 by remember { mutableStateOf("") }
    var input2 by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            TextField(
                value = input1,
                onValueChange = {
                    if (operator == EMPTY) {
                        input1 = it
                    }
                },
                textStyle = LocalTextStyle.current.copy(color = Color.White, fontSize = 24.sp),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color(0xFF2C2C2C),
                    focusedIndicatorColor = Color.White,
                    unfocusedIndicatorColor = Color.Gray
                ),
                modifier = Modifier
                    .weight(1f)
                    .height(100.dp)
            )
            TextField(
                value = input2,
                onValueChange = {
                    if (operator != EMPTY) {
                        input2 = it
                    }
                },
                textStyle = LocalTextStyle.current.copy(color = Color.White, fontSize = 24.sp),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color(0xFF2C2C2C),
                    focusedIndicatorColor = Color.White,
                    unfocusedIndicatorColor = Color.Gray
                ),
                modifier = Modifier
                    .weight(1f)
                    .height(100.dp)
            )
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                CalculatorButton("7", onClick = {
                    if (operator == EMPTY) input1 += "7" else input2 += "7"
                })
                CalculatorButton("8", onClick = {
                    if (operator == EMPTY) input1 += "8" else input2 += "8"
                })
                CalculatorButton("9", onClick = {
                    if (operator == EMPTY) input1 += "9" else input2 += "9"
                })
                CalculatorButton("/", onClick = {
                    if (input1.isNotEmpty()) {
                        operator = DIVIDE
                    }
                })
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                CalculatorButton("4", onClick = {
                    if (operator == EMPTY) input1 += "4" else input2 += "4"
                })
                CalculatorButton("5", onClick = {
                    if (operator == EMPTY) input1 += "5" else input2 += "5"
                })
                CalculatorButton("6", onClick = {
                    if (operator == EMPTY) input1 += "6" else input2 += "6"
                })
                CalculatorButton("*", onClick = {
                    if (input1.isNotEmpty()) {
                        operator = MULTIPLY
                    }
                })
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                CalculatorButton("1", onClick = {
                    if (operator == EMPTY) input1 += "1" else input2 += "1"
                })
                CalculatorButton("2", onClick = {
                    if (operator == EMPTY) input1 += "2" else input2 += "2"
                })
                CalculatorButton("3", onClick = {
                    if (operator == EMPTY) input1 += "3" else input2 += "3"
                })
                CalculatorButton("-", onClick = {
                    if (input1.isNotEmpty()) {
                        operator = MINUS
                    }
                })
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                CalculatorButton("C", onClick = {
                    input1 = ""
                    input2 = ""
                    operator = EMPTY
                })
                CalculatorButton("0", onClick = {
                    if (operator == EMPTY) input1 += "0" else input2 += "0"
                })
                CalculatorButton("=", onClick = {
                    result = viewModel.calculate(input1, input2, operator)
                    input1 = result // Przypisanie wyniku do input1
                    input2 = ""
                    operator = EMPTY
                })
                CalculatorButton(PLUS.operator, onClick = {
                    if (input1.isNotEmpty()) {
                        operator = PLUS
                    }
                })
            }
        }
    }
}

@Composable
fun CalculatorButton(label: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF444444)),
        modifier = Modifier.height(100.dp)
    ) {
        Text(text = label, fontSize = 28.sp, color = Color.White)
    }
}





