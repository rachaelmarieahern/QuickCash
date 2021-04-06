package com.example.quickcash.View;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quickcash.Config;
import com.example.quickcash.PayPalViewModel;
import com.example.quickcash.PaymentDetails;
import com.example.quickcash.R;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;

import java.math.BigDecimal;

/* Tutorial Source: lentimo.medium.com/paypal-integration-on-android-c655480ae6b2

 */
public class PayPalActivity extends AppCompatActivity {
    String amount;
    EditText edtAmount;
    private static final int PAYPAL_REQUEST_CODE = 7777;
    private static PayPalConfiguration config = new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(Config.PAYPAL_CLIENT_ID);

    @Override
    protected void onDestroy() {
        stopService(new Intent(this, PayPalService.class));
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
        super.onDestroy();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paypal);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);

        //start paypal service
        Intent intent = new Intent(this, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        startService(intent);

        Button payNow = (Button) findViewById(R.id.btnPayNow);

        edtAmount = (EditText) findViewById(R.id.edtAmount);
        Log.d("Edit Amount: ", edtAmount.toString());




        payNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processPayment();
            }
        });
    }


    private void processPayment() {
        amount = edtAmount.getText().toString();
        Log.d("Amount: ", amount);
        BigDecimal finalAmount = BigDecimal.valueOf(Double.parseDouble(amount));
        PayPalPayment payPalPayment = new PayPalPayment(finalAmount, "CAD",
                "Pay", PayPalPayment.PAYMENT_INTENT_SALE);


        Intent intent = new Intent(this, PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payPalPayment);
        startActivityForResult(intent, PAYPAL_REQUEST_CODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PAYPAL_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                PaymentConfirmation confirmation = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if (confirmation != null) {
                    try {
                        String paymentDetails = confirmation.toJSONObject().toString(4);
                        startActivity(new Intent(this, PaymentDetails.class)
                                .putExtra("Payment Details", paymentDetails)
                                .putExtra("Amount", amount));
                    } catch (JSONException e) {

                        e.printStackTrace();
                    }
                }

            } else if (resultCode == Activity.RESULT_CANCELED)

                Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show();

        } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID)

            Toast.makeText(this, "Invalid", Toast.LENGTH_SHORT).show();
    }
}