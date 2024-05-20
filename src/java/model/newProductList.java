/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;


/**
 *
 * @author thinh
 */
public class newProductList {
    private List<Product> listProductNew;
    private String nameList;

    public newProductList() {
    }

    public newProductList(List<Product> listProductNew, String nameList) {
        this.listProductNew = listProductNew;
        this.nameList = nameList;
    }

    public List<Product> getListProductNew() {
        return listProductNew;
    }

    public void setListProductNew(List<Product> listProductNew) {
        this.listProductNew = listProductNew;
    }

    public String getNameList() {
        return nameList;
    }

    public void setNameList(String nameList) {
        this.nameList = nameList;
    }
    
    
}

