package com.android521.activity;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SectionsPagerAdapter extends FragmentPagerAdapter{
	
	List<Fragment> list=null;

	public SectionsPagerAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}
	
	public SectionsPagerAdapter(FragmentManager fm,List<Fragment> list)
	{
		super(fm);
		this.list=list;
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		Fragment fragment=list.get(arg0);
		Bundle args = new Bundle();
        args.putInt("no", arg0+ 1);
        fragment.setArguments(args);
		return fragment;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

}
