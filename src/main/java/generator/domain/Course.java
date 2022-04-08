package generator.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName course
 */
@Data
public class Course implements Serializable {
    /**
     * 
     */
    private Integer courseid;

    /**
     * 课程名称
     */
    private String coursename;

    /**
     * 
     */
    private Integer teacherid;

    /**
     * 开课时间
     */
    private String coursetime;

    /**
     * 开课地点
     */
    private String classroom;

    /**
     * 学时
     */
    private Integer courseweek;

    /**
     * 课程类型
     */
    private String coursetype;

    /**
     * 所属院系
     */
    private Integer collegeid;

    /**
     * 学分
     */
    private Integer score;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Date updateTime;

    /**
     * 是否有效  0有效  1无效
     */
    private String isDelete;

    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Course other = (Course) that;
        return (this.getCourseid() == null ? other.getCourseid() == null : this.getCourseid().equals(other.getCourseid()))
            && (this.getCoursename() == null ? other.getCoursename() == null : this.getCoursename().equals(other.getCoursename()))
            && (this.getTeacherid() == null ? other.getTeacherid() == null : this.getTeacherid().equals(other.getTeacherid()))
            && (this.getCoursetime() == null ? other.getCoursetime() == null : this.getCoursetime().equals(other.getCoursetime()))
            && (this.getClassroom() == null ? other.getClassroom() == null : this.getClassroom().equals(other.getClassroom()))
            && (this.getCourseweek() == null ? other.getCourseweek() == null : this.getCourseweek().equals(other.getCourseweek()))
            && (this.getCoursetype() == null ? other.getCoursetype() == null : this.getCoursetype().equals(other.getCoursetype()))
            && (this.getCollegeid() == null ? other.getCollegeid() == null : this.getCollegeid().equals(other.getCollegeid()))
            && (this.getScore() == null ? other.getScore() == null : this.getScore().equals(other.getScore()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCourseid() == null) ? 0 : getCourseid().hashCode());
        result = prime * result + ((getCoursename() == null) ? 0 : getCoursename().hashCode());
        result = prime * result + ((getTeacherid() == null) ? 0 : getTeacherid().hashCode());
        result = prime * result + ((getCoursetime() == null) ? 0 : getCoursetime().hashCode());
        result = prime * result + ((getClassroom() == null) ? 0 : getClassroom().hashCode());
        result = prime * result + ((getCourseweek() == null) ? 0 : getCourseweek().hashCode());
        result = prime * result + ((getCoursetype() == null) ? 0 : getCoursetype().hashCode());
        result = prime * result + ((getCollegeid() == null) ? 0 : getCollegeid().hashCode());
        result = prime * result + ((getScore() == null) ? 0 : getScore().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", courseid=").append(courseid);
        sb.append(", coursename=").append(coursename);
        sb.append(", teacherid=").append(teacherid);
        sb.append(", coursetime=").append(coursetime);
        sb.append(", classroom=").append(classroom);
        sb.append(", courseweek=").append(courseweek);
        sb.append(", coursetype=").append(coursetype);
        sb.append(", collegeid=").append(collegeid);
        sb.append(", score=").append(score);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}