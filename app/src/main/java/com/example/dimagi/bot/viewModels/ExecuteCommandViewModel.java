package com.example.dimagi.bot.viewModels;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dimagi.bot.data.command.CommandDataSource;
import com.example.dimagi.bot.data.command.CommandRepository;
import com.example.dimagi.bot.models.request.CommandRequest;
import com.example.dimagi.bot.rest.ApiResult;

public class ExecuteCommandViewModel extends ViewModel {

    private MutableLiveData<ApiResult> apiResult = new MutableLiveData<>();
    private CommandRepository commandRepository;

    public MutableLiveData<ApiResult> getApiResult() {
        return apiResult;
    }

    public ExecuteCommandViewModel(){
        commandRepository = CommandRepository.getInstance();
    }

    public void executeCommand(Context context, CommandRequest commandRequest){
        commandRepository.executeCommand(context, commandRequest
                , apiResult -> ExecuteCommandViewModel.this.apiResult.setValue(apiResult));
    }
}