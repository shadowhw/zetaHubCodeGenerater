package com.heyexi.common.domain.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author heyexi
 * @Date 2023-08-23 17:31:16
 * @Description
 * 分页VO
 */
@Data
public class PageVO implements Serializable {

    @Data
    @ToString
    public static class Req {
        // 第n页
        private Integer pageIndex = 1;
        // 数据量
        private Integer pageSize = 10;
    }

    @Data
    public static class Resp<T> {
        // 分页数据列表
        private List<T> rows = new ArrayList();
        // 总的数据条数
        private Integer total = 0;
        // 每页显示条数，默认 10"
        private Integer pageSize = 10;
        // 当前页
        private Integer pageIndex = 1;
        // 总页数,默认=total%size
        private Integer pageCount = 0;

        public Resp() {}

        public Resp(IPage<T> page) {
            if (page != null) {
                this.setRows(page.getRecords());
                this.setTotal((int)page.getTotal());
                this.setPageSize((int)page.getSize());
                this.setPageIndex((int)page.getCurrent());
                this.setPageCount((int)page.getPages());
            }
        }
    }
}
