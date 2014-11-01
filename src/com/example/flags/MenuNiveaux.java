package com.example.flags;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuNiveaux extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_niveaux);
		Button[] button = new Button[4];

		button[0] = (Button) findViewById(R.id.button_niveau1);
		button[1] = (Button) findViewById(R.id.button_niveau2);
		button[2] = (Button) findViewById(R.id.button_niveau3);
		button[3] = (Button) findViewById(R.id.button_niveau4);

		for (int i = 0; i < Player.LastLevel; i++) {
			// button[i].setClickable(true);
			button[i].setEnabled(true);
		}
		for (int i = Player.LastLevel; i < GameManager.nbrNiveau; i++) {
			// button[i].setClickable(false);
			button[i].setEnabled(false);

		}

	}

	public void onClick(View v) {
		int a = 1;
		switch (v.getId()) {
		case R.id.button_niveau1:

			a = 1;
			break;
		case R.id.button_niveau2:
			a = 2;
			break;
		case R.id.button_niveau3:
			a = 3;
			break;
		case R.id.button_niveau4:
			a = 4;
			break;

		default:
			break;
		}

		Player.CurrentLevel = a;
		/* juste pour la teste on peut tester avec Player.CurrentLevel */
		Intent i = new Intent("com.example.flags.Challenges");

		i.putExtra("niveau", a);
		startActivity(i);
		finish();

	}
}
