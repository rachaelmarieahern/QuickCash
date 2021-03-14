package com.example.quickcash.Util;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quickcash.Model.Task;
import com.example.quickcash.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class TaskAdapter extends FirebaseRecyclerAdapter<Task, TaskAdapter.TaskViewHolder> {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;
    NavController navController;

    public TaskAdapter(
            @NonNull FirebaseRecyclerOptions<Task> options, Context context, NavController navController) {
        super(options);
        this.context = context;
        this.navController = navController;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPreferences.edit();
    }

    //Attaches card view for individual list items to the adapter
    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_list_item, parent, false);
        return new TaskViewHolder(view);
    }

    //Binds data to the holder variables
    @Override
    protected void  onBindViewHolder(TaskViewHolder holder,
                                     int position, @NonNull Task currentTask) {

        holder.description.setText(currentTask.getDescription());
        holder.headline.setText(currentTask.getHeadline());
        holder.wage.setText(currentTask.getWage());
        holder.distance.setText(currentTask.getHeadline());

        holder.location.setText(currentTask.getWage());

        holder.itemView.findViewById(R.id.list_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                putString(R.string.DESCRIPTION_KEY, currentTask.getDescription());
                putString(R.string.HEADLINE_KEY, currentTask.getHeadline());
                putString(R.string.WAGE_KEY, currentTask.getWage());
                putString(R.string.START_DATE_KEY, currentTask.getStartDate().toString());
                putString(R.string.START_DATE_KEY, currentTask.getStartDate().toString());
                navController.navigate(R.id.action_dashboardFragment_to_fragment_specific_task_view);
            }
        });

    }


    //Updates task list item variables to new data fetched from firebase
    static class TaskViewHolder extends RecyclerView.ViewHolder {
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

    public void putString(int keyID, String taskString){
        editor.putString(context.getResources().getString(keyID), taskString);
        editor.apply();
    }


}



