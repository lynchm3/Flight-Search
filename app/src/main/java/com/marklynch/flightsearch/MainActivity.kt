package com.marklynch.flightsearch

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.joestelmach.natty.Parser
import com.marklynch.flightsearch.data.Flight
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


private const val SPEECH_REQUEST_CODE = 0

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            displaySpeechRecognizer()
        }

        //Need
        //Origin airports (from, leaving from)
        //Destination airports (to, arriving in)
        //1 or 2 dates (number and date)

        //Phase 2
        //Ticket types and quantities :/


        //"Manchester to Barcelona
        // for one adult
        // going out on the 14th of October
        // returning on the 19th of October"

    }

    // Create an intent that can start the Speech Recognizer activity
    private fun displaySpeechRecognizer() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
        }
        // Start the activity, the intent will be populated with the speech text
        startActivityForResult(intent, SPEECH_REQUEST_CODE)


    }


    // This callback is invoked when the Speech Recognizer returns.
// This is where you process the intent and extract the speech text from the intent.
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == SPEECH_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val spokenText: String? =
                data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS).let { results ->
                    results?.get(0)
                }
            // Do something with spokenText

            val flight1: Flight? = null
            val flight2: Flight? = null
            sendVoiceSearchAndParseResultToFirebase(spokenText, flight1, flight2)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun sendVoiceSearchAndParseResultToFirebase(
        spokenText: String?,
        flight1: Flight?,
        flight2: Flight?
    ) {

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
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
