package com.instafinancials.vendoralpha.deletellater.network.pojonew

import java.io.Serializable

data class DirectorSignatoryMasterBasic (

	val directorCurrentMasterBasic : DirectorCurrentMasterBasic,
	val signatoryCurrentMasterBasic : SignatoryCurrentMasterBasic
): Serializable