package com.aplikasi.karyawan.controller;

import com.aplikasi.karyawan.entity.karyawan.Training;
import com.aplikasi.karyawan.repository.TrainingRepository;
import com.aplikasi.karyawan.service.karyawan.TrainingService;
import com.aplikasi.karyawan.utils.TemplateResponse;
import com.aplikasi.karyawan.utils.SimpleStringUtils;
import javax.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/training")
public class TrainingController {
    @Autowired
    public TrainingService trainingService;
    @Autowired
    public TrainingRepository trainingRepository;
    @Autowired
    public SimpleStringUtils simpleStringUtils;
    @Autowired
    public TemplateResponse templateResponse;

    @PostMapping(value = {"/save", "/save/"})
    public ResponseEntity<Map> save(@RequestBody Training request) {
        try {
            return new ResponseEntity<Map>(trainingService.save(request), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Map>(templateResponse.errors(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = {"/update", "/update/"})
    public ResponseEntity<Map> update(@RequestBody Training request) {
        try {
            return new ResponseEntity<Map>(trainingService.update(request), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Map>(templateResponse.errors(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = {"/delete", "/delete/"})
    public ResponseEntity<Map> delete(@RequestBody Training request) {
        try {
            return new ResponseEntity<Map>(trainingService.delete(request), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Map>(templateResponse.errors(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = {"/{id}", "/{id}/"})
    public ResponseEntity<Map> getById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<Map>(templateResponse.success(trainingRepository.findById(id).get()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Map>(templateResponse.errors(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = {"/list", "/list/"})
    public ResponseEntity<Map> listSpec(
            @RequestParam() Integer page,
            @RequestParam() Integer size,
            @RequestParam(required = false) String pengajar,
            @RequestParam(required = false) String tema,
            @RequestParam(required = false) String orderby,
            @RequestParam(required = false) String ordertype
    ) {
        Pageable show_data = simpleStringUtils.getShort(orderby, ordertype, page, size);

        Specification<Training> spec =
                ((root, query, criteriaBuilder) -> {
                    List<Predicate> predicates = new ArrayList<>();
                    if (pengajar != null && !pengajar.isEmpty()) {
                        predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("pengajar")), "%" + pengajar.toLowerCase() + "%"));
                    }
                    if (tema != null && !tema.isEmpty()) {
                        predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("tema")), "%" + tema.toLowerCase() + "%"));
                    }
                    return criteriaBuilder.and(predicates.toArray(new javax.persistence.criteria.Predicate[0]));
                });
        Page<Training> list = trainingRepository.findAll(spec, show_data);
        return new ResponseEntity<Map>(templateResponse.success(list), new HttpHeaders(), HttpStatus.OK);
    }
}
