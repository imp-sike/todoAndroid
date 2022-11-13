package io.sms.todoapp

/** [todoStatus] is 0 for pending todos, 1 for completed todos. **/
data class TodoModel(val todoText: String, var todoStatus: Boolean)

