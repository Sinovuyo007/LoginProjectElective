package za.ac.cput.loginproject

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch


@Composable
fun DisplayScreen(navController: NavHostController,userDao: UserDao) {
    var users by remember { mutableStateOf<List<User>>(emptyList()) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit) { // Fetch users when the composable is created
        scope.launch {
            users = userDao.getAllUsers()
        }
    }

        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
            ) {
            Text(
                text = "Display Screen",
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(20.dp))
            if (users.isEmpty()) {
                Text("No users registered yet.")
            } else {
                LazyColumn {
                    items(users) { user ->
                        Text("User: ${user.toString()}")
                        HorizontalDivider()
                    }
                }
            }
            Row {
                // Row with three buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Button(
                        onClick = { navController.navigate("login") },
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

                }
            }
        }
}