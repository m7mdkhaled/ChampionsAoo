package com.foadia.champions.Adapters;

import com.foadia.champions.Ui.Main.InfoDancing;
import com.foadia.champions.Ui.Main.imgs1;
import com.foadia.champions.Ui.Main.infoSinging;
import com.foadia.champions.Ui.Main.info_acting;
import com.foadia.champions.Ui.Main.info_cooking;
import com.foadia.champions.Ui.Main.info_drawing;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PageAdabter1 extends FragmentPagerAdapter {

    private int numOftabs;


    public PageAdabter1(@NonNull FragmentManager fm, int numOftabs) {

        super(fm);
        this.numOftabs = 5;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new infoSinging();
            case 1:
                return new InfoDancing();
            case 2:
                return new info_cooking();
            case 3:
                return new info_acting();
            case 4:
                return new info_drawing();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {

        return numOftabs;
    }
}
