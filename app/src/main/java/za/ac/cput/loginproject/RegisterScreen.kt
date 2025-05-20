package za.ac.cput.loginproject // Ensure this matches your package structure

import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch


@Composable
fun RegisterScreen(navController: NavHostController, userDao: UserDao) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp)
            .background(Color(0xFFEFEFEF)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Register Screen",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF6200EE)
        )



        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            shape = RoundedCornerShape(8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))


        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            shape = RoundedCornerShape(8.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))


        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Button(
                onClick = { navController.navigate("login") },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFBB86FC))
            ) {
                Text("Login")
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = { scope.launch {
                    if(email.isEmpty() ||
                        password.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches())
                    {
                        println("Please fill in all fields")
                    }else {
                        //deleteAllUsers(userDao)
                        registerUser(email, password, userDao) // Call registerUser
                        // Optionally show a success message
                        println("REGISTERED successfully")
                        navController.navigate("login")
                    }
                }

                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE))
            ) {
                Text("Register")
            }
        }
    }
}

suspend fun registerUser(email: String, password: String, userDao: UserDao) {
    val newUser = User(email = email, password = password)
    userDao.insertUser(newUser)
}
