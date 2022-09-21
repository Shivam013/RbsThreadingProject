package com.Rbs.packages;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Transient;
import java.util.HashMap;
import java.util.Set;

@Getter
public class PackageMap {

    private HashMap<Integer,PackageMapValues> packageIdToVals = new HashMap<>();

    public PackageMap(int id, String serviceDesc, int servicePrice, int numPaidServices, int numFreeServices, int expirationMonths, Boolean packageStatus) {
        this.packageIdToVals.put(id,new PackageMapValues(
                serviceDesc,servicePrice,numPaidServices,numFreeServices,expirationMonths,packageStatus
        ));
    }

    @Override
    public String toString(){
        Set<Integer> idList = this.packageIdToVals.keySet();
        String s = "";
        for(Integer i: idList){
            s +="Package ID: "+i+". \n" + this.packageIdToVals.get(i).toString();
        }
        return s;
    }

}
