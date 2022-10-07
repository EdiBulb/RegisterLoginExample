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

public class RegisterActivity extends AppCompatActivity {

    //각 위젯들 선언하기
    private EditText et_id,et_pass, et_name,et_age;
    private Button btn_register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {//액티비티 시작시 처음으로 시작되는 생명주기!
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //아이디값 찾아주기
        et_id = findViewById(R.id.et_id);
        et_pass = findViewById(R.id.et_pass);
        et_name = findViewById(R.id.et_name);
        et_age = findViewById(R.id.et_age);
        btn_register = findViewById(R.id.btn_register);

        //회원가입 버튼 클릭 시 수행
        //회원가입을 누르면 입력된 데이터들을 주르륵 가져와서 서버 registerRequest쪽으로 넘겨줘야한다.
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //EditText에 현재 입력되어있는 값을 get(가져온다) 해온다.
                String userID = et_id.getText().toString();
                String userPass = et_pass.getText().toString();
                String userName = et_name.getText().toString();
                int userAge = Integer.parseInt(et_age.getText().toString());

                //실제 앱 volley 구문을 사용한다.
                //질문 - volley구문을 사용한다??
               Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //json 오브젝트를 활용해서 실제적인 회원가입 요청을 할 것이다.
                        //json 오브젝트란? :json 오브젝트 형태가 있다. 그런 중괄호에 갇힌 String 형태들로 담아준 다음에 서버 전송할 때, 일반 String으로 보낼 수 없어서, json오브젝트라는 것을 활용한다.

                        //회원가입 요청을 한 뒤 결고값을 json 오브젝트로 받는 것
                        try {
                            //response를 json 오브젝트에 넣는다.
                            JSONObject jsonObject = new JSONObject(response);//response란? 회원가입 요청을 한 뒤, 결과값을 json 오브젝트로 받는 것. 서버통신 한 다음에, 성공여부를 알기 위해서 한다.
                            boolean success = jsonObject.getBoolean("success");//성공 여부를 담는다. php파일에 있는 success와 동일
                            if(success){//회원등록에 성공한 경우
                                Toast.makeText(getApplicationContext(),"회원등록에 성공하였습니다.",Toast.LENGTH_SHORT);
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);//출발 액티비티와 이동할 액티비티하기
                                startActivity(intent);
                            } else{ //회원등록에 실패한 경우
                                Toast.makeText(getApplicationContext(),"회원등록에 실패하였습니다..",Toast.LENGTH_SHORT);
                                return;//실패했으니까 넘어가면 안되고 리턴시킨다.
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                //서버로 Volley를 이용해서 요청을 함.
                //이 부분이 뭐지?
                RegisterRequest registerRequest = new RegisterRequest(userID,userPass,userName,userAge,responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
                
                //여기까지 완료했으면 회원가입 기능 완료한 것임
            }
        });
    }
}