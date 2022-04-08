package generator.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Teacher的扩展类
 */
@Data
@EqualsAndHashCode(callSuper = true) //注入父类的字段
public class TeacherCollegeVO extends Teacher {
    //扩展Teacher类，增加collegename
    private String collegename;

}
