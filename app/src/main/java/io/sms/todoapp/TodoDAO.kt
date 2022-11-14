package io.sms.todoapp

import androidx.room.*

@Dao
interface TodoDAO {

    @Query("SELECT * FROM TodoModel")
    suspend fun getAll(): List<TodoModel>

    // This does new insertion + update from single place
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todoModel: TodoModel)

    // single item deletion
    @Delete
    suspend fun delete(user: TodoModel)

}