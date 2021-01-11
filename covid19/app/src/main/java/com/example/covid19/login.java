package com.example.covid19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {

    Button button4Login,backLogin;
    TextView textView6;
    EditText loginUser, LoginPassword;

    FirebaseAuth auth;
    ImageView facebook,google,twitter;


    private GoogleSignInClient mGoogleSignInClient;
    private final static int RC_SIGN_IN = 123;
    private FirebaseAuth mAuth;


    @Override
    protected void onStart() {
        super.onStart();


        FirebaseUser user = mAuth.getCurrentUser();
        if(user!=null){
            Intent intent = new Intent(getApplicationContext(),Dashboardpage.class);
            startActivity(intent);
        }


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        button4Login = findViewById(R.id.button4);
        textView6 = findViewById(R.id.textView6);
        loginUser = findViewById(R.id.editTextUserNameLogin);
        LoginPassword = findViewById(R.id.passwordTextLogin);
        backLogin=findViewById(R.id.button4);
        facebook=findViewById(R.id.imageViewfb);
        google=findViewById(R.id.imageViewgoogle);
        twitter=findViewById(R.id.imageViewtwitter);


        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });



        mAuth = FirebaseAuth.getInstance();


        createRequest();

    }

    private void createRequest() {

        //Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();


        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                // ...
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {


        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(getApplicationContext(),Dashboardpage.class);
                            startActivity(intent);


                        } else {
                            Toast.makeText(login.this, "Sorry auth failed.", Toast.LENGTH_SHORT).show();


                        }


                        // ...
                    }
                });
    }












    public void setButton4(View view) {


        if(!isConnected(login.this))
        {
            showCustomDialog();
        }


        if (!validateUser() | !validatepass()) {
            return;
        } else {
            final String username = loginUser.getText().toString().trim();
            final String password = LoginPassword.getText().toString().trim();

            auth.signInWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(login.this, "successful login", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(login.this,Dashboardpage.class));
                    }else {
                        Toast.makeText(login.this, "Password please try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });






        }



    }

    private void showCustomDialog() {

        AlertDialog.Builder builder=new AlertDialog.Builder(login.this);
        builder.setMessage("Please insure your internet connection ")
                .setCancelable(false).setPositiveButton("Connect", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
            }
        })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(getApplicationContext(),login.class));
                        finish();
                    }
                });
    }

    private boolean isConnected(login Login) {

        ConnectivityManager connectivityManager=(ConnectivityManager) Login.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wificonnect=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobiledataconnect=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if((wificonnect !=null && wificonnect.isConnected()) | (mobiledataconnect !=null && mobiledataconnect.isConnected())){
            return  true;
        }
        else
        {
            return false;
        }

    }


    private boolean validateUser() {
        String usernamelogin = loginUser.getText().toString().trim();

        if (usernamelogin.isEmpty()) {
            loginUser.setError("Field can not be empty");
            return false;

        }else if(usernamelogin.length()>200)
        {
            loginUser.setError("username is too big");
            return false;
        }

        else {
            loginUser.setError(null);
            return true;
        }

    }

    private boolean validatepass() {
        String passwordlogin = LoginPassword.getText().toString().trim();

        if (passwordlogin.isEmpty()) {
            LoginPassword.setError("Field can not be empty");
            return false;

        } else {
            LoginPassword.setError(null);
            return true;
        }

    }



    public void backauthentication(View view) {
        startActivity(new Intent(login.this, authentication.class));
        finish();
    }
}
