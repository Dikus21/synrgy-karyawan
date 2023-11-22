package com.aplikasi.karyawan.controller;

import com.aplikasi.karyawan.entity.karyawan.Karyawan;
import com.aplikasi.karyawan.repository.KaryawanRepository;
import com.aplikasi.karyawan.service.karyawan.KaryawanService;
import com.aplikasi.karyawan.utils.SimpleStringUtils;
import javax.persistence.criteria.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("v1/karyawan")
@Slf4j
public class KaryawanController {
    @Autowired
    public KaryawanService karyawanService;

    SimpleStringUtils simpleStringUtils = new SimpleStringUtils();

    @Autowired
    public KaryawanRepository karyawanRepository;

//    @PostMapping(value = {"/save", "/save/"}, produces = {"application/json"})
    @PostMapping(value = {"/save", "/save/"})
    public ResponseEntity<Map> save(@RequestBody Karyawan request) {
        return new ResponseEntity<Map>(karyawanService.save(request), HttpStatus.OK);
    }

    @PutMapping(value = {"/update", "/update/"})
    public ResponseEntity<Map> update(@RequestBody Karyawan request) {
        return new ResponseEntity<Map>(karyawanService.update(request), HttpStatus.OK);
    }

    @DeleteMapping(value = {"/delete", "/delete/"})
    public ResponseEntity<Map> delete(@RequestBody Karyawan request) {
        return new ResponseEntity<Map>(karyawanService.delete(request), HttpStatus.OK);
    }

    @GetMapping(value = {"/{id}", "/{id}/"})
    public ResponseEntity<Map> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<Map>(karyawanService.getById(id), HttpStatus.OK);
    }

//    @GetMapping(value = {"/list", "/list/"})
//    public ResponseEntity<Map> listQuizHeader(
//            @RequestParam() Integer page,
//            @RequestParam(required = true) Integer size,
//            @RequestParam(required = false) String name,
//            @RequestParam(required = false) String orderby,
//            @RequestParam(required = false) String ordertype) {
//
//        Pageable show_data = simpleStringUtils.getShort(orderby, ordertype, page, size);
//        Page<Karyawan> list = null;
//
//        if (name != null && !name.isEmpty()) {
//            list = karyawanRepository.getByLikeName("%" + name + "%", show_data);
//        } else {
//            list = karyawanRepository.getALlPage(show_data);
//        }
//        Map map = new HashMap();
//        map.put("data",list);
//        return new ResponseEntity<Map>(map, new HttpHeaders(), HttpStatus.OK);
//    }

    @GetMapping(value = {"/list-spec", "/list-spec/"})
    public ResponseEntity<Map> listQuizHeaderSpec(
            @RequestParam() Integer page,
            @RequestParam(required = true) Integer size,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String orderby,
            @RequestParam(required = false) String ordertype) {

        Pageable show_data = simpleStringUtils.getShort(orderby, ordertype, page, size);

        Specification<Karyawan> spec =
                ((root, query, criteriaBuilder) -> {
                    List<Predicate> predicates = new ArrayList<>();
                    if (name != null && !name.isEmpty()) {
                        //      select  * from employee e where name like '%a%' ;
                        predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
                    }
                    if (address != null && !address.isEmpty()) {
                        // equal == select  * from employee e where  address ='jakarta'
                        predicates.add(criteriaBuilder.equal(root.get("address"), address));
                    }
                    if (status != null && !status.isEmpty()) {
                        // equal == select  * from employee e where  address ='jakarta'
                        predicates.add(criteriaBuilder.equal(root.get("status"), address));
                    }
                    return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
                });

        //        PageRequest pageRequest = PageRequest.of(page,size);
        Page<Karyawan> list = karyawanRepository.findAll(spec, show_data);

        Map map = new HashMap();
        map.put("data",list);
        return new ResponseEntity<Map>(map, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = {"/default-jpa", "/default-jpa/"})
    public ResponseEntity<?> defaultJPA() {
//        Map map = new HashMap();
        return new ResponseEntity<>(karyawanRepository.findAll(), HttpStatus.OK);
    }
}
