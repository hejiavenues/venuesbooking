package cn.cashbang.core.common.constant;

public enum ContractStatus {

	
	CONTRACTSUCCESS(1,"合同记录成功标志"),
	CONTRACTFAIL(2,"合同记录失败标志"),
    ;

    private String name;

    private int code;



    ContractStatus(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }

//    public String getCodeString() {
//        return String.valueOf(code);
//    }

    public static ContractStatus getByCode(int code) {
        for (ContractStatus applyStatusEnum : ContractStatus.values())
            if(applyStatusEnum.getCode() == code)
                return applyStatusEnum;
        throw new RuntimeException("状态异常");
    }

}
