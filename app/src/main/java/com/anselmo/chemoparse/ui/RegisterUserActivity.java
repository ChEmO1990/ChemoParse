package com.anselmo.chemoparse.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.anselmo.chemoparse.R;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterUserActivity extends BaseActivity {
    @Bind(R.id.btnRegisterUser)
    Button btnRegister;

    @Bind(R.id.btnRegisterCancel)
    Button btnCancel;

    @Bind(R.id.edit_user_register)
    EditText editUsername;

    @Bind(R.id.edit_password_register)
    EditText editPassword;

    @Bind(R.id.edit_register_email)
    EditText editEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        ButterKnife.bind(this);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING); //Keyboard Soft

        Toolbar toolbar = getToolbar();
        toolbar.setTitle(R.string.lbl_register);
        toolbar.setNavigationIcon(R.mipmap.ic_up);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_github, menu);
        return true;
    }

    @OnClick(R.id.btnRegisterUser) void createUser() {
        ParseUser user = new ParseUser();
        user.setUsername(editUsername.getText().toString().trim());
        user.setPassword(editPassword.getText().toString().trim());
        user.setEmail(editEmail.getText().toString().trim());

        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    Toast.makeText(RegisterUserActivity.this, "Usuario creado exiosamente", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(RegisterUserActivity.this, "Uppss... error", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @OnClick(R.id.btnRegisterCancel) void cancel() {
        finish();
    }
}
