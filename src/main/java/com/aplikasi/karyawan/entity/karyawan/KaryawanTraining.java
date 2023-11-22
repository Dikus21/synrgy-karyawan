package com.aplikasi.karyawan.entity.karyawan;

import com.aplikasi.karyawan.entity.AbstractDate;
import javax.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Where;

import java.io.Serializable;

@Data
@Entity
@Table(name = "karyawan_training")
@Where(clause = "deleted_date is null")
public class KaryawanTraining extends AbstractDate implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    @JoinColumn(name = "id_training")
    public Training training;

    @ManyToOne
    @JoinColumn(name = "id_karyawan")
    public Karyawan karyawan;
}
