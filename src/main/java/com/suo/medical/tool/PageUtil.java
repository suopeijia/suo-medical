package com.suo.medical.tool;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suo.medical.common.response.Result;
import lombok.Data;

import java.util.List;

@Data
public class PageUtil<T> {

    /**
     * 当前页数据
     */
    private List<T> records;

    /**
     * 总条数
     */
    private Long total;

    /**
     * 当前页
     */
    private Long current;

    /**
     * 每页大小
     */
    private Long size;

    /**
     * 总页数
     */
    private Long pages;

    public static <T> PageUtil<T> of(Page<T> page){
        //把page的records的records,total,current,size,pages赋值给PageUtil
        PageUtil<T> pageUtil = new PageUtil<>();
        pageUtil.setRecords(page.getRecords());
        pageUtil.setTotal(page.getTotal());
        pageUtil.setCurrent(page.getCurrent());
        pageUtil.setSize(page.getSize());
        pageUtil.setPages(page.getPages());
        return pageUtil;
    }
}
