package edu.tacoma.uw.stephd27.testinglab;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class SignInActivity extends AppCompatActivity implements  RegisterFragment.OnRegisterListener,
LoginFragment.onLoginFragmentInteractionListener{

    private boolean mLoginMode;
    private static final String REGISTER_URL
            = "https://stephd27.000webhostapp.com/addUser.php?email=";
    private static final String LOGIN_URL
            = "https://stephd27.000webhostapp.com/login.php?email=";
    private final String TAG = "Sign In Activity";
    FragmentManager fm = getSupportFragmentManager();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        fm.beginTransaction().replace(R.id.fragment_container, new RegisterFragment())
                .commit();

    }



    @Override
    public void register(Account account) {

        try {
            mLoginMode = false;
            StringBuilder stringBuilder = new StringBuilder(REGISTER_URL);
            stringBuilder.append(URLEncoder.encode(account.getmEmail(), "UTF-8"));
            stringBuilder.append("&pwd=");
            stringBuilder.append(URLEncoder.encode(account.getmPwd(), "UTF-8"));

            Log.i(TAG, "Url is " +stringBuilder.toString());
            SignInAsyncTask registerAsyncTask = new SignInAsyncTask();
            registerAsyncTask.execute(stringBuilder.toString());
        }
        catch (Exception e) {
            Toast.makeText(this, "Couldn't register, Something wrong with the URL" + e.getMessage(),
                    Toast.LENGTH_SHORT). show();
        }
    }


    @Override
    public void launchLoginFragment() {
                fm.beginTransaction()
                .replace(R.id.fragment_container, new LoginFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void login(Account account) {
        try {
            mLoginMode = true;
            StringBuilder stringBuilder = new StringBuilder(LOGIN_URL);
            stringBuilder.append(URLEncoder.encode(account.getmEmail(), "UTF-8"));
            stringBuilder.append("&pwd=");
            stringBuilder.append(URLEncoder.encode(account.getmPwd(), "UTF-8"));

            Log.i(TAG, "Url is " +stringBuilder.toString());
            SignInAsyncTask registerAsyncTask = new SignInAsyncTask();
            registerAsyncTask.execute(stringBuilder.toString());
        }
        catch (Exception e) {
            Toast.makeText(this, "Couldn't login, Something wrong with the URL" + e.getMessage(),
                    Toast.LENGTH_SHORT). show();
        }
    }

    public void launchMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }



    private class SignInAsyncTask extends AsyncTask<String, Void, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... urls) {
            String response = "";
            HttpURLConnection urlConnection = null;
            for (String url : urls) {
                try {
                    URL urlObject = new URL(url);
                    urlConnection = (HttpURLConnection) urlObject.openConnection();

                    InputStream content = urlConnection.getInputStream();

                    BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
                    String s = "";
                    while ((s = buffer.readLine()) != null) {
                        response += s;
                    }

                } catch (Exception e) {
                    response = "Unable to Register User, Reason: "
                            + e.getMessage();
                } finally {
                    if (urlConnection != null)
                        urlConnection.disconnect();
                }
            }
            return response;
        }



        @Override
        protected void onPostExecute(String result) {
            try {
                JSONObject jsonObject = new JSONObject(result);
                String status = (String) jsonObject.get("result");
                if (status.equals("success")) {
                    if (!mLoginMode) {
                        Toast.makeText(getApplicationContext()
                                , "User successfully registered!"
                                , Toast.LENGTH_SHORT)
                                .show();
                    } else {

                        Toast.makeText(getApplicationContext()
                                , "User successfully authenticated!"
                                , Toast.LENGTH_SHORT)
                                .show();
                    }
                    launchMain();
                } else {
                    if (!mLoginMode) {
                        Toast.makeText(getApplicationContext(), "Failed to register: "
                                        + jsonObject.get("error")
                                , Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Failed to login: "
                                        + jsonObject.get("error")
                                , Toast.LENGTH_SHORT)
                                .show();
                    }
                }
            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(), "Something wrong with the data" +
                        e.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }

    }
}
