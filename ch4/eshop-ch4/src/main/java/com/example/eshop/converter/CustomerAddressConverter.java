package com.example.eshop.converter;

import com.example.eshop.model.type.CustomerAddress;

import javax.persistence.AttributeConverter;

public class CustomerAddressConverter implements AttributeConverter<CustomerAddress, String> {

    @Override
    public String convertToDatabaseColumn(CustomerAddress customerAddress) {
        if(customerAddress == null)
            return "";
        return  customerAddress.getStreetAddress() + ", " +
                customerAddress.getCity() + ", " +
                customerAddress.getCountry();
     }

    @Override
    public CustomerAddress convertToEntityAttribute(String value) {
        CustomerAddress customerAddress = null;
        if(value != null && value.contains(",")) {
            String[] tokens = value.split(",");
            customerAddress = new CustomerAddress();
            customerAddress.setStreetAddress(tokens[0]);
            customerAddress.setCity(tokens[1]);
            customerAddress.setCountry(tokens[2]);
        }
        return customerAddress;
    }
}
