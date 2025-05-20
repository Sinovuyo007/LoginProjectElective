package za.ac.cput.loginproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import za.ac.cput.loginproject.ui.theme.LoginProjectTheme
//I Sinovuyo mathungana 230143725
//I Alvaro Ferrraz 220075018
//I Aristide Izabkora 220345821
// hereby declare that we have done this project on our own.  Any code obtained somewhere will be reference using some URL.

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val context = LocalContext.current

            val database = remember {
                Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "Users"
                ).build()
            }
            val userDao = remember { database.userDao() }

            LoginProjectTheme {
                NavHost(navController = navController, startDestination = "login") {
                    composable("login") { LoginScreen(navController = navController, userDao = userDao) }
                    composable("register") { RegisterScreen(navController = navController, userDao = userDao) }
                    composable("display") { DisplayScreen(navController = navController,userDao = userDao) } // Pass userDao here
                }
            }
        }
    }
}



