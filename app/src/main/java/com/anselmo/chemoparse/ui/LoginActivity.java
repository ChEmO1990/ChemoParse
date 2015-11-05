package com.anselmo.chemoparse.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.anselmo.chemoparse.BusProvider;
import com.anselmo.chemoparse.R;
import com.anselmo.chemoparse.models.events.LoginStatusEvent;
import com.github.mrengineer13.snackbar.SnackBar;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.squareup.otto.Subscribe;
import com.vstechlab.easyfonts.EasyFonts;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {
    @Bind(R.id.btnLogin)
    Button btnRegister;

    @Bind(R.id.edit_user_login)
    EditText editUsername;

    @Bind(R.id.edit_password_login)
    EditText editPassword;

    @Bind(R.id.lbl_link_create)
    TextView link;

    boolean login = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING); //Keyboard Soft

        Toolbar toolbar = getToolbar();
        toolbar.setTitle(R.string.app_name);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_github, menu);
        return true;
    }

    @OnClick(R.id.btnLogin)
    void doLogin() {

        String user = editUsername.getText().toString().trim();
        String password = editPassword.getText().toString().trim();

        String arrayCredentials[] = new String[2];
        arrayCredentials[0] = user;
        arrayCredentials[1] = password;

        new AsyncTaskLogin().execute(arrayCredentials);
    }

    @OnClick(R.id.lbl_link_create)
    void createUser() {
        Intent i = new Intent(LoginActivity.this, RegisterUserActivity.class);
        startActivity(i);
    }

    class AsyncTaskLogin extends AsyncTask<String, Void, Void> {
        private MaterialDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new MaterialDialog.Builder(LoginActivity.this)
                    .content("Content")
                    .progress(true, 0)
                    .show();
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
        }

        @Override
        protected Void doInBackground(String... params) {
            ParseUser.logInInBackground(params[0], params[1], new LogInCallback() {
                public void done(ParseUser user, ParseException e) {
                    if (user != null) {
                        BusProvider.getInstance().post(new LoginStatusEvent(true));
                    } else {
                        BusProvider.getInstance().post(new LoginStatusEvent(false));
                    }
                }
            });
            return null;
        }
    }

    @Subscribe
    public void checkLogin(LoginStatusEvent event) {
        if (event != null) {
            if (event.isLoginOk()) {
                Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                i.putExtra("launchFromLogin", true);
                startActivity(i);
                finish();
            } else {
                new SnackBar.Builder(LoginActivity.this)
                        .withMessage(getString(R.string.msj_login_error))
                        .withTypeFace(EasyFonts.robotoLight(LoginActivity.this))
                        .withTextColorId(R.color.colorPrimary)
                        .withStyle(SnackBar.Style.DEFAULT)
                        .withDuration(SnackBar.MED_SNACK)
                        .show();
            }
        }
    }
}