package com.weather.weatherapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.weather.weatherapp.fragments.FindWeatherFragment;
import com.weather.weatherapp.fragments.ForecastFragment;
import com.weather.weatherapp.fragments.YourWeatherFragment;
import com.weather.weatherapp.model.Weather;

/**
 *
 * Used pattern:
 * Command (1/2)
 *
 */

@SuppressWarnings("ConstantConditions")
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, YourWeatherFragment.OnWeatherLoadedListener {

    private Weather yourWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            YourWeatherFragment fragment = new YourWeatherFragment();
            fragmentTransaction.add(R.id.fragment_container, fragment);
            fragmentTransaction.commit();

            navigationView.getMenu().findItem(R.id.your_weather).setChecked(true);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        FragmentManager fragmentManager = getFragmentManager();
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.your_weather:
                fragment = new YourWeatherFragment();
                break;
            case R.id.find_weather:
                fragment = new FindWeatherFragment();
                break;
            case R.id.forecast:
                fragment = new ForecastFragment();
                break;
        }

        if (fragment != null) {
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public Weather getYourWeather() {
        return yourWeather;
    }

    /**
     * Command pattern
     */

    @Override
    public void onWeatherLoaded(Weather weather) {
        yourWeather = weather;
        ((NavigationView) findViewById(R.id.nav_view)).getMenu().findItem(R.id.your_weather).setTitle(weather.getCityName());
    }
}
