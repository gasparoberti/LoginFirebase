package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CuentaActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    private Button btnSignOut;
    private TextView txtEmail;
    private TextView txtUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuenta);

        btnSignOut = (Button) findViewById(R.id.btnSignOut);

        txtEmail = (TextView) findViewById(R.id.txtEmail);
        txtUser = (TextView) findViewById(R.id.txtUser);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        txtUser.setText(user.getDisplayName());
        txtEmail.setText(user.getEmail());
    }

    public void signOut(View view) {
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(CuentaActivity.this, "signed out succesfully ... ", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(CuentaActivity.this, LoginActivity.class));
                        finish();
                    }
                });
    }
}
