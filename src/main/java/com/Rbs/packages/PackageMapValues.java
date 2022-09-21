package com.Rbs.packages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PackageMapValues {
    private String serviceDesc;
    private int servicePrice;
    private int numPaidServices;
    private int numFreeServices;
    private int expirationMonths;
    private Boolean packageStatus;

    @Override
    public String toString(){
        return "This package is for "+serviceDesc+". \n"+
                "The base service costs $"+servicePrice+". \n"+
                "For this package you will pay for "+numPaidServices+" services. \n"+
                "And you will receive "+numFreeServices+" free service(s). \n";
    }
}
