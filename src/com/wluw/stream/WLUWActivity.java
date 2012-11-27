package com.wluw.stream;


import java.io.IOException;
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
 
public class WLUWActivity extends Activity implements OnClickListener {
	Button mButton;
	boolean isOn;
	MediaPlayer mp;
	TextView artistView;
	private static final String TAG = "WLUWActivity";
	public void onClick(View v) {
		Button playButton = (Button)findViewById(R.id.play);
		if(isOn == false)
		{
			//execute grabbing 
			new RequestTask(this).execute(new String[] { "http://staging.wluw.org/mobile" });
			//TODO: move this to an async task 
			try {
				//create music player
				mp = new MediaPlayer();
				mp.setDataSource("http://amber.streamguys.com:4100/stream.mp3");
				mp.prepare();
				mp.start();
				playButton.setText("Stop");
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			isOn = true;
		}
		else
		{
			mp.stop();
			playButton.setText("Listen");
			isOn = false;
		}	
	}
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isOn = false;
        setContentView(R.layout.main);
        Button play = (Button) findViewById(R.id.play);
        play.setOnClickListener(this);
        this.artistView = (TextView) findViewById(R.id.artist);
        
    }

	public void setLabels(String[] result) {
		// result = {track, album, artist}
		TextView albumText = (TextView)findViewById(R.id.album);
		TextView artistText = (TextView)findViewById(R.id.artist);
		TextView trackText = (TextView)findViewById(R.id.track);

		Log.v(TAG, result.toString());
		
		albumText.setText(result[1]);
		artistText.setText(result[2]);
		trackText.setText(result[0]);
	}

    
    
}
