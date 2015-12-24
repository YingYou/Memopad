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
                    // ����ÿ������ͼƬ����󳤺Ϳ�
                    .threadPoolSize(3)
                    .diskCache(new UnlimitedDiscCache(cacheDir))
                            // �̳߳صĴ�С �����ʵĬ�Ͼ���3
                    .memoryCacheSize(2 * 1024)
                            // ���û��������ֽ�
                    .denyCacheImageMultipleSizesInMemory()
                            // ������ʾ��ͬ��С��ͬһ��ͼƬ
                    .imageDownloader(
                            new BaseImageDownloader(getApplicationContext(), 5 * 1000, 30 * 1000)) // connectTimeout
                            // s)��ʱʱ��
                    .build();

            ImageLoader.getInstance().init(config);

            FileUtil.createDirImagepath(getApplicationContext());

        }


        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.rc_ic_picture) //����ͼƬ�������ڼ���ʾ��ͼƬ
                .showImageForEmptyUri(R.drawable.rc_ic_picture)//����ͼƬUriΪ�ջ��Ǵ����ʱ����ʾ��ͼƬ
                .showImageOnFail(R.drawable.rc_ic_picture)  //����ͼƬ����/��������д���ʱ����ʾ��ͼƬ
                .cacheInMemory(true)//�������ص�ͼƬ�Ƿ񻺴����ڴ���
                .cacheOnDisc(true)//�������ص�ͼƬ�Ƿ񻺴���SD����
                .considerExifParams(true)  //�Ƿ���JPEGͼ��EXIF��������ת����ת��

                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)//����ͼƬ����εı��뷽ʽ��ʾ
                .bitmapConfig(Bitmap.Config.RGB_565)//����ͼƬ�Ľ�������//
//                .decodingOptions(android.graphics.BitmapFactory.Options decodingOptions)//����ͼƬ�Ľ�������
//.delayBeforeLoading(int delayInMillis)//int delayInMillisΪ�����õ�����ǰ���ӳ�ʱ��
//����ͼƬ���뻺��ǰ����bitmap��������
//.preProcessor(BitmapProcessor preProcessor)
                .resetViewBeforeLoading(true)//����ͼƬ������ǰ�Ƿ����ã���λ
//                .displayer(new RoundedBitmapDisplayer(20))//�Ƿ�����ΪԲ�ǣ�����Ϊ����
//                .displayer(new FadeInBitmapDisplayer(100))//�Ƿ�ͼƬ���غú���Ķ���ʱ��
                .build();//�������

    }
}
