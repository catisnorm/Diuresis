package com.example.diuresis

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    private val newVolumeRecordActivityRequestCode = 1
    private lateinit var volumeRecordViewModel: VolumeRecordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = VolumeRecordAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        volumeRecordViewModel = ViewModelProvider(this).get(VolumeRecordViewModel::class.java)
        volumeRecordViewModel.allRecords.observe(this,
            Observer { records ->
                records?.let {
                    adapter.setRecords(it)
                }
            })

        fab.setOnClickListener {
            val intent = Intent(this, CreateNewRecord::class.java)
            startActivityForResult(intent, newVolumeRecordActivityRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newVolumeRecordActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getStringExtra(CreateNewRecord.EXTRA_REPLY)?.let {
                val record = VolumeRecord(0, it.toDouble(), Date())
                volumeRecordViewModel.insert(record)
            }
        } else {
            Toast.makeText(applicationContext, "Не удалось сохранить запись.", Toast.LENGTH_LONG)
                .show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when(item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
