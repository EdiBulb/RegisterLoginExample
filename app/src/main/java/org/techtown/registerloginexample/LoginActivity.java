package org.techtown.registerloginexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    //위젯 가져오기
    private EditText et_id,et_pass;
    private Button btn_login, btn_register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //연결하기
        et_id = findViewById(R.id.et_id);
        et_pass = findViewById(R.id.et_pass);
        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);

        //회원가입 버튼 클릭시 수행
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //로그인 액티비티에서 회원가입 액티비티로 이동하기
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        //로그인 버튼 클릭시 수행
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //EditText에 현재 입력되어있는 값을 get(가져온다) 해온다.
                String userID = et_id.getText().toString();
                String userPass = et_pass.getText().toString();

                //로그인 리쉐크스로 아이디 비번을 주고, 리스폰스 리스너로 응답을 돌려받는다..
                //여기 잘 모르겠음
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) { //response : JSON 전체 형태가 넘어온 스트링이다.
                        try {
                            JSONObject jsonObject = new JSONObject(response);//response를 JSONObject 안에 넣으면 파싱이 된다.
                            boolean success = jsonObject.getBoolean("success"); // 여기 잘 모르겠음
                            if(success){//로그인에 성공한 경우
                                String userID = jsonObject.getString("userID");//가져온다.
                                String userPass = jsonObject.getString("userPassword");//변경했음

                                Toast.makeText(getApplicationContext(),"로그인에 성공하였습니다.",Toast.LENGTH_SHORT);
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                                //MainActivity로 넘어갈 때, 데이터를 담아준다.
                                intent.putExtra("userID",userID);
                                intent.putExtra("userPass",userPass);

                                startActivity(intent);
                            } else{ //로그인에 실패한 경우
                                Toast.makeText(getApplicationContext(),"로그인에 실패하였습니다..",Toast.LENGTH_SHORT);
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };
                //
                LoginRequest loginRequest = new LoginRequest(userID,userPass,responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });
    }
}