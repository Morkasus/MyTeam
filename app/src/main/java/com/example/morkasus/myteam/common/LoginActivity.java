package com.example.morkasus.myteam.common;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.example.morkasus.myteam.manager.ManagerMainActivity;
import com.example.morkasus.myteam.R;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {


    private static final String TAG = LoginActivity.class.getSimpleName();
    @Bind(R.id.userNameEditText) EditText mUsernameEditText;
    @Bind(R.id.passwordEditText) EditText mPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        Log.i(TAG, "LoginActivity Start");


        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            moveToMainManagerScreen();
        }

        getSupportActionBar().hide();

    }



    @OnClick(R.id.loginButton)
    public void login() {

        if (validation()) {


            final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                    R.style.dialog_light);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Authenticating...");
            progressDialog.show();

            String email = mUsernameEditText.getText().toString();
            String password = mPasswordEditText.getText().toString();

            ParseUser.logInInBackground(email, password, new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    progressDialog.dismiss();
                    if(e == null) {
                        Log.i(TAG, "Validation success and the user success Login");
                        if (user.get("isManager").equals(true)) {
                            moveToMainManagerScreen();
                        } else {
                            moveToMainWorkerScreen();
                        }
                    } else {
                        Log.e(TAG, "Validation success and the user don't Login");
                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                        builder.setMessage(e.getMessage())
                                .setTitle(R.string.login_error_title)
                                .setPositiveButton(android.R.string.ok, null);
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                }
            });
        } else {
            Log.e(TAG, "Validation not success and the user don't Login");
        }
    }


    @OnClick(R.id.signUpLabel)
    public void signUp() {
        moveToSignUpScreen();
    }


    public boolean validation() {

        boolean valid = true;
        String email = mUsernameEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mUsernameEditText.setError("Enter a valid email address");
            valid = false;
        } else {
            mUsernameEditText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            mPasswordEditText.setError("Between 4 and 10 alphanumeric characters and numbers");
            valid = false;
        } else {
            mPasswordEditText.setError(null);
        }

        return valid;
    }

    private void moveToMainManagerScreen() {
        Intent intent = new Intent(LoginActivity.this, ManagerMainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void moveToSignUpScreen() {
        Log.i(TAG, "The user moved to SignUpActivity");
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void moveToMainWorkerScreen() {
    }


}
