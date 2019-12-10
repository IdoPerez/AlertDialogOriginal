package com.example.alertdialogoriginal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * @author Ido Perez
 * @version 0.01
 * @since 9/12/19
 */
public class MainActivity extends AppCompatActivity {
    AlertDialog.Builder adb;
    LinearLayout layout;
    int[] color = {0,0,0};
    Intent si;
    String[] Colors = {"RED", "GREEN", "BLUE"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = findViewById(R.id.layout2);

    }

    /**
     * On click open a AlertDialog with List of 3 colors to choose.
     * @param view
     */
    public void ChangeColors(View view) {
        adb = new AlertDialog.Builder(this);
        adb.setTitle("Colors");
        adb.setCancelable(false);
        adb.setItems(Colors, new DialogInterface.OnClickListener() {

            @Override
            /**
             * changing the BackGround color to the chosen color.
             */
            public void onClick(DialogInterface dialog, int which) {
                color[which] = 255;
                layout.setBackgroundColor(Color.rgb(color[0], color[1], color[2]));
                color[which] = 0;
            }
        });

        /**
         * closing the AlertDialog.
         */
        adb.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog Change = adb.create();
        Change.show();
    }

    /**
     * open a AlertDialog with 2 buttons and List of 3 colors.
     * @param view
     */
    public void RGB(View view) {
        adb = new AlertDialog.Builder(this);
        adb.setTitle("Colors");
        adb.setCancelable(false);
        adb.setMultiChoiceItems(Colors, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            /**
             * checking which of the colors chose and changing the screen to the shiluve of them.
             */
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if(isChecked) color[which] = 255;
                else if (color[which] == 255) color[which] = 0;
                layout.setBackgroundColor(Color.rgb(color[0], color[1], color[2]));

            }
        });
        /**
         * reset the changes and dismiss the Dialog.
         */
        adb.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                layout.setBackgroundColor(Color.WHITE);
                dialog.dismiss();
            }
        });
        /**
         * saving the changes and exit the dialog.
         */
        adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog RGB = adb.create();
        RGB.show();
    }

    /**
     * reset the background color.
     * @param view
     */
    public void Reset(View view) {
        layout.setBackgroundColor(Color.WHITE);
    }

    /**
     * input an text and show him on toast.
     * @param view
     */
    public void EditText(View view) {
        adb = new AlertDialog.Builder(this);
        adb.setTitle("Write why u gave me 90");
        adb.setCancelable(false);
        final EditText eT = new EditText(this);
        eT.setHint("explain here");
        adb.setView(eT);
        adb.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
            @Override
            /**
             * Receives the text and tasting him.
             */
            public void onClick(DialogInterface dialog, int which) {
                String st = eT.getText().toString();
                Toast.makeText(MainActivity.this, st, Toast.LENGTH_SHORT).show();
            }
        });
        /**
         * dismiss the dialog.
         */
        adb.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog et = adb.create();
        et.show();
    }

    @Override
    /**
     * option menu with credit and main that moving from the activitis.
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);


        return true;
    }

    /**
     * comparing ids and moving to the chosen activity.
     * @param item
     * @return
     */
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.credits) {
            si = new Intent(this, Credits.class);
            startActivity(si);
        }


        return true;
    }
}
