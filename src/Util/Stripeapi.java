package Util;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import com.stripe.net.ApiResource;
import com.stripe.net.RequestOptions;
import com.stripe.param.checkout.SessionCreateParams;

import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Stripeapi {
    public void Payer(String montant) {
        String cardNumber ;
        int expMonth ;
        int expYear ;
        String cvc ;

        // Verify the card using the Stripe API

            Stripe.apiKey = "sk_test_51MidOiDi4uLa1UR4QzBVLHTMxTOlUHa9RnQRtqkFmwxWciJlPRdoI6BWkRj0C9wXfXSCanlbW3vGha3JIp08N2kc00EvGQ49ci"; // Replace with your actual secret API key.

        Map<String, Object> chargeParam = new HashMap<String,Object>();
        chargeParam.put("amount",montant);
        chargeParam.put("currency","usd");
        chargeParam.put("customer","cus_NR5HqQQzFnoKA9");


        try {
            Charge.create(chargeParam);
            System.out.println("transaction done");
        } catch (StripeException e) {
            System.out.println("error");
            throw new RuntimeException(e);
        }


    }
        public void verifyCardAndPay(/*String email*/ String cardNumber, int expMonth, int expYear, String cvc /* String customerId*/,String montant , String cardholderName) throws StripeException {


            Stripe.apiKey = "sk_test_51MidOiDi4uLa1UR4QzBVLHTMxTOlUHa9RnQRtqkFmwxWciJlPRdoI6BWkRj0C9wXfXSCanlbW3vGha3JIp08N2kc00EvGQ49ci";

   

            Map<String, Object> cardParams = new HashMap<>();
            cardParams.put("number", cardNumber);
            cardParams.put("exp_month", expMonth);
            cardParams.put("exp_year", expYear);
            cardParams.put("cvc", cvc);
            cardParams.put("name", cardholderName);
            Map<String, Object> tokenParams = new HashMap<>();
            tokenParams.put("card", cardParams);

            try {
                Token token = Token.create(tokenParams);


                // Use the test token ID to create a charge
                Map<String, Object> chargeParams = new HashMap<>();
                chargeParams.put("amount", montant); // Charge amount in cents
                chargeParams.put("currency", "EUR"); // Charge currency
                chargeParams.put("description", "Test charge");
                chargeParams.put("source", token.getId());

                Charge charge = Charge.create(chargeParams);
                System.out.println("Charge succeeded!");
            } catch (StripeException e) {
                System.out.println("Charge failed: " + e.getMessage());
            }


        }}




