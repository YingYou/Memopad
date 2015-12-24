package com.test.memopad;

import android.app.Application;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.test.memopad.utils.FileUtil;
import com.test.memopad.view.PaintIt.PathNode;

import java.io.File;

/**
 * Created by yw on 15/9/24.
 */
public class MemoPadApplication extends PathNode {

    public static DisplayImageOptions options;

    @Override
    public void onCreate() {
        super.onCreate();

        if (!ImageLoader.getInstance().isInited()) {
//            ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext()).build();

            File cacheDir = StorageUtils.getOwnCacheDirectory(getApplicationContext(),
                    "zhiguanjia/Cache");

            ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                    getApplicationContext()).memoryCacheExtraOptions(800, 760)
                    // 保存每个缓存图片的最大长和宽
                    .threadPoolSize(3)
                    .diskCache(new UnlimitedDiscCache(cacheDir))
                            // 线程池的大小 这个其实默认就是3
                    .memoryCacheSize(2 * 1024)
                            // 设置缓存的最大字节
                    .denyCacheImageMultipleSizesInMemory()
                            // 缓存显示不同大小的同一张图片
                    .imageDownloader(
                            new BaseImageDownloader(getApplicationContext(), 5 * 1000, 30 * 1000)) // connectTimeout
                            // s)超时时间
                    .build();

            ImageLoader.getInstance().init(config);

            FileUtil.createDirImagepath(getApplicationContext());

        }


        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.rc_ic_picture) //设置图片在下载期间显示的图片
                .showImageForEmptyUri(R.drawable.rc_ic_picture)//设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.drawable.rc_ic_picture)  //设置图片加载/解码过程中错误时候显示的图片
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                .cacheOnDisc(true)//设置下载的图片是否缓存在SD卡中
                .considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）

                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)//设置图片以如何的编码方式显示
                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型//
//                .decodingOptions(android.graphics.BitmapFactory.Options decodingOptions)//设置图片的解码配置
//.delayBeforeLoading(int delayInMillis)//int delayInMillis为你设置的下载前的延迟时间
//设置图片加入缓存前，对bitmap进行设置
//.preProcessor(BitmapProcessor preProcessor)
                .resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
//                .displayer(new RoundedBitmapDisplayer(20))//是否设置为圆角，弧度为多少
//                .displayer(new FadeInBitmapDisplayer(100))//是否图片加载好后渐入的动画时间
                .build();//构建完成

    }
}
