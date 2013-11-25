package me.nereo.gztsg.ui;

import me.nereo.gztsg.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import roboguice.fragment.RoboFragment;

public class BookSearchFragment extends RoboFragment {
	
	public BookSearchFragment(){}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_search_book, container, false);
	}
}
