package com.example.flags;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuPrincipale extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_principale);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button_play:
			startActivity(new Intent("com.example.flags.MenuNiveaux"));
			break;
		case R.id.button_how:
			startActivity(new Intent("com.example.flags.CommentJouer"));

			break;
		case R.id.button_about:
			startActivity(new Intent("com.example.flags.Apropos"));

			break;

		default:
			break;
		}
	}
}
