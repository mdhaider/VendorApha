package com.instafinancials.vendoralpha.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.instafinancials.vendoralpha.R
import com.instafinancials.vendoralpha.checkout.InstaMojoHelper
import com.instafinancials.vendoralpha.extensions.showToast
import com.instamojo.android.Instamojo
import com.instamojo.android.Instamojo.InstamojoPaymentCallback
import kotlinx.android.synthetic.main.activity_checkout.*


class CheckoutActivity : AppCompatActivity(), InstamojoPaymentCallback {
 val TAG = "CheckoutActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)
        Instamojo.getInstance().initialize(this, Instamojo.Environment.TEST)
        bt_checkout.setOnClickListener {
            initiateSDKPayment("af387b6f-bb4e-4ce0-80b5-f4bca9a7b2c3")
        }
    }

    override fun onPaymentCancelled() {
        Log.d(TAG, "Payment cancelled");    }

    override fun onInstamojoPaymentComplete(orderID: String?, transactionID: String?, paymentID: String?, paymentStatus: String?) {
        Log.d(TAG, "Payment complete. Order ID: " + orderID + ", Transaction ID: " + transactionID
                + ", Payment ID:" + paymentID + ", Status: " + paymentStatus);    }

    override fun onInitiatePaymentFailure(errorMessage: String?) {
        Log.d(TAG, "Initiate payment failed");
        showToast("Initiating payment failed. Error: " + errorMessage);
    }

    private fun initiateSDKPayment(orderID: String) {
        Instamojo.getInstance().initiatePayment(this, orderID, this)
    }
}
