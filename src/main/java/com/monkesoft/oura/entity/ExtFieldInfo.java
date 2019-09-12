package com.monkesoft.oura.entity;

import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * 扩展字段信息
 */
@Data
public class ExtFieldInfo extends BaseInfo {

    public static final String TYPE_STRING = "string";//普通文本型
    public static final String TYPE_INTEGER = "int";//数字型
    public static final String TYPE_COMBO = "combo";//下拉框
    public static final String TYPE_PERSON = "person";//人员选择框
    public static final String TYPE_TREE = "tree";//树形框
    public static final String TYPE_DIALOG = "dialog";//弹出框
    public static final String TYPE_RADIO = "radio";//弹出框
    public static final String TYPE_CHECKBOX = "checkbox";//弹出框
    public static final String TYPE_DATE = "date";//弹出框
    public static final String TYPE_TIME = "time";//弹出框
    public static final String TYPE_DATETIME = "datetime";//弹出框


    //所属分组，如人员信息的扩展字段，group=user
    private String group;

    //默认值
    private String defaultValue;

    //字段类型
    private String type;

    //待选数据
    private String data;

    //是否可空
    private boolean nullable;

    //是否唯一
    private boolean unique;

    public String getData(){
        if (!StringUtils.hasText(this.data))
            return this.data;

        if (this.data.startsWith("url:")) {
            //TODO 创建http连接，获取数据；
        }

        if (this.data.startsWith("code:")) {
            //TODO 从代码配置获取数据；
        }

        return this.data;
    }
}
