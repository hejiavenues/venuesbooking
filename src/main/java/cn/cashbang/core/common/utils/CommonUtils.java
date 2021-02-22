package cn.cashbang.core.common.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import cn.cashbang.core.common.constant.MsgConstant;
import cn.cashbang.core.common.constant.SystemConstant;
import cn.cashbang.core.common.entity.Result;

/**
 * 通用工具类
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月12日 下午1:34:44
 */
public class CommonUtils {

	/**
	 * 对象是否为空
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isNullOrEmpty(Object obj) {
		if (obj == null) {
			return true;
		}
		return false;
	}

	/**
	 * 判断整数是否大于零
	 * 
	 * @param number
	 * @return
	 */
	public static boolean isIntThanZero(int number) {
		if (number > 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 新增，修改提示
	 * @param count
	 * @return
	 */
	public static Result msg(int count) {
		if(isIntThanZero(count)){
			return Result.ok(MsgConstant.MSG_OPERATION_SUCCESS);
		}
		return Result.error(MsgConstant.MSG_OPERATION_FAILED);
	}
	
	/**
	 * 查询详情提示
	 * @param data
	 * @return
	 */
	public static Result msg(Object data) {
		if(isNullOrEmpty(data)){
			return Result.error(MsgConstant.MSG_INIT_FORM);
		}
		return Result.ok().put(SystemConstant.DATA_ROWS, data);
	}
	
	/**
	 * 返回数据
	 * @param data
	 * @return
	 */
	public static Result msgNotCheckNull(Object data) {
		return Result.ok().put(SystemConstant.DATA_ROWS, data);
	}
	
	/**
	 * 删除提示
	 * @param total
	 * @param count
	 * @return
	 */
	public static Result msg(Object[] total, int count) {
		if(total.length == count){
			return Result.ok(MsgConstant.MSG_OPERATION_SUCCESS);
		}else{
			if(isIntThanZero(count)){
				return Result.error(MsgConstant.removeFailed(total.length, count));
			}else{
				return Result.error(MsgConstant.MSG_OPERATION_FAILED);
			}
		}
	}

	/**
	 * 生成 主键
	 * @return
	 */
	public static String createUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	 public static String jsonArraySort(String jsonArrStr) {
	        JSONArray jsonArr = JSON.parseArray(jsonArrStr);
	        JSONArray sortedJsonArray = new JSONArray();
	        List<JSONObject> jsonValues = new ArrayList<JSONObject>();
	        for (int i = 0; i < jsonArr.size(); i++) {
	            jsonValues.add(jsonArr.getJSONObject(i));
	        }
	        Collections.sort(jsonValues, new Comparator<JSONObject>() {
	            // You can change "Name" with "ID" if you want to sort by ID
	            private static final String KEY_NAME = "name";

	            @Override
	            public int compare(JSONObject a, JSONObject b) {
	                String valA = new String();
	                String valB = new String();
	                try {
	                    // 这里是a、b需要处理的业务，需要根据你的规则进行修改。
	                    String aStr = a.getString(KEY_NAME);
	                    valA = aStr.replaceAll("-", "");
	                    String bStr = b.getString(KEY_NAME);
	                    valB = bStr.replaceAll("-", "");
	                } catch (JSONException e) {
	                    // do something
	                }
	                return -valB.compareTo(valA);
	                // if you want to change the sort order, simply use the following:
	                // return -valA.compareTo(valB);
	            }
	        });
	        for (int i = 0; i < jsonArr.size(); i++) {
	            sortedJsonArray.add(jsonValues.get(i));
	        }
	        return sortedJsonArray.toString();
	    }
	 
	 public static Boolean zipWidthHeightImageFile(String oldPath, String copyPath, int width, int height,
             float quality) {
		 if(width == 0) {
			 width = 800;
		 }
		 if(height == 0) {
			 width = 256;
		 }
		 if(quality == 0) {
			 quality = 6f;
		 }
		 Boolean sta = false;
		 File oldFile = new File(oldPath);
		 File newFile = new File(copyPath);
		 if (oldFile == null) {
			 return null;
		 }
		 String newImage = null;
		 try {
			 /** 对服务器上的临时文件进行处理 */
			 Image srcFile = ImageIO.read(oldFile);
			 int w = srcFile.getWidth(null);
			 System.out.println(w);
			 int h = srcFile.getHeight(null);
			 System.out.println(h);

			 /** 宽,高设定 */
			 BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			 tag.getGraphics().drawImage(srcFile, 0, 0, width, height, null);
			 //String filePrex = oldFile.substring(0, oldFile.indexOf('.'));
			 /** 压缩后的文件名 */
			 //newImage = filePrex + smallIcon+ oldFile.substring(filePrex.length());
			 
			 /** 压缩之后临时存放位置 */
			 FileOutputStream out = new FileOutputStream(newFile);

			 JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			 JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);
			 /** 压缩质量 */
			 jep.setQuality(quality, true);
			 encoder.encode(tag, jep);
			 out.close();
			 sta = true;
		 } catch (Exception e) {
			 e.printStackTrace();
			 sta = false;
		 }
		 return sta;
	 }
	 
	 public static void main(String args[]) throws IOException { 
	        System.out.println(CommonUtils.zipWidthHeightImageFile("E:/piture01/71ba7d659fed43c988bd243cdefa7185-2021_02_19_15_17_43_124.png","E:/piture01/c2.png", 800, 256, 6f)); 
	  }
}
