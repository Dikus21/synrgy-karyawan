package com.aplikasi.karyawan.service.oauth;

import com.aplikasi.karyawan.request.LoginModel;
import com.aplikasi.karyawan.request.RegisterModel;

import java.util.Map;

public interface UserService {
    Map registerManual(RegisterModel objModel) ;
    Map registerByGoogle(RegisterModel objModel);
    public Map login(LoginModel objLogin);
}

