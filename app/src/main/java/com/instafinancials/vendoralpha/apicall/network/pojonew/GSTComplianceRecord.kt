package com.instafinancials.vendoralpha.apicall.network.pojonew

data class GSTComplianceRecord(

    val returnType: String,
    val taxPeriod: String,
    val financialYear: String,
    val filingDate: String,
    val filingStatus: String,
    val dueDateForTheMonth: String
)