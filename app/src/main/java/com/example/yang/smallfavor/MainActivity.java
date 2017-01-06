package com.example.yang.smallfavor;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private int flag = 0;//0:main 1:intelligence 2:labor
    private String myaccount;
    private String myaddress;
    public String depart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle bundle = this.getIntent().getExtras();
        myaccount = bundle.getString("account");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        main_layout();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode==event.KEYCODE_BACK){
            if(flag==0){
                logout();
            }else if(flag==1 || flag==2){
                main_layout();
            }else if(flag==21 || flag==22){
                labor_layout();
            }else if(flag==3 || flag==4 || flag==6 || flag==7){
                main_layout();
            }else if(flag==31){
                task_layout();
            }else if(flag==41){
                task2do_layout();
            }else if(flag==11){
                intelligence_layout();
            }else if(flag==71){
                myprobem_layout();
            }else if(flag==5 || flag==51){
                intelligence_sec_layout(depart);
            }
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_version) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("This is Version 1.0");
            builder.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.drawer_menu) {
            main_layout();
        } else if (id == R.id.drawer_intelligence) {
            intelligence_layout();
        } else if (id == R.id.drawer_labor) {
            labor_layout();
        } else if (id == R.id.favorite) {
            myprobem_layout();
        } else if (id == R.id.drawer_task) {
            task_layout();
        } else if (id == R.id.drawer_logout) {
            logout();
        } else if (id == R.id.drawer_task2do) {
            task2do_layout();
        } else if (id == R.id.drawer_transaction){
            transaction_layout();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void logout(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("確定登出？");
        builder.setPositiveButton("確認", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(MainActivity.this, Login_Activity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }
    private void SetLayout(int layout){// 0:main 1:intelligence 2:labor 3:account
        switch(layout){
            case 0:
                findViewById(R.id.main_layout).setVisibility(View.VISIBLE);
                findViewById(R.id.intelligence_layout).setVisibility(View.GONE);
                findViewById(R.id.intelligence_sec_layout).setVisibility(View.GONE);
                findViewById(R.id.labor_layout).setVisibility(View.GONE);
                findViewById(R.id.labor_add_layout).setVisibility(View.GONE);
                findViewById(R.id.labor_content_layout).setVisibility(View.GONE);
                findViewById(R.id.task_layout).setVisibility(View.GONE);
                findViewById(R.id.task_content_layout).setVisibility(View.GONE);
                findViewById(R.id.task2do_layout).setVisibility(View.GONE);
                findViewById(R.id.tas2do_content_layout).setVisibility(View.GONE);
                findViewById(R.id.problem_layout).setVisibility(View.GONE);
                findViewById(R.id.problem_edit_layout).setVisibility(View.GONE);
                findViewById(R.id.myproblem_layout).setVisibility(View.GONE);
                findViewById(R.id.myproblem_list_content_layout).setVisibility(View.GONE);
                findViewById(R.id.transaction_layout).setVisibility(View.GONE);
                break;
            case 1:
                findViewById(R.id.main_layout).setVisibility(View.GONE);
                findViewById(R.id.intelligence_layout).setVisibility(View.VISIBLE);
                findViewById(R.id.intelligence_sec_layout).setVisibility(View.GONE);
                findViewById(R.id.labor_layout).setVisibility(View.GONE);
                findViewById(R.id.labor_add_layout).setVisibility(View.GONE);
                findViewById(R.id.labor_content_layout).setVisibility(View.GONE);
                findViewById(R.id.task_layout).setVisibility(View.GONE);
                findViewById(R.id.task_content_layout).setVisibility(View.GONE);
                findViewById(R.id.task2do_layout).setVisibility(View.GONE);
                findViewById(R.id.tas2do_content_layout).setVisibility(View.GONE);
                findViewById(R.id.problem_layout).setVisibility(View.GONE);
                findViewById(R.id.problem_edit_layout).setVisibility(View.GONE);
                findViewById(R.id.myproblem_layout).setVisibility(View.GONE);
                findViewById(R.id.myproblem_list_content_layout).setVisibility(View.GONE);
                findViewById(R.id.transaction_layout).setVisibility(View.GONE);
                break;
            case 11:
                findViewById(R.id.main_layout).setVisibility(View.GONE);
                findViewById(R.id.intelligence_layout).setVisibility(View.GONE);
                findViewById(R.id.intelligence_sec_layout).setVisibility(View.VISIBLE);
                findViewById(R.id.labor_layout).setVisibility(View.GONE);
                findViewById(R.id.labor_add_layout).setVisibility(View.GONE);
                findViewById(R.id.labor_content_layout).setVisibility(View.GONE);
                findViewById(R.id.task_layout).setVisibility(View.GONE);
                findViewById(R.id.task_content_layout).setVisibility(View.GONE);
                findViewById(R.id.task2do_layout).setVisibility(View.GONE);
                findViewById(R.id.tas2do_content_layout).setVisibility(View.GONE);
                findViewById(R.id.problem_layout).setVisibility(View.GONE);
                findViewById(R.id.problem_edit_layout).setVisibility(View.GONE);
                findViewById(R.id.myproblem_layout).setVisibility(View.GONE);
                findViewById(R.id.myproblem_list_content_layout).setVisibility(View.GONE);
                findViewById(R.id.transaction_layout).setVisibility(View.GONE);
                break;
            case 2:
                findViewById(R.id.main_layout).setVisibility(View.GONE);
                findViewById(R.id.intelligence_layout).setVisibility(View.GONE);
                findViewById(R.id.intelligence_sec_layout).setVisibility(View.GONE);
                findViewById(R.id.labor_layout).setVisibility(View.VISIBLE);
                findViewById(R.id.labor_add_layout).setVisibility(View.GONE);
                findViewById(R.id.labor_content_layout).setVisibility(View.GONE);
                findViewById(R.id.task_layout).setVisibility(View.GONE);
                findViewById(R.id.task_content_layout).setVisibility(View.GONE);
                findViewById(R.id.task2do_layout).setVisibility(View.GONE);
                findViewById(R.id.tas2do_content_layout).setVisibility(View.GONE);
                findViewById(R.id.problem_layout).setVisibility(View.GONE);
                findViewById(R.id.problem_edit_layout).setVisibility(View.GONE);
                findViewById(R.id.myproblem_layout).setVisibility(View.GONE);
                findViewById(R.id.myproblem_list_content_layout).setVisibility(View.GONE);
                findViewById(R.id.transaction_layout).setVisibility(View.GONE);
                break;
            case 21:
                findViewById(R.id.main_layout).setVisibility(View.GONE);
                findViewById(R.id.intelligence_layout).setVisibility(View.GONE);
                findViewById(R.id.intelligence_sec_layout).setVisibility(View.GONE);
                findViewById(R.id.labor_layout).setVisibility(View.GONE);
                findViewById(R.id.labor_add_layout).setVisibility(View.VISIBLE);
                findViewById(R.id.labor_content_layout).setVisibility(View.GONE);
                findViewById(R.id.task_layout).setVisibility(View.GONE);
                findViewById(R.id.task_content_layout).setVisibility(View.GONE);
                findViewById(R.id.task2do_layout).setVisibility(View.GONE);
                findViewById(R.id.tas2do_content_layout).setVisibility(View.GONE);
                findViewById(R.id.problem_layout).setVisibility(View.GONE);
                findViewById(R.id.problem_edit_layout).setVisibility(View.GONE);
                findViewById(R.id.myproblem_layout).setVisibility(View.GONE);
                findViewById(R.id.myproblem_list_content_layout).setVisibility(View.GONE);
                findViewById(R.id.transaction_layout).setVisibility(View.GONE);
                break;
            case 22:
                findViewById(R.id.main_layout).setVisibility(View.GONE);
                findViewById(R.id.intelligence_layout).setVisibility(View.GONE);
                findViewById(R.id.intelligence_sec_layout).setVisibility(View.GONE);
                findViewById(R.id.labor_layout).setVisibility(View.GONE);
                findViewById(R.id.labor_add_layout).setVisibility(View.GONE);
                findViewById(R.id.labor_content_layout).setVisibility(View.VISIBLE);
                findViewById(R.id.task_layout).setVisibility(View.GONE);
                findViewById(R.id.task_content_layout).setVisibility(View.GONE);
                findViewById(R.id.task2do_layout).setVisibility(View.GONE);
                findViewById(R.id.tas2do_content_layout).setVisibility(View.GONE);
                findViewById(R.id.problem_layout).setVisibility(View.GONE);
                findViewById(R.id.problem_edit_layout).setVisibility(View.GONE);
                findViewById(R.id.myproblem_layout).setVisibility(View.GONE);
                findViewById(R.id.myproblem_list_content_layout).setVisibility(View.GONE);
                findViewById(R.id.transaction_layout).setVisibility(View.GONE);
                break;
            case 3:
                findViewById(R.id.main_layout).setVisibility(View.GONE);
                findViewById(R.id.intelligence_layout).setVisibility(View.GONE);
                findViewById(R.id.intelligence_sec_layout).setVisibility(View.GONE);
                findViewById(R.id.labor_layout).setVisibility(View.GONE);
                findViewById(R.id.labor_add_layout).setVisibility(View.GONE);
                findViewById(R.id.labor_content_layout).setVisibility(View.GONE);
                findViewById(R.id.task_layout).setVisibility(View.VISIBLE);
                findViewById(R.id.task_content_layout).setVisibility(View.GONE);
                findViewById(R.id.task2do_layout).setVisibility(View.GONE);
                findViewById(R.id.tas2do_content_layout).setVisibility(View.GONE);
                findViewById(R.id.problem_layout).setVisibility(View.GONE);
                findViewById(R.id.problem_edit_layout).setVisibility(View.GONE);
                findViewById(R.id.myproblem_layout).setVisibility(View.GONE);
                findViewById(R.id.myproblem_list_content_layout).setVisibility(View.GONE);
                findViewById(R.id.transaction_layout).setVisibility(View.GONE);
                break;
            case 31:
                findViewById(R.id.main_layout).setVisibility(View.GONE);
                findViewById(R.id.intelligence_layout).setVisibility(View.GONE);
                findViewById(R.id.intelligence_sec_layout).setVisibility(View.GONE);
                findViewById(R.id.labor_layout).setVisibility(View.GONE);
                findViewById(R.id.labor_add_layout).setVisibility(View.GONE);
                findViewById(R.id.labor_content_layout).setVisibility(View.GONE);
                findViewById(R.id.task_layout).setVisibility(View.GONE);
                findViewById(R.id.task_content_layout).setVisibility(View.VISIBLE);
                findViewById(R.id.task2do_layout).setVisibility(View.GONE);
                findViewById(R.id.tas2do_content_layout).setVisibility(View.GONE);
                findViewById(R.id.problem_layout).setVisibility(View.GONE);
                findViewById(R.id.problem_edit_layout).setVisibility(View.GONE);
                findViewById(R.id.myproblem_layout).setVisibility(View.GONE);
                findViewById(R.id.myproblem_list_content_layout).setVisibility(View.GONE);
                findViewById(R.id.transaction_layout).setVisibility(View.GONE);
                break;
            case 4:
                findViewById(R.id.main_layout).setVisibility(View.GONE);
                findViewById(R.id.intelligence_layout).setVisibility(View.GONE);
                findViewById(R.id.intelligence_sec_layout).setVisibility(View.GONE);
                findViewById(R.id.labor_layout).setVisibility(View.GONE);
                findViewById(R.id.labor_add_layout).setVisibility(View.GONE);
                findViewById(R.id.labor_content_layout).setVisibility(View.GONE);
                findViewById(R.id.task_layout).setVisibility(View.GONE);
                findViewById(R.id.task_content_layout).setVisibility(View.GONE);
                findViewById(R.id.task2do_layout).setVisibility(View.VISIBLE);
                findViewById(R.id.tas2do_content_layout).setVisibility(View.GONE);
                findViewById(R.id.problem_layout).setVisibility(View.GONE);
                findViewById(R.id.problem_edit_layout).setVisibility(View.GONE);
                findViewById(R.id.myproblem_layout).setVisibility(View.GONE);
                findViewById(R.id.myproblem_list_content_layout).setVisibility(View.GONE);
                findViewById(R.id.transaction_layout).setVisibility(View.GONE);
                break;
            case 41:
                findViewById(R.id.main_layout).setVisibility(View.GONE);
                findViewById(R.id.intelligence_layout).setVisibility(View.GONE);
                findViewById(R.id.intelligence_sec_layout).setVisibility(View.GONE);
                findViewById(R.id.labor_layout).setVisibility(View.GONE);
                findViewById(R.id.labor_add_layout).setVisibility(View.GONE);
                findViewById(R.id.labor_content_layout).setVisibility(View.GONE);
                findViewById(R.id.task_layout).setVisibility(View.GONE);
                findViewById(R.id.task_content_layout).setVisibility(View.GONE);
                findViewById(R.id.task2do_layout).setVisibility(View.GONE);
                findViewById(R.id.tas2do_content_layout).setVisibility(View.VISIBLE);
                findViewById(R.id.problem_layout).setVisibility(View.GONE);
                findViewById(R.id.problem_edit_layout).setVisibility(View.GONE);
                findViewById(R.id.myproblem_layout).setVisibility(View.GONE);
                findViewById(R.id.myproblem_list_content_layout).setVisibility(View.GONE);
                findViewById(R.id.transaction_layout).setVisibility(View.GONE);
                break;
            case 6:
                findViewById(R.id.main_layout).setVisibility(View.GONE);
                findViewById(R.id.intelligence_layout).setVisibility(View.GONE);
                findViewById(R.id.labor_layout).setVisibility(View.GONE);
                findViewById(R.id.intelligence_sec_layout).setVisibility(View.GONE);
                findViewById(R.id.labor_add_layout).setVisibility(View.GONE);
                findViewById(R.id.labor_content_layout).setVisibility(View.GONE);
                findViewById(R.id.task_layout).setVisibility(View.GONE);
                findViewById(R.id.task_content_layout).setVisibility(View.GONE);
                findViewById(R.id.task2do_layout).setVisibility(View.GONE);
                findViewById(R.id.tas2do_content_layout).setVisibility(View.GONE);
                findViewById(R.id.problem_layout).setVisibility(View.GONE);
                findViewById(R.id.problem_edit_layout).setVisibility(View.GONE);
                findViewById(R.id.myproblem_layout).setVisibility(View.GONE);
                findViewById(R.id.myproblem_list_content_layout).setVisibility(View.GONE);
                findViewById(R.id.transaction_layout).setVisibility(View.VISIBLE);
                break;
            case 5:
                findViewById(R.id.main_layout).setVisibility(View.GONE);
                findViewById(R.id.intelligence_layout).setVisibility(View.GONE);
                findViewById(R.id.intelligence_sec_layout).setVisibility(View.GONE);
                findViewById(R.id.labor_layout).setVisibility(View.GONE);
                findViewById(R.id.labor_add_layout).setVisibility(View.GONE);
                findViewById(R.id.labor_content_layout).setVisibility(View.GONE);
                findViewById(R.id.task_layout).setVisibility(View.GONE);
                findViewById(R.id.task_content_layout).setVisibility(View.GONE);
                findViewById(R.id.task2do_layout).setVisibility(View.GONE);
                findViewById(R.id.tas2do_content_layout).setVisibility(View.GONE);
                findViewById(R.id.problem_layout).setVisibility(View.VISIBLE);
                findViewById(R.id.problem_edit_layout).setVisibility(View.GONE);
                findViewById(R.id.myproblem_layout).setVisibility(View.GONE);
                findViewById(R.id.myproblem_list_content_layout).setVisibility(View.GONE);
                findViewById(R.id.transaction_layout).setVisibility(View.GONE);
                break;
            case 51:
                findViewById(R.id.main_layout).setVisibility(View.GONE);
                findViewById(R.id.intelligence_layout).setVisibility(View.GONE);
                findViewById(R.id.intelligence_sec_layout).setVisibility(View.GONE);
                findViewById(R.id.labor_layout).setVisibility(View.GONE);
                findViewById(R.id.labor_add_layout).setVisibility(View.GONE);
                findViewById(R.id.labor_content_layout).setVisibility(View.GONE);
                findViewById(R.id.task_layout).setVisibility(View.GONE);
                findViewById(R.id.task_content_layout).setVisibility(View.GONE);
                findViewById(R.id.task2do_layout).setVisibility(View.GONE);
                findViewById(R.id.tas2do_content_layout).setVisibility(View.GONE);
                findViewById(R.id.problem_layout).setVisibility(View.GONE);
                findViewById(R.id.problem_edit_layout).setVisibility(View.VISIBLE);
                findViewById(R.id.myproblem_layout).setVisibility(View.GONE);
                findViewById(R.id.myproblem_list_content_layout).setVisibility(View.GONE);
                findViewById(R.id.transaction_layout).setVisibility(View.GONE);
                break;
            case 7:
                findViewById(R.id.main_layout).setVisibility(View.GONE);
                findViewById(R.id.intelligence_layout).setVisibility(View.GONE);
                findViewById(R.id.intelligence_sec_layout).setVisibility(View.GONE);
                findViewById(R.id.labor_layout).setVisibility(View.GONE);
                findViewById(R.id.labor_add_layout).setVisibility(View.GONE);
                findViewById(R.id.labor_content_layout).setVisibility(View.GONE);
                findViewById(R.id.task_layout).setVisibility(View.GONE);
                findViewById(R.id.task_content_layout).setVisibility(View.GONE);
                findViewById(R.id.task2do_layout).setVisibility(View.GONE);
                findViewById(R.id.tas2do_content_layout).setVisibility(View.GONE);
                findViewById(R.id.problem_layout).setVisibility(View.GONE);
                findViewById(R.id.problem_edit_layout).setVisibility(View.GONE);
                findViewById(R.id.myproblem_layout).setVisibility(View.VISIBLE);
                findViewById(R.id.myproblem_list_content_layout).setVisibility(View.GONE);
                findViewById(R.id.transaction_layout).setVisibility(View.GONE);
                break;
            case 71:
                findViewById(R.id.main_layout).setVisibility(View.GONE);
                findViewById(R.id.intelligence_layout).setVisibility(View.GONE);
                findViewById(R.id.intelligence_sec_layout).setVisibility(View.GONE);
                findViewById(R.id.labor_layout).setVisibility(View.GONE);
                findViewById(R.id.labor_add_layout).setVisibility(View.GONE);
                findViewById(R.id.labor_content_layout).setVisibility(View.GONE);
                findViewById(R.id.task_layout).setVisibility(View.GONE);
                findViewById(R.id.task_content_layout).setVisibility(View.GONE);
                findViewById(R.id.task2do_layout).setVisibility(View.GONE);
                findViewById(R.id.tas2do_content_layout).setVisibility(View.GONE);
                findViewById(R.id.problem_layout).setVisibility(View.GONE);
                findViewById(R.id.problem_edit_layout).setVisibility(View.GONE);
                findViewById(R.id.myproblem_layout).setVisibility(View.GONE);
                findViewById(R.id.myproblem_list_content_layout).setVisibility(View.VISIBLE);
                findViewById(R.id.transaction_layout).setVisibility(View.GONE);
                break;
        }
    }
    public void main_layout(){
        SetLayout(0);
        flag = 0;
        setTitle("Small Favor");
        Button button_i = (Button)findViewById(R.id.main_button_intelligence);
        Button button_l = (Button)findViewById(R.id.main_button_labor);
        TextView textView_money = (TextView)findViewById(R.id.money_textView);
        TextView name = (TextView)findViewById(R.id.main_textView_ID);
        TextView intelligence_count = (TextView)findViewById(R.id.intelligence_count);
        TextView labor_count = (TextView)findViewById(R.id.labor_count);
        TextView rate = (TextView)findViewById(R.id.main_textView_Rate);
        Socket_Req socket_req = new Socket_Req("REQ", "main", myaccount, null);
        int returnCode = socket_req.runSocket();
        if(returnCode==1){
            login_information.account account = null;
            account = (login_information.account)socket_req.getobject();
            if(account != null) {
                myaddress = account.address;
                textView_money.setText("$"+Integer.toString(account.money));
                name.setText("你好, " + account.nickname);
                intelligence_count.setText(Integer.toString(account.intelligence_task)+"項");
                labor_count.setText(Integer.toString(account.labor_task)+"項");
                ColorDefiner colorDefiner = new ColorDefiner();
                if (account.rate != (-1)){
                    DecimalFormat df = new DecimalFormat("#.##");
                    String s = df.format((account.rate)*100);
                    rate.setText(s + "% 分區是" + colorDefiner.AddText(account.rate));
                }else{
                    rate.setText("請完成10項 分區"+colorDefiner.AddText(account.rate));
                }
                colorDefiner.SetColor(account.rate, rate);
            }
        }else if(returnCode==-1){
            Toast.makeText(this, "Can't connect to Server", Toast.LENGTH_LONG);
        }else if(returnCode == 0){
            Toast.makeText(this, "other error",Toast.LENGTH_LONG);
        }
        socket_req = null;
        button_i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intelligence_layout();
            }
        });
        button_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                labor_layout();
            }
        });
    }
    public void labor_layout(){
        SetLayout(2);
        flag = 2;
        setTitle("勞力區");
        LaborAdapter adapter = null;
        ListView list = (ListView)findViewById(R.id.labor_listView);
        List<Labor_information> labor_list;// = new ArrayList<Labor_information>();
        Socket_Req socket_req = new Socket_Req("REQ", "labor", myaccount, null);
        socket_req.runSocket();
        labor_list = (List<Labor_information>) socket_req.getobject();
        adapter = new LaborAdapter(MainActivity.this, labor_list);
        list.setAdapter(adapter);
        ImageButton imageButton_add = (ImageButton)findViewById(R.id.labor_imageButton_add);
        imageButton_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                labor_add_layout();
            }
        });
    }
    public void labor_add_layout(){
        SetLayout(21);
        flag = 21;
        Button post = (Button)findViewById(R.id.labor_add_button_submit);
        Button giveup = (Button)findViewById(R.id.labor_add_button_giveup);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText title = (EditText)findViewById(R.id.labor_add_editText_title);
                EditText price = (EditText)findViewById(R.id.labor_add_editText_price);
                EditText content = (EditText)findViewById(R.id.labor_add_editText_content);
                if(title.getText().toString().length() == 0 || price.getText().toString().length() == 0){
                    Toast.makeText(MainActivity.this, "Must fill title and price", Toast.LENGTH_LONG);
                }else {
                    Labor_information labor_information = new Labor_information(title.getText().toString(), Integer.valueOf(price.getText().toString()), myaccount, -1, 0, content.getText().toString());
                    Socket_Req socket_req = new Socket_Req("ADD", "labor_content", myaccount, labor_information);
                    int returnCode = socket_req.runSocket();
                    if (returnCode == 1) {
                        title.setText("");
                        title.setText("");
                        price.setText("");
                        content.setText("");
                        title.setHint("標題");
                        price.setHint("價格");
                        content.setHint("內文");
                        labor_layout();
                    } else if (returnCode == -1) {
                        Toast.makeText(MainActivity.this, "Can't connect to Server", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this, "other error", Toast.LENGTH_LONG).show();
                    }
                    socket_req = null;
                    labor_information = null;
                }
            }
        });
        giveup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                labor_layout();
            }
        });
    }
    public void labor_content_layout(final Labor_information now_labor){
        SetLayout(22);
        flag = 22;
        Socket_Req socket_req = new Socket_Req("REQ", "labor_content", myaccount, now_labor);
        int returnCode = socket_req.runSocket();
        if(returnCode == 1){
            Button request = (Button)findViewById(R.id.labor_content_button_request);
            TextView title = (TextView)findViewById(R.id.labor_content_title);
            final TextView ID = (TextView)findViewById(R.id.labor_content_ID);
            ColorDefiner colorDefiner = new ColorDefiner();
            TextView price = (TextView)findViewById(R.id.labor_content_price);
            TextView content = (TextView)findViewById(R.id.labor_content_content);
            Labor_information labor_information = (Labor_information) socket_req.getobject();
            if(labor_information!=null){
                title.setText(labor_information.title);
                ID.setText(labor_information.ID);
                price.setText("$"+Integer.toString(labor_information.price));
                content.setText(labor_information.content);
                colorDefiner.SetColor(labor_information.rate, ID);
            }
            request.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(myaccount == ID.getText()){
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("你不能接受自己的任務！");
                        builder.setNegativeButton("確定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        });
                        builder.show();
                    }else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("確定接受？");
                        builder.setPositiveButton("確認", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Socket_Req socket_req1 = new Socket_Req("AC", "", myaccount, now_labor);
                                socket_req1.runSocket();
                                labor_layout();
                            }
                        });
                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        });
                        builder.show();
                    }
                }
            });
        }else if(returnCode == -1){
            Toast.makeText(MainActivity.this, "Can't connect to Server", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(MainActivity.this, "request error", Toast.LENGTH_LONG).show();
        }
        Button back = (Button)findViewById(R.id.labor_content_button_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                labor_layout();
            }
        });
    }
    public void task_layout(){
        SetLayout(3);
        flag = 3;
        setTitle("我的任務");
        TaskAdapter adapter = null;
        ListView list = (ListView)findViewById(R.id.task_listView);
        List<Labor_information> task_list;
        Socket_Req socket_req = new Socket_Req("REQ", "task", myaccount, null);
        socket_req.runSocket();
        task_list = (List<Labor_information>) socket_req.getobject();
        adapter = new TaskAdapter(MainActivity.this, task_list, 0);
        list.setAdapter(adapter);
        if (adapter.getCount()==0){
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("你的List是空的！");
            builder.setNegativeButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            builder.show();
        }
    }
    public void task_content_layout(final Labor_information task_information){
        SetLayout(31);
        flag = 31;
        Button deal = (Button)findViewById(R.id.task_content_button_request);
        Button giveup = (Button)findViewById(R.id.task_content_button_back);
        Button fail = (Button)findViewById(R.id.task_content_button_fail);
        TextView title = (TextView)findViewById(R.id.task_content_title);
        TextView ID = (TextView)findViewById(R.id.task_content_ID);
        TextView price = (TextView)findViewById(R.id.task_content_price);
        TextView content = (TextView)findViewById(R.id.task_content_content);
        title.setText(task_information.title);
        ID.setText(task_information.ID);
        ColorDefiner colorDefiner = new ColorDefiner();
        colorDefiner.SetColor(task_information.rate, ID);
        price.setText("$"+Integer.toString(task_information.price));
        content.setText(task_information.content);
        if(task_information.state == 1){
            fail.setVisibility(View.VISIBLE);
            fail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Labor_information tmp = new Labor_information("", 0, "",task_information.post_ID, 0, "");
                    Socket_Req socket_req = new Socket_Req("DEL", "", myaccount, tmp);
                    socket_req.runSocket();
                    task_layout();
                }
            });
        }else if(task_information.state == 0){
            fail.setVisibility(View.GONE);
        }

        deal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(task_information.state == 0){
                    Toast.makeText(MainActivity.this, "Still waiting", Toast.LENGTH_LONG).show();
                }else {
                    Labor_information tmp = new Labor_information("", 0, "", task_information.post_ID, 1, "");
                    Socket_Req socket_req = new Socket_Req("DEL", "", myaccount, tmp);
                    if(socket_req.runSocket() == 1){
                        task_layout();
                    }else {
                        Toast.makeText(MainActivity.this, "餘額不足", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        giveup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(task_information.state == 1){
                    Toast.makeText(MainActivity.this, "Pleas wait for"+task_information.accepter, Toast.LENGTH_LONG).show();
                }else {
                    Labor_information tmp = new Labor_information("", 0, "", task_information.post_ID, -1, "");
                    Socket_Req socket_req = new Socket_Req("DEL", "", myaccount, tmp);
                    socket_req.runSocket();
                    task_layout();
                }
            }
        });

    }
    public void task2do_layout(){
        SetLayout(4);
        flag = 4;
        setTitle("我要完成的任務");
        TaskAdapter adapter = null;
        ListView list = (ListView)findViewById(R.id.task2do_listView);
        List<Labor_information> task_list;
        Socket_Req socket_req = new Socket_Req("REQ", "task2do", myaccount, null);
        socket_req.runSocket();
        task_list = (List<Labor_information>) socket_req.getobject();
        adapter = new TaskAdapter(MainActivity.this, task_list, 1);
        list.setAdapter(adapter);
        if (adapter.getCount()==0){
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("你的List是空的！");
            builder.setNegativeButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            builder.show();
        }
    }
    public void task2do_content_layout(final Labor_information task_information){
        SetLayout(41);
        flag = 41;
        Button giveup = (Button)findViewById(R.id.task2do_content_button_back);
        TextView title = (TextView)findViewById(R.id.task2do_content_title);
        TextView ID = (TextView)findViewById(R.id.task2do_content_ID);
        TextView price = (TextView)findViewById(R.id.task2do_content_price);
        TextView content = (TextView)findViewById(R.id.task2do_content_content);
        title.setText(task_information.title);
        ID.setText(task_information.ID);
        ColorDefiner colorDefiner = new ColorDefiner();
        colorDefiner.SetColor(task_information.rate, ID);
        price.setText("$"+Integer.toString(task_information.price));
        content.setText(task_information.content);
        giveup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Labor_information tmp = new Labor_information("", 0, "", task_information.post_ID, 0, "");
                Socket_Req socket_req = new Socket_Req("DEL", "", myaccount, tmp);
                socket_req.runSocket();
                task2do_layout();
            }
        });
    }
    public void transaction_layout(){
        SetLayout(6);
        flag = 6;
        setTitle("交易紀錄");
        TextView address = (TextView)findViewById(R.id.transaction_address_textView);
        address.setText("Block chain address:"+myaddress);
        TransactionAdapter adapter = null;
        ListView list = (ListView)findViewById(R.id.transaction_listView);
        List<login_information.trade> trade_list;
        Socket_Req socket_req = new Socket_Req("REQ", "trade", myaccount, null);
        socket_req.runSocket();
        trade_list = (List<login_information.trade>) socket_req.getobject();
        adapter = new TransactionAdapter(MainActivity.this, trade_list, myaddress);
        list.setAdapter(adapter);

    }
    public void intelligence_layout(){
        SetLayout(1);
        flag = 1;
        setTitle("智力區");
        Intelligence_depart_Adapter adapter = null;
        ListView list = (ListView)findViewById(R.id.intelligence_listView);
        String department;
        List<depart_info> intelligence_list = new ArrayList<depart_info>();
        intelligence_list.add(new depart_info("中文系"));
        intelligence_list.add(new depart_info("資工系"));
        intelligence_list.add(new depart_info("法律系"));
        intelligence_list.add(new depart_info("電資系"));
        intelligence_list.add(new depart_info("外文系"));
        intelligence_list.add(new depart_info("森林系"));
        intelligence_list.add(new depart_info("日文系"));
        intelligence_list.add(new depart_info("土木系"));
        intelligence_list.add(new depart_info("化工系"));
        intelligence_list.add(new depart_info("機械系"));
        intelligence_list.add(new depart_info("經濟系"));
        adapter = new Intelligence_depart_Adapter(this, intelligence_list);
        list.setAdapter(adapter);
        //imageButton_add.setOnClickListener();
    }
    public void intelligence_sec_layout(String department){
        depart = department;
        SetLayout(11);
        flag = 11;
        Button addproblem = (Button)findViewById(R.id.add_problem);
        List<Intelligence_information> intell_inform;
        IntelligenceAdapter adapter;
        ListView list = (ListView)findViewById(R.id.intelligence_sec_listView);
        socket_req_intell socket_req = new socket_req_intell(MainActivity.this,"REQ", "intelligence", myaccount, null);
        socket_req.runSocket();
        intell_inform = (List<Intelligence_information>) socket_req.getobject();
        adapter = new IntelligenceAdapter(MainActivity.this, intell_inform);
        list.setAdapter(adapter);

        addproblem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                problem_edit();
            }
        });

    }
    private void normalDialogEvent(final Intelligence_information now_intell, final TextView content, final String content_detail){
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("觀賞解答")
                .setMessage("確定要付費購買解答嗎?")
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "gogo", Toast.LENGTH_SHORT).show();
                        socket_req_intell socket_req = new socket_req_intell(MainActivity.this ,"ACIQ", "", myaccount, now_intell );
                        socket_req.runSocket();
                        content.setText(content_detail);
                    }

                })
                .setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //不買
                        Toast.makeText(getApplicationContext(), "nono", Toast.LENGTH_SHORT).show();
                        intelligence_sec_layout(depart);
                    }
                })
                .show();
    }
    public void problem(Intelligence_information now_intell){
        //System.out.println(myaccount+" hahahah " +now_intell.ID);
        final TextView content = (TextView) findViewById(R.id.problem_answer_content);

        SetLayout(5);
        flag = 5;
        Intelligence_information intell_information = null;
        socket_req_intell socket_req = new socket_req_intell(MainActivity.this ,"REQ", "intelligence_content", myaccount, now_intell);
        int returnCode = socket_req.runSocket();
        if(returnCode == 1) {
            TextView title = (TextView) findViewById(R.id.problem_set_title);
            TextView ID = (TextView) findViewById(R.id.problem_postername);
            //TextView content = (TextView) findViewById(R.id.problem_answer_content);
            TextView price = (TextView)findViewById(R.id.problem_set_price) ;
            intell_information = (Intelligence_information) socket_req.getobject();
            if (intell_information != null) {
                title.setText(intell_information.title);
                ID.setText(intell_information.ID);
                System.out.println(intell_information.content + "ewtwegewrg");
                content.setText("");
                price.setText("$"+Integer.toString(intell_information.price));
            }

        }
        if( !myaccount.equals(now_intell.ID) && intell_information!= null) {
            normalDialogEvent(now_intell, content, intell_information.content);
        }

    }
    public void problem_edit(){
        SetLayout(51);
        flag = 51;

        Button add = (Button)findViewById(R.id.problem_post_button_edit);
        Button giveup = (Button)findViewById(R.id.problem_giveup_button_edit);
        final TextView postername = (TextView)findViewById(R.id.problem_postername_edit);
        postername.setText(myaccount);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("fuckyou ");
                EditText title = (EditText)findViewById(R.id.problem_set_title_edit);
                EditText content = (EditText)findViewById(R.id.problem_answer_content_edit);
                EditText price = (EditText)findViewById(R.id.problem_set_price_edit);
                postername.setText(myaccount);
                if(postername.getText().toString().length() == 0 || title.getText().toString().length() == 0){
                    Toast.makeText(MainActivity.this, "Must fill postername and title", Toast.LENGTH_LONG);
                }else {
                    Intelligence_information intelligence_information = new Intelligence_information(title.getText().toString(), Integer.valueOf(price.getText().toString()), myaccount, 234 ,"CSIE",-1,content.getText().toString());
                    socket_req_intell socket_req = new socket_req_intell(MainActivity.this ,"ADD", "intelligence_content", myaccount, intelligence_information);
                    int returnCode = socket_req.runSocket();
                    if (returnCode == 1) {
                        title.setText("");
                        intelligence_sec_layout(depart);
                    } else if (returnCode == -1) {
                        Toast.makeText(MainActivity.this, "Can't connect to Server", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this, "other error", Toast.LENGTH_LONG).show();
                    }
                    socket_req = null;
                    intelligence_information = null;
                }
            }
        });

        giveup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intelligence_sec_layout(depart);
            }
        });
    }
    public void myprobem_layout(){
        SetLayout(7);
        flag = 7;
        List<Intelligence_information> intell_inform;
        MyproblemAdapter adapter;
        ListView list = (ListView)findViewById(R.id.myproblem_listView);
        socket_req_intell socket_req = new socket_req_intell(MainActivity.this,"REQ", "collection", myaccount, null);
        socket_req.runSocket();
        intell_inform = (List<Intelligence_information>) socket_req.getobject();
        adapter = new MyproblemAdapter(MainActivity.this, intell_inform);
        list.setAdapter(adapter);
    }
    public void myproblem_content_layout(final Intelligence_information now_intell){
        SetLayout(71);
        flag = 71;
        socket_req_intell socket_req = new socket_req_intell(MainActivity.this,"REQ", "intelligence_content", myaccount, now_intell);
        int returnCode = socket_req.runSocket();
        if(returnCode == 1) {
            TextView title = (TextView) findViewById(R.id.myproblem_set_title);
            TextView ID = (TextView) findViewById(R.id.myproblem_postername);
            TextView content = (TextView) findViewById(R.id.myproblem_answer_content);
            TextView price = (TextView)findViewById(R.id.myproblem_set_price) ;
            Intelligence_information intell_information = (Intelligence_information) socket_req.getobject();
            if (intell_information != null) {
                title.setText(intell_information.title);
                ID.setText(intell_information.ID);
                System.out.println(intell_information.content + "ewtwegewrg");
                content.setText(intell_information.content);
                price.setText("$"+Integer.toString(intell_information.price));
            }
        }

    }

}
