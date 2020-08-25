package org.rtruesoft.kiosk;


import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;



import org.rtruesoft.kiosk.event.EventFragment;
import org.rtruesoft.kiosk.restaurant.RestaurantFragment;
import org.rtruesoft.kiosk.scene.AttractionFragment;
import org.rtruesoft.kiosk.scene.AttractionViewFragment;


public class MainActivity extends BaseActivity {

    public static Context mainContext;
    private Spinner spinner;
    static final String TAG = "키오스크 테스트";
    private String mLanguageCode = "ko";
    private boolean isUserAction = false;


    AttractionFragment attractionFragment;
    AttractionViewFragment attractionViewFragment;
    RestaurantFragment restaurantFragment;
    EventFragment eventFragment;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase,"en"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainContext = this;
        attractionFragment = new AttractionFragment();
        attractionViewFragment = new AttractionViewFragment();
        restaurantFragment = new RestaurantFragment();
        eventFragment = new EventFragment();


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

    public void onFragmentChanged(int index) {
        switch (index) {
            case 0:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, attractionFragment).commit();
                break;
            case 1:
                getSupportFragmentManager().beginTransaction().replace(R.id.container,attractionViewFragment).commit();
                break;
            case 2:
                getSupportFragmentManager().beginTransaction().replace(R.id.container,eventFragment).commit();
                break;
        }
    }


}