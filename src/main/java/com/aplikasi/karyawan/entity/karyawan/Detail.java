package com.aplikasi.karyawan.entity.karyawan;

import com.aplikasi.karyawan.entity.AbstractDate;
import javax.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Where;

import java.io.Serializable;

@Data
@Entity
@Table(name = "detail_karyawan")
@Where(clause = "deleted_date is null")
public class Detail extends AbstractDate implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @Column(name = "nik")
    public String nik;

    @Column(name = "npwp")
    public String npwp;
}
