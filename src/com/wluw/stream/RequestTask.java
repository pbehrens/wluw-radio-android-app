package com.wluw.stream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;


class RequestTask extends AsyncTask<String, String, String>{
    private WeakReference<WLUWActivity> mParentActivity = null;
    private WLUWActivity parentActivity;
	private static final String TAG = "RequestTask";

    public RequestTask(WLUWActivity aParentActivity) {
        mParentActivity = new WeakReference<WLUWActivity>(aParentActivity);
    }
    
    @Override
    protected String doInBackground(String... uri) {
    	if (mParentActivity.get() != null) {
            // the WeakReference is still valid and hasn't been reclaimed
            // by the GC
            parentActivity = mParentActivity.get();
        }
    	//get http client to hit up the api
        HttpClient httpclient = new DefaultHttpClient();
        HttpResponse response;
        String responseString = null;
        try {
        	
        	Log.v(TAG, "uri should be " + uri[0]);
        	URL hostUrl = new URL(uri[0]);

        	URI hostUri = hostUrl.toURI();
        	
            response = httpclient.execute(new HttpGet(hostUri));
            StatusLine statusLine = response.getStatusLine();
            //turn response to correct character type
            if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                response.getEntity().writeTo(out);
                out.close();
                responseString = out.toString();
                Log.v(TAG, responseString + "error ");
            } else{
                //Closes the connection.
                response.getEntity().getContent().close();
                Log.v(TAG, "error with connection");
                throw new IOException(statusLine.getReasonPhrase());
            }
        } catch (ClientProtocolException e) {
            //TODO Handle problems..
        	Log.v(TAG, "client protocol exception");
        } catch (IOException e) {
            //TODO Handle problems..
        	Log.v(TAG, e.toString());
        } catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return responseString;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if(result != null){
        	try {
        		//take the json object from the response
        		JSONObject jsonObj = new JSONObject(result);
        		String[] labelText = new String[3];
        		//grab elemnts
        		labelText[0] = jsonObj.get("track").toString();
        		labelText[1] = jsonObj.get("album").toString();
        		labelText[2] = jsonObj.get("artist").toString();
        		
				Log.v(TAG, jsonObj.toString());
				//set labels
				parentActivity.setLabels(labelText);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        else{
        	String[] blank = {"", "", ""};
        	parentActivity.setLabels(blank);
        }
        //Do anything with response..
    }
}