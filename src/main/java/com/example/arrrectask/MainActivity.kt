package com.example.arrrectask

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private val data = ArrayList<Model>()
    private lateinit var adapter: ArrAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = ArrAdapter(this,data)
        adapter.setItemClickListener(object : ArrAdapter.ItemClickListener {
            override fun onItemClick(position: Int) {
                showDeleteDialog(position)
            }
        })
        et2.setOnClickListener {
            val calendar = Calendar.getInstance()

            val dateListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val selectedDate = dateFormat.format(calendar.time)
                et2.setText(selectedDate)
            }

            val datePickerDialog = DatePickerDialog(
                this,
                dateListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.show()
        }

        et3.setOnClickListener {
                val calendar = Calendar.getInstance()

                val timeListener = TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                    calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                    calendar.set(Calendar.MINUTE, minute)

                    val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
                    val selectedTime = timeFormat.format(calendar.time)
                    et3.setText(selectedTime)
                }

                val timePickerDialog = TimePickerDialog(
                    this,
                    timeListener,
                    calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE),
                    true
                )
                timePickerDialog.show()
        }
        btn1.setOnClickListener {
            val getName = et1.text.toString()
            val getDate = et2.text.toString()
            val getTime = et3.text.toString()
            if (getName.isNotEmpty()&&getDate.isNotEmpty()&&getTime.isNotEmpty()){
                data.add(Model(getName,getDate,getTime))
                et1.text.clear()
                et2.text.clear()
                et3.text.clear()
                recycleview.layoutManager = LinearLayoutManager(this)
                recycleview.adapter = adapter
            }
            else{
                Toast.makeText(this,"Enter all fields",Toast.LENGTH_SHORT).show()
            }
        }

    }
    private fun showDeleteDialog(position: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Delete Item")
        builder.setMessage("Are you sure you want to delete this item?")
        builder.setPositiveButton("Delete") { _: DialogInterface, _: Int ->
            data.removeAt(position)
            adapter.notifyDataSetChanged()
        }
        builder.setNegativeButton("Cancel") { dialog: DialogInterface, _: Int ->
            dialog.dismiss()
        }
        val alertDialog = builder.create()
        alertDialog.show()
    }
}

