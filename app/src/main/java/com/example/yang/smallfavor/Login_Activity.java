package com.example.yang.smallfavor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import static android.widget.RadioGroup.*;

public class Login_Activity extends AppCompatActivity {
    private Button button_login = null;
    private Button button_register = null;
    private short flag = 0;//0 for login, 1 for register
    private Button button_submit = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        login_page();
    }
    private void login_page(){
        setContentView(R.layout.login);
        button_login = (Button) findViewById(R.id.login_button);
        button_register = (Button) findViewById(R.id.register_button);
        button_login.setOnClickListener(click_login);
        button_register.setOnClickListener(click_register);
        setTitle("登入");
    }
    private login_information.login l_info = new login_information.login();
    private View.OnClickListener click_login = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            EditText account = (EditText)findViewById(R.id.editText_login_name);
            EditText pswd = (EditText)findViewById(R.id.editText_login_pswd);
            l_info.account = account.getText().toString();
            l_info.password = pswd.getText().toString();
            if(!l_info.isfull()){
                Toast.makeText(view.getContext(), "請全部填寫", Toast.LENGTH_LONG).show();
            }else {
                Socket_LR socket = new Socket_LR(l_info, null);
                ProgressBar spinner = (ProgressBar) findViewById(R.id.login_progressBar);
                spinner.setVisibility(View.VISIBLE);
                int returnCode = socket.runSocket();
                if(returnCode == -1){
                    Toast.makeText(view.getContext(), "Can't Connect to Server", Toast.LENGTH_LONG).show();
                }else if(returnCode == 0) {
                    Toast.makeText(view.getContext(), "No Account", Toast.LENGTH_LONG).show();
                }else if(returnCode == 2){
                    Toast.makeText(view.getContext(), "Wrong Password", Toast.LENGTH_LONG).show();
                }else if(returnCode == 1) {
                    Intent intent = new Intent(Login_Activity.this, MainActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("account", l_info.account);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(view.getContext(), "ERROR", Toast.LENGTH_LONG).show();
                }
            }
        }
    };
    private View.OnClickListener click_register = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setContentView(R.layout.register);
            button_submit = (Button) findViewById(R.id.register_button_submit);
            setTitle("註冊");
            flag = 1;
            button_submit.setOnClickListener(click_submit);
        }
    };
    private login_information.register r_info = new login_information.register();
    private View.OnClickListener click_submit = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            EditText account = (EditText)findViewById(R.id.editText_register_account);
            EditText password = (EditText)findViewById(R.id.editText_register_pswd);
            EditText phone = (EditText)findViewById(R.id.editText_register_phone);
            EditText email = (EditText)findViewById(R.id.editText_register_email);
            EditText nickname = (EditText)findViewById(R.id.editText_register_nickname);
            r_info.account = account.getText().toString();
            r_info.password = password.getText().toString();
            r_info.phone = phone.getText().toString();
            r_info.email = email.getText().toString();
            r_info.nickname = nickname.getText().toString();
            RadioGroup radioGroup = (RadioGroup)findViewById(R.id.register_rgroup);
            if(radioGroup.getCheckedRadioButtonId()!=-1) {
                RadioButton radioButton = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
                System.out.println(radioButton.getText());
                if(radioButton.getText().equals("男")){
                    r_info.sex = 0;
                }else if(radioButton.getText().equals("女")){
                    r_info.sex = 1;
                }else{
                    r_info.sex = -1;
                }
            }else{
                r_info.sex = -1;
            }
            if(!r_info.isfull() || !r_info.isValid()){
                Toast.makeText(view.getContext(), "請全部填寫", Toast.LENGTH_LONG).show();
            }else{
                Socket_LR socket = new Socket_LR(null, r_info);
                int returnCode = socket.runSocket();
                if(returnCode==-1){
                    Toast.makeText(view.getContext(), "Can't Connect to Server", Toast.LENGTH_LONG).show();
                }else if(returnCode==1) {
                    Toast.makeText(view.getContext(), "SUBMITTED", Toast.LENGTH_LONG).show();
                    flag = 0;
                    login_page();
                }else if(returnCode == 0){
                    Toast.makeText(view.getContext(), "Account Used. Please try another account.", Toast.LENGTH_LONG).show();
                }
            }
        }
    };
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == event.KEYCODE_BACK){
            if(flag == 1){
                setContentView(R.layout.login);
                flag = 0;
                login_page();
            }else{
                finish();
            }
        }
        return true;
    }
}
