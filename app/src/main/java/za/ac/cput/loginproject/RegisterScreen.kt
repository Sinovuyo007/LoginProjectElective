package za.ac.cput.loginproject

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(navController: NavHostController,userDao: UserDao) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text("Register Screen")
        // Email TextField
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Password TextField
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Row with three buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Button(
                onClick = { scope.launch {
                    registerUser(email, password, userDao) // Call registerUser
                    navController.navigate("login") // Navigate to login screen after registration
                    // Optionally show a success message
                    println("REGISTERED successfully")
                }},
                modifier = Modifier.weight(1f)
            ) {
                Text("Login")
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = { navController.navigate("register") },
                modifier = Modifier.weight(1f)
            ) {
                Text("Register")
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = {navController.navigate("display") },
                modifier = Modifier.weight(1f)
            ) {
                Text("Display")
            }
        }
    }
}
suspend fun registerUser(email: String, password: String, userDao: UserDao) {
    val newUser = User(email = email, password = password)
    userDao.insertUser(newUser)
}