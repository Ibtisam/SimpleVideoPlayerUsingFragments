package com.example.sp21assignment2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    public static SharedViewModel sharedViewModel;
    private Context context;
    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView textView;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            textView = (TextView) view.findViewById(R.id.textView);
            textView.setOnClickListener(this);

        }

        public TextView getTextView() {
            return textView;
        }

        @Override
        public void onClick(View v) {
            int index = getAdapterPosition();
            File file = sharedViewModel.getAllMediaList().get(index);
            sharedViewModel.setData(file.getPath());
        }
    }

    public CustomAdapter(Context context) {
        this.context = context;
        sharedViewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(SharedViewModel.class);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.text_row_item, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getTextView().setText(sharedViewModel.getAllMediaList().get(position).getName());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return sharedViewModel.getMediaListLength();
    }
}
