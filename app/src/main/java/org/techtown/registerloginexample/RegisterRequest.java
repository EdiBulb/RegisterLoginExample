package org.techtown.registerloginexample;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/*회원가입 요청 구문 만들기*/

public class RegisterRequest extends StringRequest {

    // 서버 URL 설정(PHP 파일 연동) - 통신할 서버를 연결해줍니다. 이것은 고정값입니다. 아까 서버 호스팅 주소를 넣어줍니다.
    final static private String URL = "http://gnsqud24.dothome.co.kr/Register.php";
    //해시맵을 쓸겁니다.
    private Map<String,String>map;

    //회원가입 요청 - 여기 안에 생성자들을 다 넣어줄 겁니다.
    public RegisterRequest(String userID,String userPassword, String userName, int userAge, Response.Listener<String>listener){//문자열 형태로 쏠 거다.
        //여기서 php와 연결한다.
        super(Method.POST, URL,listener,null);//포스트 방식으로 할 것이다.

        //해시맵 형태로 만들고
        map = new HashMap<>();
        //실제 넣을 문자열 값 넣어준다.
        map.put("userID",userID);
        map.put("userPassword",userPassword);
        map.put("userName",userName);
        map.put("userAge",userAge+"");//int 형이니까 Stirng으로 눈속임하기
    }

    @Nullable
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;//map을 리턴한다.
    }

    public RegisterRequest(String url, Response.Listener<String> listener, @Nullable Response.ErrorListener errorListener) {
        super(url, listener, errorListener);
    }
}
