package com.foadia.champions.util;

import android.content.Context;
import android.content.Intent;

import com.foadia.champions.Ui.Main.MainActivity;
import com.foadia.champions.Ui.Main.MainCountriesList;
import com.foadia.champions.Ui.Main.MainDancing;
import com.foadia.champions.Ui.Main.MainHome;
import com.foadia.champions.Ui.Main.MainLogin;
import com.foadia.champions.Ui.Main.MainRegister;


public class ActivitiesLauncher {

	static private Intent intent;

	public static void openLoginActivity(Context context) {
		intent = new Intent(context, MainLogin.class);
		context.startActivity(intent);
	}

	public static void openHomeActivity(Context context) {
		intent = new Intent(context, MainHome.class);
		context.startActivity(intent);
	}

	public static void openRegisterActivity(Context context) {
		intent = new Intent(context, MainRegister.class);
		context.startActivity(intent);
	}
	public static void openDancingActivity(Context context) {
		intent = new Intent(context, MainDancing.class);
		context.startActivity(intent);
	}

	public static void openCountriesActivity(Context context) {
		intent = new Intent(context, MainCountriesList.class);
		context.startActivity(intent);
	}




}
