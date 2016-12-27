package com.example.yang.smallfavor;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private int flag = 0;//0:main 1:intelligence 2:labor
    private String myaccount;
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
        if (id == R.id.action_settings) {
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
        } else if (id == R.id.four) {

        } else if (id == R.id.drawer_account) {

        } else if (id == R.id.drawer_logout) {
            logout();
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
                findViewById(R.id.labor_layout).setVisibility(View.GONE);
                findViewById(R.id.labor_add_layout).setVisibility(View.GONE);
                findViewById(R.id.labor_content_layout).setVisibility(View.GONE);
                break;
            case 1:
                findViewById(R.id.main_layout).setVisibility(View.GONE);
                findViewById(R.id.intelligence_layout).setVisibility(View.VISIBLE);
                findViewById(R.id.labor_layout).setVisibility(View.GONE);
                findViewById(R.id.labor_add_layout).setVisibility(View.GONE);
                findViewById(R.id.labor_content_layout).setVisibility(View.GONE);
                break;
            case 2:
                findViewById(R.id.main_layout).setVisibility(View.GONE);
                findViewById(R.id.intelligence_layout).setVisibility(View.GONE);
                findViewById(R.id.labor_layout).setVisibility(View.VISIBLE);
                findViewById(R.id.labor_add_layout).setVisibility(View.GONE);
                findViewById(R.id.labor_content_layout).setVisibility(View.GONE);
                break;
            case 21:
                findViewById(R.id.main_layout).setVisibility(View.GONE);
                findViewById(R.id.intelligence_layout).setVisibility(View.GONE);
                findViewById(R.id.labor_layout).setVisibility(View.GONE);
                findViewById(R.id.labor_add_layout).setVisibility(View.VISIBLE);
                findViewById(R.id.labor_content_layout).setVisibility(View.GONE);
                break;
            case 22:
                findViewById(R.id.main_layout).setVisibility(View.GONE);
                findViewById(R.id.intelligence_layout).setVisibility(View.GONE);
                findViewById(R.id.labor_layout).setVisibility(View.GONE);
                findViewById(R.id.labor_add_layout).setVisibility(View.GONE);
                findViewById(R.id.labor_content_layout).setVisibility(View.VISIBLE);
                break;
        }
    }
    public void main_layout(){
        SetLayout(0);
        flag = 0;
        Button button_i = (Button)findViewById(R.id.main_button_intelligence);
        Button button_l = (Button)findViewById(R.id.main_button_labor);
        TextView textView_money = (TextView)findViewById(R.id.money_textView);
        TextView name = (TextView)findViewById(R.id.main_textView_ID);
        TextView intelligence_count = (TextView)findViewById(R.id.intelligence_count);
        TextView labor_count = (TextView)findViewById(R.id.labor_count);
        Socket_Req socket_req = new Socket_Req("REQ", "main", myaccount, null);
        int returnCode = socket_req.runSocket();
        if(returnCode==1){
            login_information.account account = null;
            account = (login_information.account)socket_req.getobject();
            if(account != null) {
                textView_money.setText("$"+Integer.toString(account.money));
                name.setText("你好, " + account.nickname);
                intelligence_count.setText(Integer.toString(account.intelligence_task)+"項");
                labor_count.setText(Integer.toString(account.labor_task)+"項");
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
    public void intelligence_layout(){
        SetLayout(1);
        flag = 1;
    }
    public void labor_layout(){
        SetLayout(2);
        flag = 2;
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
    public void labor_content_layout(Labor_information now_labor){
        SetLayout(22);
        flag = 22;
        Socket_Req socket_req = new Socket_Req("REQ", "labor_content", myaccount, now_labor);
        int returnCode = socket_req.runSocket();
        if(returnCode == 1){
            Button request = (Button)findViewById(R.id.labor_content_button_request);
            TextView title = (TextView)findViewById(R.id.labor_content_title);
            TextView ID = (TextView)findViewById(R.id.labor_content_ID);
            TextView price = (TextView)findViewById(R.id.labor_content_price);
            TextView content = (TextView)findViewById(R.id.labor_content_content);
            Labor_information labor_information = (Labor_information) socket_req.getobject();
            if(labor_information!=null){
                title.setText(labor_information.title);
                ID.setText(labor_information.ID);
                price.setText(Integer.toString(labor_information.price));
                content.setText(labor_information.content);
            }
            request.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("確定接受？");
                    builder.setPositiveButton("確認", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
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

}
