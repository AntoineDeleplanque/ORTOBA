package com.example.ortoba;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Classement extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_classement);

		HttpResponse httpreponse = null;
		HttpResponse httpResponse = null;
		String result = "";
		HttpClient httpclient = new DefaultHttpClient();        
        // 2. make POST request to the given URL
        HttpPost httpPost = new HttpPost("http://192.168.1.96/equipe/classement");

        // 8. Execute POST request to the given URL
        try {
			httpreponse = httpclient.execute(httpPost);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(httpreponse.getEntity().getContent(), "UTF-8"));
		} catch (IllegalStateException
				| IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        StringBuilder builder = new StringBuilder();
        try {
			for (String line = null; (line = reader.readLine()) != null;) {
			    builder.append(line).append("\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        JSONTokener tokener = new JSONTokener(builder.toString());
        JSONArray finalResult = null;
        try {
			finalResult = new JSONArray(tokener);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        /*InputStream inputStream = null;
		try {
			inputStream = httpResponse.getEntity().getContent();
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
        /*if(inputStream != null)
			try {
				result = convertInputStreamToString(inputStream);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else
            result = "Did not work!";*/
       
        
        /*JSONObject jsonObj = null;
		try {
			jsonObj = new JSONObject(result);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        // Getting JSON Array node
        JSONArray contacts = null;
		try {
			 contacts = jsonObj.getJSONArray("");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

        // looping through All Contacts
		List<equipe> equipeList = new ArrayList<equipe>();
        for (int i = 0; i < finalResult.length(); i++) {
            JSONObject c = null;
			try {
				c = finalResult.getJSONObject(i);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
             
            String id = null;
			try {
				id = c.getString("id_equipe");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            String name = null;
			try {
				name = c.getString("nom_equipe");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            String ville = null;
			try {
				ville = c.getString("ville_equipe");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            String score = null;
			try {
				score = c.getString("score_equipe");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

            equipeList.add(new equipe(Integer.parseInt(id), name, ville, Integer.parseInt(score)));
        }
        final RelativeLayout rl=(RelativeLayout) findViewById(R.id.ClassmentRLayout);
        for (int i = 0; i < equipeList.size(); i++) {
        	TextView tv =new TextView(this);   
            RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams
                    ((int)LayoutParams.WRAP_CONTENT,(int)LayoutParams.WRAP_CONTENT);
            params.leftMargin=50;
                params.topMargin=i*50;
            tv.setText(equipeList.get(i).getId());
            tv.setTextSize((float) 20);
            tv.setPadding(20, 50, 20, 50);
            tv.setLayoutParams(params);
            rl.addView(tv);
        }
	}
	
	private static String convertInputStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;
 
        inputStream.close();
        return result;
 
    }
}
