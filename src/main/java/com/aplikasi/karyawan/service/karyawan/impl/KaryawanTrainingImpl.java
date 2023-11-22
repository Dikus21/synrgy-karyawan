package com.aplikasi.karyawan.service.karyawan.impl;

import com.aplikasi.karyawan.Config;
import com.aplikasi.karyawan.entity.karyawan.KaryawanTraining;
import com.aplikasi.karyawan.entity.karyawan.Karyawan;
import com.aplikasi.karyawan.entity.karyawan.Training;
import com.aplikasi.karyawan.repository.KaryawanTrainingRepository;
import com.aplikasi.karyawan.repository.KaryawanRepository;
import com.aplikasi.karyawan.repository.TrainingRepository;
import com.aplikasi.karyawan.service.karyawan.KaryawanTrainingService;
import com.aplikasi.karyawan.utils.TemplateResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class KaryawanTrainingImpl implements KaryawanTrainingService {

    @Autowired
    public KaryawanTrainingRepository karyawanTrainingRepository;
    @Autowired
    public KaryawanRepository karyawanRepository;
    @Autowired
    public TrainingRepository trainingRepository;
    @Autowired
    public TemplateResponse templateResponse;

    @Override
    public Map save(KaryawanTraining request) {
        try {
            log.info("Save Account");
            if (request.getTraining() == null && request.getTraining().getId() == null) {
                return templateResponse.errors(Config.TRAINING_REQUIRED);
            }
            Optional<Training> checkDataDBTraining = trainingRepository.findById(request.getTraining().getId());
            if (!checkDataDBTraining.isPresent()) {
                return templateResponse.errors(Config.TRAINING_NOT_FOUND);
            }
            if (request.getKaryawan() == null && request.getKaryawan().getId() == null){
                return templateResponse.errors(Config.EMPLOYEE_REQUIRED);
            }
            Optional<Karyawan> chekDataDBKaryawan = karyawanRepository.findById(request.getKaryawan().getId());
            if(!chekDataDBKaryawan.isPresent()){
                return templateResponse.errors(Config.EMPLOYEE_NOT_FOUND);
            }

            request.setTraining(checkDataDBTraining.get());
            request.setKaryawan(chekDataDBKaryawan.get());

            return templateResponse.success(karyawanTrainingRepository.save(request));
        }catch (Exception e){
            log.error("save KaryawanTraining eror: "+e.getMessage());
            return templateResponse.errors("Save KaryawanTraining ="+e.getMessage());
        }

    }

    @Override
    public Map update(KaryawanTraining request) {
        try {
            log.info("Update KaryawanTraining");
            if(request.getId() == null ){
                return templateResponse.errors(Config.ID_REQUIRED);
            }
            Optional<KaryawanTraining> chekDataDBKaryawanTraining = karyawanTrainingRepository.findById(request.getId());
            if(!chekDataDBKaryawanTraining.isPresent()){
                return templateResponse.errors(Config.KARYAWAN_TRAINING_NOT_FOUND);
            }
            if(request.getTraining() == null && request.getTraining().getId() == null){
                return templateResponse.errors(Config.TRAINING_REQUIRED);
            }
            Optional<Training> chekDataDBTraining = trainingRepository.findById(request.getKaryawan().getId());
            if(!chekDataDBTraining.isPresent()){
                return templateResponse.errors(Config.TRAINING_NOT_FOUND);
            }
            if(request.getKaryawan() == null && request.getKaryawan().getId() == null){
                return templateResponse.errors(Config.EMPLOYEE_REQUIRED);
            }
            Optional<Karyawan> chekDataDBKaryawan = karyawanRepository.findById(request.getKaryawan().getId());
            if(!chekDataDBKaryawan.isPresent()){
                return templateResponse.errors(Config.EMPLOYEE_NOT_FOUND);
            }

            chekDataDBKaryawanTraining.get().setTraining(chekDataDBTraining.get());
            chekDataDBKaryawanTraining.get().setKaryawan(chekDataDBKaryawan.get());

            return templateResponse.success(karyawanTrainingRepository.save(chekDataDBKaryawanTraining.get()));
        }catch (Exception e){
            log.error("Update Karyawan Training eror: "+e.getMessage());
            return templateResponse.errors("Update Karyawan Training ="+e.getMessage());
        }
    }

    @Override
    public Map delete(KaryawanTraining request) {
        try {
            log.info("Delete Karyawan Training");
            if(request.getId() == null ){
                return templateResponse.errors(Config.ID_REQUIRED);
            }
            Optional<KaryawanTraining> chekDataDBKaryawanTraining = karyawanTrainingRepository.findById(request.getId());
            if(!chekDataDBKaryawanTraining.isPresent()){
                return templateResponse.errors(Config.KARYAWAN_TRAINING_NOT_FOUND);
            }
            chekDataDBKaryawanTraining.get().setDeletedDate(new Date());
            return templateResponse.success(Config.SUCCESS);
        }catch (Exception e){
            log.error("Delete Karyawan Training eror: "+e.getMessage());
            return templateResponse.errors("Delete Karyawan Training ="+e.getMessage());
        }
    }
}
