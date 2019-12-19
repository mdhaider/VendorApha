
package com.instafinancials.vendoralpha.apicall.network.pojonew2;


public class GSTRegistrationDetails {

    private String lastUpdatedDateTime;
    private String gSTIN;
    private String legalNameOfBusiness;
    private String registeredState;
    private String taxpayerType;
    private String gSTNStatus;
    private String constitution;
    private GSTComplianceLastest6Months gSTComplianceLastest6Months;

    public String getLastUpdatedDateTime() {
        return lastUpdatedDateTime;
    }

    public void setLastUpdatedDateTime(String lastUpdatedDateTime) {
        this.lastUpdatedDateTime = lastUpdatedDateTime;
    }

    public String getGSTIN() {
        return gSTIN;
    }

    public void setGSTIN(String gSTIN) {
        this.gSTIN = gSTIN;
    }

    public String getLegalNameOfBusiness() {
        return legalNameOfBusiness;
    }

    public void setLegalNameOfBusiness(String legalNameOfBusiness) {
        this.legalNameOfBusiness = legalNameOfBusiness;
    }

    public String getRegisteredState() {
        return registeredState;
    }

    public void setRegisteredState(String registeredState) {
        this.registeredState = registeredState;
    }

    public String getTaxpayerType() {
        return taxpayerType;
    }

    public void setTaxpayerType(String taxpayerType) {
        this.taxpayerType = taxpayerType;
    }

    public String getGSTNStatus() {
        return gSTNStatus;
    }

    public void setGSTNStatus(String gSTNStatus) {
        this.gSTNStatus = gSTNStatus;
    }

    public String getConstitution() {
        return constitution;
    }

    public void setConstitution(String constitution) {
        this.constitution = constitution;
    }

    public GSTComplianceLastest6Months getGSTComplianceLastest6Months() {
        return gSTComplianceLastest6Months;
    }

    public void setGSTComplianceLastest6Months(GSTComplianceLastest6Months gSTComplianceLastest6Months) {
        this.gSTComplianceLastest6Months = gSTComplianceLastest6Months;
    }

}
