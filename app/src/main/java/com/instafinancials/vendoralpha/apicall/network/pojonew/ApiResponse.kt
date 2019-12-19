package com.instafinancials.vendoralpha.apicall.network.pojonew

data class ApiResponse (
	val gSTInformationAndCompliance : GSTInformationAndCompliance,
	val companyMasterSummary : CompanyMasterSummary,
	val directorSignatoryMasterBasic : DirectorSignatoryMasterBasic,
	val potentialRelatedPartyMasterBasic : PotentialRelatedPartyMasterBasic
)