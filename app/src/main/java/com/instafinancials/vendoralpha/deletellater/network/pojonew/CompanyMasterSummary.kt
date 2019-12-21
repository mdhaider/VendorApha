package com.instafinancials.vendoralpha.deletellater.network.pojonew

import java.io.Serializable

data class CompanyMasterSummary (

	val lastUpdatedDateTime : String,
	val companyName : String,
	val companyMcaStatus : String,
	val companyMcaIndustry : String,
	val companyCategory : String,
	val companyLastBsDate : String,
	val companyPaidUpCapital : Int,
	val companyRegCity : String,
	val companyRegState : String,
	val companyWebSite : String,
	val companyRevenueRange : String,
	val companyCIN : String
):Serializable