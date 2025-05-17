package com.rahul.dataprovider.dataprovider_99031;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Recordset;
import com.rahul.utility.FilloManager;
import org.testng.annotations.DataProvider;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;


public class DataProvider_99031 {
    private static Connection connection=null;

    @DataProvider(name = "SignUpDataProvider")
    public Object[] getSignUpData() {
        Object[] Data=null;
        try {
            connection = FilloManager.getFilloConnection(System.getProperty("user.dir") + "\\src\\test\\resources\\99031_TestData\\TestData_99031.xlsx");
            String query = "SELECT * FROM SignUp_Data where Flag='yes'";
            Recordset recordset = connection.executeQuery(query);
            Data = new Object[recordset.getCount()];
            ArrayList<Map> ArrData = new ArrayList<>();;
            Map<String, String> Mapdata;
            while (recordset.next()) {
                Mapdata = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
                for (String fieldName : recordset.getFieldNames()) {
                    Mapdata.put(fieldName, recordset.getField(fieldName));
                }
                ArrData.add(Mapdata);
            }
            Data = ArrData.toArray();
        } catch (FilloException e) {
            System.out.println("No Any Record Found");
        }
        FilloManager.closeFilloConnection();
        return Data;
    }


    @DataProvider(name = "CorrectLoginDataProvider")
    public Object[] getLoginData() {
        Object[] Data=null;
        try {
            connection = FilloManager.getFilloConnection(System.getProperty("user.dir") + "\\src\\test\\resources\\99031_TestData\\TestData_99031.xlsx");
            String query = "SELECT * FROM LoginData where Flag='Correct'";
            Recordset recordset = connection.executeQuery(query);
            Data = new Object[recordset.getCount()];
            ArrayList<Map> ArrData = new ArrayList<>();
            Map<String, String> Mapdata;
            while (recordset.next()) {
                Mapdata = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
                for (String fieldName : recordset.getFieldNames()) {
                    Mapdata.put(fieldName, recordset.getField(fieldName));
                }
                ArrData.add(Mapdata);
            }
            System.out.println("Size of ArrayLisy"+ArrData.size());
            Data = ArrData.toArray();
        } catch (FilloException e) {
            System.out.println("No Any Record Found");
        }
        FilloManager.closeFilloConnection();
        return Data;
    }

    @DataProvider(name = "InCorrectLoginDataProvider")
    public Object[] getLoginData_2() {
        Object[] Data=null;
        try {
            connection = FilloManager.getFilloConnection(System.getProperty("user.dir") + "\\src\\test\\resources\\99031_TestData\\TestData_99031.xlsx");
            String query = "SELECT * FROM LoginData where Flag='Incorrect'";
            Recordset recordset = connection.executeQuery(query);
            Data = new Object[recordset.getCount()];
            ArrayList<Map> ArrData = new ArrayList<>();;
            Map<String, String> Mapdata;
            while (recordset.next()) {
                Mapdata = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
                for (String fieldName : recordset.getFieldNames()) {
                    Mapdata.put(fieldName, recordset.getField(fieldName));
                }
                ArrData.add(Mapdata);
            }
            System.out.println("Size of ArrayLisy"+ArrData.size());
            Data = ArrData.toArray();
        } catch (FilloException e) {
            System.out.println("No Any Record Found");
        }
        FilloManager.closeFilloConnection();
        return Data;
    }
}
