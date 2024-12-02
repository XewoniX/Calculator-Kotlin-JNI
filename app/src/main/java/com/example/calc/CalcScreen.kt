import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import java.text.DecimalFormat

@Composable
fun CalcScreen(viewModel: CalculatorViewModel = viewModel()) {
    var action by remember { mutableStateOf("") }
    var result: String by remember { mutableStateOf("") }
    var history by remember { mutableStateOf("") }
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
                value = action,
                onValueChange = {
                    action = it
                },
                label = { Text(text = history, color = Color.Yellow, fontSize = 24.sp) },
                textStyle = LocalTextStyle.current.copy(color = Color.White, fontSize = 38.sp),
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
                    action += 7
                })
                CalculatorButton("8", onClick = {
                    action += 8
                })
                CalculatorButton("9", onClick = {
                    action += 9
                })
                CalculatorButton("/", onClick = {
                    action += "/"
                })
                CalculatorButton("(", onClick = {
                    action += "("
                })
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                CalculatorButton("4", onClick = {
                    action += 4
                })
                CalculatorButton("5", onClick = {
                    action += 5
                })
                CalculatorButton("6", onClick = {
                    action += 6
                })
                CalculatorButton("X", onClick = {
                    action += "*"
                })
                CalculatorButton(")", onClick = {
                    action += ')'
                })
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                CalculatorButton("1", onClick = {
                    action += 1
                })
                CalculatorButton("2", onClick = {
                    action += 2
                })
                CalculatorButton("3", onClick = {
                    action += 3
                })
                CalculatorButton("-", onClick = {
                    action += "-"
                })
                CalculatorButton(".", onClick = {
                    action += '.'
                })
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                CalculatorButton("C", onClick = {
                    action = ""
                    history = ""
                })
                CalculatorButton("0", onClick = {
                    action += 0
                })
                CalculatorButton("=", onClick = {
                    try {
                        result = viewModel.calculate(action)
                        history = action
                        action = result

                    } catch (e: Exception) {
                        result = "Błąd"
                        action = ""
                    }
                })
                CalculatorButton("+", onClick = {
                    action += '+'
                })
                CalculatorButton("<-", onClick = {
                    action = action.dropLast(1)
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





