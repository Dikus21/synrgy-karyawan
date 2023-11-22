package ch4.samplemvc.service;


import ch4.samplemvc.entity.Karyawan;

import java.util.List;

public interface KaryawanService {

    Karyawan addKaryawan(Karyawan karyawan);
    List<Karyawan> getAllKaryawan();
}
