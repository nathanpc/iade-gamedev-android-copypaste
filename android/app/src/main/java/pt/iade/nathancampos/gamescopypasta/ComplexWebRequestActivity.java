package pt.iade.nathancampos.gamescopypasta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.net.URL;
import java.util.HashMap;

import pt.iade.nathancampos.gamescopypasta.utilities.WebRequest;

public class ComplexWebRequestActivity extends AppCompatActivity {
    protected TextView counterLabel;
    protected Button clickerButton;
    protected EditText stringEdit;
    protected Button setStringButton;
    protected Button getStringButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complex_web_request);

        setupComponents();
    }

    public void countClicker() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    WebRequest webRequest = new WebRequest(
                            new URL(WebRequest.LOCALHOST + "/count"));
                    String result = webRequest.performGetRequest();
                    counterLabel.setText(result);
                } catch (Exception e) {
                    Log.e("Clicker", e.toString());
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    public void getTheString() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    WebRequest webRequest = new WebRequest(
                            new URL(WebRequest.LOCALHOST + "/get_string"));
                    String result = webRequest.performGetRequest();
                    stringEdit.setText(result);
                } catch (Exception e) {
                    Log.e("GetString", e.toString());
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    public void setTheString() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    WebRequest webRequest = new WebRequest(
                            new URL(WebRequest.LOCALHOST + "/set_string"));
                    HashMap<String, String> params = new HashMap<String, String>();
                    params.put("some_string", stringEdit.getText().toString());
                    webRequest.performPostRequest(params);
                } catch (Exception e) {
                    Log.e("SetString", e.toString());
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    private void setupComponents() {
        // Clicker
        counterLabel = (TextView) findViewById(R.id.counter_label);
        clickerButton = (Button) findViewById(R.id.clicker_button);
        clickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countClicker();
            }
        });

        // String thing.
        stringEdit = (EditText) findViewById(R.id.string_edit);
        setStringButton = (Button) findViewById(R.id.set_button);
        setStringButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTheString();
            }
        });
        getStringButton = (Button) findViewById(R.id.get_button);
        getStringButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTheString();
            }
        });
    }
}