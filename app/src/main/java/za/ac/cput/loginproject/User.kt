package za.ac.cput.loginproject

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Users")
data class User(
    @PrimaryKey val email: String,
    val password: String)
