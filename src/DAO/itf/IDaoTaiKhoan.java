package DAO.itf;

import model.TaiKhoan;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

public interface IDaoTaiKhoan {
    boolean update();
    boolean create();
    boolean delete();
    TaiKhoan get(String userName);
    List<TaiKhoan> read();
}
