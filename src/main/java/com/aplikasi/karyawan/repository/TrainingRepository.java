package com.aplikasi.karyawan.repository;

import com.aplikasi.karyawan.entity.karyawan.KaryawanTraining;
import com.aplikasi.karyawan.entity.karyawan.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TrainingRepository extends JpaRepository<Training, Long>, JpaSpecificationExecutor<Training> {
    @Query(value = "select t from Training t WHERE t.id = :idTraining")
    public Training getById(@Param("idTraining") Long idTraining);

}
