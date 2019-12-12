package fish.unit.response;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/12/11
 */
public class ResponseCode implements Serializable {
    public enum Success{
        SUCCESS(0, "操作成功"),
        SAVE(0, "保存成功"),
        QUERY(0, "查询成功"),
        DELETE(0, "删除成功"),
        ;
        public Integer code;
        public String text;
        Success(Integer code, String text){
            this.code = code;
            this.text = text;
        }
    }
    public enum Fail{
        FAIL(500, "操作失败"),
        SAVE(500, "保存失败"),
        QUERY(500, "查询失败"),
        DELETE(500, "删除失败"),
        OVERTIME(500, "运行超时"),
        RET_ENUM(500, "返回枚举参数错误"),
        ;
        public Integer code;
        public String text;
        Fail(Integer code, String text){
            this.code = code;
            this.text = text;
        }
    }
}
