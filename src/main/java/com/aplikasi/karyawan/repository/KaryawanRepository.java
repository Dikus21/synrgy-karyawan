package com.aplikasi.karyawan.repository;

import com.aplikasi.karyawan.entity.karyawan.Karyawan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
//public interface KaryawanRepository extends JpaRepository<Karyawan, Long> {
public interface KaryawanRepository extends JpaRepository<Karyawan, Long>, JpaSpecificationExecutor<Karyawan> {
    //JPA Query
    @Query(value = "select c from Karyawan c WHERE c.id = :idKaryawan")
    public Karyawan getById(@Param("idKaryawan") Long idKaryawan);

    //Native Query : menggunakan JPAQL
    //yang diselect adalah nama tablenya
    @Query(value = "select c from karyawan c WHERE c.id = :idKaryawan", nativeQuery = true)
    public Karyawan getByIdNativeQuery(@Param("idKaryawan") Long idKaryawan);

//    @Query(value = "select e from Employee e where LOWER(e.name) like LOWER(:nameParam)")
//    public Page<Karyawan> getByLikeName(@Param("nameParam") String nameParam, Pageable pageable);
//
//    @Query(value = "select e from Employee e ")
//    public Page<Karyawan> getALlPage(Pageable pageable);
}
