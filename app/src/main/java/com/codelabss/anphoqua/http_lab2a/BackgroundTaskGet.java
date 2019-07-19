package com.codelabss.anphoqua.http_lab2a;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class BackgroundTaskGet extends AsyncTask<Void, Void, Void> {
    Context context;
    String link = MainActivity.SEVER_NAME;
    TextView result;
    String str_name, str_age;
    String str;
    ProgressDialog progressDialog;

    public BackgroundTaskGet(Context context, TextView result, String str_name, String str_age) {
        this.context = context;
        this.result = result;
        this.str_name = str_name;
        this.str_age = str_age;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        link += "?name=" +this.str_name + "&age=" + this.str_age;

        URL url = null;
        try {
            url = new URL(link);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String line = "";
            StringBuffer stringBuffer = new StringBuffer();
            while ((line = bufferedReader.readLine()) != null){
                stringBuffer.append(line);
            }

            str = stringBuffer.toString();



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Calculating... Please wait!");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        progressDialog.show();

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        result.setText(str);
    }
}
