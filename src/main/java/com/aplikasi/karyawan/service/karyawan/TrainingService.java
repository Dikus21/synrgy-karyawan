package com.aplikasi.karyawan.service.karyawan;

import com.aplikasi.karyawan.entity.karyawan.Account;
import com.aplikasi.karyawan.entity.karyawan.Training;

import java.util.Map;

public interface TrainingService {
    Map save(Training request);

    Map update(Training request);

    Map delete(Training request);

//    Map getById(Long id);
}
