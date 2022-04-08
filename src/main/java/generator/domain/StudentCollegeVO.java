package generator.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * Student的扩展类
 */
@Data
@EqualsAndHashCode(callSuper = true) //注入父类的字段
public class StudentCollegeVO extends Student {
     //扩展student类，增加collegename
     private String collegename;

    }

