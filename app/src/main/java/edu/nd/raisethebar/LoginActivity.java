package edu.nd.raisethebar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import static android.R.attr.value;

/**
 * Created by jack1 on 10/24/2016.
 */

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences pref;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pref = getSharedPreferences(getString(R.string.pref), Context.MODE_PRIVATE);
        String user = pref.getString("email",null);
        ((EditText)findViewById(R.id.email)).setText(user);
        final EditText passwordView = (EditText) findViewById(R.id.password);
        passwordView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub
                if (hasFocus) {
                    if (passwordView.getText().toString().trim().length() < 3) {
                        passwordView.setError("Password must be longer");
                        //TODO improve password requirements
                    } else {
                        passwordView.setError(null);
                    }
                }
            }
        });
    }
    public void submit(View v){
        //http://www.codeproject.com/Articles/704865/Salted-Password-Hashing-Doing-it-Right
        String email = ((EditText) findViewById(R.id.email)).getText().toString();
        String pass = ((TextView) findViewById(R.id.password)).getText().toString();
        pref.edit().putString("email", email).putString("password", pass).apply();
        //TODO check via online
        if(true) {
            startActivity(new Intent(this, SelectorActivity.class));
        }else{

        }
    }
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            //TODO create server call

            // TODO: register the new account here.
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            /*
            mAuthTask = null;
            showProgress(false);

            if (success) {
                finish();
            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }*/
        }

        @Override
        protected void onCancelled() {
        }
    }
}
