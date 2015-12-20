package com.example.morkasus.myteam.common;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.example.morkasus.myteam.commonhelpers.ParseConstans;
import com.example.morkasus.myteam.manager.ManagerMainActivity;
import com.example.morkasus.myteam.R;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = SignUpActivity.class.getSimpleName();

    @Bind(R.id.userNameEditText) EditText mUsernameEditText;
    @Bind(R.id.nameEditText) EditText mNameEditText;
    @Bind(R.id.passwordEditText) EditText mPasswordEditText;
    @Bind(R.id.confirmPasswordEditText) EditText mConfirmPasswordEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        Log.i(TAG, "SignUpActivity Start");

        getSupportActionBar().hide();
    }


    @OnClick(R.id.signUpButton)
    public void signUp() {

        if(validation()) {

            final ProgressDialog progressDialog = new ProgressDialog(SignUpActivity.this,
                    R.style.dialog_light);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Sign Up...");
            progressDialog.show();

            ParseUser newUser = new ParseUser();
            newUser.setUsername(mUsernameEditText.getText().toString());
            newUser.setPassword(mPasswordEditText.getText().toString());
            newUser.put(ParseConstans.NAME, mNameEditText.getText().toString());
            newUser.put(ParseConstans.IS_MANAGER, true);


            newUser.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    progressDialog.dismiss();
                    if (e == null) {
                        Log.i(TAG, "Validation success and the user success sign up");
                        moveToManagerMenu();
                    } else {
                        Log.e(TAG, "Validation success and the user don't sign up");
                        AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                        builder.setMessage(e.getMessage())
                                .setTitle("Sign Up failed ! Try again")
                                .setPositiveButton(android.R.string.ok, null);
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                }
            });

        } else {
            Log.e(TAG, "Validation not success and the user don't SignUp");
        }

    }

    @OnClick(R.id.alreadyLoginLabel)
    public void alreadyLogin() {
        moveToLoginScreen();
    }


    public boolean validation() {

        boolean valid = true;
        String email = mUsernameEditText.getText().toString();
        String name = mNameEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();
        String confirmPassword = mConfirmPasswordEditText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mUsernameEditText.setError("Enter a valid email address");
            valid = false;
        } else {
            mUsernameEditText.setError(null);
        }

        if (name.isEmpty() || name.length() < 3) {
            mNameEditText.setError("At least 3 characters");
            valid = false;
        } else {
            mNameEditText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10 ) {
            mPasswordEditText.setError("Between 4 and 10 alphanumeric characters and numbers");
            valid = false;
        } else {
            mPasswordEditText.setError(null);
        }

        if (confirmPassword.isEmpty() || !confirmPassword.equals(password)) {
            mConfirmPasswordEditText.setError("Confirm password not match");
        } else {
            mConfirmPasswordEditText.setError(null);
        }

        return valid;
    }

    private void moveToLoginScreen() {
        Log.i(TAG, "The user moved to LoginActivity");
        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public void moveToManagerMenu(){
        Log.i(TAG, "The user moved to ManagerMainActivity");
        Intent intent = new Intent(SignUpActivity.this, ManagerMainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}
