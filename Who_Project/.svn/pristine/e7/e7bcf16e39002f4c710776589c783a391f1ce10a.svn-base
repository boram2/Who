package com.who.onecupafterwork.network;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.who.onecupafterwork.signin.R;

public class MyApplication extends Application {
	
	private static Context sContext;

	@Override
	public void onCreate() {
		super.onCreate();
		sContext = this;

		initImageLoader(this);
	}

	public static Context getContext() {
		return sContext;
	}

	public static void initImageLoader(Context context) {
		// This configuration tuning is custom. You can tune every option, you may tune some of them,
		// or you can create default configuration by
		// ImageLoaderConfiguration.createDefault(this);
		// method.
		DisplayImageOptions options = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.photo_loading)
				.showImageForEmptyUri(R.drawable.join_picture).showImageOnFail(R.drawable.photo_err).cacheInMemory(true) // 메모리 캐시 사용
				.cacheOnDisc(true) // 디스크 캐시 사용
				.considerExifParams(true) // 사진에 관한 정보 : Exif
				.build();

		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory().discCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO).writeDebugLogs() // Remove for release app
				.defaultDisplayImageOptions(options).build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
	}
}
