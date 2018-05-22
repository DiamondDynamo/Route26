/*
 Name: RegisterAccountActivity.java
 Written by: Charles Bein
 Description: Handles users creating new accounts on the server
 */

package com.i4i.hcvb.route26;

import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

import io.swagger.client.ApiException;
import io.swagger.client.api.MemberApi;
import io.swagger.client.model.MemberCreate;
import io.swagger.client.model.MemberPrivate;

public class RegisterAccountActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<MemberPrivate> {

    EditText nameView;
    EditText emailView;
    EditText usernameView;
    EditText passwordView;
    EditText confirmPwdView;
    Button submit;

    MemberCreate newMember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("Create New Account");

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

    public void submitRegistration() {
        //Once the user

        View focusView = null;
        boolean cancel = false;

        nameView = findViewById(R.id.regName);
        emailView = findViewById(R.id.regEmail);
        usernameView = findViewById(R.id.regUName);
        passwordView = findViewById(R.id.regPwd);
        confirmPwdView = findViewById(R.id.regConfirmPwd);
        submit = findViewById(R.id.regSave);

        String nameText = nameView.getText().toString();
        String emailText = emailView.getText().toString();
        String usernameText = usernameView.getText().toString();
        String passwordText = passwordView.getText().toString();
        String confirmText = confirmPwdView.getText().toString();

        if (!passwordText.equals(confirmText)) {
            confirmPwdView.setError("Passwords do not match");
            focusView = confirmPwdView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            newMember = new MemberCreate();

            newMember.setName(nameText);
            newMember.setUsername(usernameText);
            newMember.setEmail(emailText);
            newMember.setPassword(passwordText);
            newMember.setDescription("string");

            Bundle args = new Bundle();
            args.putSerializable("member", newMember);

            startLoader(args);

        }
    }

    public void startLoader(Bundle args) {
        getLoaderManager().initLoader(1, args, this).forceLoad();
    }

    @Override
    public Loader<MemberPrivate> onCreateLoader(int id, Bundle args) {
        return new CreateMemberTask(RegisterAccountActivity.this, (MemberCreate) args.get("member"));
    }

    @Override
    public void onLoadFinished(Loader<MemberPrivate> loader, MemberPrivate data) {
        if (data == null) {
            Toast.makeText(getApplicationContext(), "Member creation returned null", Toast.LENGTH_SHORT).show();
        } else {
            SharedPreferences preferences = getSharedPreferences("loginCredentials", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("username", usernameView.getText().toString());
            editor.putString("password", passwordView.getText().toString());

            SharedPreferences prefs = getSharedPreferences("logState", MODE_PRIVATE);
            SharedPreferences.Editor logEdit = prefs.edit();
            logEdit.putBoolean("loggedIn", true);
            editor.apply();
            logEdit.apply();
            Intent intent = new Intent(getApplicationContext(), ProducerDashboardActivity.class);
            finish();
            startActivity(intent);
        }
    }

    @Override
    public void onLoaderReset(Loader<MemberPrivate> loader) {

    }

    private static class CreateMemberTask extends AsyncTaskLoader<MemberPrivate> {
        MemberCreate mMember;

        CreateMemberTask(Context context, MemberCreate inMember) {
            super(context);
            mMember = inMember;
        }

        @Override
        public MemberPrivate loadInBackground() {
            MemberApi memberApi = new MemberApi();
            MemberPrivate result;
            try {
                result = memberApi.createMember(mMember);
                return result;
            } catch (ApiException e) {
                System.err.println("Exception when calling MemberApi.createMember");
                e.printStackTrace();
            }
            return null;
        }

    }

}
