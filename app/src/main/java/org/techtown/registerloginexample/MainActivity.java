package org.techtown.registerloginexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
//회원가입과 로그인이 끝나면 이제 화면에서 데이터를 뿌려준다.

public class MainActivity extends AppCompatActivity {

    //위젯 선언
    private TextView tv_id,tv_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //위젯 연결
        tv_id = findViewById(R.id.tv_id);
        tv_pass = findViewById(R.id.tv_pass);

        //인텐트로 데이터를 받는다.
        Intent intent = getIntent();

        //인텐트로부터 꺼내온다.
        String userID = intent.getStringExtra("userID");
        String userPass = intent.getStringExtra("userPass");
        //여기까지 하면 아이디와 비밀번호 값이 MainActivity까지 정상적으로 도착했다.

        tv_id.setText(userID);
        tv_pass.setText(userPass);
    }
}