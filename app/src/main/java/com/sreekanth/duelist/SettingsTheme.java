package com.sreekanth.duelist;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;

public class SettingsTheme extends Activity implements View.OnClickListener

{
 /** Called when the activity is first created. */
	SharedPreferences preferences;
	SharedPreferences.Editor editor;
 @Override
 public void onCreate(Bundle savedInstanceState)
 {
  super.onCreate(savedInstanceState);
  Utils.setThemeToActivity(this);
  setContentView(R.layout.activity_setting_theme);

  findViewById(R.id.button1).setOnClickListener(this);
  findViewById(R.id.button2).setOnClickListener(this);
  
 }

 public void onClick(View v)
 {
  switch (v.getId())
  {
  case R.id.button1:
//      Utils.THEME="Dark";
//      Utils.settingChanged=true;
//      //Utils.SIZE="Dark";
//      startActivity(new Intent( SettingsTheme.this,  PmActivity.class));
       preferences = PreferenceManager.getDefaultSharedPreferences(this);
      editor = preferences.edit();
      editor.putString("Theme","Dark");
      editor.apply();
      Toast.makeText(this, "Settings Saved", Toast.LENGTH_SHORT).show();
   break;
  case R.id.button2:
//      Utils.THEME="Light";
//      Utils.settingChanged=true;
//      Utils.SIZE="Small";
//      startActivity(new Intent( SettingsTheme.this,  PmActivity.class));
       preferences = PreferenceManager.getDefaultSharedPreferences(this);
       editor = preferences.edit();
      editor.putString("Theme","Light");
      editor.apply();
      Toast.makeText(this, "Settings Saved", Toast.LENGTH_SHORT).show();
   break;
  
  default :
   break;

  }
}
}