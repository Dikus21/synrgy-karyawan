package com.aplikasi.karyawan.service.karyawan;

import com.aplikasi.karyawan.entity.karyawan.Karyawan;

import java.util.Map;

public interface KaryawanService {
    //
    Map save(Karyawan request);

    Map update(Karyawan request);

    Map delete(Karyawan request);

    Map getById(Long id);
}
