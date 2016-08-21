package f.lucia.tournamentmakerapp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;



/**
 *
 * Created by Nel on 2015-11-30.
 * A screen that offers app-user to create an account
 */
public class RegisterActivity extends ActionBarActivity implements LoaderCallbacks<Cursor> {

    MyGlobals myGlob;

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserCreateTask mRegTask = null;

    // UI references.
    private AutoCompleteTextView mEmailView;
    private AutoCompleteTextView mPasswordView;
    private AutoCompleteTextView mPasswordConfirmView;
    private View mProgressView;
    private View mLoginFormView;
    private TextView  selectUser;
    private Button mEmailSignInButton;
    private RadioGroup userGroup;
    private String userType;
    private RadioButton admin;
    private RadioButton tmanager;
    private RadioButton reguser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        myGlob = (MyGlobals)getApplicationContext();


        //this makes on-screen keyboard hidden until an EditText is clicked
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);


        mPasswordView = (AutoCompleteTextView) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    registerUser();
                    return true;
                }
                return false;
            }
        });

        mPasswordConfirmView = (AutoCompleteTextView) findViewById(R.id.confirmPassword);
        selectUser = (TextView) findViewById(R.id.textViewSelUser);
        userGroup = (RadioGroup) findViewById(R.id.userGroup);
        admin = (RadioButton) findViewById(R.id.adminRad);
        tmanager = (RadioButton) findViewById(R.id.tManageRad);
        reguser = (RadioButton) findViewById(R.id.regularUserRad);

        mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    public void onRegisterClick(View view) {
        registerUser();
    }

    public void onSignInHereClick(View view){
        onGoToSignIn();
    }

    public void onGoToSignIn() {
        showProgress(true);
        Intent logIntent = new Intent(getApplicationContext(), LoginActivity.class);//send to login activity
        startActivity(logIntent);
        finish();
    }
    public void goToHelp(View view)
    {
        Intent helpIntent = new Intent(getApplicationContext(), ActivityHelp.class);//send to manager activity
        startActivity(helpIntent);

    }

    /**
     * Attempts to register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void registerUser() {
        System.out.println("registeruser");
        if (mRegTask != null) {
            return;
        }

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);
        mPasswordConfirmView.setError(null);
        selectUser.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        String confirmPassword = mPasswordConfirmView.getText().toString();
        RadioButton checked=(RadioButton) findViewById(userGroup.getCheckedRadioButtonId());

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password) && !passwordLength(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }
        //check for empty confirm password
        if (TextUtils.isEmpty(confirmPassword)) {
            mPasswordConfirmView.setError(getString(R.string.error_field_required));
            focusView = mPasswordView;
            cancel = true;
        }
        //check if psswrds match
        if (!isPasswordConfirmed(password, confirmPassword)) {
            mPasswordConfirmView.setError(getString(R.string.error_notmatching_password));
            focusView = mPasswordConfirmView;
            cancel = true;
        }


        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (myGlob.isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_taken_email));
            focusView = mEmailView;
            cancel = true;
        }
        //check if userrtype is null
        if(checked==null)
        {
            selectUser.setError(getString(R.string.error_field_required));
            focusView = selectUser;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            userType = checked.getText().toString();
            mRegTask = new UserCreateTask(email, password, userType);
            mRegTask.execute((Void) null);
        }
    }

    private boolean isPasswordConfirmed(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    private boolean passwordLength(String password) {
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        System.out.println("oncreateloader");
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        System.out.println("onloadfinished");
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {
        System.out.println("onloadreset");
    }

    private interface ProfileQuery {

        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;

    }


    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        System.out.println("addemailtoauto");
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(RegisterActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserCreateTask extends AsyncTask<Void, Void, Boolean> {


        private final String mUser;
        UserCreateTask(String email, String password, String user) {


            if (user.equals("Administrator"))
            {
                mUser = "admin";
                myGlob.addUserToSystem(new User(email,password,mUser));
            }

            else if(user.equals("Team Manager"))
            {
                mUser="tmanager";
                myGlob.addUserToSystem(new User(email,password,mUser));
            }
            else
            {
                mUser = "reguser";
                myGlob.addUserToSystem(new User(email,password,mUser));
            }

        }





        @Override
        protected Boolean doInBackground(Void... params) {
            System.out.println("do in background");
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mRegTask = null;
            showProgress(false);

            if (success) {
                // myGlob.addUserToSystem(new User(mEmail,mPassword,mUser));
                onGoToSignIn();
                //   finish();
            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mRegTask = null;
            showProgress(false);
        }
    }
}
