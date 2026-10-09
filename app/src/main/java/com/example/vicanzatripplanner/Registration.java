
package com.example.vicanzatripplanner;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Registration extends AppCompatActivity {

    private EditText username, email, password;
    private Button registerBtn;

    private final String registerUrl =
            "http://10.0.2.2/android_api/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registration);

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main),
                (v, insets) -> {
                    Insets systemBars = insets.getInsets(
                            WindowInsetsCompat.Type.systemBars()
                    );

                    v.setPadding(
                            systemBars.left,
                            systemBars.top,
                            systemBars.right,
                            systemBars.bottom
                    );

                    return insets;
                }
        );

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        registerBtn = findViewById(R.id.registrationButton);

        registerBtn.setOnClickListener(v -> registerUser());
    }

    private void registerUser() {

        final String uname =
                username.getText().toString().trim();

        final String mail =
                email.getText().toString().trim();

        final String pass =
                password.getText().toString();

        // Validate input fields
        if (uname.isEmpty()) {
            username.setError("Please enter a username");
            username.requestFocus();
            return;
        }

        if (mail.isEmpty()) {
            email.setError("Please enter your email");
            email.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
            email.setError("Please enter a valid email");
            email.requestFocus();
            return;
        }

        if (pass.isEmpty()) {
            password.setError("Please enter a password");
            password.requestFocus();
            return;
        }

        registerBtn.setEnabled(false);

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                registerUrl,

                response -> {
                    registerBtn.setEnabled(true);

                    Log.d("RegistrationResponse", response);

                    Toast.makeText(
                            Registration.this,
                            response,
                            Toast.LENGTH_LONG
                    ).show();
                },

                error -> {
                    registerBtn.setEnabled(true);

                    Log.e(
                            "RegistrationError",
                            "Registration failed",
                            error
                    );

                    String message = "Registration failed. Please try again.";

                    if (error.networkResponse != null) {
                        message = "Server error: HTTP "
                                + error.networkResponse.statusCode;
                    } else if (error.getMessage() != null) {
                        message = error.getMessage();
                    }

                    Toast.makeText(
                            Registration.this,
                            message,
                            Toast.LENGTH_LONG
                    ).show();
                }
        ) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();

                params.put("username", uname);
                params.put("email", mail);
                params.put("password", pass);

                return params;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }
}
