package com.example.healthcareapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.HashMap;
import java.util.Map;

public class Healthcare extends AppCompatActivity {
    EditText etName, etEmail, etlocation;
    Button btnH;
    RequestQueue queue;
    final String URL = "https://192.168.0.3/healthcare/admin/view.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healthcare);

        queue = Volley.newRequestQueue(getApplicationContext());

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etlocation = findViewById(R.id.etLocation);

        Button button = findViewById(R.id.submit);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeRequest();
            }
        });

        btnH = findViewById(R.id.btnH);
        btnH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Close the Healthcare activity and go back to HomeActivity
            }
        });
    }

    public void makeRequest() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Response", response); // Log server response for debugging
                // Display "successful" message
                Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", error.toString()); // Log error for debugging
                // Error message removed
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("name", etName.getText().toString());
                params.put("email", etEmail.getText().toString());
                params.put("location", etlocation.getText().toString());
                return params;
            }
        };
        queue.add(stringRequest);
    }
}
