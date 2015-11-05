package com.anselmo.chemoparse.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.anselmo.chemoparse.R;
import com.github.mrengineer13.snackbar.SnackBar;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.vstechlab.easyfonts.EasyFonts;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddCommentActivity extends BaseActivity {
    @Bind(R.id.edit_name)
    EditText name;

    @Bind(R.id.spinnerSex)
    Spinner spinnerSex;


    @Bind(R.id.spinnerMaritalStatus)
    Spinner spinnerMaritalStatus;


    @Bind(R.id.editAge)
    EditText age;

    @Bind(R.id.btnAddComment)
    Button addComment;

    @Bind(R.id.btnCancelComment)
    Button cancelComment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comment);
        ButterKnife.bind(this);

        Toolbar toolbar = getToolbar();
        toolbar.setTitle(R.string.lbl_activity_agregar);
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

    @OnClick(R.id.btnAddComment) void createComment() {
        String valueSex = spinnerSex.getSelectedItem().toString();
        String valueMaritalStatus = spinnerMaritalStatus.getSelectedItem().toString();

        String sex = null;
        String marital = null;

        int indexValueSex = spinnerSex.getSelectedItem().toString().lastIndexOf(":") + 1;
        int indexValueMaritalStatus = spinnerMaritalStatus.getSelectedItem().toString().lastIndexOf(":") + 1;

        switch (valueSex.substring(indexValueSex, valueSex.length()).trim()) {
            case "Masculino":
                sex = "M";
                break;
            case "Femenino":
                sex = "F";
                break;
        }

        switch (valueMaritalStatus.substring(indexValueMaritalStatus, valueMaritalStatus.length()).trim()) {
            case "Soltero(a)":
                marital = "SINGLE";
                break;
            case "Casado(a)":
                marital = "MARRIED";
                break;
            case "Divorciado(a)":
                marital = "DIVORCED";
                break;
            case "Viudo(a)":
                marital = "WIDOWED";
                break;
        }

        ParseObject userComment = new ParseObject("Comment");
        userComment.put("name", name.getText().toString().trim());
        userComment.put("sex", sex);
        userComment.put("age", Integer.parseInt(age.getText().toString().trim()));
        userComment.put("marital_status", marital);

        userComment.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    finish();
                } else {
                    new SnackBar.Builder(AddCommentActivity.this)
                            .withMessage(getString(R.string.msj_error_add_comment))
                            .withTypeFace(EasyFonts.robotoLight(AddCommentActivity.this))
                            .withTextColorId(R.color.colorPrimary)
                            .withStyle(SnackBar.Style.DEFAULT)
                            .withDuration(SnackBar.MED_SNACK)
                            .show();
                }
            }
        });
    }

    @OnClick(R.id.btnCancelComment) void cancel() {
        finish();
    }

}
