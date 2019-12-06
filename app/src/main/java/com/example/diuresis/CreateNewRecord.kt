package com.example.diuresis

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText

class CreateNewRecord : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_record)
        val editVolumeText = findViewById<EditText>(R.id.volumeText)

        val button = findViewById<Button>(R.id.saveButton)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editVolumeText.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val volume = editVolumeText.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, volume)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    }
}
