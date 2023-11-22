package com.aplikasi.karyawan.entity.karyawan;

import com.aplikasi.karyawan.entity.AbstractDate;
import javax.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;

import java.util.UUID;

@Data
@Entity
@Table(name = "test_user")
@Where(clause = "deleted_date is null")
public class Users extends AbstractDate {
    @Id
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id")
    public UUID id;

    public String name;
}
