package com.instafinancials.vendoralpha.shared

enum class TaxPayerEnum(var taxType:String, var retType:String, var noOfCol:Int) {
    REGULAR("Regular", "GSTR1", 2),
    SEZ_UNIT("SEZ Unit", "GSTR1", 2),
    SEZ_DEV("SEZ Developer", "GSTR1", 2),
    COMPOSITION("Composition", "GSTR4", 1),
    INPUT_SERVICE("Input Service Distributor", "GSTR6", 1),
    TAX_COLL("Tax Collector", "GSTR8", 1),
    TAX_DEDU("Tax Deductor", "GSTR7", 1),
    CASUAL_TAX("Casual Taxable Person", "GSTR3B", 1)
}