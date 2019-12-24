package com.instafinancials.vendoralpha.ui.activities

import android.R.attr
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.instafinancials.vendoralpha.R
import com.instafinancials.vendoralpha.utils.DigestCreaterUtil
import com.payumoney.core.PayUmoneySdkInitializer.PaymentParam
import com.payumoney.core.entity.TransactionResponse
import com.payumoney.sdkui.ui.utils.PayUmoneyFlowManager
import com.payumoney.sdkui.ui.utils.ResultModel
import kotlinx.android.synthetic.main.activity_checkout.*



class CheckoutActivity : AppCompatActivity() {

    val TAG = "CheckoutActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        bt_checkout.setOnClickListener {
            proceedPayment()
        }
    }

    fun proceedPayment() {
        val amount = et_amount.text.toString()
        val name = et_name.text.toString()
        val email = et_email.text.toString()
        val phone = "9986255521"
        val txId = System.currentTimeMillis().toString()
        val checksum = DigestCreaterUtil.hashCal(amount, name,
            email, txId)
        val builder = PaymentParam.Builder()
        builder.setAmount(amount)
        .setTxnId(txId)
        .setProductName(DigestCreaterUtil.productinfo)                   // Product Name or description
        .setFirstName(name)                              // User First name
        .setEmail(email)
        .setPhone(phone)
            .setIsDebug(true)
            .setKey(DigestCreaterUtil.key)
            .setMerchantId(DigestCreaterUtil.merchantId)
            .setsUrl(DigestCreaterUtil.surl)
            .setfUrl(DigestCreaterUtil.furl)

        //declare paymentParam object
        val paymentParam: PaymentParam = builder.build()
        //set the hash
        //set the hash
        paymentParam.setMerchantHash(checksum)
        /* Invoke the following function to open the checkout page.
            PayUmoneyFlowManager.startPayUMoneyFlow(
            PayUmoneySdkInitializer.PaymentParam paymentParam,
            Activity context,
            int style,
            boolean isOverrideResultScreen) */
        PayUmoneyFlowManager.startPayUMoneyFlow(paymentParam,
            this, R.style.AppTheme_default, false);

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(TAG, "request code " + requestCode + " resultcode " + resultCode)
        if (requestCode == PayUmoneyFlowManager.REQUEST_CODE_PAYMENT && resultCode == RESULT_OK && data !=
            null) {
            val transactionResponse: TransactionResponse =
                data.getParcelableExtra(PayUmoneyFlowManager.INTENT_EXTRA_TRANSACTION_RESPONSE)

            val resultModel: ResultModel =
                data.getParcelableExtra(PayUmoneyFlowManager.ARG_RESULT)

            // Check which object is non-null
            // Check which object is non-null
            if (transactionResponse != null && transactionResponse.getPayuResponse() != null) {
                if (transactionResponse.getTransactionStatus().equals(TransactionResponse.TransactionStatus.SUCCESSFUL)) {
                    Log.d(TAG, "Success Transaction")
                } else {
                    Log.d(TAG, "Failure Transaction")
                }
                // Response from Payumoney
                val payuResponse: String = transactionResponse.getPayuResponse()
                // Response from SURl and FURL
                val merchantResponse: String = transactionResponse.getTransactionDetails()
                AlertDialog.Builder(this)
                    .setCancelable(false)
                    .setMessage("Payu's Data : $payuResponse\n\n\n Merchant's Data: $merchantResponse")
                    .setPositiveButton(
                        R.string.ok,
                        DialogInterface.OnClickListener { dialog, whichButton -> dialog.dismiss() })
                    .show()
            } else if (resultModel != null && resultModel.error != null) {
                Log.d(TAG,"Error response : " + resultModel.error.transactionResponse
                )
            } else {
                Log.d(TAG, "Both objects are null!")
            }
        }
    }
}
