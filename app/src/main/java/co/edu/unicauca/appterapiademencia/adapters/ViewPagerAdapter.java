package co.edu.unicauca.appterapiademencia.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SEBAS on 07/11/2016.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private  final List<Fragment> mFragmentList= new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();
    private final Bundle fragmentBundle;
    public ViewPagerAdapter(FragmentManager fragmentManager,Bundle args){
        super(fragmentManager);
        fragmentBundle = args;
    }
    @Override
    public Fragment getItem(int position) {

        mFragmentList.get(position).setArguments(fragmentBundle);
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
    public void addFragment(Fragment fragment,String title){
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);

    }
    @Override
    public CharSequence getPageTitle(int position){
        return mFragmentTitleList.get(position);

    }



}
