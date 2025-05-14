package za.ac.cput.loginproject

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
//Navigation Imports
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.room.Room
import za.ac.cput.loginproject.ui.theme.LoginProjectTheme

@Composable
fun AppNavigationHost(){
    //1. Create a NavController
    val navController = rememberNavController()
    val context = LocalContext.current

    // Build the database (ensure this happens only once)
    val database = remember {
        Room.databaseBuilder(
            context.applicationContext,
            UserDatabase::class.java,
            "user_database"
        ).build()
    }
    val userDao = remember { database.userDao() }
    //2. Create a NavHost container

    NavHost(
        navController = navController,
        startDestination = "login"
    ){
        // 3. Define each screen ("destination") associated with a route string
        composable(route = "login"){
            LoginScreen(navController, userDao = userDao)
        }
        composable(route = "register"){
            RegisterScreen(navController,userDao = userDao)
        }
        composable(route = "display"){
            DisplayScreen(navController,userDao = userDao)
        }
    }
}
@Preview(showBackground = true)
@Composable
fun AppNavigationPreview() {
    LoginProjectTheme {
        AppNavigationHost()
    }
}