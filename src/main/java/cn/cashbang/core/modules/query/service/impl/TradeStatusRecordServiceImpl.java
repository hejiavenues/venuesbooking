package cn.cashbang.core.modules.query.service.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.cashbang.core.common.annotation.DataSource;
import cn.cashbang.core.common.entity.Application;
import cn.cashbang.core.common.entity.OssEntity;
import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.common.utils.CommonUtils;
import cn.cashbang.core.common.utils.DateUtils;
import cn.cashbang.core.common.utils.HttpClientUtils;
import cn.cashbang.core.common.utils.OSSManageUtil;
import cn.cashbang.core.common.utils.RandomUtils;
import cn.cashbang.core.common.utils.ShiroUtils;
import cn.cashbang.core.common.utils.SpringContextUtils;
import cn.cashbang.core.common.utils.HttpClientUtils.HttpPostParams;
import cn.cashbang.core.modules.query.entity.MTopublicLogEntity;
import cn.cashbang.core.modules.query.entity.TradeStatusRecordEntity;
import cn.cashbang.core.modules.query.manager.MTopublicLogManager;
import cn.cashbang.core.modules.query.manager.TradeStatusRecordManager;
import cn.cashbang.core.modules.query.service.TradeStatusRecordService;


/**
 * 交易记录表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2018年7月27日 上午11:13:23
 */
@Service("tradeStatusRecordService")
public class TradeStatusRecordServiceImpl implements TradeStatusRecordService {

	@Autowired
	private TradeStatusRecordManager tradeStatusRecordManager;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private MTopublicLogManager mTopublicLogManager;


	@Override
	@DataSource("slaveDataSourceRead")
	public Page<TradeStatusRecordEntity> listTradeStatusRecord(Map<String, Object> params) {
		Query query = new Query(params);
		Page<TradeStatusRecordEntity> page = new Page<>(query);
		tradeStatusRecordManager.listTradeStatusRecord(page, query);
		return page;
	}

	@Override
	public Result saveTradeStatusRecord(TradeStatusRecordEntity role) {
		int count = tradeStatusRecordManager.saveTradeStatusRecord(role);
		return CommonUtils.msg(count);
	}

	@Override
	public Result getTradeStatusRecordById(Long id) {
		TradeStatusRecordEntity tradeStatusRecord = tradeStatusRecordManager.getTradeStatusRecordById(id);
		return CommonUtils.msg(tradeStatusRecord);
	}

	@Override
	public Result updateTradeStatusRecord(TradeStatusRecordEntity tradeStatusRecord) {
		int count = tradeStatusRecordManager.updateTradeStatusRecord(tradeStatusRecord);
		return CommonUtils.msg(count);
	}
	
	public String uploadFilesToOss( InputStream img,String applyId) throws IOException{
		
		/*List<String> supportsType = new ArrayList<String>();
		supportsType.add("application/x-bmp");	//.bmp
		supportsType.add("image/jpeg");			//.jpe   .jpeg	.jpg
		supportsType.add("image/png");			//.png
		supportsType.add("application/x-jpg");	//.jpg
		supportsType.add("application/x-png");	//.png
*/		
       try {
        	
        			OssEntity item=OssEntity.getInstance(img, applyId,"jpeg");
        			item.setFilename(applyId+"_"+System.currentTimeMillis()+".jpeg");
        			String url=OSSManageUtil.uploadimageOSS(OSSManageUtil.getOSSClient(), item);
        			if(url.equals("")) {
        				throw new IOException("上传图片失败："+item.getFilename());
        			}
        			
        			 
        			return url;
                	
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            
        }
       return "";
        
	}
	
	@Override
	public Result toPublic(Map<String, Object> params,String operUser,Long operUserId) throws IOException{
		String name="";String mobile="";String base64="";
		 String applyId="";int period=0;BigDecimal money=BigDecimal.valueOf(0);
		try {
			  name=(String) params.get("name");
			  mobile=(String) params.get("mobile");
			  base64=(String) params.get("base64");
			  applyId=(String) params.get("applyId");
			  period=Integer.valueOf((String)params.get("period"));
			  money=BigDecimal.valueOf(Double.valueOf((String) params.get("money")));
			 
			 if(base64.isEmpty()||applyId.isEmpty()) {
				 return Result.warning("参数有误");
			 }
		}catch(Exception e) {
			logger.error("{}",e);
			 return Result.warning("参数有误");
		}
		
	
		 
		 
		 byte[] img;
		 InputStream input = null;
		 String url="";
		 
		 try {
			img = OSSManageUtil.compress((new Base64()).decode(base64));
			input=new ByteArrayInputStream(img);
			url=uploadFilesToOss(input,applyId);
			if(url.equals("")) {
				return Result.warning("上传图片失败!");
			}
		 } catch (Exception e) {
			logger.error("{}",e);
		 }
		 
		 logger.info("对公还款：{},{},{},{},{}",applyId,period,money,url,operUser);
		 
		 
		 
		 String  res=toPublic(applyId,period,money,url,operUser);
		 
		 MTopublicLogEntity mTopublicLog =new MTopublicLogEntity();
		 mTopublicLog.setAccountid(applyId);
		 mTopublicLog.setAmount(money);
		 mTopublicLog.setId(RandomUtils.UUID());
		 mTopublicLog.setOperdate(DateUtils.now());
		 mTopublicLog.setOperuser(operUser);
		 mTopublicLog.setPaymentid(url);
		 mTopublicLog.setPeriod(period);
		 mTopublicLog.setOperuserid(operUserId);
		 mTopublicLog.setName(name);
		 mTopublicLog.setMobile(mobile);
		 mTopublicLog.setRep(res);
		 
		 mTopublicLogManager.saveMTopublicLog(mTopublicLog);
		 String msg="对公还款失败";
		 try {
			 JSONObject json=JSONObject.parseObject(res);
			 if(json.containsKey("message")&& json.getJSONObject("message").containsKey("desc")) {
				 msg= json.getJSONObject("message").getString("desc");
				if(msg.equals("成功")) {
					return	Result.ok("对公还款成功");
				}else {
					return Result.error(msg);
				}
			 }
			 
		 } catch (Exception e) {
				logger.error("{}",e);
		 }
			 
			// return Result.ok("对公还款成功");
		 return Result.error(msg);
		 
		 
	};
	
	
	public String toPublic(String accountId,int period,BigDecimal amount,String imgurl,String userName) {
		
		
		String url = SpringContextUtils.getApplicationProperties().getServerUrlByKey("toPublic");
		
		JSONObject json=new JSONObject();
		json.put("accountId", accountId);
		json.put("amount", amount);
		json.put("operDate", System.currentTimeMillis());
		json.put("operUser", userName);
		json.put("period", period);
		json.put("paymentId", imgurl);
		
		HttpPostParams httpPostParams = HttpClientUtils.createHttpPostParams(url, json);
		logger.info(httpPostParams.toString());
		String result = HttpClientUtils.doPost(httpPostParams);
		logger.info(result);
		return result;
	}
	
	@Override
	public Result batchRemove(Long[] id) {
		int count = tradeStatusRecordManager.batchRemove(id);
		return CommonUtils.msg(id, count);
	}
	
	
	@Override
	public Result reduceRepay(Map<String, Object> params,String operUser,Long operUserId) throws IOException{
		String name="";String mobile="";
		 String applyId="";int period=0;BigDecimal money=BigDecimal.valueOf(0);
		try {
			  name=(String) params.get("name");
			  mobile=(String) params.get("mobile");
			  applyId=(String) params.get("applyId");
			  period=Integer.valueOf((String)params.get("period"));
			  money=BigDecimal.valueOf(Double.valueOf((String) params.get("money")));
			 
		}catch(Exception e) {
			logger.error("{}",e);
			 return Result.warning("参数有误");
		}
		
		 
		 
		 logger.info("减免操作：{},{},{},{}",applyId,period,money,operUser);
		 
		 String  res=reduceRepay(applyId,period,money,operUser);
		 
		 MTopublicLogEntity mTopublicLog =new MTopublicLogEntity();
		 mTopublicLog.setAccountid(applyId);
		 mTopublicLog.setAmount(money);
		 mTopublicLog.setId(RandomUtils.UUID());
		 mTopublicLog.setOperdate(DateUtils.now());
		 mTopublicLog.setOperuser(operUser);
		 mTopublicLog.setPaymentid("减免操作");
		 mTopublicLog.setPeriod(period);
		 mTopublicLog.setOperuserid(operUserId);
		 mTopublicLog.setName(name);
		 mTopublicLog.setMobile(mobile);
		 mTopublicLog.setRep(res);
		 
		 mTopublicLogManager.saveMTopublicLog(mTopublicLog);
		 String msg="减免失败";
		 try {
			 JSONObject json=JSONObject.parseObject(res);
			 if(json.containsKey("message")&& json.getJSONObject("message").containsKey("desc")) {
				 msg= json.getJSONObject("message").getString("desc");
				if(msg.equals("成功")) {
					return	Result.ok("减免成功");
				}else {
					return Result.error(msg);
				}
			 }
			 
		 } catch (Exception e) {
				logger.error("{}",e);
		 }
			 
			// return Result.ok("对公还款成功");
		 return Result.error(msg);
		 
		 
	};
	
	
	public String reduceRepay(String accountId,int period,BigDecimal amount,String userName) {
		
		
		/*String queryUrl = SpringContextUtils.getApplicationProperties().getServerUrlByKey("queryReduce");
		JSONObject query_params=new JSONObject();
		query_params.put("applyCode", accountId);
		HttpPostParams httpPostParams = HttpClientUtils.createHttpPostParams(queryUrl, query_params);

		logger.info(httpPostParams.toString())
		
		
		String result = HttpClientUtils.doPost(httpPostParams);
		logger.info(result);
		JSONObject queryRes=JSONObject.parseObject(result);;
		
		 if(queryRes.containsKey("message")&& queryRes.getJSONObject("message").containsKey("desc")) {
			 msg= json.getJSONObject("message").getString("desc");
			if(msg.equals("成功")) {
				return	Result.ok("减免成功");
			}else {
				return Result.error(msg);
			}
		 }
		*/
		String reduceUrl = SpringContextUtils.getApplicationProperties().getServerUrlByKey("reduceRepay");
		JSONObject reduce_params=new JSONObject();
		reduce_params.put("accountId", accountId);
		reduce_params.put("amount", amount);
		reduce_params.put("operDate", System.currentTimeMillis());
		reduce_params.put("operUser", userName);
		reduce_params.put("period", period);
		reduce_params.put("paymentId", "减免");
		HttpPostParams httpPostParams = HttpClientUtils.createHttpPostParams(reduceUrl, reduce_params);

		logger.info(httpPostParams.toString());
		
		
		String result = HttpClientUtils.doPost(httpPostParams);
		logger.info(result);
		return result;
		
		
	
		
	}
	@Override
	public  Result doPublicAndReduce( MultipartFile file ,String userId,String applyId,String name,String mobile,String plans,Integer period,String userName) {
		logger.info("对公减免入参:{},{},{},{},{},{}",file==null,userId,applyId,name,mobile,plans);
		
		
		JSONObject allPlan=JSONObject.parseObject(plans);
		String resStr="";
		
		String  imgUrl=null;//对公提交的图片路径，不再重复提交
		
		
		if(file!=null) {
			 try {
				 InputStream input = null;
				 input=file.getInputStream();
				 imgUrl=uploadFilesToOss(input,applyId);
				 if(imgUrl.equals("")) {
					return Result.error("上传图片失败");
				 }
			 } catch (Exception e) {
				logger.error("{}",e);
			 }
		}
		
		
		for(int i=1;i<=period;i++) {
			if(allPlan.containsKey("period"+i)) {
				
				JSONObject periodMsg=allPlan.getJSONObject("period"+i);
				BigDecimal toPublicAmount=periodMsg.getBigDecimal("toPublic");
				BigDecimal reduceAmount=periodMsg.getBigDecimal("reduce");
				
				if(reduceAmount==null) {
					reduceAmount=new BigDecimal("0");
				}
				if(toPublicAmount==null) {
					toPublicAmount=new BigDecimal("0");
				}
				
				boolean doTopublic=toPublicAmount.compareTo(BigDecimal.ZERO)!=0;
				boolean doReduce=reduceAmount.compareTo(BigDecimal.ZERO)!=0;
				
				logger.info("执行第{}期操作,是否对公:{},是否减免：{},{}",i,doTopublic,doReduce,periodMsg.toJSONString());

				boolean next=true;
				
				
				if(doReduce) {
					logger.info("执行第{}期减免操作++++++++++",i);
					next=reduceOne( name, mobile, applyId, i, reduceAmount, Long.parseLong(userId),userName);
					if(!next) {
						resStr+="执行第"+i+"期减免失败,";
						return Result.error(resStr);

					}
					if(next) {
						resStr+="执行第"+i+"期减免成功,";
					}
				}
				
				
				
				if(doTopublic) {
					logger.info("执行第{}期对公操作",i);
					
					if(imgUrl==null) {
						resStr+="执行第"+i+"期对公失败,缺少对公证明";
						return Result.error(resStr);
					}
					
					next=toPublicOne( name, mobile, applyId, i, toPublicAmount, Long.parseLong(userId),userName,imgUrl);
					
					if(!next) {
						resStr+="执行第"+i+"期对公失败,";
						return Result.error(resStr);

					}
					if(next) {
						resStr+="执行第"+i+"期对公成功,";
					}
				}
			
			}
		}
		
		if(resStr.equals("")) {
			return Result.error("执行失败!");
		}
		return Result.ok("执行成功");
		
	}
	
	private boolean reduceOne(String name,String mobile,String applyId,int period,BigDecimal money,Long userId,String userName) {
		
		 
		 logger.info("减免操作：{},{},{},{}",applyId,period,money,name);
		 
		 String  res=reduceRepay(applyId,period,money,name);
		 
		 MTopublicLogEntity mTopublicLog =new MTopublicLogEntity();
		 mTopublicLog.setAccountid(applyId);
		 mTopublicLog.setAmount(money);
		 mTopublicLog.setId(RandomUtils.UUID());
		 mTopublicLog.setOperdate(DateUtils.now());
		 mTopublicLog.setOperuser(userName);
		 mTopublicLog.setPaymentid("减免操作");
		 mTopublicLog.setPeriod(period);
		 mTopublicLog.setOperuserid(userId);
		 mTopublicLog.setName(name);
		 mTopublicLog.setMobile(mobile);
		 mTopublicLog.setRep(res);
		 
		 mTopublicLogManager.saveMTopublicLog(mTopublicLog);
		 String msg="减免失败";
		 try {
			 JSONObject json=JSONObject.parseObject(res);
			 if(json.containsKey("message")&& json.getJSONObject("message").containsKey("desc")) {
				 msg= json.getJSONObject("message").getString("desc");
				if(msg.equals("成功")) {
					return	true;
				}else {
					return false;
				}
			 }
			 
		 } catch (Exception e) {
				logger.error("{}",e);
		 }
		 
		 return false;
	}
	
	/**
	 * 单期对公
	 * @param params
	 * @param operUser
	 * @param operUserId
	 * @return
	 * @throws IOException
	 */
	private  boolean toPublicOne(String name,String mobile,String applyId,int period,BigDecimal money,Long userId,String userName,String imgUrl) {
		
		 

		 
		 logger.info("对公还款：{},{},{},{},{}",applyId,period,money,imgUrl,name);
		 
		 
		 
		 String  res=toPublic(applyId,period,money,imgUrl,name);
		 
		 MTopublicLogEntity mTopublicLog =new MTopublicLogEntity();
		 mTopublicLog.setAccountid(applyId);
		 mTopublicLog.setAmount(money);
		 mTopublicLog.setId(RandomUtils.UUID());
		 mTopublicLog.setOperdate(DateUtils.now());
		 mTopublicLog.setOperuser(userName);
		 mTopublicLog.setPaymentid(imgUrl);
		 mTopublicLog.setPeriod(period);
		 mTopublicLog.setOperuserid(userId);
		 mTopublicLog.setName(name);
		 mTopublicLog.setMobile(mobile);
		 mTopublicLog.setRep(res);
		 
		 mTopublicLogManager.saveMTopublicLog(mTopublicLog);
		 String msg="对公还款失败";
		 try {
			 JSONObject json=JSONObject.parseObject(res);
			 if(json.containsKey("message")&& json.getJSONObject("message").containsKey("desc")) {
				 msg= json.getJSONObject("message").getString("desc");
				if(msg.equals("成功")) {
					return	true;
				}else {
					return false;
				}
			 }
			 
		 } catch (Exception e) {
				logger.error("{}",e);
		 }
			 
			// return Result.ok("对公还款成功");
		 return false;
		 
		 
	};
	
}
