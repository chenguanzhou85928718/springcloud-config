package generator.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true) //注入父类的字段
public class CourseTeacherCollegeVO extends Course {
    //扩展Course类
    private String collegename;
    private String teachername;
}
