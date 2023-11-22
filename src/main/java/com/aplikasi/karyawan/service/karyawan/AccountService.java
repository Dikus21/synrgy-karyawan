package com.aplikasi.karyawan.service.karyawan;

import com.aplikasi.karyawan.entity.karyawan.Account;
import com.aplikasi.karyawan.entity.karyawan.Karyawan;

import java.util.Map;

public interface AccountService {
    Map save(Account request);

    Map update(Account request);

    Map delete(Account request);

//    Map getById(Long id);
}
