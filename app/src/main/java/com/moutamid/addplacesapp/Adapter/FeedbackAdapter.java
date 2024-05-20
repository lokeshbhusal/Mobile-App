package com.moutamid.addplacesapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moutamid.addplacesapp.R;
import com.moutamid.addplacesapp.model.RatingModel;
import com.willy.ratingbar.ScaleRatingBar;

import java.util.List;

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.FeedbackViewHolder> {

    private List<RatingModel> feedbackList; // List of feedback items
    // Constructor for the adapter takes a list of RatingModel
    public FeedbackAdapter(List<RatingModel> feedbackList) {
        this.feedbackList = feedbackList;
    }

    @NonNull
    @Override
    public FeedbackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflating the custom layout for each item
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review, parent, false);
        return new FeedbackViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedbackViewHolder holder, int position) {
        // Getting the feedback item based on position
        RatingModel feedback = feedbackList.get(position);
        holder.nameTextView.setText(feedback.name);
      holder.ratingBar.setRating(Float.parseFloat(feedback.rating));
        holder.feedbackTextView.setText(feedback.feedback);
    }

    @Override
    public int getItemCount() {
        // Returns the total number of items in the feedback list
        return feedbackList.size();
    }
    // ViewHolder class defines the view elements from the layout
    public static class FeedbackViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;// TextView for the user's name
        public ScaleRatingBar ratingBar; // Custom rating bar for displaying ratings
        public TextView feedbackTextView; // TextView for the actual feedback content

        public FeedbackViewHolder(@NonNull View itemView) {
            super(itemView);
            // Linking the layout elements to the view holder fields
            nameTextView = itemView.findViewById(R.id.nameTextView);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            feedbackTextView = itemView.findViewById(R.id.feedbackTextView);
        }
    }
}
