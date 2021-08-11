package com.priyangona.priangona_backend.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class EditInfo extends BaseModel{
    @Column(name = "created_by", nullable = false, updatable = false)
    private String createdBy;

    @Column(name = "created_by_id", nullable = false, updatable = false)
    private Long createdById;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_by_id")
    private Long updatedById;

    @Override
    public String toString() {
        return super.toString() +
                "createdBy='" + createdBy + '\'' +
                ", createdById=" + createdById +
                ", updatedBy='" + updatedBy + '\'' +
                ", updatedById=" + updatedById + ", ";
    }
}
