package za.ac.cput.loginproject

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
@Dao
interface UserDao {
    @Query("SELECT * FROM Users WHERE email = :email AND password = :password")
    suspend fun getUser(email: String,password : String): User?

    @Insert(onConflict = OnConflictStrategy.REPLACE) // Or REPLACE
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM Users")
    suspend fun getAllUsers(): List<User>
}