/*
 * Apparpav is copyright of Agenzia Regionale per la Prevenzione e
 * Protezione Ambientale del Veneto - Via Matteotti, 27 - 35137
 * Padova Italy
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation; either version 2 of
 * the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program; if not, write to the Free
 * Software Foundation, Inc., 59 Temple Place, Suite 330, Boston,
 * MA 02111-1307 USA.
 */

package it.redturtle.mobile.apparpav;

import it.redturtle.mobile.apparpav.utils.Util;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.viewpagerindicator.CirclePageIndicator;

/**
 * @author Nicola Senno
 */
public class RadarActivity extends IndicatorActivity implements RadarFragment.OnPageListener {

	String bulletinid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.simple_circles);

		// START METEO
		final Button meteo = (Button) this.findViewById(R.id.meteograms);
		meteo.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.bg_footer));
		meteo.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				meteo.setBackgroundDrawable(RadarActivity.this.getResources().getDrawable(R.drawable.bg_footer_reversed));
				Intent newintent = new Intent();
				newintent.setClass(RadarActivity.this, MeteogramsActivity.class);
				startActivity(newintent);
			}
		});

		// START BULLETINS
		final Button bulletins = (Button) this.findViewById(R.id.bulletins);
		bulletins.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.bg_footer));
		bulletins.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				bulletins.setBackgroundDrawable(RadarActivity.this.getResources().getDrawable(R.drawable.bg_footer_reversed));
				Intent newintent = new Intent();
				newintent.setClass(RadarActivity.this, BulletinActivity.class);
				startActivity(newintent);
			}
		});

		// RADAR active
		final Button radar = (Button) this.findViewById(R.id.radar);
		radar.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.bg_footer_reversed));

		this.updateDisplay();
	}

	public void updateDisplay(){
		mAdapter = new RadarFragmentAdapter(getSupportFragmentManager(), this);
		mPager = (ViewPager)findViewById(R.id.pager);
		mPager.setAdapter(mAdapter);
		mIndicator = (CirclePageIndicator)findViewById(R.id.indicator);
		mIndicator.setViewPager(mPager);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent newintent = new Intent();
			newintent.setClass(this, MeteogramsActivity.class);
			newintent.putExtra("state", 1);
			startActivity(newintent);
	        return true;
	    }
	    return super.onKeyDown(keyCode, event);
	}
	
	
	// #################################
	// implementation of interface OnPageListener declared in RadarFragment.java
	@Override
	public void swypeLeft() {
		mPager.setCurrentItem( mPager.getCurrentItem()-1 );
	}
	
	public void swypeRight() {
		mPager.setCurrentItem( mPager.getCurrentItem()+1 );
	}
	
	// #################################
	
}