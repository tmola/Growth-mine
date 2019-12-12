package com.uniform.common.enums;

import lombok.Getter;
import org.springframework.dao.DataIntegrityViolationException;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/12/4
 */
@Getter
public enum ResultCode {

    success(0, "操作成功"),


    faild(-1, "操作失败"),

    parameterError(100, "参数错误"),
    missingRequiredArguments(100, "缺少必选参数"),
    invalidArguments(100, "非法的参数"),
    argumentsIsBlank(100, "参数值为空"),
    invalidFormat(100, "非法数据格式"),

    accountErros(200, "账户名不存在，请重新输入"),
    accountRegisted(200, "账户已注册"),
    accountNotLogin(200, "账户未登录"),
    accountNotPermissions(200, "账户没有权限"),
    accounOrPwdtErros(200, "账户名与密码不匹配，请重新输入"),
    accountStop(200, "该用户已经被停用"),
    captchaErros(200, "验证码错误"),

    invalidSignature(201, "非法签名"),
    forbiddenRequest(201, "请求被禁止"),

    serviceError(202, "服务错误"),
    serviceException(202, "服务异常"),

    fileNotExitis(300, "文件不存在"),
    fileUploadFaild(300, "文件上传失败"),
    fileDownloadFaild(300, "文件下载失败"),
    fileTransInterrupt(300, "文件传输中断"),

    databaseDataIntegrityViolation(256, "违反唯一性约束"),
    databasDuplicateKey(257,"违反主键约束")
    ;
    public Integer code;
    public String info;
    ResultCode(Integer code, String info){
        this.code = code;
        this.info = info;
    }
}
