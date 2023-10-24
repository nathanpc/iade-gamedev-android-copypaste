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

public class WebRequestsActivity extends AppCompatActivity {
    public static String localhost = "http://10.0.2.2:5000";
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
                            String result = performGetRequest(localhost + "/");
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

    public String performGetRequest(String strUrl) throws IOException {
        URL url = new URL(strUrl);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        String result = null;

        try {
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            result = readStreamToString(in);
        } finally {
            urlConnection.disconnect();
        }

        return result;
    }

    public String readStreamToString(InputStream in) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(in);
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        for (int result = bis.read(); result != -1; result = bis.read()) {
            buf.write((byte) result);
        }

        return buf.toString("UTF-8");
    }
}