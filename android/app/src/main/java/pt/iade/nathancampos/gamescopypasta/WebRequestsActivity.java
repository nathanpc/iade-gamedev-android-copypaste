package pt.iade.nathancampos.gamescopypasta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import pt.iade.nathancampos.gamescopypasta.utilities.WebRequest;

public class WebRequestsActivity extends AppCompatActivity {
    private EditText responseText;
    private Button getRequestButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_requests);

        setupComponents();
    }

    public void setupComponents() {
        responseText = (EditText)findViewById(R.id.web_resp_text);

        getRequestButton = (Button)findViewById(R.id.web_get_button);
        getRequestButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("WebRequest", "Began GET web request");
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            WebRequest webRequest = new WebRequest(
                                    new URL(WebRequest.LOCALHOST + "/"));
                            String result = webRequest.performGetRequest();

                            Log.d("WebRequest", "Finished GET web request");
                            responseText.setText(result);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

                thread.start();
                Log.d("WebRequest", "GET request thread started");
            }
        });
    }
}