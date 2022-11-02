package com.example.dimagi.bot.data.command;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.dimagi.bot.models.request.CommandRequest;
import com.example.dimagi.bot.models.response.HelpResponse;
import com.example.dimagi.bot.models.response.InOfficeResponse;
import com.example.dimagi.bot.rest.ApiResult;
import com.example.dimagi.bot.rest.VolleyQueueManager;
import com.example.dimagi.bot.utils.UrlBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CommandDataSource {

    public void executeCommand(
            Context context
            , CommandRequest commandRequest
            , CommandExecutionCallback callback){

        String url = new UrlBuilder(UrlBuilder.API_EXECUTE_COMMAND).build();
        try {
            JSONObject jsonObject = commandRequest.toJson();
            System.out.println("request: "+jsonObject.toString());
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST
                    , url
                    , jsonObject
                    , response -> {
                if(response.has("status")
                        && response.optString("status").equals("success")){
                        try {
                            if(commandRequest.getCommand().equals("inoffice")){
                            callback.onResult(new ApiResult.Success<>(new InOfficeResponse(response.toString())));
                            }
                            else if(commandRequest.getCommand().equals("help")){
                                callback.onResult(new ApiResult.Success<>(new HelpResponse(response.toString())));
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            callback.onResult(new ApiResult.Error(new Exception("Response parsing error")));
                        }
                }else{
                    callback.onResult(new ApiResult.Error(new Exception("Command execution error")));
                }
            }, error -> {
                error.printStackTrace();
                callback.onResult(new ApiResult.Error(new Exception("Command execution error")));
            });
            VolleyQueueManager.getInstance(context).addToRequestQueue(request);
        } catch (JsonProcessingException | JSONException e) {
            e.printStackTrace();
        }

    }


    public interface CommandExecutionCallback{
        public void onResult(ApiResult apiResult);
    }
}
