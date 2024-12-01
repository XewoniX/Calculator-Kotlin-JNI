import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CalculatorScreen(viewModel: CalculatorViewModel = viewModel()) {
    var input1 by remember { mutableStateOf("") }
    var input2 by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextField(
            value = input1,
            onValueChange = { input1 = it },
            label = { Text("First Number") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = input2,
            onValueChange = { input2 = it },
            label = { Text("Second Number") },
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = {
                result = viewModel.calculate(input1, input2, "add")
            }) {
                Text("Add")
            }
            Button(onClick = {
                result = viewModel.calculate(input1, input2, "subtract")
            }) {
                Text("Subtract")
            }
            Button(onClick = {
                result = viewModel.calculate(input1, input2, "multiply")
            }) {
                Text("Multiply")
            }
            Button(onClick = {
                result = viewModel.calculate(input1, input2, "divide")
            }) {
                Text("Divide")
            }
        }
        Text("Result: $result", fontSize = 18.sp, modifier = Modifier.padding(top = 16.dp))
    }
}
