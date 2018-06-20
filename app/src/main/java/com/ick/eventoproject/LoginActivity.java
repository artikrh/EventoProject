package com.ick.eventoproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.os.Handler;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.ProviderQueryResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.net.Inet4Address;


public class LoginActivity extends AppCompatActivity {
    TextView tvRegister;
    TextView tvEmail;
    TextView tvPassword;
    AutoCompleteTextView atEmail;
    AutoCompleteTextView atPassword;
    Button signIn;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    ProgressBar prgBar;


    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        prgBar=(ProgressBar)findViewById(R.id.progresBar);
        prgBar.setVisibility(View.GONE);
        atEmail = (AutoCompleteTextView) findViewById(R.id.atEmail);
        atPassword = (AutoCompleteTextView) findViewById(R.id.atPassword);
        signIn = (Button) findViewById(R.id.btnSignIn);
        tvRegister = (TextView) findViewById(R.id.tvRegister);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }
            }
        };


        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final  String email = atEmail.getText().toString();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                final String password = atPassword.getText().toString();



                if (!TextUtils.isEmpty(email)) {
                    if (!TextUtils.isEmpty(password)) {
                        if(email.matches(emailPattern)) {
                            prgBar.setVisibility(View.VISIBLE);
                            mAuth.fetchProvidersForEmail(email).addOnCompleteListener(new OnCompleteListener<ProviderQueryResult>() {
                                @Override
                                public void onComplete(@NonNull Task<ProviderQueryResult> task) {
                                    boolean check = !task.getResult().getProviders().isEmpty();
                                    if (check) {
                                        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                if (task.isSuccessful()) {
                                                    login_succesful();
                                                    prgBar.setVisibility(View.GONE);
                                                    return;
                                                } else {
                                                    Toast.makeText(LoginActivity.this, "Password is wrong!", Toast.LENGTH_LONG).show();
                                                    prgBar.setVisibility(View.GONE);
                                                }

                                            }
                                        });
                                    } else {
                                        Toast.makeText(LoginActivity.this, "User does not exist.Please register!", Toast.LENGTH_LONG).show();
                                        prgBar.setVisibility(View.GONE);
                                        startActivity(new Intent(LoginActivity.this, UserActivity.class));
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(LoginActivity.this, "Invalid email", Toast.LENGTH_LONG).show();
                        }


                    } else {
                        Toast.makeText(LoginActivity.this, "Please fill in  the password field", Toast.LENGTH_LONG).show();
                        return;
                    }

                } else {
                    Toast.makeText(LoginActivity.this, "Please fill in the email field", Toast.LENGTH_LONG).show();
                    return;
                }


            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                account();
            }
        });


    }

     /* @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser() !=null ){
            startActivity(new Intent ( loginActivity.this,MainActivity.class));
        }
    }
*/

    private Boolean exit = false;
    @Override
    public void onBackPressed() {
        if (exit) {
            // Mshele aplikacionin kur e prek Back (duhet me shkatrru session)
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);

        } else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }
    }


    public void login_succesful() {
        Intent login = new Intent(this, MainActivity.class);
        startActivity(login);
    }

    public void account() {
        Intent account = new Intent(this, UserActivity.class);
        startActivity(account);
    }


    }



