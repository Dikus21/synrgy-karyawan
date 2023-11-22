package com.aplikasi.karyawan.service.karyawan.impl;

import com.aplikasi.karyawan.entity.karyawan.Karyawan;
import com.aplikasi.karyawan.repository.KaryawanRepository;
import com.aplikasi.karyawan.service.karyawan.KaryawanService;
import com.aplikasi.karyawan.utils.TemplateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class KaryawanImpl implements KaryawanService {

    //step 1 : koneksi ke database
    @Autowired // untuk injeksi
    public KaryawanRepository karyawanRepository;

    @Autowired
    public TemplateResponse templateResponse;

    @Override
    public Map save(Karyawan karyawan) {
        Map map = new HashMap();
        Karyawan doSave = karyawanRepository.save(karyawan);
        return templateResponse.success(doSave);
    }

    @Override
    public Map update(Karyawan karyawan) {
        /*
        1. ngecek ke db base param ID
        2. jika ditemuakan baru di update
        3. jika tidak ditemukan: di tolak -return data notfound.
         */
        Map map = new HashMap();
        Karyawan checkData = karyawanRepository.getById(karyawan.getId());
        if(checkData == null) templateResponse.errors("data not found");
        checkData.setAddress(karyawan.getAddress());
        checkData.setName(karyawan.getName());

        Karyawan doUpdate = karyawanRepository.save(checkData);
        return templateResponse.success(doUpdate);
    }

    @Override
    public Map delete(Karyawan request) {
        /*
        1. chek data ke db
        2. jika ada, delete langsung
        hard delete  ?   permanent
        soft delete ?    deleted date status
         */
        Map map = new HashMap();
        Karyawan checkData = karyawanRepository.getById(request.getId());
        if(checkData == null) templateResponse.errors("data not found");
        checkData.setDeletedDate(new Date());
        Karyawan doDelete = karyawanRepository.save(checkData);

        // deleted permanent ?
        //karyawanRepository.delete(chekData);
        return templateResponse.success(doDelete);

    }

    @Override
    public Map getById(Long id) {
        Map map = new HashMap();
        Optional<Karyawan> getBaseOptional = karyawanRepository.findById(id);
        if(!getBaseOptional.isPresent()) templateResponse.errors("data not found");
        map.put("data",getBaseOptional.get());
        return map;

    }
}
