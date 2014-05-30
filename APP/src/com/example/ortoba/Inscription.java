package com.example.ortoba;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class Inscription extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inscription);
		
		Button valider = (Button) findViewById(R.id.ValiderButton);
		valider.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
  
                new AlertDialog.Builder(Inscription.this)
                .setTitle("Confirmation")
                .setMessage("Etes-vous sure de vouloir inscrire cette equipe?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    	
                        try {
        	            	// 1. create HttpClient
        	                HttpClient httpclient = new DefaultHttpClient();
        	     
        	                // 2. make POST request to the given URL
        	                HttpPost httpPost = new HttpPost("http://192.168.1.96/equipe/add/" +((EditText) findViewById(R.id.NomTexte)).getText().toString() +"/"+((EditText)findViewById(R.id.editText2)).getText().toString());

        	                // 8. Execute POST request to the given URL
        	                httpclient.execute(httpPost);
        	     
                    	} catch (Exception e) {
                    		Log.d("InputStream", e.getLocalizedMessage());
                    	}
                    }
                 })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) { 
                        // do nothing
                    }
                 })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
            }
        });
		
    }  
}
