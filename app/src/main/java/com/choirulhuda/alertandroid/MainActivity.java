package com.choirulhuda.alertandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void actionSimpleAlert(View view) {
        AlertDialog.Builder ab = new AlertDialog.Builder(this);
        ab.setTitle(R.string.warning)
                .setMessage(R.string.gampang_bukan)
                .setIcon(R.drawable.ic_error)
                .setCancelable(false)
                .setPositiveButton(R.string.ya, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(), "Mudah Sekali!", Toast.LENGTH_SHORT).show();
                        dialogInterface.dismiss();
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(), "Susah Banget!", Toast.LENGTH_SHORT).show();
                        dialogInterface.dismiss();
                    }
                })
                .setNeutralButton("Netral", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(), "Biasa Aja!", Toast.LENGTH_SHORT).show();
                        dialogInterface.dismiss();
                    }
                });

        AlertDialog alertDialog = ab.create();
        alertDialog.show();
    }

    public void actionListAlert(View view) {
        final ArrayAdapter<String> choiceAdapater = new ArrayAdapter<>(this,
                android.R.layout.select_dialog_singlechoice);
        choiceAdapater.add("Sangat Mudah");
        choiceAdapater.add("Mudah");
        choiceAdapater.add("Netral");
        choiceAdapater.add("Susah");
        choiceAdapater.add("Susah Sekali");

        String[] items = {"Mudah Sekali", "Susah"};


        AlertDialog.Builder singleChoice = new AlertDialog.Builder(this);
        singleChoice.setTitle("Bagaimana, mudah bukan?: ")
                    .setIcon(R.drawable.ic_list)
                    .setAdapter(choiceAdapater, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(getApplicationContext(), choiceAdapater.getItem(i), Toast.LENGTH_LONG).show();
                        }
                    });

        AlertDialog alertDialog = singleChoice.create();
        alertDialog.show();

    }

    public void actionCheckBoxAlert(View view) {
        final String[] items = {"Sangat Mudah", "Mudah", "Netral", "Susah", "Susah Sekali"};
        boolean[] checkItems = {false, false, false, false, false};
        final ArrayList<Integer> selectedItem = new ArrayList<>();

        final AlertDialog.Builder multiChoice = new AlertDialog.Builder(this);
        multiChoice.setTitle("Bagaimana, gampangkan? ")
                   .setIcon(R.drawable.ic_check)
                   .setMultiChoiceItems(items, checkItems, new DialogInterface.OnMultiChoiceClickListener() {
                       @Override
                       public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                            if (b) {
                                selectedItem.add(i);
                            } else if (selectedItem.contains(i)){
                                selectedItem.remove(i);
                                selectedItem.notify();
                            }
                       }
                   })
                    .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ArrayList<String> selectedString = new ArrayList<>();
                            for (int a = 0; a < selectedItem.size(); a++) {
                                selectedString.add(items[selectedItem.get(a)]);
                            }

                            Toast.makeText(getApplicationContext(), "Level Kemudahan :"+ Arrays.toString(selectedString.toArray()), Toast.LENGTH_SHORT).show();

                            dialogInterface.dismiss();
                        }
                    });

        AlertDialog alertDialog = multiChoice.create();
        alertDialog.show();
    }
}