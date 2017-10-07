/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.temel.pgswingexample;

import com.temel.pgswingexample.db.DBOperations;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vektorel
 */
public class KisiDbIslemleri {

    private DBOperations dbOperations = new DBOperations();

    public void kisiEkle(Kisi kisi) {
        String sqlQuery = " insert into kisi (ad,soyad,email,tel,adres) "
                + "values ('" + kisi.getIsim() + "','" + kisi.getSoyisim() + "','" + kisi.getEmail()
                + "','" + kisi.getTel() + "','" + kisi.getAdres() + "') ";

        int count = dbOperations.insert(sqlQuery);
    }

    public void kisiGuncelle(Kisi kisi) {
        String sqlQuery = " update kisi set ad='" + kisi.getIsim() + "', soyad='" + kisi.getSoyisim()
                + "', tel='" + kisi.getTel() + "', email='" + kisi.getEmail() + "', adres ='" + kisi.getAdres() + "' "
                + " where id=" + kisi.getId();

        dbOperations.update(sqlQuery);
    }

    public List<Kisi> kisileriGetir() {
        List<Kisi> list = new ArrayList();
        try {
            String sqlQuery = " select * from kisi ";
            ResultSet resultSet = dbOperations.select(sqlQuery);
            while (resultSet.next()) {
                Kisi k = new Kisi();
                k.setIsim(resultSet.getString("ad"));
                k.setSoyisim(resultSet.getString("soyad"));
                k.setAdres(resultSet.getString("adres"));
                k.setEmail(resultSet.getString("email"));
                k.setId(resultSet.getInt("id"));
                k.setTel(resultSet.getString("tel"));
                list.add(k);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public void kisiSil(Kisi kisi) {
        String sqlQuery=" delete from kisi where id="+kisi.getId();
        dbOperations.delete(sqlQuery);
    }
    
}