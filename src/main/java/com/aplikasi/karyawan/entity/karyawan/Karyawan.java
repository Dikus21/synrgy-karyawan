package com.aplikasi.karyawan.entity.karyawan;

import com.aplikasi.karyawan.entity.AbstractDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.websocket.OnError;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Where;
import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "karyawan")
@Where(clause = "deleted_date is null")
public class Karyawan extends AbstractDate implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    String name;

    @Column(name = "address", columnDefinition = "TEXT")
    private String address;

    @Temporal(TemporalType.DATE)
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dob;

    private String status = "active";

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_detail_karyawan", foreignKey = @ForeignKey(name = "id_detail_karyawan_constraint"))
    private Detail detailKaryawan;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "user_id_constraint"))
//    private Users users;

//    @JsonIgnore
//    @OneToMany(mappedBy = "karyawan", fetch = FetchType.LAZY, cascade = CascadeType.ALL) // employee_id
//    private List<Account> accountList;
//
//    @JsonIgnore
//    @OneToMany(mappedBy = "karyawan", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<KaryawanTraining> karyawanTrainingList;

}
