package com.example.androidcomponentsexam

import android.os.Bundle
import android.provider.UserDictionary
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class ContentProviderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_provider)

        val projectionString = arrayOf(
            UserDictionary.Words._ID,
            UserDictionary.Words.WORD,
            UserDictionary.Words.LOCALE
        )

        val selectClause: String? = null

        val selectArgs: Array<String>? = null

        val order: String? = null

        // Queries the user dictionary and returns results
        val cursor = contentResolver.query(
            UserDictionary.Words.CONTENT_URI,   // The content URI of the words table
            projectionString,                        // The columns to return for each row
            selectClause,                   // Selection criteria
            selectArgs,      // Selection criteria
            order                          // The sort order for the returned rows
        )

    }
}