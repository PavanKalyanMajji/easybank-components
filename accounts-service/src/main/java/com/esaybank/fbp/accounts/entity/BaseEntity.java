package com.esaybank.fbp.accounts.entity;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;


import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Data
public class BaseEntity implements Serializable {
    private String createBy;
    private String modifiedBy;
    private Date createdDate;
    private Date modifiedDate;

    @PreUpdate
    public void modifiedDateAndModifiedBy() {
        modifiedBy = "Pavan Kalyan";
        modifiedDate = new Date();
    }

    @PrePersist
    public void createdDateAndCreatedBy() {
        createBy = "Pavan";
        createdDate = new Date();
    }
}
