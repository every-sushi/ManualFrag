package com.ryungna.myexample;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Button join = (Button)findViewById(R.id.signUp);//
        final Button login = (Button)findViewById(R.id.signIn);//

        final EditText email = (EditText)findViewById(R.id.email);//
        final EditText passwd = (EditText)findViewById(R.id.passwd);//

        mAuth = FirebaseAuth.getInstance(); //로그인이나 회원가입하면 mAuth에 회원정보가 들어감
        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) { //mAuth의 회원정보가 있다면 메인으로 이동
                FirebaseUser user = firebaseAuth.getCurrentUser();
                Intent in = new Intent(com.ryungna.myexample.login.this, frag_main.class);
                if (user != null) {
                    // User is signed in

                    startActivity(in);
                } else { //그렇지 않다면
                    // User is signed out

                }
                // ...
            }
        };

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tojoin(email.getText().toString(),passwd.getText().toString());
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tologin(email.getText().toString(),passwd.getText().toString());


            }
        });

    }


    public void tojoin(String email, String passwd){
        mAuth.createUserWithEmailAndPassword(email, passwd)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(login.this, "가입 오류",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else{//보통 이메일이 이미 존재하거나, 이메일 형식이아니거나, 비밀번호가 6자리 이상이 아닐 때 발생
                            Toast.makeText(login.this, "가입성공 \n HELLO dear",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    public void tologin(String email, String passwd){
        mAuth.signInWithEmailAndPassword(email, passwd)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(login.this, "로그인 오류",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(login.this, "로그인 성공",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });

    }

    //언제든지 유저 정보를 가져올 수 있음 //사용자의 프로필을 가져옴
    //If a user has signed in successfully you can get their account data at any point with the getCurrentUser method.
    public void getCurrentUser(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            String uid = user.getUid();
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

}
