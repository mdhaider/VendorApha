package com.instafinancials.vendoralpha.apicall.network.pojonew

data class GSTRegistrationDetails (

	val lastUpdatedDateTime : String,
	val gSTIN : String,
	val legalNameOfBusiness : String,
	val registeredState : String,
	val taxpayerType : String,
	val gSTNStatus : String,
	val constitution : String,
	val gSTComplianceLastest6Months : GSTComplianceLastest6Months
)