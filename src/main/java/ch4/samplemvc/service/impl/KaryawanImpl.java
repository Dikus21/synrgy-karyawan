package ch4.samplemvc.service.impl;

import ch4.samplemvc.entity.Karyawan;
import ch4.samplemvc.service.KaryawanService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KaryawanImpl implements KaryawanService {
    List<Karyawan> listKaryawan = new ArrayList<>();

    @Override
    public Karyawan addKaryawan(Karyawan karyawan) {
        listKaryawan.add(karyawan);
        return karyawan;
    }

    @Override
    public List<Karyawan> getAllKaryawan() {
        return listKaryawan;
    }
}
