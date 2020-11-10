package com.atguigu.crowd.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: Crowd-funding -- com.atguigu.crowd.entity.vo
 * @description: TODO
 * @author: xia liang
 * @create: 2020-09-04 10:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PortalProjectVO {
    private Integer projectId;
    private String projectName;
    private String headerPicturePath;
    private Integer money;
    private String deployDate;
    private Integer percentage;
    private Integer supporter;
}
