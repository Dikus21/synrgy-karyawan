package com.aplikasi.karyawan;

import com.aplikasi.karyawan.controller.fileupload.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class KaryawanApplication {

	public static void main(String[] args) {
		SpringApplication.run(KaryawanApplication.class, args);
	}

}
