package com.muke.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "department", schema = "test")
public class DepartmentEntity {
    private Integer did;
    private String dname;
    private String ddesc;

    @Id
    @Column(name = "did", nullable = false)
    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    @Basic
    @Column(name = "dname", nullable = true, length = 255)
    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    @Basic
    @Column(name = "ddesc", nullable = true, length = 255)
    public String getDdesc() {
        return ddesc;
    }

    public void setDdesc(String ddesc) {
        this.ddesc = ddesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentEntity that = (DepartmentEntity) o;
        return Objects.equals(did, that.did) &&
                Objects.equals(dname, that.dname) &&
                Objects.equals(ddesc, that.ddesc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(did, dname, ddesc);
    }
}
