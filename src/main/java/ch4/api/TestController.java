package ch4.api;

import ch4.samplemvc.service.KaryawanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/ch4/api")
@Slf4j
class TestController {
    @Autowired
    KaryawanService karyawanService;

    @RequestMapping("/TestController")
    @ResponseBody
    public String hello() {
        log.info("Test, log INFO");
        //response
        return "Hello Spring Boot";
    }

//    @RequestMapping("/Karyawan")
//    @ResponseBody
//    public String listKaryawan(Model model) {
//
//        KaryawanController karyawanController = new KaryawanController(karyawanService);
//        karyawanController.addKaryawan(1L, "Dika");
//        karyawanController.addKaryawan(2L, "Doki");
//
//        model.addAttribute("listKaryawan", karyawanController.getListKaryawan());
//
//        return "test";
//    }
}
