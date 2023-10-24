package pt.iade.nathancampos.gamescopypasta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WebRequestActivity extends AppCompatActivity {
    private TextView responseText;
    private Button getTestButton;

    public static String localhost = "http://10.0.2.2:5000";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_request);

        setupComponents();
    }

    public void setupComponents() {
        responseText = (TextView)findViewById(R.id.web_response_text);

        getTestButton = (Button)findViewById(R.id.web_get_button);
        getTestButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("WebRequest", "Started the GET operation");
                try {
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                responseText.setText(performGetRequest(localhost + "/"));
                                Log.d("WebRequest", "Finished the GET operation");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });

                    thread.start();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                Log.d("WebRequest", "GET operation is happening but this has already returned");
            }
        });
    }

    public String performGetRequest(String strUrl) throws IOException {
        String str = null;
        URL url = new URL(strUrl);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        try {
            str = readStreamToString(urlConnection.getInputStream());
        } finally {
            urlConnection.disconnect();
        }

        return str;
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