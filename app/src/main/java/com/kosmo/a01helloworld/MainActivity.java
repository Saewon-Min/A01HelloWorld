package com.kosmo.a01helloworld;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    // 전화번호 입력 상자를 전역적으로 사용하기 위해 선언
    private EditText editText; // EditText는 HTML의 input과 동일한 역할을 하는 위젯(입력상자)

    /*
    Java에서의 출발점은 main() 메소드였듯이
    안드로이드의 출발점은 onCreate()메소드이다.
    수명주기(LifeCycle) 메소드 중에서 첫번째로 실행된다.
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
        xml로 생성된 레이아웃을 액티비티에 전개(Inflate) 하는 메소드로
        해당 메소드가 실행되지 않으면 아무것도 보이지 않게된다.
         */
        setContentView(R.layout.activity_main);

        /*
        findViewById(res로 설정된 id값)
            : xml에 설정된 id값을 통해 버튼 객체를 Java클래스로 얻어와서 사용한다.
         */
        Button btnNate = findViewById(R.id.btnNate);

        /*
        리스너 부착방법 1
            : 버튼 객체에 직접 리스너 인터페이스를 사용한다.
         */
        btnNate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                버튼을 눌렀을때 토스트 메세지를 띄워준다.
                토스트는 JS의 alert()와 유사하게 메세지를 일정 시간 보여준다.
                형식[
                    Toast.makeText(메세지를 띄울 컨텍스트(뷰),
                                    메세지 내용,
                                    시간(LENGTH_Long or LENGTH_Short)).show();
                 */
                Toast.makeText(v.getContext(),
                        "잠시 후 네이트로 이동합니다.",
                        Toast.LENGTH_LONG).show();

                /*
                Intent 통해 새로운 액티비티를 띄워준다.
                웹 URL이 전달되므로 내장 웹브라우저를 통해 네이트로 접속한다.
                 */
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://nate.com"));
                // 실제로 실행되는 부분
                startActivity(intent);
            }
        });

        /*
        전화걸기 앱을 통해 입력한 전화번호 전달하기
         */
        editText = findViewById(R.id.editText); // 전화번호를 입력 할 수 있는 editText를 가져옴
        Button btnCall = findViewById(R.id.btnCall); // 전화 걸기 버튼을 가져옴
        btnCall.setOnClickListener(listener); // 전화걸기 버튼에 리스너 부착

        /*
        화면전환 버튼을 눌렀을때 액티비티 띄우기
         */
        Button btnNext = findViewById(R.id.btnNext);
        btnNext.setOnClickListener(listener);



    }//// onCreate() 끝

    /*
    리스너 부착방법2
        리스너 선언
     */
    View.OnClickListener listener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            // 버튼을 누를대 전달되는 View객체를 통해 눌러진 버튼을 알아낼 수 있다.
            if(v.getId()==R.id.btnCall){
                // 전화걸기 버튼을 눌렀다면 입력 상자에 입력된 전화번호를 가져와서 문자열로 변경
                String callNumber = editText.getText().toString();
                // 인텐트를 통해 tel: 과 함께 전달하면 전화걸기 앱이 실행된다.
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:"+callNumber));
                startActivity(intent);

            // 화면전환 버튼을 눌렀다면 내부 액티비티를 띄워준다.
            }else if(v.getId()==R.id.btnNext){
                Intent intent = new Intent(v.getContext(), SubActivity.class);
                startActivity(intent);
            }


        }
    };

    /*
    리스너 부착방법4
        : 버튼의 onclick 속성을 이용한다.
        이때 호출할 메소드명을 기술하면 아래 메소드를 즉시 호출할 수 있다.
        메소드 선언시 매개변수로 View 객체를 사용한다.
     */
    public void myBtnClick(View view){
        Toast.makeText(view.getContext(),
                "onclick속성을 이용한 버튼입니다. 시간 짧음",
                Toast.LENGTH_SHORT).show();

    }//// myBtnClick() 끝


}//// MainActivity 끝