package com.aplikasi.karyawan.service.karyawan;

import com.aplikasi.karyawan.entity.karyawan.Account;
import com.aplikasi.karyawan.entity.karyawan.KaryawanTraining;

import java.util.Map;

public interface KaryawanTrainingService {
    Map save(KaryawanTraining request);

    Map update(KaryawanTraining request);

    Map delete(KaryawanTraining request);

//    Map getById(Long id);
}
