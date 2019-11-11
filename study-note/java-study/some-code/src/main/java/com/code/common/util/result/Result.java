package com.code.common.util.result;


import com.code.common.AppConst;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/11/8
 */
@Data
public class ResultInfo {
    private int code;
    private String message;
    private Object data;
    private String time = AppConst.sdf_y_s.format(new Date(System.currentTimeMillis()));

    public static ResultInfo success(){
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.code = SType.success.key;
        resultInfo.message = SType.success.value;
        return resultInfo;
    }
    public static ResultInfo success(SType type, Object data){
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.code = type.key;
        resultInfo.message = type.value;
        resultInfo.data = data;
        return resultInfo;
    }
    public static ResultInfo error(EType type, Object data){
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.code = type.key;
        resultInfo.message = type.value;
        resultInfo.data = data;
        return resultInfo;
    }

    public static ResultInfo serviceError(){
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.code = EType.remoteServiceError.key;
        resultInfo.message = EType.remoteServiceError.value;
        return resultInfo;
    }
    public static ResultInfo serviceError(String error){
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.code = EType.remoteServiceError.key;
        resultInfo.message = error;
        return resultInfo;
    }

    public enum SType {
        /**
         * 操作成功
         */
        success(0,"成功"),
        save(1,"保存成功");
        private Integer key;
        private String value;
        SType(Integer key, String name) {
            this.key = key;
            this.value = name;
        }
        public Integer getKey() {
            return key;
        }
        public void setKey(Integer key) {
            this.key = key;
        }
        public String getValue() {
            return value;
        }
        public void setValue(String value) {
            this.value = value;
        }
    }

    public enum EType {
        /**
         * 操作失败
         */
        fail(-1,"失败"),
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
        parameterError(-114, "参数错误")	,
        accountErros(-115,"账户名不存在，请重新输入"),
        accountRegisted(-116,"账户已注册"),
        captchaErros(-117,"验证码错误"),
        accounOrPwdtErros(-118,"账户名与密码不匹配，请重新输入"),
        accountStop(-156,"该用户已经被停用"),
        notLogin (-121,"用户未登录"),
        notPermissions(-122,"用户没有权限"),
        oldPasswordErros(-123,"原密码错误"),
        entityError(-124,"上传文件的实体对象不匹配"), ;
        private Integer key;
        private String value;
        EType(Integer key, String name) {
            this.key = key;
            this.value = name;
        }
        public Integer getKey() {
            return key;
        }
        public void setKey(Integer key) {
            this.key = key;
        }
        public String getValue() {
            return value;
        }
        public void setValue(String value) {
            this.value = value;
        }
    }
}
