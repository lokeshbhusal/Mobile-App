package com.moutamid.addplacesapp.Activities;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.icu.lang.UCharacter;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.fxn.stash.Stash;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moutamid.addplacesapp.R;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    // UI references.
    private EditText inputEmail, inputPassword;
    private FirebaseAuth auth;
    private ProgressBar progressBar;
    private TextView btnSignup;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();
        // If the user is already logged in, redirect to HomePage
        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, HomePage.class));
            finish();
        }
        setContentView(R.layout.activity_login);
        // Animation for the layout entrance
        Animation bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnSignup = (TextView) findViewById(R.id.btn_signup);
        btnLogin = (Button) findViewById(R.id.btn_login);
        LinearLayout main_layout = findViewById(R.id.main_layout);
        main_layout.setAnimation(bottomAnim);
        auth = FirebaseAuth.getInstance();
        // Setup click listener for signup button to redirect to SignupActivity
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });

        // Handling the login button click
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();

                // Validate if the email and password are not empty
                if (TextUtils.isEmpty(email)) {
                    show_toast("Email address is not yet provided", 0);
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    show_toast("Password is not yet provided", 0);
                    return;
                }

                // Initialize and display the loading dialog
                Dialog loadingDialog = new Dialog(LoginActivity.this);
                loadingDialog.setContentView(R.layout.loading);
                Objects.requireNonNull(loadingDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(UCharacter.JoiningType.TRANSPARENT));
                loadingDialog.setCancelable(false);
                loadingDialog.show();

                // Authenticate user
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // Ensure to dismiss the loading dialog regardless of the result
                                loadingDialog.dismiss();

                                if (!task.isSuccessful()) {
                                    // There was an error
                                    if (password.length() < 6) {
                                        show_toast(getString(R.string.minimum_password), 0);
                                    } else {
                                        show_toast(getString(R.string.auth_failed), 0);
                                    }
                                } else {
                                    // Authentication succeed
                                    FirebaseDatabase.getInstance().getReference().child("AddPlacesApp").child("Users")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                    String name = snapshot.child("name").getValue(String.class);
                                                    Stash.put("name", name);
                                                    show_toast("Successfully Logged In", 1);
                                                    startActivity(new Intent(LoginActivity.this, HomePage.class));
                                                    finish();
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError error) {
                                                    show_toast("Database error: " + error.getMessage(), 0);
                                                }
                                            });
                                }
                            }
                        });
            }
        });

    }
    // Method to display custom toast messages
    public void show_toast(String message, int type) {
        LayoutInflater inflater = getLayoutInflater();

        View layout;
        if (type == 0) { // Error message layout
            layout = inflater.inflate(R.layout.toast_wrong,
                    (ViewGroup) findViewById(R.id.toast_layout_root));
        } else { // Success message layou
            layout = inflater.inflate(R.layout.toast_right,
                    (ViewGroup) findViewById(R.id.toast_layout_root));

        }
        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText(message);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.BOTTOM, 0, 10);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
}