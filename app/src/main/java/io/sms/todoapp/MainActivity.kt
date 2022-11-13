package io.sms.todoapp

import android.content.DialogInterface
import android.opengl.Visibility
import android.os.Bundle
import android.text.InputType
import android.view.Display
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {

    var mText: String = ""
    lateinit var todoRecyclerView: RecyclerView
    lateinit var adapter: CustomAdapter
    val todoModelList: ArrayList<TodoModel> = ArrayList()
    lateinit var fab: FloatingActionButton
    lateinit var nothing: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nothing = findViewById(R.id.nothing)

        adapter = CustomAdapter(todoModelList)

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
                todoModelList.add(TodoModel(mText, false))
                adapter.notifyItemInserted(adapter.itemCount)
                if(todoModelList.isEmpty()) {
                    nothing.visibility = View.VISIBLE
                } else {
                    nothing.visibility = View.GONE
                }

            }
            builder.setNegativeButton(
                "Cancel"
            ) { dialog, _ -> dialog.cancel() }



            builder.show()


        }

        if(todoModelList.isEmpty()) {
            nothing.visibility = View.VISIBLE
        } else {
            nothing.visibility = View.GONE
        }


    }
}