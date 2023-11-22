package com.aplikasi.karyawan.utils;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TemplateResponse {

    public Map<Object, Object> success(Object data) {
        Map<Object, Object> map = new HashMap<>();
        map.put("data", data);
        map.put("message","Success");
        map.put("code",200);
        return map;
    }

    public Map<Object, Object> errors(Object message) {
        Map<Object, Object> map = new HashMap<>();
        map.put("message", message);
        map.put("code",404);
        return map;
    }

//    public Object integerChecker (Integer object) {
//        return Objects.requireNonNullElse(object, 0);
//    }
}
