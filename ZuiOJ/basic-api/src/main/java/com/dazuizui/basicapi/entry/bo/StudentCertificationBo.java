package com.dazuizui.basicapi.entry.bo;

import com.dazuizui.basicapi.entry.StudentCertification;

import java.io.Serializable;

/**
 * 学生认证Bo
 */
public class StudentCertificationBo implements Serializable {
    private String token;                               //用户token
    private String idemtoken;                           //幂等性操作
    private StudentCertification studentCertification;  //学生认证

    @Override
    public String toString() {
        return "StudentCertificationBo{" +
                "token='" + token + '\'' +
                ", idemtoken='" + idemtoken + '\'' +
                ", studentCertification=" + studentCertification +
                '}';
    }

    public StudentCertificationBo() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getIdemtoken() {
        return idemtoken;
    }

    public void setIdemtoken(String idemtoken) {
        this.idemtoken = idemtoken;
    }

    public StudentCertification getStudentCertification() {
        return studentCertification;
    }

    public void setStudentCertification(StudentCertification studentCertification) {
        this.studentCertification = studentCertification;
    }

    public StudentCertificationBo(String token, String idemtoken, StudentCertification studentCertification) {
        this.token = token;
        this.idemtoken = idemtoken;
        this.studentCertification = studentCertification;
    }
}
