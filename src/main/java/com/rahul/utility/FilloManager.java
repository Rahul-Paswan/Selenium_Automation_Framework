package com.rahul.utility;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;

public class FilloManager {
    private static Connection connection;

    public static Connection getFilloConnection(String excelPath) throws FilloException {
        Fillo fillo = new Fillo();
         connection = fillo.getConnection(excelPath);
        return  connection;
    }

    public static void closeFilloConnection(){
            if(connection!=null){
                connection.close();
            }
    }
}
