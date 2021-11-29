package android.example.android.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;


import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    int quantity = 1;
    int priceOfWhippedCream = 1;
    int priceOfChocolate = 2;
    int priceOfCup = 5;
    int price = 5;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void increment(View view) {
        if (quantity == 100) {
            Toast.makeText(this, "You cannot have more than 100 coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    public void decrement(View view) {
        if (quantity == 1) {
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }


    public boolean onCheckBox(View view) {
        CheckBox checkBox = findViewById(R.id.checkBox);
        boolean hasWhippedCream = checkBox.isChecked();

        return hasWhippedCream;
    }

    public boolean onCheckBox2(View view) {
        CheckBox checkBox = findViewById(R.id.checkBox2);
        boolean hasChocolate = checkBox.isChecked();

        return hasChocolate;
    }


    /**
     * Button click ORDER
     */

    public void submitOrder(View view) {

        CheckBox whippedCreamCheckBox = findViewById(R.id.checkBox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox chocolateCheckBox = findViewById(R.id.checkBox2);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        /** EditText */
        EditText nameEditText = findViewById(R.id.name);
        Editable nameEditable = nameEditText.getText();
        String name = nameEditable.toString();

        /** The final price */
        price = calculatePrice(quantity, priceOfCup, hasWhippedCream, hasChocolate);

        /** Message */


        String message = createOrderSummary(name, price, hasWhippedCream, hasChocolate);
        displayMessage(message);

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setDataAndType(Uri.parse("maksymbudzhak00@gmail.com"), "*/*");
        intent.putExtra(Intent.EXTRA_EMAIL, "emailaddress@emailaddress.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Order info");
        intent.putExtra(Intent.EXTRA_TEXT, createOrderSummary(name, price, hasWhippedCream, hasChocolate));


        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * Calculate the final price of the order
     */

    public int calculatePrice(int quantity, int priceOfCup, boolean isWhippedCream, boolean isChocolate) {
        int finalPrice = priceOfCup * quantity;

        if (isWhippedCream) {
            finalPrice = finalPrice + (quantity * priceOfWhippedCream);
        }

        if (isChocolate) {
            finalPrice = finalPrice + (quantity * priceOfChocolate);
        }

        return finalPrice;
    }


    public String createOrderSummary(String name, int price, boolean addWhippedCream, boolean addChocolate) {


        return "Name: " + name + "\n" +
                "Add Whippped Cream: " + addWhippedCream + "\n" +
                "Add Chocolate: " + addChocolate + "\n" +
                "Quantity: " + quantity + "\n" +
                "Total: " + price + "\n" +
                "Thank You!";
    }


    /**
     * This method displays the given quantity value on the screen.
     */

    public void displayQuantity(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


    /**
     * This method displays the given text on the screen.
     */

    public void displayMessage(String message) {
        TextView orderSummaryTextView = findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

}