package cn.cashbang.core.common.constant;

import com.alibaba.fastjson.JSONObject;

import cn.cashbang.core.common.utils.ShiroUtils;

/**
 * 系统级静态变量
 *
 * @author weimi
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月8日 下午1:35:38
 */
public class SystemMsg {
	
    /**
     * 系统保留字段
     * @author weimi
     *
     */
    public static class sys{
   /* 	public static final String channel=ShiroUtils.getUserEntity().getChannel();*/
  	}
    /**
     * 系统保留角色字段相关信息
     * @author weimi
     *
     */
    public static class sysRole{
    		//admin
    		public static final String ADMIN = "Admin";
    		
    		
      		//风控经理
      		public static final String RISKMANAGER="RiskManager";
      		public static final String RISKMANAGERNAME="风控经理";
      		
      		//运营经理
      		public static final String OPERATIONMANAGER="OperationManager";
      		public static final String OPERATIONMANAGERNAME="运营经理";
      		
      		//电核人员
      		public static final String 	TELEPHONEDECISION="TelephoneDecision";
      		public static final String 	TELEPHONEDECISIONNAME="电核人员";

      		
      		//电核质检
      		public static final String DECISIONINSPECT="DecisionInspect";
      		public static final String DECISIONINSPECTNAME="电核质检";
      		
      		//渠道管理员
      		public static final String 	CHANNELADMIN="_Admin";
      		public static final String 	CHANNELADMINNAME="_管理员";
      		
      		
      		//催收
      		/**
      		 * 催收经理
      		 */
      		public static final String 	collectionManager="collectionManager";
      		public static final String 	collectionManagerNAME="催收经理";

      		/**
      		 * 催收组长
      		 */
      		public static final String collectionGroupLeader="collectionGroupLeader";
      		public static final String collectionGroupLeaderNAME="催收组长";
      		
      		/**
      		 * 催收员S1
      		 */
      		public static final String 	collector="collector";
      		public static final String 	collectorNAME="催收员";
      		
      		
      		/**
      		 * 提醒专员
      		 */
      		public static final String 	reminder="reminder";
      		public static final String 	reminderNAME="提醒专员";
     /* 		//催收员S1
      		public static final String 	collectionS2="collectionS2";
      		public static final String 	collectionS2NAME="催收员S2";
      		
      		//催收员S1
      		public static final String 	collectionS3="collectionS3";
      		public static final String 	collectionS3NAME="催收员S3";
      		
      		//催收员S1
      		public static final String 	collectionS4="collectionS4";
      		public static final String 	collectionS4NAME="催收员S4";

      		
      		//渠道管理员
      		public static final String 	collectionM1="collectionM1";
      		public static final String 	collectionM1NAME="催收员M1";

      		
      		//催收员M2
      		public static final String collectionM2="collectionM2";
      		public static final String collectionM2NAME="催收员M2";
      		
      		//催收员M3+
      		public static final String 	collectionM3="collectionM3+";
      		public static final String 	collectionM3NAME="催收员M3+";*/
      		
  		
      		
      		public static final JSONObject Rlist() {
      			
      			JSONObject json=new JSONObject();
      			json.put("Admin", "管理员");
      			json.put("ChannelAdmin", "渠道管理员");
      			json.put("RiskManager", "风控经理");
      			json.put("OperationManager", "运营经理");
      			json.put("TelephoneDecision", "电核人员");
      			json.put("DecisionInspect", "电核质检");
      			json.put("ChannelAdmin", "渠道管理员");
      			
      			json.put("collectionManager", "催收经理");
      			json.put("collectionGroupLeader", "催收组长");
      			json.put("collector", "催收员");
      			
      			json.put("reminder", "提醒专员");

      			/*json.put("collectionS2", "催收员S2");
      			json.put("collectionS3", "催收员S3");
      			json.put("collectionS4", "催收员S4");
      			json.put("collectionM1", "催收员M1");
      			json.put("collectionM2", "催收员M2");
      			json.put("collectionM3+", "催收员M3+");*/
      			
      			return json ;
      		
      		};
      		public static final String ADDERRMSG="角色标识为系统保留标识,无法添加,请尝试其他标识!";
      		public static final String UPDATEERRMSG="角色标识为系统保留标识,无法修改!";
      		public static final String DETELEERRMSG=" 为系统保留标识,无法删除!";
      		public static final String REMARK="【系统内置生成】"; 
      		
    }
    
    /**
     * 系统默认渠道user生成规则
     * @author weimi
     *
     */
    public static class sysUser{
    		
    		
      		//风控经理
      		public static final String RISKMANAGER="risk";
      		public static final String RISKMANAGERNAME="风控经理";
      		
      		//运营经理
      		public static final String OPERATIONMANAGER="op";
      		public static final String OPERATIONMANAGERNAME="运营经理";
      		
      		//电核人员
      		public static final String 	TELEPHONEDECISION="td";
      		public static final String 	TELEPHONEDECISIONNAME="电核";

      		
      		//电核质检
      		public static final String DECISIONINSPECT="inspect";
      		public static final String DECISIONINSPECTNAME="电核质检";
      		
      		//渠道管理员
      		public static final String 	CHANNELADMIN="admin";
      		public static final String 	CHANNELADMINNAME="管理员";
      		
      		public static final String PASSWORD="123456!";
      		/**
      		 * 默认状态
      		 */
      		public static final Integer STATUS=1;
      		public static final String SUCCESSMSG="初始化管理员成功";

      		
      		public static final String REMARK="【系统内置生成】"; 
      		
    }
    
    
    
    public static class sysOrg{
  		public static final String SAAS = "saas";
  		
  		public static final String RISK="risk";
  		public static final String RISKNAME="风控部门";

  		public static final String OPERATION="operation";
  		public static final String OPERATIONNAME="运营部门";
  		
  		
  		public static final String COLLECTION="collection";
  		public static final String COLLECTIONNAME="催收部门";
  		
  		/**
  		 * 默认状态
  		 */
  		public static final Integer STATUS=1;
  		
  		/**
  		 * 默认排序
  		 */
  		public static final Integer ORDER=1;

  		
    }
    /**
     * 短信成功状态
     */
    public static final int smsSuccess=200;
    /**
     * 系统常用部分信息
     * @author weimi
     *
     */
  /*  public static class sysMsg{
    	public 
    	
    }*/
}
