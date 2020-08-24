package org.rtruesoft.kiosk;


import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

public class MainActivity extends BaseActivity {

    private Spinner spinner;
    static final String TAG = "키오스크 테스트";
    private String mLanguageCode = "ko";
    private boolean isUserAction = false;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase,"en"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.spinner);
        setUpAdmin();
        spinner.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                isUserAction = true;
                return false;
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (isUserAction) {
                    mLanguageCode = spinner.getSelectedItem().toString().equals("한국어") ? "ko" : "en";
                    LocaleHelper.setLocale(MainActivity.this,mLanguageCode);
                    recreate();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

}