package io.sms.todoapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    var mText: String = ""
    lateinit var todoRecyclerView: RecyclerView
    lateinit var adapter: CustomAdapter
    val todoModelList: ArrayList<TodoModel> = ArrayList()
    lateinit var fab: FloatingActionButton
    lateinit var nothing: TextView


    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = Room.databaseBuilder(
            applicationContext,
            TodoDatabase::class.java, "todo-db"
        ).build()

        val todoDAO = db.todoDAO()



        nothing = findViewById(R.id.nothing)

        adapter = CustomAdapter(todoModelList, todoDAO)

        todoRecyclerView = findViewById(R.id.todoList)
        todoRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
        todoRecyclerView.adapter = adapter

        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Add new Item!")

        fab = findViewById(R.id.addNewTodo)
        fab.setOnClickListener {
            val input = EditText(this)
            builder.setView(input)
            builder.setPositiveButton(
                "OK"
            ) { _, _ ->
                mText = input.text.toString()

                CoroutineScope(Dispatchers.Default).launch {
                    todoDAO.insertTodo(TodoModel(0, mText, false))
                    todoModelList.clear()
                    todoModelList.addAll(todoDAO.getAll())
                    CoroutineScope(Dispatchers.Main).launch {
                        adapter.notifyDataSetChanged()
                    }
                }



            }
            builder.setNegativeButton(
                "Cancel"
            ) { dialog, _ -> dialog.cancel() }



            builder.show()


        }

        // felt lazy , simple find the place yourself
        //        if(todoModelList.isEmpty()) {
        //            nothing.visibility = View.VISIBLE
        //        } else {
        //            nothing.visibility = View.GONE
        //        }

        CoroutineScope(Dispatchers.Default).launch {

            val todoList: List<TodoModel> = todoDAO.getAll()

            todoModelList.addAll(todoList)
        }



    }
}