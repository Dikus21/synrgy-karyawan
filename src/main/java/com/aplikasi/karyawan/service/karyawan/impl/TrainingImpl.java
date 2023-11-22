package com.aplikasi.karyawan.service.karyawan.impl;

import com.aplikasi.karyawan.Config;
import com.aplikasi.karyawan.entity.karyawan.Training;
import com.aplikasi.karyawan.repository.TrainingRepository;
import com.aplikasi.karyawan.repository.KaryawanRepository;
import com.aplikasi.karyawan.service.karyawan.TrainingService;
import com.aplikasi.karyawan.utils.TemplateResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class TrainingImpl implements TrainingService {

    @Autowired
    public TrainingRepository trainingRepository;
    @Autowired
    public KaryawanRepository karyawanRepository;

    @Autowired
    public TemplateResponse templateResponse;

    @Override
    public Map save(Training request) {
        try {
            log.info("Save Training");
            if (request.getPengajar().isEmpty()) {
                return templateResponse.errors("Pengajar is Required");
            }
            if (request.getTema().isEmpty()) {
                return templateResponse.errors("Tema is Required");
            }
            return templateResponse.success(trainingRepository.save(request));
        }catch (Exception e){
            log.error("save training eror: "+e.getMessage());
            return templateResponse.errors("Save Training ="+e.getMessage());
        }

    }

    @Override
    public Map update(Training request) {
        try {
            log.info("Update Training");
            if(request.getId() == null ){
                return templateResponse.errors(Config.ID_REQUIRED);
            }
            Optional<Training> chekDataDBTraining = trainingRepository.findById(request.getId());
            if(!chekDataDBTraining.isPresent()){
                return templateResponse.errors(Config.TRAINING_NOT_FOUND);
            }

            // apa yang ingin kalian update
            chekDataDBTraining.get().setPengajar(request.getPengajar());
            chekDataDBTraining.get().setTema(request.getTema());

            // do save , balikin responya

            return templateResponse.success(trainingRepository.save(chekDataDBTraining.get()));
        }catch (Exception e){
            log.error("Update training eror: "+e.getMessage());
            return templateResponse.errors("Update Training ="+e.getMessage());
        }
    }

    @Override
    public Map delete(Training request) {
        try {
            log.info("Delete Training");
            if(request.getId() ==null ){
                return templateResponse.errors(Config.ID_REQUIRED);
            }
            Optional<Training> chekDataDBTraining = trainingRepository.findById(request.getId());
            if(!chekDataDBTraining.isPresent()){
                return templateResponse.errors(Config.ACCOUNT_NOT_FOUND);
            }
            // apa yang ingin kalian update
            chekDataDBTraining.get().setDeletedDate(new Date());
            // do save , balikin responya
            return templateResponse.success(Config.SUCCESS);
        }catch (Exception e){
            log.error("Delete training eror: "+e.getMessage());
            return templateResponse.errors("Delete training ="+e.getMessage());
        }
    }
}
