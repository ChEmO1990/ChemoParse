package com.anselmo.chemoparse.ui;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.anselmo.chemoparse.R;
import com.anselmo.chemoparse.adapters.CommentAdapter;
import com.anselmo.chemoparse.models.Comment;
import com.anselmo.chemoparse.utils.DividerItemDecoration;
import com.github.ksoichiro.android.observablescrollview.ObservableRecyclerView;
import com.github.mrengineer13.snackbar.SnackBar;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.vstechlab.easyfonts.EasyFonts;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeActivity extends ToolbarControlBaseActivity<ObservableRecyclerView> {
    @Bind(R.id.recyclerHome)
    ObservableRecyclerView recyclerHome;

    private ArrayList<Comment> itemsComments;
    private CommentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadAllUserComments();

        Bundle d = getIntent().getExtras();
        if (d != null) {
            if (d.getBoolean("launchFromLogin")) {
                showMessageWelcome();
            }
        }
    }

    @Override
    protected ObservableRecyclerView createScrollable() {
        ButterKnife.bind(this);

        recyclerHome.setLayoutManager(new LinearLayoutManager(this));
        recyclerHome.setHasFixedSize(true);
        recyclerHome.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));

        itemsComments = new ArrayList<>();

        adapter = new CommentAdapter(this, itemsComments);
        recyclerHome.setAdapter(adapter);

        return recyclerHome;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_home;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        itemsComments.clear();
        loadAllUserComments();
    }

    private void loadAllUserComments() {
        ParseQuery query = ParseQuery.getQuery("Comment");

        try {
            List<ParseObject> parseItems = query.find();

            for (int i = 0; i < parseItems.size(); i++) {
                String objectId = parseItems.get(i).getString("objectId");
                String name = parseItems.get(i).getString("name");
                Date createdAt = parseItems.get(i).getCreatedAt();
                String sex = parseItems.get(i).getString("sex");
                int age = parseItems.get(i).getInt("age");
                String marital_status = parseItems.get(i).getString("marital_status");

                //Add item
                itemsComments.add(new Comment(objectId, name, createdAt, sex, age, marital_status));
            }
            adapter.notifyDataSetChanged();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void showMessageWelcome() {
        new SnackBar.Builder(HomeActivity.this)
                .withMessage("Bienvenido " + ParseUser.getCurrentUser().getUsername())
                .withTypeFace(EasyFonts.robotoLight(HomeActivity.this))
                .withTextColorId(R.color.colorPrimary)
                .withStyle(SnackBar.Style.DEFAULT)
                .withDuration(SnackBar.MED_SNACK)
                .show();
    }
}
