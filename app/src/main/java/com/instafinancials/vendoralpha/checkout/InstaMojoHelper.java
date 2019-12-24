package com.instafinancials.vendoralpha.checkout;

public class InstaMojoHelper {

    public static String generateTransactionID() {
        return "test_insta_" + System.currentTimeMillis();
    }

    public static PaymentOrder getPaymentOrderObject() {
        PaymentOrder order = new PaymentOrder();
        order.setName("John Smith");
        order.setEmail("john.smith@gmail.com");
        order.setPhone("12345678790");
        order.setCurrency("INR");
        order.setAmount(9D);
        order.setDescription("This is a test transaction.");
        //order.setRedirectUrl("http://www.someexample.com");
        //order.setWebhookUrl("http://www.someurl.com/");
        order.setTransactionId(generateTransactionID());

        return order;
    }

}
