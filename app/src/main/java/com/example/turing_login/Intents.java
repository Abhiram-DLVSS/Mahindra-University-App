package com.example.turing_login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.widget.NestedScrollView;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.turing_login.timetable.TimeTable;
import com.google.firebase.auth.FirebaseAuth;

import toan.android.floatingactionmenu.FloatingActionButton;
import toan.android.floatingactionmenu.FloatingActionsMenu;

public class Intents extends AppCompatActivity {

    public FloatingActionButton timetable_button,faculty,fee,event,grades,assignments,forms;
    public FloatingActionsMenu floatingmenu;
    public ConstraintLayout tt;
    public View background;
    public View open,close;
    public NestedScrollView fab_scroll;
//    public void first_time(int k){
//        //first time
//        final String PREFS_NAME = "MyPrefsFile";
//
//        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
//        if(k==0)
//            settings.edit().putBoolean("my_first_time", false).commit();
//        else if(k==1)
//            settings.edit().putBoolean("my_first_time", true).commit();
//
//        if (settings.getBoolean("my_first_time", true)) {
//            //the app is being launched for first time, do something
//            Log.d("Comments", "First time");
//
//            // first time task
//            Toast.makeText(this, "First time", Toast.LENGTH_SHORT).show();
//
//            // record the fact that the app has been started at least once
//
//
//        }
//    }
    public void floatinginit(){
        floatingmenu=findViewById(R.id.fm_menu);
        fee=findViewById(R.id.fm_fees);
        faculty=findViewById(R.id.fm_faculty);
        event=findViewById(R.id.fm_events);
        timetable_button=findViewById(R.id.fm_timetable);
        tt=findViewById(R.id.tt_fm);
        grades=findViewById(R.id.fm_grades);
        assignments=findViewById(R.id.fm_assignment);
        forms=findViewById(R.id.fm_forms);
        background=findViewById(R.id.background_dimmer);
        open=findViewById(R.id.open);
        close=findViewById(R.id.closed);
        fab_scroll=findViewById(R.id.fab_menu_scroll);


        floatingmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatingmenu.collapse();
            }
        });
        background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatingmenu.collapse();
            }
        });

        floatingmenu.setOnFloatingActionsMenuUpdateListener(new FloatingActionsMenu.OnFloatingActionsMenuUpdateListener() {
            @Override
            public void onMenuExpanded() {
                timetable_button.setVisibility(View.VISIBLE);
                fee.setVisibility(View.VISIBLE);
                faculty.setVisibility(View.VISIBLE);
                event.setVisibility(View.VISIBLE);
                grades.setVisibility(View.VISIBLE);
                assignments.setVisibility(View.VISIBLE);
                forms.setVisibility(View.VISIBLE);
                background.setVisibility(View.VISIBLE);
                floatingmenu.setIcon(ResourcesCompat.getDrawable(getResources(),R.drawable.ic_multip,null));
              //  statusbar1();
                new Handler().postDelayed(new Runnable() {
                        public void run() {
                            fab_scroll.scrollTo(0,fab_scroll.getBottom());
                        }
                    }, 50);
            }
            @Override
            public void onMenuCollapsed() {
                floatingmenu.setIcon(ResourcesCompat.getDrawable(getResources(),R.drawable.ic_hamburger_icon_svg,null));
                timetable_button.setVisibility(View.GONE);
                fee.setVisibility(View.GONE);
                faculty.setVisibility(View.GONE);
                event.setVisibility(View.GONE);
                grades.setVisibility(View.GONE);
                assignments.setVisibility(View.GONE);
                forms.setVisibility(View.GONE);
                background.setVisibility(View.GONE);
                //statusbar();


            }});




        //Button Clicks
        faculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFaculty();
            }
        });
        timetable_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTimeTable();
            }
        });
        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEvent();
            }
        });
        forms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openForms();
            }
        });fee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://mahindraecolecentrale.unicampus.in/ERPLogin.aspx?type=std");
            }
        });
        fee.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Uri copyUri = Uri.parse("https://mahindraecolecentrale.unicampus.in/ERPLogin.aspx?type=std");
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newUri(getContentResolver(), "URI", copyUri);
                clipboard.setPrimaryClip(clip);


                Toast toast=Toast.makeText(getApplicationContext(),"Link Copied to Clipboard",Toast.LENGTH_SHORT);
                toast.show();
                // Get instance of Vibrator from current Context
                Vibrator vib = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                // Vibrate for 400 milliseconds
                vib.vibrate(40);
                return true;
            }
        });

        grades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("http://www.mu-parentsportal.com/grade");
            }
        });
        grades.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Uri copyUri = Uri.parse("http://www.mu-parentsportal.com/grade");
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newUri(getContentResolver(), "URI", copyUri);
                clipboard.setPrimaryClip(clip);


                Toast toast=Toast.makeText(getApplicationContext(),"Link Copied to Clipboard",Toast.LENGTH_SHORT);
                toast.show();
                // Get instance of Vibrator from current Context
                Vibrator vib = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                // Vibrate for 400 milliseconds
                vib.vibrate(40);
                return true;
            }
        });
        assignments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intents.this, Assignment1.class);
                startActivity(intent);
                floatingmenu.collapse();
                finish();
            }
        });

    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if(floatingmenu.isExpanded())
            floatingmenu.collapse();
        else {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }
    }


    public void statusbar(){
        //To get custom status bar color
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.stan));
    }
    public void statusbar1(){
        //To get custom status bar color
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.black));
    }
    public void openFaculty() {

        Intent intent=new Intent(this,FacultyMenu.class);
        startActivity(intent);
        floatingmenu.collapse();
        finish();
    }
    //Below both are for menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_items,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id= item.getItemId();
        switch(id){
            case R.id.logout_in_menu: {
                            Toast.makeText(Intents.this, "Signing out...", Toast.LENGTH_SHORT).show();
                            FirebaseAuth.getInstance().signOut();
                            startActivity(new Intent(getApplicationContext(), Login.class));
                            finish();
            }
            break;
        }
        return true;
    }

    public void openTimeTable() {
        Intent intent=new Intent(this, TimeTable.class);
        startActivity(intent);
        floatingmenu.collapse();
        finish();
    }

    public void gotoUrl(String s) {

        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
        floatingmenu.collapse();
        finish();
    }

    public void openForms()
    {
        Intent intent=new Intent(this, Forms.class);
        startActivity(intent);
        floatingmenu.collapse();
        finish();
    }
    public void openEvent()
    {

        ProgressDialog nDialog;
        nDialog = new ProgressDialog(Intents.this);
        nDialog.setMessage("Loading the Website");
        nDialog.setIndeterminate(false);
        nDialog.show();
        floatingmenu.collapse();
        Intent intent=new Intent(this, Event.class);
        startActivity(intent);
        nDialog.dismiss();
        finish();
    }
}