
package com.instafinancials.vendoralpha.apicall.network.pojonew2;


public class GSTComplianceRecord {

    private String returnType;
    private String taxPeriod;
    private String financialYear;
    private String filingDate;
    private String filingStatus;
    private String dueDateForTheMonth;

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String getTaxPeriod() {
        return taxPeriod;
    }

    public void setTaxPeriod(String taxPeriod) {
        this.taxPeriod = taxPeriod;
    }

    public String getFinancialYear() {
        return financialYear;
    }

    public void setFinancialYear(String financialYear) {
        this.financialYear = financialYear;
    }

    public String getFilingDate() {
        return filingDate;
    }

    public void setFilingDate(String filingDate) {
        this.filingDate = filingDate;
    }

    public String getFilingStatus() {
        return filingStatus;
    }

    public void setFilingStatus(String filingStatus) {
        this.filingStatus = filingStatus;
    }

    public String getDueDateForTheMonth() {
        return dueDateForTheMonth;
    }

    public void setDueDateForTheMonth(String dueDateForTheMonth) {
        this.dueDateForTheMonth = dueDateForTheMonth;
    }

}
