package birt.service;

import birt.domain.Invoice;
import birt.domain.Service;
import birt.domain.User;

import java.util.ArrayList;
import java.util.Date;

/**
 * Service folosit la generarea datelor pentru dataset-ul construit din Java
 */

public class POJOReportService {

    public POJOReportService() {
    }

    public ArrayList<Object> getUser(String type){
        ArrayList<Object> data = new ArrayList<>();
        switch (type){
            case "Customer":{
                data.add(new User("Name 1", "Last 1", "CIF1", "Address 1", "iban1", "bank1"));
                break;
            }
            case "Provider":{
                data.add(new User("Name 2", "Last 2", "CIF2", "Address 2", "iban2", "bank2"));
                break;
            }
            case "Invoice":{
                data.add(new Invoice(new Date(), 10));
                break;
            }
            case "Service":{
                data.add(new Service("Service mock", 5, 5, 5.5, 24.0));
                data.add(new Service("Service mock 2", 10, 10, 10.5, 24.0));
                break;
            }
            default: assert false;
        }
        return data;
    }

}
