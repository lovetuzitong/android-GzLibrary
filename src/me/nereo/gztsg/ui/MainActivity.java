/**
 *  Copyright 2013 Nereo Tu
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package me.nereo.gztsg.ui;

import me.nereo.gztsg.BaseActivity;
import me.nereo.gztsg.R;
import android.app.ActionBar;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Toast;
import roboguice.inject.InjectView;

public class MainActivity extends BaseActivity {

	@InjectView(R.id.drawer_layout)
	DrawerLayout mDrawerLayout;
	@InjectView(R.id.left_drawer)
	ListView mLeftDrawer;

	private ActionBarDrawerToggle mDrawerToggle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// setup shadow for drawer
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);
		// setup content modal color
		mDrawerLayout.setScrimColor(Color.argb(100, 0, 0, 0));

		final ActionBar mActionBar = getActionBar();
		// display left to icon
		mActionBar.setDisplayHomeAsUpEnabled(true);
		// enable icon click
		mActionBar.setHomeButtonEnabled(true);
		mActionBar.setDisplayShowTitleEnabled(true);
		mActionBar.setTitle(R.string.app_name);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {

			@Override
			public void onDrawerClosed(View drawerView) {
				mActionBar.setTitle(R.string.app_name);
				// recreate option menu
				invalidateOptionsMenu();
			}

			@Override
			public void onDrawerOpened(View drawerView) {
				mActionBar.setTitle(R.string.setting_title);
				// recreate option menu
				invalidateOptionsMenu();
			}

		};

		mDrawerLayout.setDrawerListener(mDrawerToggle);
		
		if(savedInstanceState == null){
			attachSearchBookFragment();
		}

	}
	
	private void attachSearchBookFragment(){
		Fragment fragment = Fragment.instantiate(this, SearchHistoryFragment.class.getName());
		FragmentManager fm = getSupportFragmentManager();
		fm.beginTransaction().replace(R.id.content_frame, fragment).commit();
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {

		// if the drawer is open, hide SEARCH item, else display it
		boolean drawrOpen = mDrawerLayout.isDrawerOpen(mLeftDrawer);
		menu.findItem(R.id.action_search_book).setVisible(!drawrOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		
		final SearchView mSearchView = (SearchView) menu.findItem(R.id.action_search_book).getActionView();
		// setup query listener
		mSearchView.setOnQueryTextListener(onQueryListener);
		
		return true;
	}
	
	private final OnQueryTextListener onQueryListener = new SearchView.OnQueryTextListener() {
		
		@Override
		public boolean onQueryTextSubmit(String query) {
			// TODO Auto-generated method stub
			Toast.makeText(getApplicationContext(), "Search for: "+query, Toast.LENGTH_SHORT).show();
			return false;
		}
		
		@Override
		public boolean onQueryTextChange(String newText) {
			// TODO Auto-generated method stub
			return false;
		}
	};

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}

		switch (item.getItemId()) {
		case R.id.action_search_book:
			
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}

	}

}
