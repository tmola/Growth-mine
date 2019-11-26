package com.design.common.exception;

/**
 *
 */
public enum ReturnMessage {
	/**
	 * 操作成功
	 */
	success("0","成功"),
	/**
	 * 操作失败
	 */
	fail("-1","失败"),
	/**
	 * 文件上传失败
	 */
	uploadFail("101", "文件上传失败"),
	/**
	 * 服务不可用
	 */
	serviceCurrentlyUnavailable("102", "服务不可用"),
	/**
	 * 远程服务出错
	 */
	remoteServiceError("103", "远程服务出错"),
	/**
	 * 缺少方法名参数
	 */
	missingMethod("104", "缺少方法名参数"),

	/**
	 * 非法数据格式 
	 */
	invalidFormat("105", "非法数据格式"),
	/**
	 * 缺少签名参数
	 */
	missingSignature("106", "参数为空"),
	/**
	 * 非法签名
	 */
	invalidSignature("107", "非法签名"),
	/**
	 * 缺少必选参数
	 */
	missingRequiredArguments("111", "缺少必选参数"),
	/**
	 * 非法的参数
	 */
	invalidArguments("112", "非法的参数"),
	/**
	 * 请求被禁止
	 */
	forbiddenRequest("113", "请求被禁止"),
	/**
	 * 参数错误
	 */
	parameterError("114", "参数错误")	,
	/**
	 * 账户不存在
	 */
	accountErros("115","账户名不存在，请重新输入"),
	/**
	 * 账户已注册
	 */
	accountRegisted("116","账户已注册"),
	/**
	 * 验证码错误
	 */
	captchaErros("117","验证码错误"),
	/**
	 * 密码错误或用户不存在
	 */
	accounOrPwdtErros("118","账户名与密码不匹配，请重新输入"),
	/**
	 * 该用户已经被停用
	 */
	accountStop("156","该用户已经被停用"),

	/**
	 * 用户未登录
	 */
	notLogin ("121","用户未登录"),

	/**
	 * 用户没有权限
	 */
	notPermissions("122","用户没有权限"),

	/**
	 * 原密码错误
	 */
	oldPasswordErros("123","原密码错误"),

	/**
	 * 实体对象不匹配
	 */
	entityError("124","上传文件的实体对象不匹配"),

	/**
	 * 违反约束
	 */
	constrintError("124","违反约束"),
	;

	/**
	 * key值
	 */
	private String key;
	
	/**
	 * value值
	 */
	private String typeName;
	
	private ReturnMessage(String key, String typeName) {
		this.key = key;
		this.typeName = typeName;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}
