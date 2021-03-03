package com.foadia.champions.Ui.Main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.foadia.champions.Adapters.PageAdabter1;
import com.foadia.champions.R;

public class MainHome extends AppCompatActivity implements View.OnClickListener {
    ViewPager viewPager;
    LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;
    RelativeLayout RL_Act, RL_Sing, RL_Draw, RL_Dance, RL_Cook;
    //Singing Fragment
    ImageView Singing_icon_on, sActing_icon_off, sCooking_icon_off, sDrawing_icon_off, sDancing_icon_off;
    //Dancing Fragment
    ImageView Dancing_icon_on, dSinging_icon_off, dActing_icon_off, dCooking_icon_off, dDrawing_icon_off;
    //Acting Fragment
    ImageView Acting_icon_on, aSinging_icon_off, aCooking_icon_off, aDrawing_icon_off, aDancing_icon_off;
    //Cooking Fragment
    ImageView Cooking_icon_on, cSinging_icon_off, cDrawing_icon_off, cDancing_icon_off, cActing_icon_off;
    //Drawing Fragment
    ImageView Drawing_icon_on, rSinging_icon_off, rDancing_icon_off, rActing_icon_off, rCooking_icon_off;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);


        viewPager = findViewById(R.id.VPager);
        sliderDotspanel = findViewById(R.id.SliderDots);

        //Call RelativeLayouts by Ids
        RL_Act = findViewById(R.id.RLActing);
        RL_Cook = findViewById(R.id.RL_Cooking);
        RL_Dance = findViewById(R.id.RL_Dancing);
        RL_Draw = findViewById(R.id.RL_Drawing);
        RL_Sing = findViewById(R.id.RL_Singing);

        //Assign variables to Singing Fragment
        Singing_icon_on = findViewById(R.id.Sing_icon_on);
        sActing_icon_off = findViewById(R.id.sAct_icon_off);
        sCooking_icon_off = findViewById(R.id.sCook_icon_off);
        sDrawing_icon_off = findViewById(R.id.sDraw_icon_off);
        sDancing_icon_off = findViewById(R.id.sDance_icon_off);

        //Assign variables to Acting Fragment
        Acting_icon_on = findViewById(R.id.Act_icon_on);
        aSinging_icon_off = findViewById(R.id.aSing_icon_off);
        aCooking_icon_off = findViewById(R.id.aCook_icon_off);
        aDancing_icon_off = findViewById(R.id.aDance_icon_off);
        aDrawing_icon_off = findViewById(R.id.aDraw_icon_off);

        //Assign variables to Cooking Fragment
        Cooking_icon_on = findViewById(R.id.Cook_icon_on);
        cActing_icon_off = findViewById(R.id.cAct_icon_off);
        cDancing_icon_off = findViewById(R.id.cDance_icon_off);
        cDrawing_icon_off = findViewById(R.id.cDraw_icon_off);
        cSinging_icon_off = findViewById(R.id.cSing_icon_off);

        //Assign variables to Drawing Fragment
        Drawing_icon_on = findViewById(R.id.Draw_icon_on);
        rActing_icon_off = findViewById(R.id.rAct_icon_off);
        rCooking_icon_off = findViewById(R.id.rCook_icon_off);
        rDancing_icon_off = findViewById(R.id.rDance_icon_off);
        rSinging_icon_off = findViewById(R.id.rSing_icon_off);

        //Assign variables to Dancing Fragment
        Dancing_icon_on = findViewById(R.id.Dance_icon_on);
        dSinging_icon_off = findViewById(R.id.dSing_icon_off);
        dActing_icon_off = findViewById(R.id.dAct_icon_off);
        dCooking_icon_off = findViewById(R.id.dCook_icon_off);
        dDrawing_icon_off = findViewById(R.id.dDraw_icon_off);

        //OnClickListener for Singing fragment
        sCooking_icon_off.setOnClickListener(this);
        sDancing_icon_off.setOnClickListener(this);
        sDrawing_icon_off.setOnClickListener(this);
        sActing_icon_off.setOnClickListener(this);
        Singing_icon_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSingingActivity();
            }
        });

        //OnClickListener for Acting fragment
        aCooking_icon_off.setOnClickListener(this);
        aDancing_icon_off.setOnClickListener(this);
        aDrawing_icon_off.setOnClickListener(this);
        aSinging_icon_off.setOnClickListener(this);
        Acting_icon_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActingActivity();
            }
        });

        //OnClickListener for Cooking fragment
        cSinging_icon_off.setOnClickListener(this);
        cDancing_icon_off.setOnClickListener(this);
        cDrawing_icon_off.setOnClickListener(this);
        cActing_icon_off.setOnClickListener(this);
        Cooking_icon_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenCookingActivity();
            }
        });

        //OnClickListener for Drawing fragment
        rSinging_icon_off.setOnClickListener(this);
        rDancing_icon_off.setOnClickListener(this);
        rCooking_icon_off.setOnClickListener(this);
        rActing_icon_off.setOnClickListener(this);
        Drawing_icon_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDrawingActivity();
            }
        });

        //OnClickListener for Dancing fragment
        dSinging_icon_off.setOnClickListener(this);
        dDrawing_icon_off.setOnClickListener(this);
        dCooking_icon_off.setOnClickListener(this);
        dActing_icon_off.setOnClickListener(this);
        Dancing_icon_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDancingActivity();
            }
        });


        PageAdabter1 pagerAdapter = new PageAdabter1(getSupportFragmentManager(), 3);
        dotscount = pagerAdapter.getCount();
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

        viewPager.setAdapter(pagerAdapter);
        dots = new ImageView[dotscount];

        for (int i = 0; i < dotscount; i++) {

            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.non_active_dash));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDotspanel.addView(dots[i], params);

        }
        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dash));


    }

    private void openDancingActivity() {
        Intent intentD = new Intent(this,MainDancing.class);
        startActivity(intentD);
    }

    private void openDrawingActivity() {
        Intent intentR = new Intent(this,MainDrawing.class);
        startActivity(intentR);
    }

    private void OpenCookingActivity() {
        Intent intentC = new Intent(this,MainCooking.class);
        startActivity(intentC);
    }

    private void openActingActivity() {
        Intent intentA = new Intent(this,MainFilming.class);
        startActivity(intentA);
    }

    private void openSingingActivity() {
        Intent intentS = new Intent(this,MainSinging.class);
        startActivity(intentS);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //Singing & Other Fragments
            case R.id.sDance_icon_off:
                RL_Sing.setVisibility(v.GONE);
                RL_Dance.setVisibility(v.VISIBLE);
                viewPager.setCurrentItem(1);
                break;
            case R.id.dSing_icon_off:
                RL_Sing.setVisibility(v.VISIBLE);
                RL_Dance.setVisibility(v.GONE);
                viewPager.setCurrentItem(0);
                break;
            case R.id.sAct_icon_off:
                RL_Act.setVisibility(v.VISIBLE);
                RL_Sing.setVisibility(v.GONE);
                viewPager.setCurrentItem(3);
                break;
            case R.id.aSing_icon_off:
                RL_Act.setVisibility(v.GONE);
                RL_Sing.setVisibility(v.VISIBLE);
                viewPager.setCurrentItem(0);
                break;
            case R.id.sDraw_icon_off:
                RL_Sing.setVisibility(v.GONE);
                RL_Draw.setVisibility(v.VISIBLE);
                viewPager.setCurrentItem(4);
                break;
            case R.id.rSing_icon_off:
                RL_Sing.setVisibility(v.VISIBLE);
                RL_Draw.setVisibility(v.GONE);
                viewPager.setCurrentItem(0);
                break;
            case R.id.sCook_icon_off:
                RL_Cook.setVisibility(v.VISIBLE);
                RL_Sing.setVisibility(v.GONE);
                viewPager.setCurrentItem(2);
                break;
            case R.id.cSing_icon_off:
                RL_Sing.setVisibility(v.VISIBLE);
                RL_Cook.setVisibility(v.GONE);
                viewPager.setCurrentItem(0);
                break;
            //End of Singing in Other Fragments
            //RL_Act Item NO. 3
            //RL_Dance Item NO. 1
            //RL_Draw Item NO. 4
            //RL_Cook Item NO. 2
            //RL_Sing Item NO. 0
            //Start Of Dancing
            case R.id.aDance_icon_off:
                RL_Dance.setVisibility(v.VISIBLE);
                RL_Act.setVisibility(v.GONE);
                viewPager.setCurrentItem(1);
                break;
            case R.id.dAct_icon_off:
                RL_Act.setVisibility(v.VISIBLE);
                RL_Dance.setVisibility(v.GONE);
                viewPager.setCurrentItem(3);
                break;
            case R.id.cDance_icon_off:
                RL_Dance.setVisibility(v.VISIBLE);
                RL_Cook.setVisibility(v.GONE);
                viewPager.setCurrentItem(1);
                break;
            case R.id.dCook_icon_off:
                RL_Dance.setVisibility(v.GONE);
                RL_Cook.setVisibility(v.VISIBLE);
                viewPager.setCurrentItem(2);
                break;
            case R.id.rDance_icon_off:
                RL_Dance.setVisibility(v.VISIBLE);
                RL_Draw.setVisibility(v.GONE);
                viewPager.setCurrentItem(1);
                break;
            case R.id.dDraw_icon_off:
                RL_Dance.setVisibility(v.GONE);
                RL_Draw.setVisibility(v.VISIBLE);
                viewPager.setCurrentItem(4);
                break;
            //Start Of Cooking
            case R.id.aCook_icon_off:
                RL_Cook.setVisibility(v.VISIBLE);
                RL_Act.setVisibility(v.GONE);
                viewPager.setCurrentItem(2);
                break;
            case R.id.cAct_icon_off:
                RL_Cook.setVisibility(v.GONE);
                RL_Act.setVisibility(v.VISIBLE);
                viewPager.setCurrentItem(3);
                break;
            case R.id.rCook_icon_off:
                RL_Cook.setVisibility(v.VISIBLE);
                RL_Draw.setVisibility(v.GONE);
                viewPager.setCurrentItem(2);
                break;
            case R.id.cDraw_icon_off:
                RL_Cook.setVisibility(v.GONE);
                RL_Draw.setVisibility(v.VISIBLE);
                viewPager.setCurrentItem(4);
                break;
            //Start Of Acting
            case R.id.aDraw_icon_off:
                RL_Draw.setVisibility(v.VISIBLE);
                RL_Act.setVisibility(v.GONE);
                viewPager.setCurrentItem(4);
                break;
            case R.id.rAct_icon_off:
                RL_Act.setVisibility(v.VISIBLE);
                RL_Draw.setVisibility(v.GONE);
                viewPager.setCurrentItem(3);
                break;
        }
    }
//    private void replaceFragment(Fragment fragment) {
//        FragmentTransaction transaction = getFragmentManager().beginTransaction();
//        transaction.replace(R.id.SingFragment,fragment);
//        transaction.addToBackStack(null);
//        transaction.commit();
//    }
}