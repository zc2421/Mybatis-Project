package com.zilinsproject.mybatis.form;

import lombok.Data;

/**
 * @author zilinsmac
 */

@Data
public class CategoryForm {

    private Integer category_id;

    private String category_name;

    private Integer category_type;

    private Integer parent_id;

    private Boolean if_parent;
}
