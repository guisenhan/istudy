package hise.hznu.istudy.util;

import com.nostra13.universalimageloader.cache.disc.impl.LimitedAgeDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

import java.io.File;

import hise.hznu.istudy.app.AppConfig;

/**
 * Created by PC on 2016/10/12.
 */
public class ImageLoaderUtils {
    private static ImageLoader imageLoaderDefault;
    private static ImageLoader imageLoaderDynamic;

    public ImageLoaderUtils() {
    }

    public static synchronized ImageLoader getImageLoader() {
        if(imageLoaderDefault == null) {
            imageLoaderDefault = ImageLoader.getInstance();
            ImageLoaderConfiguration.Builder configBuilder = new ImageLoaderConfiguration.Builder(AppConfig.getContext());
            configBuilder.threadPoolSize(3);
            configBuilder.diskCacheFileNameGenerator(new Md5FileNameGenerator());
            configBuilder.tasksProcessingOrder(QueueProcessingType.LIFO);
            configBuilder.memoryCache(new WeakMemoryCache());
            configBuilder.defaultDisplayImageOptions(getDefaultDisplayImageOptions());
            File cacheDir;
            if(DataUtils.isExternalStorageMounted()) {
                cacheDir = new File(DataUtils.getSDCardAppPath() + "/cache");
                if(!cacheDir.exists()) {
                    boolean success = cacheDir.mkdirs();
                    if(!success) {
                        cacheDir = AppConfig.getContext().getCacheDir();
                    }
                }
            } else {
                cacheDir = AppConfig.getContext().getCacheDir();
            }

            configBuilder.diskCache(new LimitedAgeDiskCache(cacheDir, 864000L));
            imageLoaderDefault = ImageLoader.getInstance();
            imageLoaderDefault.init(configBuilder.build());
        }

        return imageLoaderDefault;
    }

    public static synchronized ImageLoader getDynamicImageLoader(int maxWidth, int maxHeight) {
        if(imageLoaderDynamic == null) {
            imageLoaderDynamic = ImageLoader.getInstance();
            ImageLoaderConfiguration.Builder configBuilder = new ImageLoaderConfiguration.Builder(AppConfig.getContext());
            configBuilder.memoryCacheExtraOptions(maxWidth, maxHeight).threadPoolSize(3).memoryCache(new WeakMemoryCache()).diskCacheFileNameGenerator(new Md5FileNameGenerator()).tasksProcessingOrder(QueueProcessingType.LIFO);
            File cacheDir;
            if(DataUtils.isExternalStorageMounted()) {
                cacheDir = new File(DataUtils.getSDCardAppPath() + "/cache");
                if(!cacheDir.exists()) {
                    boolean success = cacheDir.mkdirs();
                    if(!success) {
                        cacheDir = AppConfig.getContext().getCacheDir();
                    }
                }
            } else {
                cacheDir = AppConfig.getContext().getCacheDir();
            }

            configBuilder.diskCache(new LimitedAgeDiskCache(cacheDir, 864000L));
            imageLoaderDefault = ImageLoader.getInstance();
            imageLoaderDefault.init(configBuilder.build());
        }

        return imageLoaderDynamic;
    }

    public static DisplayImageOptions getDefaultDisplayImageOptions() {
        return (new com.nostra13.universalimageloader.core.DisplayImageOptions.Builder()).cacheInMemory(true).considerExifParams(true).resetViewBeforeLoading(true).cacheOnDisk(true).displayer(new SimpleBitmapDisplayer()).build();
    }
}
