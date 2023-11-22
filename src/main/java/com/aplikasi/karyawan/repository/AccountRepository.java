package com.aplikasi.karyawan.repository;

import com.aplikasi.karyawan.entity.karyawan.Account;
import com.aplikasi.karyawan.entity.karyawan.Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>, JpaSpecificationExecutor<Account> {
    @Query(value = "select a from Account a WHERE a.id = :idAccount")
    public Account getById(@Param("idAccount") Long idAccount);
}
