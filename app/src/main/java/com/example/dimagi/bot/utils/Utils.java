package com.example.dimagi.bot.utils;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.text.Html;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;

import com.example.dimagi.bot.DimagiBotApplication;
import com.example.dimagi.bot.R;
import com.example.dimagi.bot.rest.HttpConnectException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.google.android.material.snackbar.Snackbar;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

public class Utils {

    private static boolean isDialogShown;

    static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    public static String getTimeKey(String timeMeridian) {
        String time = timeMeridian.split(" ")[0];
        String meridian = timeMeridian.split(" ")[1];
        int hour = Integer.parseInt(time.split(":")[0]);
        if (hour == 12) {
            hour = 0;
        }
        if (meridian.equals("P.M")) {
            hour += 12;
        }
        return hour + "_59";
    }

    public static void showAlertDialog(Context context, int titleId, int messageId, DialogInterface.OnClickListener listener) {
        showAlertDialog(context, context.getString(titleId), context.getString(messageId), listener);
    }

    public static void showAlertDialog(Context context, String title, String message, final DialogInterface.OnClickListener listener) {
        if (isDialogShown) return;
        isDialogShown = true;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(context.getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        isDialogShown = false;
                        if (listener != null) {
                            listener.onClick(dialog, which);
                        }
                    }
                })
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        isDialogShown = false;
                    }
                })
                .show();
    }

    public static void showConfirmDialog(Context context, int titleResourceId, int messageResourceId, DialogInterface.OnClickListener positiveListener) {
        showConfirmDialog(context, titleResourceId, messageResourceId, null, positiveListener);
    }

    public static void showConfirmDialog(Context context, int titleResourceId, int messageResourceId, DialogInterface.OnClickListener negativeListener, DialogInterface.OnClickListener positiveListener) {
        showConfirmDialog(context, context.getString(titleResourceId), context.getString(messageResourceId), negativeListener, positiveListener);
    }

    public static void showConfirmDialog(Context context, String title, String message, final DialogInterface.OnClickListener negativeListener, final DialogInterface.OnClickListener positiveListener) {

        showConfirmDialog(context, title, message, context.getString(R.string.no), negativeListener, context.getString(R.string.yes), positiveListener);
    }

    public static void showConfirmDialog(Context context, String title, String message, String negativeButtonName, final DialogInterface.OnClickListener negativeListener, String positiveButtonName, final DialogInterface.OnClickListener positiveListener) {

        if (isDialogShown) return;
        isDialogShown = true;
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog
                .setTitle(title)
                .setMessage(message)
                .setNegativeButton(negativeButtonName, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        isDialogShown = false;
                        if (negativeListener != null) negativeListener.onClick(dialog, which);
                    }
                })
                .setPositiveButton(positiveButtonName, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        isDialogShown = false;
                        if (positiveListener != null) positiveListener.onClick(dialog, which);
                    }
                })
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        isDialogShown = false;
                    }
                })
                .show();
    }

    public static ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        return objectMapper;
    }

    public static ObjectReader getObjectReaderForUpdating(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        ObjectReader reader = objectMapper.readerForUpdating(object);
        return reader;
    }

    public static ObjectMapper getWritableObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE);
        return objectMapper;
    }

    public static double getDoubleFromString(String expression) {
        double value = 0;
        if (!TextUtils.isEmpty(expression) && !expression.equals("-") && !expression.equals(".")) {
            value = Double.parseDouble(expression);
        }
        return Double.isNaN(value) ? 0 : value;
    }

    public static void voidShowNoInternetToast(Context context){
        Toast.makeText(context.getApplicationContext(), HttpConnectException.MSG_NO_INTERNET, Toast.LENGTH_SHORT).show();
    }

    public static void showSnackBar(Activity activity, String message){
        Snackbar.make(activity.findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show();
    }

    public static void showSnackBar(Activity activity, int messageId){
        showSnackBar(activity, activity.getString(messageId));
    }

    public static Uri ResourceToUri(Context context, int resID) {
        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                + context.getResources().getResourcePackageName(resID) + '/'
                + context.getResources().getResourceTypeName(resID) + '/'
                + context.getResources().getResourceEntryName(resID));
    }

    public static boolean isPackageInstalled(String uri) {

        Context context = DimagiBotApplication.getmInstance().getApplicationContext();
        PackageManager pm = context.getPackageManager();
        boolean app_installed;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }
}
