package com.instafinancials.vendoralpha.deletellater.network.homeapi.instabasic.pojo;

import java.io.Serializable;

public class InstaBasic implements Serializable {
    private ChargesMasterBasic ChargesMasterBasic;

    private CompanyMasterSummary CompanyMasterSummary;

    private DirectorSignatoryMasterBasic DirectorSignatoryMasterBasic;

    @Override
    public String toString() {
        return "InstaBasic{" +
                "ChargesMasterBasic=" + ChargesMasterBasic +
                ", CompanyMasterSummary=" + CompanyMasterSummary +
                ", DirectorSignatoryMasterBasic=" + DirectorSignatoryMasterBasic +
                '}';
    }
}
