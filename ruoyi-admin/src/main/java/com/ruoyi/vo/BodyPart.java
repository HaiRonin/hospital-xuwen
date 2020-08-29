package com.ruoyi.vo;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wu123
 * @since 2019-01-20
 */

public class BodyPart implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    /**
     * 身体部位
     */
    private String bodyPart;
    /**
     * 排序
     */
    private Long sort;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBodyPart() {
        return bodyPart;
    }

    public void setBodyPart(String bodyPart) {
        this.bodyPart = bodyPart;
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }
}
