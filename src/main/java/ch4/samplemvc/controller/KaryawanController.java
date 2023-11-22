package ch4.samplemvc.controller;

import ch4.samplemvc.entity.Karyawan;
import ch4.samplemvc.service.KaryawanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("/ch4/samplemvc")
@Slf4j
public class KaryawanController {
    @Autowired
    private KaryawanService karyawanService;

    public KaryawanController(KaryawanService karyawanService) {
        this.karyawanService = karyawanService;
    }

    public Karyawan addKaryawan(Karyawan karyawan) {
        return karyawanService.addKaryawan(karyawan);
    }

    public List<Karyawan> getListKaryawan(){
        return karyawanService.getAllKaryawan();
    }

    @RequestMapping("/karyawan")
    public String getListKaryawan(Model model) {
        karyawanService.addKaryawan(new Karyawan(1L, "Dika"));
        karyawanService.addKaryawan(new Karyawan(2L, "Doki"));

        List<Karyawan> karyawanList = karyawanService.getAllKaryawan();
        model.addAttribute("listKaryawan", karyawanList);
        log.trace("THIS IS TRACEE TESTT");
        return "test";
    }
}
