package com.example.flags;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

@SuppressLint("ShowToast")
public class GameManager extends Activity {

	public static Country[] countryTab;
	public static int nbRestant;
	public static InputStream fileIn;
	public static int nbrNiveau = 4;
	public static int nbrChallenge = 3;
    
	public void init(Activity ac, int niveau) {

		int i = 0, j = 0;
		try {
			switch (niveau) {
			case 1:
				j = R.raw.niveau1;
				break;
			case 2:
				j = R.raw.niveau2;
				break;
			case 3:
				j = R.raw.niveau3;
				break;
			case 4:
				j = R.raw.niveau4;
				break;
			}

			fileIn = ac.getResources().openRawResource(j);

			BufferedReader br = new BufferedReader(
					new InputStreamReader(fileIn));
			String noms = null;

			noms = br.readLine();
			countryTab = new Country[Integer.valueOf(noms)];
			while ((noms = br.readLine()) != null) {

				countryTab[i] = new Country(ac.getResources().getIdentifier(
						"com.example.flags:" + "drawable/" + noms, null, null),
						false, false);
				Log.d("val ", noms);
				Log.d("val_res ",
						ac.getResources().getResourceEntryName(
								ac.getResources().getIdentifier(
										"com.example.flags:" + "drawable/"
												+ noms, null, null)));

				i++;

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		nbRestant = countryTab.length;
	}

	public static int getFlags(Country[] Tabcountry) {

		int i = 0;
		while (i < 4) {
			int a = (int) (Math.random() * 1000) % countryTab.length;
			if (!countryTab[a].isSelectedAsAnAnswer()) {
				Tabcountry[i] = countryTab[a];
				i++;
				countryTab[a].setSelectedAsAnAnswer(true);
			}
		}

		for (i = 0; i < 4; i++)
			Tabcountry[i].setSelectedAsAnAnswer(false);

		i = (int) ((Math.random() * 317)+2 ) % 4;

		/*
		 * while (countryTab[i].isSelectedAsMainAnswer()) { i = (int)
		 * (Math.random() * 500) % 4; }
		 */
		// countryTab[i].setSelectedAsMainAnswer(true);

		return i;
	}

	/*
	 * public static int remainingAnswers() {
	 * 
	 * int i = 0; for (int j = 0; j < countryTab.length; j++) { if
	 * (!countryTab[j].isSelectedAsMainAnswer()) i++; } return i; }
	 */
}
