package cn.cashbang.core.common.constant;

import com.alibaba.fastjson.JSONObject;

/**
 * 系统级静态变量
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月8日 下午1:35:38
 */
public class SystemConstant {
	
	/**
	 * 超级管理员ID
	 */
	public static final long SUPER_ADMIN = 1;
	
	
	/**
	 * 超级管理员ID
	 */
	public static final String SUPER_ADMIN_NAME = "admin";
	
	
	/**
	 * 超级管理员ID
	 */
	public static final String SUPER_ADMIN_NAME_1 = "super";
	
	/**
	 * 超级管理员ID
	 */
	public static final String SUPER_ADMIN_NAME_2 = "wmh";
	
	
	
	/**
	 * 数据标识
	 */
	public static final String DATA_ROWS = "rows";
	
	/**
	 * 未授权错误代码
	 */
	public static final int UNAUTHORIZATION_CODE = 401;
	
	/**
	 * 菜单类型
	 *
	 * @author Tiny
	 * @email: xxx@cashbang.cn
	 * @url: www.cashbang.cn
	 * @date 2017年8月8日 下午1:36:27
	 */
    public enum MenuType {
        /**
         * 目录
         */
    	CATALOG(0),
        /**
         * 菜单
         */
        MENU(1),
        /**
         * 按钮
         */
        BUTTON(2);

        private int value;

        private MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
    
    /**
     * 定时任务状态
     *
     * @author Tiny
     * @email: xxx@cashbang.cn
     * @url: www.cashbang.cn
     * @date 2017年8月8日 下午1:36:17
     */
    public enum ScheduleStatus {
        /**
         * 正常
         */
    	NORMAL(1),
        /**
         * 暂停
         */
    	PAUSE(0);

        private int value;

        ScheduleStatus(int value) {
            this.value = value;
        }
        
        public int getValue() {
            return value;
        }
    }
    
    /**
     * 通用字典
     *
     * @author Tiny
     * @email xxx@cashbang.cn
     * @url www.cashbang.cn
     * @date 2017年8月15日 下午7:29:02
     */
    public enum MacroType {
    	
    	/**
    	 * 类型
    	 */
    	TYPE(0),
    	
    	/**
    	 * 参数
    	 */
    	PARAM(1);
    	
    	private int value;
    	
    	MacroType(int value) {
            this.value = value;
        }
        
        public int getValue() {
            return value;
        }
    	
    }
    
    /**
     * 通用变量，表示可用、禁用、显示、隐藏
     *
     * @author Tiny
     * @email xxx@cashbang.cn
     * @url www.cashbang.cn
     * @date 2017年8月15日 下午7:31:49
     */
    public enum StatusType {
    	
    	/**
    	 * 禁用，隐藏
    	 */
    	DISABLE(0),
    	
    	/**
    	 * 可用，显示
    	 */
    	ENABLE(1),
    	
    	/**
    	 * 显示
    	 */
    	SHOW(1),
    	
    	/**
    	 * 隐藏
    	 */
    	HIDDEN(0);
    	
    	private int value;
    	
    	StatusType(int value) {
            this.value = value;
        }
        
        public int getValue() {
            return value;
        }
    	
    }

	/**
	 * 电子签章渠道
	 */
	public static class channel{
		public static final String FDD = "fdd";
		public static final int STATUSONE = 0;
	}
    
    /**
     * 审批通过状态
     * @author weimi
     *
     */
    public static class mRiskSuccesssStatus{

		public static final int PASS = 0;//审批通过
		public static final int REJECT = 1;//审批拒绝
		public static final int WAIT_ASSIGN = 8;//等待分审(没有设置队列人员，自动分配失败时，为此状态)
		public static final int WAIT_LEVEL_1 = 9;//等待1级审批(分给1级了）
    	public static final int WAIT_LEVEL_2 = 10;//等待2级审批(分给2级了)
		public static final int WAIT_LEVEL_3 = 11;//等待3级审批(分给3级了)
		//...

    	public static final String passToNextStatus(Long currentQueue, int maxQueue) {
    		/**
    		 * 最大队列状态
    		 */
    		if(currentQueue == maxQueue) {
    			return toString(PASS);
    		}

			if(currentQueue >= 1) {
    			return toString(currentQueue.intValue()+WAIT_LEVEL_1);
			}

    		return null;
  		};

    	static String toString(int val){
			return String.valueOf(val);
		}
    }
    
    /**
     * 审批中客户主动放弃贷款，请求bizServer状态
     * 
     */
    public static final Integer GIVEUPLOANTOBIZSERVER=0;
    
}
