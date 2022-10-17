package org.techtown.registerloginexample;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {

    // 서버 URL 설정(PHP 파일 연동)
    final static private String URL = "http://gnsqud24.dothome.co.kr/Login.php";

    //Stting 두개로 구현된 map을 만들어서 DB에서 값을 받아온다.
    private Map<String,String>map;

    //생성자
    //로그인 요청 - 로그인에 필요한 것만 넣는다.
    public LoginRequest(String userID, String userPassword,Response.Listener<String>listener){
        super(Method.POST, URL,listener,null);//POST방식으로 전송한다.

        map = new HashMap<>();//map을 HashMap으로 생성한다.

        // DB테이블에 있는 변수를 가져와서 userID에 넣는다.
        map.put("userID",userID);
        map.put("userPassword",userPassword);
    }

    @Nullable
    @Override
    //php에 작성되어있는 success나 그외를 반환한다.
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }

    public LoginRequest(String url, Response.Listener<String> listener, @Nullable Response.ErrorListener errorListener) {
        super(url, listener, errorListener);
    }
}
