package com.i4i.hcvb.route26;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Objects;

import io.swagger.client.model.MemberCreate;

public class RegisterAccountActivity extends AppCompatActivity {

    EditText emailView;
    EditText usernameView;
    EditText passwordView;
    EditText confirmPwdView;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        confirmPwdView = findViewById(R.id.regConfirmPwd);
        submit = findViewById(R.id.regSave);

        confirmPwdView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    submitRegistration();
                    return true;
                }
                return false;
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitRegistration();
            }
        });



    }

    public void submitRegistration(){

        View focusView = null;
        boolean cancel = false;

        emailView = findViewById(R.id.regEmail);
        usernameView = findViewById(R.id.regName);
        passwordView = findViewById(R.id.regPwd);
        confirmPwdView = findViewById(R.id.regConfirmPwd);
        submit = findViewById(R.id.regSave);

        String emailText = emailView.getText().toString();
        String usernameText = usernameView.getText().toString();
        String passwordText = passwordView.getText().toString();
        String confirmText = confirmPwdView.getText().toString();

        if(!passwordText.equals(confirmText)){
            confirmPwdView.setError("Passwords do not match");
            focusView = confirmPwdView;
            cancel = true;
        }

        if(cancel){
            focusView.requestFocus();
        } else {
            final MemberCreate newMember = new MemberCreate();

            newMember.setUsername(usernameText);
            newMember.setEmail(emailText);
            newMember.setPassword(passwordText);

        }
    }

}
