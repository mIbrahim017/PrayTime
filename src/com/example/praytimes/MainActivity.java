package com.example.praytimes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	
	private TextView prayTime ; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		prayTime = (TextView) findViewById(R.id.prayTime); 
		getPrayTime();
	}
	
	
	
 private void  getPrayTime(){

	 StringBuilder builder = new StringBuilder() ;
	 
	 double latitude =  31.19000 ; //  30.06474166666667; //-37.823689;
     double longitude = 29.95000 ;//31.24950833333334;  // 145.121597;
     double timezone = (Calendar.getInstance().getTimeZone()
             .getOffset(Calendar.getInstance().getTimeInMillis()))
             / (1000 * 60 * 60);
     // Test Prayer times here
     PrayTime prayers = new PrayTime();

     
     prayers.setTimeFormat(prayers.Time12);
     prayers.setCalcMethod(prayers.Egypt);
     prayers.setAsrJuristic(prayers.Shafii);
     prayers.setAdjustHighLats(prayers.AngleBased);
     int[] offsets = {0, 0, 0, 0, 0, 0, 0}; // {Fajr,Sunrise,Dhuhr,Asr,Sunset,Maghrib,Isha}
     prayers.tune(offsets);

     Date now = new Date();
     
     Calendar cal = Calendar.getInstance();
     cal.setTime(now);

     ArrayList<String> prayerTimes = prayers.getPrayerTimes(cal,
             latitude, longitude, timezone);
     ArrayList<String> prayerNames = prayers.getTimeNames();

     for (int i = 0; i < prayerTimes.size(); i++) {
         System.out.println(prayerNames.get(i) + " - " + prayerTimes.get(i));
         builder.append(prayerNames.get(i) + " - " + prayerTimes.get(i)+ "\n");
     }
     prayTime.setText(builder.toString()+ "\n " +  cal.get(Calendar.YEAR) + ": "+ cal.get(Calendar.MONTH) + " :"+  cal.get(Calendar.DAY_OF_MONTH));
    

 }

}
/*http://praytimes.org/code/git/?a=viewblob&p=PrayTimes&h=093f77d6cc83b53fb12e9900803d5fa75dacd110&hb=HEAD&f=v1/java/PrayTime.java

prayer times service API

*/