package com.monkesoft.oura.entity;

import lombok.Data;

/**
 * 代码项信息
 */
@Data
public class CodeInfo extends BaseTreeInfo {


    /**
     * 是否允许选择，比如树形结构的，部分中间节点不允许选择，仅用来分组使用
     */
    private boolean clickable;
}
