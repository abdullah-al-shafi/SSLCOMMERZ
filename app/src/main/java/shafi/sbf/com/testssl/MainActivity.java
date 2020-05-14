package shafi.sbf.com.testssl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCAdditionalInitializer;
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCCustomerInfoInitializer;
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCEMITransactionInitializer;
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCProductInitializer;
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCShipmentInfoInitializer;
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCommerzInitialization;
import com.sslwireless.sslcommerzlibrary.model.response.SSLCTransactionInfoModel;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCCurrencyType;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCSdkType;
import com.sslwireless.sslcommerzlibrary.view.singleton.IntegrateSSLCommerz;
import com.sslwireless.sslcommerzlibrary.viewmodel.listener.SSLCTransactionResponseListener;

public class MainActivity extends AppCompatActivity implements SSLCTransactionResponseListener {


    EditText amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amount = findViewById(R.id.amount);

//
//        final SSLCCustomerInfoInitializer customerInfoInitializer = new SSLCCustomerInfoInitializer("customer name", "customer email",
//                "address", "dhaka", "1214", "Bangladesh","123456");
//
//        final SSLCEMITransactionInitializer emiTransactionInitializer = new SSLCEMITransactionInitializer(1);
//
//        final SSLCProductInitializer productInitializer = new SSLCProductInitializer ("food", "food",
//                new SSLCProductInitializer.ProductProfile.TravelVertical("Travel", "10",
//                        "A", "12", "Dhk-Syl"));
//
//        final SSLCShipmentInfoInitializer shipmentInfoInitializer = new SSLCShipmentInfoInitializer ("Courier",
//                2, new SSLCShipmentInfoInitializer.ShipmentDetails("AA","Address 1",
//                "Dhaka","1000","BD"));
//
//
//
//        IntegrateSSLCommerz
//                .getInstance(context)
//                .addSSLCommerzInitialization(sslCommerzInitialization)
//                .addEMITransactionInitializer(emiTransactionInitializer)
//                .addCustomerInfoInitializer(customerInfoInitializer)
//                .addProductInitializer(productInitializer)
//                .buildApiCall(MainActivity.this);

    }

    @Override
    public void transactionSuccess(SSLCTransactionInfoModel sslcTransactionInfoModel) {

        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void transactionFail(String s) {
        Toast.makeText(this, " "+s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void merchantValidationError(String s) {
        Toast.makeText(this, " "+s, Toast.LENGTH_SHORT).show();
    }

    public void pay(View view) {

        String amou = amount.getText().toString();

        Double total = Double.parseDouble(amou);

        final SSLCommerzInitialization sslCommerzInitialization = new SSLCommerzInitialization ("Your Store ID","Your Store Password", total, SSLCCurrencyType.BDT,"ajbdsncncvlk", "Fees", SSLCSdkType.TESTBOX);


        final SSLCCustomerInfoInitializer customerInfoInitializer = new SSLCCustomerInfoInitializer("Shafi", "abdullah_al.shafi@outlook.com",
                "address", "dhaka", "1200", "Bangladesh", "123456789");

        final SSLCAdditionalInitializer additionalInitializer = new SSLCAdditionalInitializer();
        additionalInitializer.setValueA("10");


        IntegrateSSLCommerz
                .getInstance(view.getContext())
                .addSSLCommerzInitialization(sslCommerzInitialization)
                .addCustomerInfoInitializer(customerInfoInitializer)
                .buildApiCall(this);
    }

}
