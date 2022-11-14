package io.sms.todoapp

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [TodoModel::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDAO(): TodoDAO
}