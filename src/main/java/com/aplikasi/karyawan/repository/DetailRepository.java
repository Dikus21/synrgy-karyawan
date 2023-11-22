package com.aplikasi.karyawan.repository;

import com.aplikasi.karyawan.entity.karyawan.Detail;
import com.aplikasi.karyawan.entity.karyawan.Karyawan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DetailRepository extends JpaRepository<Detail, Long>, JpaSpecificationExecutor<Detail> {
    @Query(value = "select d from Detail d WHERE d.id = :idDetail")
    public Detail getById(@Param("idDetail") Long idDetail);
}
