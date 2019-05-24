package com.ying.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 列表对象
 *
 * @author lyz
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListResultDto<T> {
    private long count;
    private List<T> datas;
    private int page;
    private int size;
}
