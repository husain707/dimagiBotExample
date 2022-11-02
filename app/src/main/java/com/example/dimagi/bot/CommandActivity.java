package com.example.dimagi.bot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.example.dimagi.bot.viewModels.ExecuteCommandViewModel;

public class CommandActivity extends AppCompatActivity {

    private ExecuteCommandViewModel executeCommandViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_command);
        executeCommandViewModel = ViewModelProviders.of(this)
                .get(ExecuteCommandViewModel.class);

    }

    private static class ChatAdaptor extends RecyclerView.Adapter<ChatAdaptor.ChatItemHolder>{

        @NonNull
        @Override
        public ChatItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull ChatItemHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }

        private class ChatItemHolder extends RecyclerView.ViewHolder{


            public ChatItemHolder(@NonNull View itemView) {
                super(itemView);
            }
        }

    }
}