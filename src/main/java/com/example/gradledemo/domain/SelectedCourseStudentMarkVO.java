package com.example.gradledemo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true) //注入父类的字段
public class SelectedCourseStudentMarkVO extends Selectedcourse {
    //扩展字段
    private String studentName;

    //扩展字段
    private String courseName;

    //判断该学生是否已经完成该课程
    private Boolean over = false;
}
