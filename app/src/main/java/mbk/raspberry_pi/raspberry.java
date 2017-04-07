package mbk.raspberry_pi;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Locale;


public class raspberry extends Activity {

    public TextView speechOutput;
    private TextToSpeech tts;
    String spokenText;

    // TESTING//
    public String action[] = {"turn on", "turn off", "unlock", "lock", "status", "temperature"};
    public String noun[] = {"main light", "main door", "stove"};
    private String action_todo;
    private String noun_todo;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    public String get_action(String speechOutput) {
        int i = 0;
        while (action[i] != null & i< action.length-1) {
            if (speechOutput.contains(action[i])) {
                return action[i];
            } else {
                i++;

            }
        }
        return null;
    }

    public String get_noun(String speechOutput) {
        int i = 0;
        while (noun[i] != null & i< noun.length-1) {
            if (speechOutput.contains(noun[i])) {
                return noun[i];
            }
            else
                i++;
        }
        return null;
    }

    public void main_lights(String action_todo) {
        if (action_todo == "turn off") {
            //turn off the light
        } else if (action_todo == "turn on") {
            //turn on the light
        }
    }

    public void main_door(String action_todo) {
        if (action_todo == "lock") {
            //lock the door
        } else if (action_todo == "unlock") {
            //un lock the door
        }
    }

    public void stove(String action_todo) {
        if (action_todo == "temperature") {
            //lock the door
        }
    }

    public boolean confirmation(String action_todo, String noun_todo) {
        boolean act = false;
        String confirm;
            if (action_todo==null|| noun_todo==null){
                speak("Sorry I didnt get you, Please speak again");
        }
        else {
                confirm = "Do you want me to " + action_todo + noun_todo;
                speak(confirm);
                act= true;
            }
        SystemClock.sleep(2500);
        //displaySpeechRecognizer();
/*
        if (spokenText == "yes") {
            act = true;
        } else if (spokenText == "no") {
            act = false;
        }*/
        return act;
    }

    public void My_Main() {
        action_todo = get_action(spokenText);
        noun_todo = get_noun(spokenText);
        SystemClock.sleep(1500);
        if (confirmation(action_todo, noun_todo)) {
            if (noun_todo == "main lights") {
                main_lights(action_todo);
            } else if (noun_todo == "main door") {
                speak("Alright, just give me a second ");
                main_door(action_todo);
            } else if (noun_todo == "stove") {
                stove(action_todo);
            }
        }
    }

    // Testing //
    public void onclick(View v) {
        if (v.getId() == R.id.speak) {
            speak(spokenText);
        } else if (v.getId() == R.id.Listen) {
            displaySpeechRecognizer();
        } else if (v.getId() == R.id.acting) {
            My_Main();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raspberry);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /*****************************************************/
       /*  Text to Speech  */
        /* Speak function */

    /*****************************************************/

    private void speak(String text) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        } else {
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    // ------------------------------------------------------

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_raspberry, menu);

        /*****************************************************/
        /* Text to Speech  */
        /* TTS Listner */
        /*****************************************************/

        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = tts.setLanguage(Locale.US);
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "This Language is not supported");
                    }

                } else {
                    Log.e("TTS", "Initilization Failed!");
                }
            }
        });
        // -----------------------------------------------------------------
        return true;
    }

    /*****************************************************/
       /*  Text to Speech  */

    /*****************************************************/

    @Override
    public void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
    // ------------------------------


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            // return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("raspberry Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }


    /*****************************************************/
       /*  This is a background process for connecting      */
      /*   to the arduino server and sending               */
     /*    the GET request with the added data           */

    /*****************************************************/

    private class Background_get extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            try {
                /* Change the IP to the IP you set in the arduino sketch */
                URL url = new URL("http://192.168.0.11/?" + params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder result = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null)
                    result.append(inputLine).append("\n");

                in.close();
                connection.disconnect();
                return result.toString();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    /*****************************************************/
       /*  This is a background process for Speech Recognition  */

    /*****************************************************/


    private static final int SPEECH_REQUEST_CODE = 0;

    // Create an intent that can start the Speech Recognizer activity
    private void displaySpeechRecognizer() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
// Start the activity, the intent will be populated with the speech text
        startActivityForResult(intent, SPEECH_REQUEST_CODE);
    }

    // This callback is invoked when the Speech Recognizer returns.
// This is where you process the intent and extract the speech text from the intent.
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        if (requestCode == SPEECH_REQUEST_CODE && resultCode == RESULT_OK) {
            List<String> results = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS);
            spokenText = results.get(0);
            // Do something with spokenText
//            speechOutput.setText(spokenText);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}