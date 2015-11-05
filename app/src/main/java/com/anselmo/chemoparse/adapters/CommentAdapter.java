package com.anselmo.chemoparse.adapters;


import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anselmo.chemoparse.R;
import com.anselmo.chemoparse.models.Comment;
import com.vstechlab.easyfonts.EasyFonts;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Anselmo Hernandez on 9/28/15.
 */
public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    private Activity context;
    private List<Comment> items;

    public CommentAdapter(Activity context, List<Comment> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public CommentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_comment, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);

        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(CommentAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Comment comment = items.get(position);

        viewHolder.name.setText( context.getString(R.string.item_title_name) + " " + comment.getName() );
        viewHolder.sex.setText(  context.getString(R.string.item_title_sex) + " " + comment.getSex() );
        viewHolder.marital_status.setText( context.getString(R.string.item_title_maritalstatus) + " " + comment.getMarital_status() );
        viewHolder.age.setText( context.getString(R.string.item_title_age) + " " + String.valueOf( comment.getSex() ) );

        Date date = comment.getCreatedAt();
        SimpleDateFormat formatter = new SimpleDateFormat("EEE, MMM d, ''yy");
        String formattedDateString = formatter.format(date);
        
        //set Date
        viewHolder.createdAt.setText( formattedDateString );
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name;
        public TextView sex;
        public TextView marital_status;
        public TextView age;
        public TextView createdAt;

        public ViewHolder(View itemView) {

            super(itemView);

            name            = (TextView) itemView.findViewById(R.id.item_name);
            sex             = (TextView) itemView.findViewById(R.id.item_sex);
            marital_status  = (TextView) itemView.findViewById(R.id.item_marital_status);
            age             = (TextView) itemView.findViewById(R.id.item_age);
            createdAt       = (TextView) itemView.findViewById(R.id.item_createdAt);

            name.setTypeface(EasyFonts.robotoLight(context));
            sex.setTypeface(EasyFonts.robotoLight(context));
            marital_status.setTypeface(EasyFonts.robotoLight(context));
            age.setTypeface(EasyFonts.robotoLight(context));
            createdAt.setTypeface(EasyFonts.robotoThin(context));

            // Attach a click listener to the entire row view
            itemView.setOnClickListener(this);
        }

        // Handles the row being being clicked
        @Override
        public void onClick(View view) {
            int position = getLayoutPosition();

            /*
            if (items.get(position).getStores().isEmpty()) {
                new SnackBar.Builder(context)
                        .withMessage(context.getString(R.string.message_empyt_stores))
                        .withTypeFace(EasyFonts.robotoLight(context))
                        .withTextColorId(R.color.color_primary)
                        .withStyle(SnackBar.Style.DEFAULT)
                        .withDuration(SnackBar.MED_SNACK)
                        .show();
            } else {
                Intent i = new Intent(context, StoreDetailActivity.class);
                i.putExtra("arrayPosition", position);
                context.startActivity(i);
            }
            */
        }
    }

    // Return the total count of items
    @Override
    public int getItemCount() {
        return items.size();
    }
}
