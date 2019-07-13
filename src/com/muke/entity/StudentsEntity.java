package com.muke.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "students", schema = "test", catalog = "")
public class StudentsEntity {
    private Integer stuId;
    private String stuName;
    private String stuSex;
    private Date birthday;
    private Integer stuAge;
    private String location;

    @Id
    @Column(name = "stu_id")
    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    @Basic
    @Column(name = "stu_name")
    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    @Basic
    @Column(name = "stu_sex")
    public String getStuSex() {
        return stuSex;
    }

    public void setStuSex(String stuSex) {
        this.stuSex = stuSex;
    }

    @Basic
    @Column(name = "birthday")
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "stu_age")
    public Integer getStuAge() {
        return stuAge;
    }

    public void setStuAge(Integer stuAge) {
        this.stuAge = stuAge;
    }

    @Basic
    @Column(name = "location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentsEntity that = (StudentsEntity) o;
        return Objects.equals(stuId, that.stuId) &&
                Objects.equals(stuName, that.stuName) &&
                Objects.equals(stuSex, that.stuSex) &&
                Objects.equals(birthday, that.birthday) &&
                Objects.equals(stuAge, that.stuAge) &&
                Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stuId, stuName, stuSex, birthday, stuAge, location);
    }
}
