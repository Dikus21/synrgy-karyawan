package com.aplikasi.karyawan.service.karyawan.impl;

import com.aplikasi.karyawan.Config;
import com.aplikasi.karyawan.entity.karyawan.Account;
import com.aplikasi.karyawan.entity.karyawan.Karyawan;
import com.aplikasi.karyawan.repository.AccountRepository;
import com.aplikasi.karyawan.repository.KaryawanRepository;
import com.aplikasi.karyawan.service.karyawan.AccountService;
import com.aplikasi.karyawan.utils.TemplateResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class AccountImpl implements AccountService {
    
    @Autowired
    public AccountRepository accountRepository;
    @Autowired
    public KaryawanRepository karyawanRepository;

    @Autowired
    public TemplateResponse templateResponse;

    @Override
    public Map save(Account request) {
        try {
            log.info("Save Account");
            if (request.getNama().isEmpty()) {
                return templateResponse.errors(Config.NAME_REQUIRED);
            }
            if (request.getKaryawan() == null && request.getKaryawan().getId() == null){
                return templateResponse.errors(Config.EMPLOYEE_REQUIRED);
            }
            Optional<Karyawan> chekDataDB = karyawanRepository.findById(request.getKaryawan().getId());
            if(!chekDataDB.isPresent()){
                return templateResponse.errors(Config.EMPLOYEE_NOT_FOUND);
            }

            request.setKaryawan(chekDataDB.get());

            return templateResponse.success(accountRepository.save(request));
        }catch (Exception e){
            log.error("save rekening eror: "+e.getMessage());
            return templateResponse.errors("Save Rekening ="+e.getMessage());
        }

    }

    @Override
    public Map update(Account request) {
        try {
            log.info("Update rekening");
            if(request.getId() ==null ){
                return templateResponse.errors(Config.ID_REQUIRED);
            }
            Optional<Account> chekDataDBRekening = accountRepository.findById(request.getId());
            if(!chekDataDBRekening.isPresent()){
                return templateResponse.errors(Config.ACCOUNT_NOT_FOUND);
            }

            if(request.getKaryawan() ==null && request.getKaryawan().getId() == null){
                return templateResponse.errors(Config.EMPLOYEE_REQUIRED);
            }
            Optional<Karyawan> chekDataDB = karyawanRepository.findById(request.getKaryawan().getId());
            if(!chekDataDB.isPresent()){
                return templateResponse.errors(Config.EMPLOYEE_NOT_FOUND);
            }

            // apa yang ingin kalian update
            chekDataDBRekening.get().setRekening(request.getRekening());
            chekDataDBRekening.get().setKaryawan(chekDataDB.get());
            chekDataDBRekening.get().setJenis(request.getJenis());

            // do save , balikin responya

            return templateResponse.success(accountRepository.save(chekDataDBRekening.get()));
        }catch (Exception e){
            log.error("Update rekening eror: "+e.getMessage());
            return templateResponse.errors("Update Rekening ="+e.getMessage());
        }
    }

    @Override
    public Map delete(Account request) {
        try {
            log.info("Delete rekening");
            if(request.getId() ==null ){
                return templateResponse.errors(Config.ID_REQUIRED);
            }
            Optional<Account> chekDataDBRekening = accountRepository.findById(request.getId());
            if(!chekDataDBRekening.isPresent()){
                return templateResponse.errors(Config.ACCOUNT_NOT_FOUND);
            }
            // apa yang ingin kalian update
            chekDataDBRekening.get().setDeletedDate(new Date());
            // do save , balikin responya
            return templateResponse.success(Config.SUCCESS);
        }catch (Exception e){
            log.error("Delete rekening eror: "+e.getMessage());
            return templateResponse.errors("Delete Rekening ="+e.getMessage());
        }
    }

//    @Override
//    public Map getById(Long id) {
//        Map map = new HashMap();
//        Optional<Account> getBaseOptional = accountRepository.findById(id);
//        if(getBaseOptional.isEmpty())response.errors("data not found", 400);
//        map.put("data",getBaseOptional.get());
//        return map;
//
//    }
}
