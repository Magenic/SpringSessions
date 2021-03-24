package com.robertw.EmployeeSkillsTracker.model;

import com.robertw.EmployeeSkillsTracker.config.auditing.AuditingConfig;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @CreatedDate
    @Column(name = "CreatedDate")
    private Date createdAt;

    @LastModifiedDate
    @Column(name = "UpdatedDate")
    private Date updatedAt;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getLastModifiedDate() {
        return updatedAt;
    }

    public void setLastModifiedDate(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
