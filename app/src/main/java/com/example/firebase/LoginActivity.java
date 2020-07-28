package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.Arrays;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

        private static final int RC_SIGN_IN = 0;
        private static final String TAG = "LoginActivity";
        private FirebaseAuth.AuthStateListener mAuthListener;
        private FirebaseAuth mAuth;

//        private Button btnSignOut;
//        private TextView txtEmail;
//        private TextView txtUser;

        private Button btnSignIn;
        private Button btnExit;

        private Button button_miCuenta;
        private Button button_misParcelas;
        private Button button_mapa;
        private Button button_noticias;
        private Button button_reclamos;
        private Button button_configuracion;

//        private ImageView imgProfile;

        List<AuthUI.IdpConfig> providers = Arrays.asList(
//                new AuthUI.IdpConfig.PhoneBuilder().build(),
//                new AuthUI.IdpConfig.TwitterBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build(),
                new AuthUI.IdpConfig.FacebookBuilder().build(),
                new AuthUI.IdpConfig.EmailBuilder().build()
        );


        @Override
        protected void onStart() {
            super.onStart();
            mAuth.addAuthStateListener(mAuthListener);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

            //Asi obtengo el id unico del dispositivo
            Log.i(TAG, "KK: " + FirebaseInstanceId.getInstance().getToken());

            mAuth = FirebaseAuth.getInstance();

//            imgProfile = (ImageView) findViewById(R.id.imgProfile);
            btnSignIn = (Button) findViewById(R.id.btnSignIn);
            btnExit = (Button) findViewById(R.id.btnExit);
//            btnSignOut = (Button) findViewById(R.id.btnSignOut);
//
//            txtEmail = (TextView) findViewById(R.id.txtEmail);
//            txtUser = (TextView) findViewById(R.id.txtUser);

            button_miCuenta = (Button) findViewById(R.id.button_miCuenta);
            button_misParcelas = (Button) findViewById(R.id.button_misParcelas);
            button_mapa = (Button) findViewById(R.id.button_mapa);
            button_noticias = (Button) findViewById(R.id.button_noticias);
            button_reclamos = (Button) findViewById(R.id.button_reclamos);
            button_configuracion = (Button) findViewById(R.id.button_configuracion);


            mAuthListener = new FirebaseAuth.AuthStateListener() {

                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    updateUi();
                }
            };
        }

        private void updateUi() {
            FirebaseUser user = mAuth.getCurrentUser();
            //ver de intentar cargar fragments segun si esta logueado o no
            if (user == null) {
                btnSignIn.setVisibility(View.VISIBLE);
                btnExit.setVisibility(View.VISIBLE);
//                btnSignOut.setVisibility(View.GONE);
//                txtEmail.setVisibility(View.GONE);
//                txtUser.setVisibility(View.GONE);
//                imgProfile.setImageBitmap(null);

                button_miCuenta.setVisibility(View.GONE);
                button_misParcelas.setVisibility(View.GONE);
                button_mapa.setVisibility(View.GONE);
                button_noticias.setVisibility(View.GONE);
                button_reclamos.setVisibility(View.GONE);
                button_configuracion.setVisibility(View.GONE);
            } else {
                btnSignIn.setVisibility(View.GONE);
                btnExit.setVisibility(View.GONE);
//                btnSignOut.setVisibility(View.VISIBLE);
//                txtEmail.setVisibility(View.VISIBLE);
//                txtUser.setVisibility(View.VISIBLE);
//
//                txtUser.setText(user.getDisplayName());
//                txtEmail.setText(user.getEmail());
//                Picasso.with(ActivityFUIAuth.this).load(user.getPhotoUrl()).into(imgProfile);

                button_miCuenta.setVisibility(View.VISIBLE);
                button_misParcelas.setVisibility(View.VISIBLE);
                button_mapa.setVisibility(View.VISIBLE);
                button_noticias.setVisibility(View.VISIBLE);
                button_reclamos.setVisibility(View.VISIBLE);
                button_configuracion.setVisibility(View.VISIBLE);
            }
        }

//        public void signOut(View view) {
//            AuthUI.getInstance()
//                    .signOut(this)
//                    .addOnCompleteListener(new OnCompleteListener<Void>() {
//                        public void onComplete(@NonNull Task<Void> task) {
//                            Toast.makeText(LoginActivity.this, "signed out succesfully ... ", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == RC_SIGN_IN) {
                IdpResponse response = IdpResponse.fromResultIntent(data);
                if (resultCode == RESULT_OK) {
                    Log.d(this.getClass().getName(), "This user signed in with " + response.getProviderType());
                    updateUi();
                } else {
                    updateUi();
                }
            }
        }

        public void deleteAccount(View view) {
            AuthUI.getInstance()
                    .delete(this)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                        }
                    });
        }

        public void signIn(View view) {
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            //.setIsSmartLockEnabled(false,true)
                            .setIsSmartLockEnabled(false)
                            .setAvailableProviders(providers)
                            //.setTheme(R.style.AppTheme)
//                            .setTosUrl("https://superapp.example.com/terms-of-service.html")
//                            .setPrivacyPolicyUrl("https://superapp.example.com/privacy-policy.html")
//                            .setLogo(R.drawable.logo)
                            .build(),
                    RC_SIGN_IN);
        }

    public void toMiCuenta(View view) {
        startActivity(new Intent(LoginActivity.this, CuentaActivity.class));
//        finish();
    }

    public void exitApp(View view) {
        finish();
    }
}
