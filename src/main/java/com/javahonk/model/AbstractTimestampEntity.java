package com.javahonk.model;

import javax.persistence.Column;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class AbstractTimestampEntity {

    @Column(name = "created_at")
    Timestamp createdAt;

    @Column(name = "updated_at")
    Timestamp updateAt;

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = Timestamp.valueOf(LocalDateTime.now());
        this.updateAt = Timestamp.valueOf(LocalDateTime.now());
    }

    @PreUpdate
    protected void onUpdate() {
        this.updateAt = Timestamp.valueOf(LocalDateTime.now());
    }
}
