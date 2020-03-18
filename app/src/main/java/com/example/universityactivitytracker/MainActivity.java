package com.example.universityactivitytracker;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.TaskStackBuilder;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        RecordAsync recordAsync = new RecordAsync(getApplicationContext());
        List<Record> records = recordAsync.getAllRecords();
        String info = "";
        textView = findViewById(R.id.textView);

        for(Record record : records){
            int id = record.getUid();
            String module = record.getModule();
            String notes = record.getNote();
            Date clockIn = record.getClockIn();
            Date clockOut = record.getClockOut();

            info += "\n\n" + "ID: " + id + "\n Module: "+ module + "\n Notes: " + notes + "\n Clock In: " +
                    clockIn + "\n Clock Out: " + clockOut;
        }
        textView.setText(info);
    }

}
