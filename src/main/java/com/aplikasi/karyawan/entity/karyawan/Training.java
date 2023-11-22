package com.aplikasi.karyawan.entity.karyawan;

import com.aplikasi.karyawan.entity.AbstractDate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Where;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "training")
@Where(clause = "deleted_date is null")
public class Training extends AbstractDate implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;

    @Column(length = 200)
    public String pengajar;

    @Column(length = 200)
    public String tema;

    @JsonIgnore
    @OneToMany(mappedBy = "training", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<KaryawanTraining> karyawanTrainingList;
}
