package com.example.quickcash.Util;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quickcash.Model.Task;
import com.example.quickcash.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class TaskAdapter extends FirebaseRecyclerAdapter<Task, TaskAdapter.TaskViewHolder> {

    public TaskAdapter(
            @NonNull FirebaseRecyclerOptions<Task> options)
    {
        super(options);
    }


    //Binds data to the holder variables
    @Override
    protected void
    onBindViewHolder(@NonNull TaskViewHolder holder,
                     int position, @NonNull Task currentTask)
    {

        holder.description.setText(currentTask.getDescription());
        holder.headline.setText(currentTask.getHeadline());
        holder.wage.setText(currentTask.getWage());
        holder.distance.setText(currentTask.getHeadline());
        holder.location.setText(currentTask.getWage());
    }

  //Attaches card view for individual list items to the adapter
    @NonNull
    @Override
    public TaskViewHolder
    onCreateViewHolder(@NonNull ViewGroup parent,
                       int viewType)
    {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_list_item, parent, false);
        return new TaskAdapter.TaskViewHolder(view);
    }

    //Updates task list item variables to new data fetched from firebase
    static class TaskViewHolder
            extends RecyclerView.ViewHolder {
        TextView taskType, wage, description, headline, distance, location;
        public TaskViewHolder(@NonNull View itemView)
        {
            super(itemView);
            taskType = itemView.findViewById(R.id.itemTaskType);
            wage = itemView.findViewById(R.id.itemWage);
            description = itemView.findViewById(R.id.itemDescription);
            headline = itemView.findViewById(R.id.itemHeadline);
            distance = itemView.findViewById(R.id.itemDistance);
            location = itemView.findViewById(R.id.itemLocation);
        }
    }
}
