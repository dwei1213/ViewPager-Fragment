package com.android521.activity;

import java.util.ArrayList;
import java.util.List;

import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.android521.activity.fragment.dynamicFm;
import com.android521.activity.fragment.friendFm;
import com.android521.activity.fragment.groupFm;

public class FragmentPageSwitcher extends FragmentActivity{
	  private ViewPager mPager;//页卡内容
	  private SectionsPagerAdapter mSectionsPagerAdapter;  //页卡适配器
	  private FragmentManager fgManager;
	    private List<Fragment> listViews; // Tab页面列表
	    private ImageView cursor;// 动画图片
	    private ImageView t1, t2, t3;// 页卡头标
	    private int offset = 0;// 动画图片偏移量
	    private int currIndex = 0;// 当前页卡编号
	    private int bmpW;// 动画图片宽度
	    
	    @Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			setContentView(R.layout.new_userlist);
			fgManager=getSupportFragmentManager();
			InitTextView();
			InitImageView();
			InitViewPager();
			
		}

		

			/**
			* 初始化头标
			*/
			private void InitTextView() {
			t1 = (ImageView) findViewById(R.id.text1);
			t2 = (ImageView) findViewById(R.id.text2);
			t3 = (ImageView) findViewById(R.id.text3);
			
			t1.setOnClickListener(new MyOnClickListener(0));
			t2.setOnClickListener(new MyOnClickListener(1));
			t3.setOnClickListener(new MyOnClickListener(2));
			}
			
			
			/**
			* 头标点击监听
			*/
			public class MyOnClickListener implements View.OnClickListener {
			private int index = 0;

			public MyOnClickListener(int i) {
			index = i;
			}

			@Override
			public void onClick(View v) {
			mPager.setCurrentItem(index);
			}
			}
			
			
			/**
			* 初始化ViewPager
			*/
			private void InitViewPager() {
			mPager = (ViewPager) findViewById(R.id.vPager);
			listViews = new ArrayList<Fragment>();
		    listViews.add(new friendFm());
		    listViews.add(new dynamicFm());
		    listViews.add(new groupFm());
		    mSectionsPagerAdapter = new SectionsPagerAdapter(
			        fgManager,listViews);
			mPager.setAdapter(mSectionsPagerAdapter);
			mPager.setCurrentItem(0);
			mPager.setOnPageChangeListener(new MyOnPageChangeListener());
			}
			
			
			/**
			* 初始化动画
			*/
			private void InitImageView() {
			cursor = (ImageView) findViewById(R.id.cursor);
			bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.topbar_select)
			.getWidth();// 获取图片宽度
			DisplayMetrics dm = new DisplayMetrics();
			getWindowManager().getDefaultDisplay().getMetrics(dm);
			int screenW = dm.widthPixels;// 获取分辨率宽度
			offset = (screenW / 3 - bmpW) / 2;// 计算偏移量
			Matrix matrix = new Matrix();
			matrix.postTranslate(offset, 0);
			cursor.setImageMatrix(matrix);// 设置动画初始位置
			}
			
			/**
			* 页卡切换监听
			*/
			public class MyOnPageChangeListener implements OnPageChangeListener {

			int one = offset * 2 + bmpW;// 页卡1 -> 页卡2 偏移量
			int two = one * 2;// 页卡1 -> 页卡3 偏移量

			@Override
			public void onPageSelected(int arg0) {
			Animation animation = null;
			switch (arg0) {
			case 0:
			if (currIndex == 1) {
			animation = new TranslateAnimation(one, 0, 0, 0);
			} else if (currIndex == 2) {
			animation = new TranslateAnimation(two, 0, 0, 0);
			}
			break;
			case 1:
			if (currIndex == 0) {
			animation = new TranslateAnimation(offset, one, 0, 0);
			} else if (currIndex == 2) {
			animation = new TranslateAnimation(two, one, 0, 0);
			}
			break;
			case 2:
			if (currIndex == 0) {
			animation = new TranslateAnimation(offset, two, 0, 0);
			} else if (currIndex == 1) {
			animation = new TranslateAnimation(one, two, 0, 0);
			}
			break;
			}
			currIndex = arg0;
			animation.setFillAfter(true);// True:图片停在动画结束位置
			animation.setDuration(300);
			cursor.startAnimation(animation);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
			}
}
