package com.kaweel.rookieworkshopspring.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class ActionEntity {
    @Column(name = "create_by", length = 50, updatable = false)
    private String createBy;

    @CreationTimestamp
    @Column(name = "create_date", updatable = false)
    @Temporal(TemporalType.DATE)
    private Date createDate;

    @Column(name = "update_by", length = 50, insertable = false)
    private String updateBy;

    @UpdateTimestamp
    @Column(name = "update_date", insertable = false)
    @Temporal(TemporalType.DATE)
    private Date updateDate;
}
