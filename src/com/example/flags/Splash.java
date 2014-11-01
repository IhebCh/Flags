package com.example.flags;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
//import android.media.MediaPlayer;

public class Splash extends Activity{

	//MediaPlayer sound ;
	@Override
	protected void onCreate(Bundle ahmed) {
		// TODO Auto-generated method stub
		super.onCreate(ahmed);
		setContentView(R.layout.splash) ;
		
		//sound =MediaPlayer.create(Splash.this, R.raw.crazy_loop) ;
		//sound.start() ;
		Thread timer=new Thread(){
			public void run(){
				try{
					sleep(1000) ;
				}catch(InterruptedException e){
					e.printStackTrace() ;
				}finally{
					Intent opentMainActivity=new Intent("com.example.flags.MenuPrincipale") ;
					startActivity(opentMainActivity) ;
				}
			}
			
		};
		timer.start() ;
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		//MainActivity.counter=10 ;
		super.onPause();
		//sound.release() ;
		finish() ;
	}

}

