package io.sms.todoapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/** [todoStatus] is 0 for pending todos, 1 for completed todos. **/
@Entity
data class TodoModel(
    @PrimaryKey(autoGenerate = true) val tid: Int,
    @ColumnInfo(name = "todo_text") val todoText: String?,
    @ColumnInfo(name = "todo_status")var todoStatus: Boolean?)

