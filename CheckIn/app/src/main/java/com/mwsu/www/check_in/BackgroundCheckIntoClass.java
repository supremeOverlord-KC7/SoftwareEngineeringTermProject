package com.mwsu.www.check_in;


import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundCheckIntoClass extends AsyncTask<String,Void,String> {
    Context context;
    AlertDialog alertDialog;
    BackgroundCheckIntoClass(Context ctx){
        context = ctx;
    }
    @Override
    protected String doInBackground(String... params) { //changed void to params
        //10.0.2.2 standard ip address to communicate with local host
        String checkInto_url = "http://159.65.46.34/UpRecord.php";
        try {
            String username = params[0];
            String classID = params[1];
            String timeID = params[2];
            String className = params[3];
            URL url = new URL(checkInto_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&"
                    + URLEncoder.encode("day&time", "UTF-8") + "=" + URLEncoder.encode(timeID, "UTF-8") + "&"
                    + URLEncoder.encode("room", "UTF-8") + "=" + URLEncoder.encode(classID, "UTF-8")  + "&"
                    + URLEncoder.encode("Cname", "UTF-8") + "=" + URLEncoder.encode(className, "UTF-8");
            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
            String result = "";
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }
    @Override
    protected void onPreExecute() {
        //alertDialog = new AlertDialog.Builder(context).create();
        //alertDialog.setTitle("Registration Successful");
    }

    @Override
    protected void onPostExecute(String result) {
        //alertDialog.setMessage(result);
        //alertDialog.show();

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
