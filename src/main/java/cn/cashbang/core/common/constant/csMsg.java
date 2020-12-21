package cn.cashbang.core.common.constant;

import com.alibaba.fastjson.JSONObject;

import cn.cashbang.core.common.utils.SpringContextUtils;

/**
 * 系统级静态变量
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月8日 下午1:35:38
 */
public class csMsg {
	/**
	 * plan 借款还款明细中 期限逾期标志
	 */
	public static final int PLANOVERDUE=2;
	
	
	/**
	 * redis 提醒规则key
	 */
	public static final String REDIS_CSREMINDRULE="csRemindRule";
	
	/**
	 * api返回成功状态
	 */
	public static final String APISUCCESSSTATUS="000000";
	
	
	/**
	 * accountLoan表逾期标志
	 */
	public static final int account_loan_overStatus=6;
	
	
	/**
	 * case 需要留案标志
	 */
	public static final int NEEDSTAY=1;
	
	/**
	 * case 不需要留案标志
	 */
	public static final int NONEEDSTAY=1;
	
	
	/**
	 * case每日处理标志 true已经处理,false未处理
	 */
	
	public static final boolean initUpdateStatus=false;
	
	
	
	/**
	 * case每日处理标志 true已经处理
	 */
	public static final boolean UpdateStatus=true;
	
	/**
	 * org_id ,product_id 初始化值-1 标识未设置
	 */
	public static final Long INITSTATE=(long) -1;

    
    /**
     * 催收状态
     * @author weimi
     *
     */
    public static class Status{
    	
    	/**
    	 * 成功入催
    	 */
		public static final int INNER = 0;
		
		
		/**
    	 * record表初始状态
    	 */
		public static final boolean recordInitStatus = false;
		
		/**
		 * 入催出现错误
		 */
		public static final int INNERERR = 2;
		
		/**
		 * 出催
		 */
		public static final int OUT = 1;//
		
		/**
		 * 停催
		 *//*
		public static final int END = 2;*/
		
    }
    
    
    /**
     * 申请类
     * @author weimi
     *
     */
    public static class apply{
    	
    	/**
    	 * 对公还款
    	 */
		public static final String TOPUBLIC="topublic";
		
		/**
    	 * 减免
    	 */
		public static final String REDUCE="reduce";
		
		/**
		 * c_case审批中，不可再提交
		 */
		public static final int  CCASE_Approval=1;
		

		/**
		 * c_case，可再提交
		 */
		public static final int  CCASE_init=0;
		
		/**
		 * c_apply 审批中
		 */
		public static final String CAPPLY_initStatus="0";
		
		/**
		 * c_apply 审批通过
		 */
		public static final String CAPPLY_successStatus="1";
		/**
		 * c_apply 审批拒绝
		 */
		public static final String CAPPLY_failStatus="2";
		
    }
    
    /**
     * 催记类型
     * @author weimi
     *
     */
    public static class recordType{
    	
    	/**
    	 * 电催类
    	 */
		public static final String MOBILE="mobile";
		
		/**
    	 * 结论类
    	 */
		public static final String LABEL="label";
		
		
		/**
    	 *提醒类
    	 */
		public static final String REMIND="remind";
		
		
		/**
    	 *客户承诺类
    	 */
		public static final String PROMISE="promise";
		
		/**
    	 *短信类
    	 */
		public static final String SMS="sms";
		
		
		
		
    }
    
    /**
     * 日志类型,（1，系统入催，2，出催，3，停催，4，人为调动）
     * @author weimi
     *
     */
    public static class Log{
    	
    	/**
    	 * 系统入催
    	 */
		public static final int INNER =1;
		
		
		/**
    	 * 由于对公或减免造成的单独入催
    	 */
		//public static final int ONEINNER =5;
		
		/**
		 * 人为调动,已被调走
		 */
		public static final int banish = 5;
		/**
		 * 人为调动
		 */
		public static final int PEOPLE = 4;
		
		/**
		 * 出催
		 */
		public static final int OUT = 2;//
		
		/**
		 * 停催
		 */
		public static final int END = 3;
		
    }
    
    /**
     * 催收级别
     * @author weimi
     *
     */
    public static class Level{
    	
    	/**
    	 * s1
    	 */
		public static final String S1 = "S1";
		/**
    	 * s2
    	 */
		public static final String  S2 = "S2";
		
		/**
    	 * s3
    	 */
		public static final String  S3 = "S3";
		/**
    	 * s4
    	 */
		public static final String  S4 = "S4";
		/**
    	 * m2
    	 */
		public static final String  M2 = "M2";
		
		/**
    	 * m3
    	 */
		public static final String  M3 = "M3";
		/**
    	 * m3+
    	 */
		public static final String  M3j = "M3+";
    }
    /**
     * 催收级别划定标志
     * @author weimi
     *
     */
    public static class days{
    	
    	/**
    	 * s1结束日期
    	 */
		public static final int S1 =Integer.parseInt(SpringContextUtils.getApplicationProperties().getCs().get("S1END"));
		/**
    	 * s2结束日期
    	 */
		public static final int  S2 =Integer.parseInt(SpringContextUtils.getApplicationProperties().getCs().get("S2END"));
		
		/**
    	 * s3结束日期
    	 */
		public static final int  S3 =Integer.parseInt(SpringContextUtils.getApplicationProperties().getCs().get("S3END"));
		/**
    	 * s4结束日期
    	 */
		public static final int  S4 =Integer.parseInt(SpringContextUtils.getApplicationProperties().getCs().get("S4END"));
		/**
    	 * m2结束日期
    	 */
		public static final int  M2 = 60;
		
		/**
    	 * m3结束日期
    	 */
		public static final int  M3 = 90;
    }
    
    
    
    
}
