package com.test.memopad.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;



/**
 * 文件工具类
* @ClassName: FileUtil 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhyang 
* @date 2014-7-27 下午11:16:53 
*
 */
public class FileUtil {

	public static String getPath(Context context){
		
		if (Environment.getExternalStorageState().equals( Environment.MEDIA_MOUNTED)) {// 优先保存到SD卡中
			    return Environment.getExternalStorageDirectory() + File.separator + "zhiguanjia"+ File.separator ;
			 } else {// 如果SD卡不存在，就保存到本应用的目录下 
			   return context.getFilesDir().getAbsolutePath()+ File.separator + "zhiguanjia"+ File.separator ;
		}
	}

    public static void createDirImagepath(Context context){
        File fileDir = new File(getPath(context)+"Image/");
        if (!fileDir.exists())
         fileDir.mkdirs();

    }
	
	public static boolean writeFile(StringBuffer sb,String fileName,String path) {
		String string;
		DataOutputStream bfo = null;
		File fileDir = new File(path);
		if(!fileDir.exists())
			fileDir.mkdirs();

		try {
			string = sb.substring(0);
			bfo = new DataOutputStream(new FileOutputStream(path+fileName));
			bfo.write(string.getBytes("gbk"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return true;
	}

//    public static Bitmap readFileToBit(Context context,String path)
//    {
//
//        Log.d("TAG", "readFile---path:" + path);
//        Bitmap bitmap = null;
//
//        if (path != null){
//            bitmap = BitmapFactory.decodeFile(path);
//            return ImageUtils.comp(bitmap);
//        }
//
//        return null;
//    }
	
	public static void writeBitmapFile(Context context ,Bitmap bmp,String pathStr){



		if(pathStr!=null){
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(pathStr);
				if(bmp!=null&&fos!=null){
                    bmp.compress(CompressFormat.JPEG, 100, fos);
				}
				fos.close();
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
	}
	
	public static String readFile(File file)  throws Exception
	{ 
        StringBuffer sb = new StringBuffer();
        try { 
            FileInputStream fis = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis,"UTF-8"));
            String line = null;
            while ((line = br.readLine()) != null) { 
                sb.append(line); 
            } 
            fis.close(); 
        } catch (FileNotFoundException e) {
            e.printStackTrace(); 
        } catch (IOException e) {
            e.printStackTrace(); 
        } 
        if(sb.toString().trim().length()<=0) return null;
        return sb.toString(); 
    }

    /** * 删除方法 这里只会删除某个文件夹下的文件 * * @param directory */
    public static void deleteFilesByDirectory(File directory) {
//    	LogUtil.e("TAG", "deleteFiles--directory:"+directory);
//    	LogUtil.e("TAG", "deleteFiles--exists:"+directory.exists());
//    	LogUtil.e("TAG", "deleteFiles--isDirectory:"+directory.isDirectory());
        if (directory != null && directory.exists() && directory.isDirectory()) {
            for (File item : directory.listFiles()) {
                Log.e("TAG", "deleteFiles--item:"+item);
                Log.e("TAG", "deleteFiles--item.isdir:"+item.isDirectory());
                if (item != null && item.isDirectory()) {
                    for (File itemsec : item.listFiles()) {
                        itemsec.delete();
                    }
                }
                item.delete();
            }
        }
    }

}
