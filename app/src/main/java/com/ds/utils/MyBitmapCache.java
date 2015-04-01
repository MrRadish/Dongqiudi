package com.ds.utils;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader.ImageCache;

public class MyBitmapCache implements ImageCache {
	private static MyBitmapCache imageCache = null;
	private static LruCache<String, Bitmap> lruCache = null;

	private MyBitmapCache() {
		int memoryCount = (int) Runtime.getRuntime().maxMemory();
		// 获取剩余内存的8分之一作为缓存
		int cacheSize = memoryCount / 8;
		lruCache = new LruCache<String, Bitmap>(cacheSize) {
			@Override
			protected int sizeOf(String key, Bitmap bitmap) {
				return bitmap.getRowBytes() * bitmap.getHeight();
				// return bitmap.getByteCount();
			}
		};
	}

	public static MyBitmapCache getInstance() {
		if (imageCache == null) {
			imageCache = new MyBitmapCache();
		}
		return imageCache;
	}

	@Override
	public Bitmap getBitmap(String url) {
		return lruCache.get(url);
	}

	@Override
	public void putBitmap(String url, Bitmap bitmap) {
		lruCache.put(url, bitmap);
	}
}
