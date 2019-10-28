package vn.com.mob1032_lab8103;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
String url = "http://www.vnexpress.net";
InputStream stream = null;
HttpURLConnection urlConnection;
String content = "";
TextView tvMain;
BufferedReader reader = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvMain = findViewById(R.id.tvMain);
        new WebInfo().execute(url);
    }
    class WebInfo extends AsyncTask<String, String, String>{

        @Override
        protected String doInBackground(String... params) {
            try{
                URL url1 = new URL(params[0]);
                urlConnection = (HttpURLConnection) url1.openConnection();
                urlConnection.connect();
                stream = urlConnection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line="";
                while((line = reader.readLine())!=null){
                    buffer.append(line);
                }
                return buffer.toString();
                }

            catch(MalformedURLException e) {
                e.printStackTrace();
            }
             catch(IOException ioException) {
                ioException.printStackTrace();
            }
            finally{
                if(urlConnection!=null){
                    urlConnection.disconnect();
                }
                try{
                    if(reader!=null){
                        reader.close();
                    }
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
                return null;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            tvMain.setText(s);
        }
    }

}
