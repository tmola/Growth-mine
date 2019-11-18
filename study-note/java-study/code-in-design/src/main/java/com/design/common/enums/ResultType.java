package com.design.common.enums;

/**
 * <p>
 *
 * </p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/11/18 20:33
 */
public enum ResultType {
    /**
     * 操作成功
     */
    success(0, "操作成功"),
    save(1, "保存成功"),
    /**
     * 操作失败
     */
    fail(-1, "失败"),
    uploadFail(-101, "文件上传失败"),
    serviceCurrentlyUnavailable(-102, "服务不可用"),
    remoteServiceError(-103, "远程服务出错"),
    missingMethod(-104, "缺少方法名参数"),
    invalidFormat(-105, "非法数据格式"),
    missingSignature(-106, "参数为空"),
    invalidSignature(-107, "非法签名"),
    missingRequiredArguments(-111, "缺少必选参数"),
    invalidArguments(-112, "非法的参数"),
    forbiddenRequest(-113, "请求被禁止"),
    parameterError(-114, "参数错误"),
    accountErros(-115, "账户名不存在，请重新输入"),
    accountRegisted(-116, "账户已注册"),
    captchaErros(-117, "验证码错误"),
    accounOrPwdtErros(-118, "账户名与密码不匹配，请重新输入"),
    accountStop(-156, "该用户已经被停用"),
    notLogin(-121, "用户未登录"),
    notPermissions(-122, "用户没有权限"),
    oldPasswordErros(-123, "原密码错误"),
    entityError(-124, "上传文件的实体对象不匹配"),
    ;

    private Integer key;
    private String value;

    ResultType(Integer key, String name) {
        this.key = key;
        this.value = name;
    }

    public Integer getKey() {
        return key;
    }


    public String getValue() {
        return value;
    }

//        public void setKey(Integer key) {
//            this.key = key;
//        }
//
//
//        public void setValue(String value) {
//            this.value = value;
//        }

}
