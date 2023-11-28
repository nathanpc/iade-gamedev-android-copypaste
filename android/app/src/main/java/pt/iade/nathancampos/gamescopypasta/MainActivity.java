package pt.iade.nathancampos.gamescopypasta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.*;
import android.util.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends AppCompatActivity {
    private Button toastButton;
    private Button sensorsButton;
    private Button webRequestsButton;
    private Button closeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupComponents();
    }

    private void setupComponents() {
        toastButton = (Button)findViewById(R.id.toast_button);
        toastButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast toast = Toast.makeText(MainActivity.this,
                        "This is a Hello World toast! An amazing example of bad UX.",
                        Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        sensorsButton = (Button)findViewById(R.id.gyroaccel_button);
        sensorsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,
                        SensorsActivity.class);
                startActivity(intent);
            }
        });

        webRequestsButton = (Button)findViewById(R.id.webreq_button);
        webRequestsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,
                        WebRequestsActivity.class);
                startActivity(intent);
            }
        });

        closeButton = (Button)findViewById(R.id.close_button);
        closeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("BUTTONS", "User tapped the Close Button");
            }
        });
    }
}