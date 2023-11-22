package com.aplikasi.karyawan.repository;

import com.aplikasi.karyawan.entity.karyawan.Detail;
import com.aplikasi.karyawan.entity.karyawan.KaryawanTraining;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface KaryawanTrainingRepository extends JpaRepository<KaryawanTraining, Long>, JpaSpecificationExecutor<KaryawanTraining> {
    @Query(value = "select c from KaryawanTraining c WHERE c.id = :idKaryawanTraining")
    public KaryawanTraining getById(@Param("idKaryawanTraining") Long idKaryawanTraining);
}
