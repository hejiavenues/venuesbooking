package cn.cashbang.core.common.constant;

public enum SignStatus {

	/**
	 * 未注册用户
	 */
	SIGNUNREGIST(0, "未注册用户"),

	/**
	 * 已注册未生成合同
	 */
	SIGNREGIST(1, "已注册未生成合同"),

	/**
	 * 已注册未生成合同
	 */
	SIGNCONTRACT(2, "生成合同,成功"),

	/**
	 * 生成合同过程失败
	 */
	SIGNERROR(3, "生成合同过程失败"),

	;

	private String name;

	private int code;

	SignStatus(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getCode() {
		return code;
	}

	public static SignStatus getByCode(int code) {
		for (SignStatus applyStatusEnum : SignStatus.values())
			if (applyStatusEnum.getCode() == code)
				return applyStatusEnum;
		throw new RuntimeException("状态异常");
	}

}
