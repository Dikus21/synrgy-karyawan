package com.aplikasi.karyawan.entity.karyawan;

import com.aplikasi.karyawan.entity.AbstractDate;
import javax.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Where;

import java.io.Serializable;

@Entity
@Table(name = "rekening_karyawan")
@Data
@Where(clause = "deleted_date is null")
public class Account extends AbstractDate implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "nama")
    private String nama;

    @Column(name = "jenis")
    private String jenis;

    @Column(length = 100)
    private String rekening;

    @ManyToOne
    @JoinColumn(name = "id_karyawan", foreignKey = @ForeignKey(name = "id_karyawan_constraint"))
    private Karyawan karyawan;
}
