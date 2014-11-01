package com.example.flags;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint({ "ShowToast", "DefaultLocale" })
public class Jeux extends Activity {

	ImageButton[] button = new ImageButton[4];
	TextView nomPays;
	TextView stage;
	TextView textScore;
	ImageView[] stars = new ImageView[20];
	Country[] tabPays = new Country[4];
	// MediaPlayer mpRight;
	// MediaPlayer mpWrong;
	int cpt = 0;

	int nbrEtapes = 20;

	Animation buttonAnimation = new AlphaAnimation(1, 0);
	Animation scoreAnimation, textAnimation ,starAnimation ;

	// int score = 0;
	GameManager gm = new GameManager();

	int ind_rep;
	
	public static int sonRight ,sonWrong ;
    public static SoundPool sp =new  SoundPool(3,AudioManager.STREAM_RING,0);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.jeux);
		Player.score = 0;
		button[0] = (ImageButton) findViewById(R.id.imageButton1);
		button[1] = (ImageButton) findViewById(R.id.ImageButton2);
		button[2] = (ImageButton) findViewById(R.id.ImageButton3);
		button[3] = (ImageButton) findViewById(R.id.ImageButton4);

		stars[0] = (ImageView) findViewById(R.id.star1);
		stars[1] = (ImageView) findViewById(R.id.star2);
		stars[2] = (ImageView) findViewById(R.id.star3);
		stars[3] = (ImageView) findViewById(R.id.star4);
		stars[4] = (ImageView) findViewById(R.id.star5);
		stars[5] = (ImageView) findViewById(R.id.star6);
		stars[6] = (ImageView) findViewById(R.id.star7);
		stars[7] = (ImageView) findViewById(R.id.star8);
		stars[8] = (ImageView) findViewById(R.id.star9);
		stars[9] = (ImageView) findViewById(R.id.star10);
		stars[10] = (ImageView) findViewById(R.id.star11);
		stars[11] = (ImageView) findViewById(R.id.star12);
		stars[12] = (ImageView) findViewById(R.id.star13);
		stars[13] = (ImageView) findViewById(R.id.star14);
		stars[14] = (ImageView) findViewById(R.id.star15);
		stars[15] = (ImageView) findViewById(R.id.star16);
		stars[16] = (ImageView) findViewById(R.id.star17);
		stars[17] = (ImageView) findViewById(R.id.star18);
		stars[18] = (ImageView) findViewById(R.id.star19);
		stars[19] = (ImageView) findViewById(R.id.star20);

		nomPays = (TextView) findViewById(R.id.nomPays);
		textScore = (TextView) findViewById(R.id.score);
		stage = (TextView) findViewById(R.id.TextView02);
		// mpRight = MediaPlayer.create(this, R.raw.right);
		// mpWrong = MediaPlayer.create(this, R.raw.wrong);
		// textScore.setBackgroundColor(0x7705AB8C);
		buttonAnimation.setDuration(100);
		buttonAnimation.setInterpolator(new LinearInterpolator());
		buttonAnimation.setRepeatCount(1);
		buttonAnimation.setRepeatMode(Animation.RESTART);

		/*
		 * scoreAnimation.setDuration(100); scoreAnimation.setInterpolator(new
		 * LinearInterpolator()); scoreAnimation.setRepeatCount(3);
		 * scoreAnimation.setRepeatMode(Animation.RESTART);
		 */
		scoreAnimation = AnimationUtils
				.loadAnimation(this, R.anim.anim_alpha);
		scoreAnimation.setDuration(800);

		textAnimation = AnimationUtils.loadAnimation(this, R.anim.push_left_in);
		textAnimation.setDuration(800);
		
		starAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_rotate);
		starAnimation.setDuration(1000);
		
		switch (Player.CurrentLevel) {

		case 1:
			stage.setText("EASY");
			break;
		case 2:
			stage.setText("MEDIUM");
			break;
		case 3:
			stage.setText("HARD");
			break;
		case 4:
			stage.setText("EXPERT");
			break;

		}

		gm.init(this, Player.CurrentLevel);
		ind_rep = GameManager.getFlags(tabPays);
		textScore.setText(""
				+ Math.max(0, (Challenges.challenge - Player.score)));

		

		nomPays.setTextSize(14 * getResources().getDisplayMetrics().density);
		textScore.setTextSize(16 * getResources().getDisplayMetrics().density);
		stage.setTextSize(12 * getResources().getDisplayMetrics().density);

		Typeface type = Typeface.createFromAsset(getAssets(),
				"fonts/jokerman.ttf");
		nomPays.setTypeface(type);

		textScore.setTypeface(type);
		stage.setTypeface(type);

		textAnimation.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation arg0) {
				// TODO Auto-generated method stub
				
				enableButton();
				
				
			}
		});
		
		scoreAnimation.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				next();
				textScore.setText(""
						+ Math.max(0, (Challenges.challenge - Player.score)));

			}
		});
		chargerSon();
		updateView();
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@SuppressLint("DefaultLocale")
	public void updateView() {

		for (int i = 0; i < 4; i++) {
			button[i].setBackgroundColor(0xFF4E8CEA);
		}
		for (int i = 0; i < 4; i++) {
			button[i].setImageResource(tabPays[i].getId());
		}
		nomPays.setText(getResources()
				.getResourceEntryName(tabPays[ind_rep].getId()).toUpperCase()
				.replace('_', ' '));
	}

	public void onClick(View v) {
		int ind_clicked = v.getId();

		/* desactivate buttons so they can not be clicked again */

		disableButton();

		button[ind_rep].startAnimation(buttonAnimation);

		if (ind_clicked == button[ind_rep].getId()) {
			button[ind_rep].setBackgroundColor(0x7700FF00);
			Player.score++;
			
			stars[cpt].setImageResource(R.drawable.green_star_icon);
			sp.play(sonRight, 1, 1, 0, 0, 2);

			} else {
			button[ind_rep].setBackgroundColor(0x7700FF00);
			findViewById(ind_clicked).setBackgroundColor(Color.RED);
			stars[cpt].setImageResource(R.drawable.red_star_icon);
			sp.play(sonWrong, 1, 1, 0, 0, 4);
		}

		stage.post(new Runnable() {

			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				//stars[cpt].startAnimation(scoreAnimation);
				// TODO Auto-generated method stub
				textScore.startAnimation(scoreAnimation);
				
				slideToLeft(nomPays, (9 * getWindowManager()
						.getDefaultDisplay().getWidth()) / 10);
				// nomPays.startAnimation(textAnimation);
				

			}
		});

		


		cpt++;

	}

	public void sortir(View v) {
		finish();
	}

	@SuppressWarnings("deprecation")
	public void next() {

		nbrEtapes--;

		if (nbrEtapes == 0) {

			if (Player.CurrentLevel <= GameManager.nbrNiveau) {
				if (Player.score >= Challenges.challenge) {
					Challenges.challenge += 4;
					if (Player.currentChallenge < GameManager.nbrChallenge)
						Player.currentChallenge++;
					else {
						Player.CurrentLevel++;
						Challenges.challenge = 10;
						if (Player.CurrentLevel >= Player.LastLevel)
							Player.LastLevel = Player.CurrentLevel;
						Player.currentChallenge = 1;
					}
					recreate();
				} else {
					showDialog(Player.score);
				}
			}

		} else {
			
			ind_rep = GameManager.getFlags(tabPays);
			Log.d("name", getResources().getResourceName(tabPays[0].getId())
					+ "  " + getResources().getResourceName(tabPays[1].getId())
					+ "  " + getResources().getResourceName(tabPays[2].getId())
					+ "  " + getResources().getResourceName(tabPays[3].getId())
					+ "  ");

		}

	}

	@Override
	protected Dialog onCreateDialog(int id) {

		Builder builder = new AlertDialog.Builder(this);
		builder.setIcon(R.drawable.ic_launcher);
		builder.setCancelable(false);
		builder.setTitle("Votre score est :  " + id);

		// if (Player.score < Challenges.challenge)
		// builder.setMessage(R.string.score_not);

		builder.setPositiveButton("Rejouer",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						recreate();
					}
				});
		builder.setNegativeButton("Quitter",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						// finishAffinity();

					}
				});

		return builder.create();

	}

	public void disableButton() {
		for (int i = 0; i < 4; i++) {
			button[i].setClickable(false);
		}
	}

	public void enableButton() {
		for (int i = 0; i < 4; i++) {
			button[i].setClickable(true);
		}
	}

	// To animate view slide out from right to left
	public void slideToLeft(View view, int deplacement) {
		TranslateAnimation animate = new TranslateAnimation(0, -deplacement, 0,
				0);
		animate.setDuration(800);
		animate.setFillAfter(true);
		animate.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation arg0) {
				// TODO Auto-generated method stub
				
				
			}
			
			@Override
			public void onAnimationRepeat(Animation arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation arg0) {
				// TODO Auto-generated method stub
				updateView();
				
				nomPays.startAnimation(textAnimation);
				
				
			}
		});
		
		view.startAnimation(animate);
		//view.setVisibility(View.GONE);
	}

	 public void chargerSon(){
	    	sonRight = sp.load(this,R.raw.right ,0);
	    	sonWrong =sp.load(this, R.raw.wrong,0);
	    }
}