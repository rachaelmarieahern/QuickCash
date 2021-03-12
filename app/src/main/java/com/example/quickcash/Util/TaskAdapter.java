package com.example.quickcash.Util;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quickcash.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;




public class TaskAdapter extends FirebaseRecyclerAdapter< task, taskAdapter.taskViewholder> {

    public taskAdapter(
            @NonNull FirebaseRecyclerOptions<task> options)
    {
        super(options);
    }

    // Function to bind the view in Card view(here
    // "task.xml") iwth data in
    // model class(here "task.class")
    @Override
    protected void
    onBindViewHolder(@NonNull tasksViewholder holder,
                     int position, @NonNull task model)
    {

        // Add firstname from model class (here
        // "task.class")to appropriate view in Card
        // view (here "task.xml")
        holder.firstname.setText(model.getFirstname());

        // Add lastname from model class (here
        // "task.class")to appropriate view in Card
        // view (here "task.xml")
        holder.lastname.setText(model.getLastname());

        // Add age from model class (here
        // "task.class")to appropriate view in Card
        // view (here "task.xml")
        holder.age.setText(model.getAge());
    }

    // Function to tell the class about the Card view (here
    // "task.xml")in
    // which the data will be shown
    @NonNull
    @Override
    public tasksViewholder
    onCreateViewHolder(@NonNull ViewGroup parent,
                       int viewType)
    {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task, parent, false);
        return new taskAdapter.tasksViewholder(view);
    }

    // Sub Class to create references of the views in Crad
    // view (here "task.xml")
    class tasksViewholder
            extends RecyclerView.ViewHolder {
        TextView firstname, lastname, age;
        public tasksViewholder(@NonNull View itemView)
        {
            super(itemView);

            firstname
                    = itemView.findViewById(R.id.firstname);
            lastname = itemView.findViewById(R.id.lastname);
            age = itemView.findViewById(R.id.age);
        }
    }
}
