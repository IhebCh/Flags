package com.example.flags;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Gallery;

public class Challenges extends Activity {

	public static int challenge;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.challenges);
		Button[] button = new Button[3];

		button[0] = (Button) findViewById(R.id.button_challenge1);
		button[1] = (Button) findViewById(R.id.button_challenge2);
		button[2] = (Button) findViewById(R.id.button_challenge3);

		int fin = Player.LastLevel;

		if (getIntent().getIntExtra("niveau", 1) == fin) {
			
			for (int i = Player.currentChallenge; i < GameManager.nbrChallenge; i++) {
				// button[i].setClickable(false);
				button[i].setEnabled(false);
			}
			fin=Player.currentChallenge ;
		} else {
			fin = GameManager.nbrChallenge;
		}

		for (int i = 0; i < fin; i++) {
			// button[i].setClickable(true);
			button[i].setEnabled(true);
		}
	}

	public void onClick(View v) {
		startActivity(new Intent("com.example.flags.Jeux"));
		switch (v.getId()) {
		case R.id.button_challenge1:
			Challenges.challenge = 10;
			break;
		case R.id.button_challenge2:
			Challenges.challenge = 14;
			break;
		case R.id.button_challenge3:
			Challenges.challenge = 18;
			break;
		default:
			break;
		}
		finish();
	}
}
