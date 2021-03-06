package com.ick.eventoproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.ProviderQueryResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class UserActivity extends AppCompatActivity  {

    public Button btnRegister;
    public AutoCompleteTextView atEmail;
    public AutoCompleteTextView atEmri;
    public  AutoCompleteTextView atMbiemri;
    public  AutoCompleteTextView atPassword;
    public   CheckBox chckMale, chckFemale;
    public ProgressBar prgBar;
    public RadioGroup rdgProfile_type;
    public RadioButton rbUser;
    public RadioButton rbBusiness;
    public RadioGroup rgbGender;
    public RadioButton rbMale;
    public RadioButton rbFemale;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        atEmail= findViewById(R.id.atEmail);
        atEmri= findViewById(R.id.atEmri);
        atMbiemri= findViewById(R.id.atMbiemri);
        atPassword= findViewById(R.id.atPassword);

        btnRegister= findViewById(R.id.btnRegister);
        prgBar= findViewById(R.id.progresBar);
        prgBar.setVisibility(View.GONE);
        mAuth=FirebaseAuth.getInstance();
        rdgProfile_type=findViewById(R.id.rdgProfile_Type);
        rbUser=findViewById(R.id.rbUser);
        rbBusiness=findViewById(R.id.rbBusiness);
        rgbGender=findViewById(R.id.rdgGender);
        rbMale=findViewById(R.id.rbMale);
        rbFemale=findViewById(R.id.rbFemale);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = atEmail.getText().toString();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                final String emri = atEmri.getText().toString();
                final String mbiemri = atMbiemri.getText().toString();
                final String password = atPassword.getText().toString();
                final boolean profile_type;
                profile_type = rbUser.isChecked();

                if (emri.equals("") || mbiemri.equals("") || email.equals("") || password.equals("") || rgbGender.getCheckedRadioButtonId()==-1
                        || rdgProfile_type.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(UserActivity.this, "Please fill in all fields!", Toast.LENGTH_LONG).show();
                } else {
                    if (rbUser.isChecked()) {
                        if (email.matches(emailPattern)) {

                            prgBar.setVisibility(View.VISIBLE);
                            //checks if user is already registered
                            mAuth.fetchProvidersForEmail(email).addOnCompleteListener(new OnCompleteListener<ProviderQueryResult>() {
                                @Override
                                public void onComplete(@NonNull Task<ProviderQueryResult> task) {
                                    boolean check = !task.getResult().getProviders().isEmpty();

                                    if (!check) {
                                        //Ruajtja ne databaze
                                        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                prgBar.setVisibility(View.GONE);
                                                if (task.isSuccessful()) {
                                                    User user = new User(emri, mbiemri, email, true);


                                                    FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {

                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            prgBar.setVisibility(View.GONE);
                                                            if (task.isSuccessful()) {
                                                                Toast.makeText(UserActivity.this, "Registration successfully", Toast.LENGTH_LONG).show();
                                                                prgBar.setVisibility(View.VISIBLE);
                                                                Intent login = new Intent(UserActivity.this, LoginActivity.class);
                                                                startActivity(login);


                                                            } else {

                                                            }
                                                        }

                                                    });
                                                } else {
                                                    Toast.makeText(UserActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        });

                                    } else {
                                        Toast.makeText(UserActivity.this, "User already registered.Please sign in!", Toast.LENGTH_LONG).show();
                                        prgBar.setVisibility(View.GONE);
                                        startActivity(new Intent(UserActivity.this, LoginActivity.class));
                                    }
                                }
                            });

                        } else {
                            Toast.makeText(UserActivity.this, "Invalid Email!", Toast.LENGTH_LONG).show();

                        }

                    }
                    else {
                        if (rbBusiness.isChecked()) {
                            if (email.matches(emailPattern)) {
                                prgBar.setVisibility(View.VISIBLE);
                                //checks if user is already registered
                                mAuth.fetchProvidersForEmail(email).addOnCompleteListener(new OnCompleteListener<ProviderQueryResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<ProviderQueryResult> task) {
                                        boolean check = !task.getResult().getProviders().isEmpty();

                                        if (!check) {
                                            //Ruajtja ne databaze
                                            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                @Override
                                                public void onComplete(@NonNull Task<AuthResult> task) {
                                                    prgBar.setVisibility(View.GONE);
                                                    if (task.isSuccessful()) {
                                                        User user = new User(emri, mbiemri, email, false);


                                                        FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {

                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                prgBar.setVisibility(View.GONE);
                                                                if (task.isSuccessful()) {
                                                                    Toast.makeText(UserActivity.this, "Registration successfully", Toast.LENGTH_LONG).show();
                                                                    prgBar.setVisibility(View.VISIBLE);
                                                                    Intent login = new Intent(UserActivity.this, LoginActivity.class);
                                                                    startActivity(login);


                                                                } else {

                                                                }
                                                            }

                                                        });
                                                    } else {
                                                        Toast.makeText(UserActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                                    }
                                                }
                                            });

                                        } else {
                                            Toast.makeText(UserActivity.this, "User already registered.Please sign in!", Toast.LENGTH_LONG).show();
                                            prgBar.setVisibility(View.GONE);
                                            startActivity(new Intent(UserActivity.this, LoginActivity.class));
                                        }
                                    }
                                });

                            } else {
                                Toast.makeText(UserActivity.this, "Invalid Email!", Toast.LENGTH_LONG).show();

                            }

                        }


                    }

                }
            }

        });







    }

   /* protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser() !=null ){
            startActivity(new Intent ( UserActivity.this,MainActivity.class));
        }
    } */



}
