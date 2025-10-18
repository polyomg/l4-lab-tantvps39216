package com.poly.Lab5_PS39216.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ParamService {
    @Autowired
    private HttpServletRequest yeuCau;

    public String getString(String ten, String giaTriMacDinh) {  // Đổi tên từ layChuoi
        String giaTri = yeuCau.getParameter(ten);
        return (giaTri != null) ? giaTri : giaTriMacDinh;
    }

    public int getInt(String ten, int giaTriMacDinh) {  // Đổi tên từ laySoNguyen
        try {
            return Integer.parseInt(yeuCau.getParameter(ten));
        } catch (NumberFormatException e) {
            return giaTriMacDinh;
        }
    }

    public double getDouble(String ten, double giaTriMacDinh) {  // Đổi tên từ laySoThuc
        try {
            return Double.parseDouble(yeuCau.getParameter(ten));
        } catch (NumberFormatException e) {
            return giaTriMacDinh;
        }
    }

    public boolean getBoolean(String ten, boolean giaTriMacDinh) {  // Đổi tên từ layBoolean
        String giaTri = yeuCau.getParameter(ten);
        return (giaTri != null) ? Boolean.parseBoolean(giaTri) : giaTriMacDinh;
    }

    public Date getDate(String ten, String dinhDang) {  // Đổi tên từ layNgay
        String giaTri = yeuCau.getParameter(ten);
        if (giaTri == null) {
            return null;
        }
        try {
            return new SimpleDateFormat(dinhDang).parse(giaTri);
        } catch (ParseException e) {
            throw new RuntimeException("Định dạng ngày không hợp lệ", e);
        }
    }

    public File saveFile(MultipartFile tapTin, String duongDan) {  // Đổi tên từ luuTapTin
        if (tapTin.isEmpty()) {
            return null;
        }
        try {
            File dich = new File(duongDan, tapTin.getOriginalFilename());
            tapTin.transferTo(dich);
            return dich;
        } catch (IOException e) {
            throw new RuntimeException("Lỗi lưu tệp tin", e);
        }
    }
}
