package com.example.appsuperseguro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class OnboardingActivity extends AppCompatActivity {

    private OnboardingAdapter onboardingAdapter;
    private LinearLayout layoutOnboardingIndicator;
    private Button btSkip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        layoutOnboardingIndicator = findViewById(R.id.lyOnboardingIndicators);
        btSkip = findViewById(R.id.btSkip);

        setupOnboardingItems();

        final ViewPager2 onboardingViewPager = findViewById(R.id.vpOnboarding);
        onboardingViewPager.setAdapter(onboardingAdapter);

        setupOnboardingIndicator();
        setCurrentOnboardingIndicator(0);

        onboardingViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnboardingIndicator(position);
            }
        });

        btSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onboardingViewPager.getCurrentItem() + 1 < onboardingAdapter.getItemCount()){
                    onboardingViewPager.setCurrentItem(onboardingViewPager.getCurrentItem() + 1);
                }else {
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                }
            }
        });

    }

    private void setupOnboardingItems(){

        List<OnboardingItem> onboardingItems = new ArrayList<>();

        OnboardingItem item01 = new OnboardingItem();
        item01.setTitle("Solicitar viaje");
        item01.setDescription("Solicite que lo lleve un conductor de la comunidad cercana");
        item01.setImage(R.drawable.intro01);

        OnboardingItem item02 = new OnboardingItem();
        item02.setTitle("Elije tu conductor");
        item02.setDescription("La enorme red de conductores lo ayuda a encontrar un viaje cómodo seguro y económico");
        item02.setImage(R.drawable.intro02);

        OnboardingItem item03 = new OnboardingItem();
        item03.setTitle("Sigue tu viaje");
        item03.setDescription("Conozca a su conductor de antemano y pueda ver la ubicación actual en tiempo real en el mapa");
        item03.setImage(R.drawable.intro03);

        onboardingItems.add(item01);
        onboardingItems.add(item02);
        onboardingItems.add(item03);

        onboardingAdapter = new OnboardingAdapter(onboardingItems);

    }

    private void setupOnboardingIndicator(){

        ImageView[] indicators = new ImageView[onboardingAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8,0,8,0);
        for (int i = 0; i < indicators.length; i++){
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(),
                    R.drawable.onboarding_indicator_inactive
            ));
            indicators[i].setLayoutParams(layoutParams);
            layoutOnboardingIndicator.addView(indicators[i]);
        }

    }

    private void setCurrentOnboardingIndicator(int index){

        int childCount = layoutOnboardingIndicator.getChildCount();
        for (int i = 0; i < childCount; i++){
            ImageView imageView = (ImageView) layoutOnboardingIndicator.getChildAt(i);
            if (i == index){
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(),
                                R.drawable.onboarding_indicator_active)
                );
            }else{
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(),
                                R.drawable.onboarding_indicator_inactive)
                );
            }
        }

        if (index == onboardingAdapter.getItemCount() - 1){
            btSkip.setVisibility(View.VISIBLE);
            btSkip.setText("¡EMPEZAR!");
        }else {
            btSkip.setVisibility(View.INVISIBLE);
        }

    }
}