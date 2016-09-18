package com.prueba.carlos.rappitext.utils;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.util.Log;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.utils.DiskCacheUtils;
import com.nostra13.universalimageloader.utils.MemoryCacheUtils;
import com.prueba.carlos.rappitext.R;

public class ImageManageUtils {

    /** Tag para logs **/
    private static final String TAG_LOG = ImageManageUtils.class.getName();

    public static void displayImage(ImageView view, String path, ImageLoadingListener listener) {
        ImageLoader loader = ImageLoader.getInstance();
        loader.init(ImageLoaderConfiguration.createDefault(view.getContext()));
        try {
            loader.displayImage(path, view, DEFAULT_DISPLAY_IMAGE_OPTIONS, listener);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            loader.clearMemoryCache();
        }
    }

    public static void displayRoundImage(ImageView view, String path,
                                         ImageLoadingListener listener) {
        ImageLoader loader = ImageLoader.getInstance();
        try {
            loader.displayImage(path, view, ROUND_DISPLAY_IMAGE_OPTIONS, listener);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            loader.clearMemoryCache();
        }
    }

    public static void loadImage(String path, ImageLoadingListener listener) {
        ImageLoader loader = ImageLoader.getInstance();
        try {
            loader.loadImage(path, DEFAULT_DISPLAY_IMAGE_OPTIONS, listener);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
    }

    public static Bitmap loadImageSync(String path) {
        ImageLoader loader = ImageLoader.getInstance();
        try {
            return loader.loadImageSync(path, DEFAULT_DISPLAY_IMAGE_OPTIONS);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void removeFromCache(String url) {
        try {
            MemoryCacheUtils.removeFromCache(url, ImageLoader.getInstance().getMemoryCache());
            DiskCacheUtils.removeFromCache(url, ImageLoader.getInstance().getDiskCache());
        } catch (Exception e) {
            Log.e(TAG_LOG, "Ocurrió un error eliminando la memoria caché de la url [%s]"+ url);
        }
    }

    private static final DisplayImageOptions.Builder DEFAULT_DISPLAY_IMAGE_OPTIONS_BUIDLER =
            new DisplayImageOptions.Builder()
                    .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
                    .displayer(new FadeInBitmapDisplayer(300, true, false, false))
                    .showImageForEmptyUri(R.drawable.default_image)
                    .showImageOnLoading(R.drawable.default_image).considerExifParams(true)
                    .showImageOnFail(R.drawable.default_image).cacheOnDisk(true)
                    .cacheInMemory(true).bitmapConfig(Config.ARGB_8888);

    private static final DisplayImageOptions DEFAULT_DISPLAY_IMAGE_OPTIONS =
            DEFAULT_DISPLAY_IMAGE_OPTIONS_BUIDLER
                    .build();
    private static final DisplayImageOptions ROUND_DISPLAY_IMAGE_OPTIONS =
            DEFAULT_DISPLAY_IMAGE_OPTIONS_BUIDLER
                    .displayer(new RoundedBitmapDisplayer(500)).build();
}
