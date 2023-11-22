package com.aplikasi.karyawan.controller;

import com.aplikasi.karyawan.entity.karyawan.Account;
import com.aplikasi.karyawan.repository.AccountRepository;
import com.aplikasi.karyawan.service.karyawan.AccountService;
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
@RequestMapping("/v1/account")
public class AccountController {
    @Autowired
    public AccountService accountService;
    @Autowired
    public AccountRepository accountRepository;
    @Autowired
    public SimpleStringUtils simpleStringUtils;
    @Autowired
    public TemplateResponse templateResponse;

    @PostMapping(value = {"/save", "/save/"})
    public ResponseEntity<Map> save(@RequestBody Account request) {
        try {
            return new ResponseEntity<Map>(accountService.save(request), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Map>(templateResponse.errors(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = {"/update", "/update/"})
    public ResponseEntity<Map> update(@RequestBody Account request) {
        try {
            return new ResponseEntity<Map>(accountService.update(request), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Map>(templateResponse.errors(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = {"/delete", "/delete/"})
    public ResponseEntity<Map> delete(@RequestBody Account request) {
        try {
            return new ResponseEntity<Map>(accountService.delete(request), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Map>(templateResponse.errors(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = {"/{id}", "/{id}/"})
    public ResponseEntity<Map> getById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<Map>(templateResponse.success(accountRepository.findById(id).get()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Map>(templateResponse.errors(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = {"/list", "/list/"})
    public ResponseEntity<Map> listSpec(
            @RequestParam() Integer page,
            @RequestParam() Integer size,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String orderby,
            @RequestParam(required = false) String ordertype
    ) {
        Pageable show_data = simpleStringUtils.getShort(orderby, ordertype, page, size);

        Specification<Account> spec =
                ((root, query, criteriaBuilder) -> {
                    List<Predicate> predicates = new ArrayList<>();
                    if (name != null && !name.isEmpty()) {
                        predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
                    }
                    return criteriaBuilder.and(predicates.toArray(new javax.persistence.criteria.Predicate[0]));
                });
        Page<Account> list = accountRepository.findAll(spec, show_data);
        return new ResponseEntity<Map>(templateResponse.success(list), new HttpHeaders(), HttpStatus.OK);
    }

}
