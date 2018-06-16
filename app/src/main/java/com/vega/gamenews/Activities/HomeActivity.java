package com.vega.gamenews.Activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.vega.gamenews.Database.DBInstance;
import com.vega.gamenews.Database.Entities.CategoryEntity;
import com.vega.gamenews.Fragments.NewsFragment;
import com.vega.gamenews.R;
import com.vega.gamenews.ViewModels.CategoryVModel;

import java.util.List;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private CategoryVModel categoryVModel;
    private DBInstance db;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    Fragment fragment = new NewsFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        Logged();
        prepareStuff();
        db = DBInstance.createDB(this);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment).commit();

        categoryVModel = ViewModelProviders.of(this).get(CategoryVModel.class);
        categoryVModel.getAllCategories()
                .observe(this, new Observer<List<CategoryEntity>>() {
            @Override
            public void onChanged(@Nullable List<CategoryEntity> categoryEntities) {
                addCategories(categoryEntities);

                for(int i = 0; i<navigationView.getMenu().findItem(R.id.games).getSubMenu().size();i++){

                    String name = "";
                    name = navigationView.getMenu().findItem(R.id.games).getSubMenu().getItem(i).getTitle().toString();
                    name = capitalizeFirstLetter(name);
                    navigationView.getMenu().findItem(R.id.games).getSubMenu().getItem(i).setTitle(name);
                }
            }
        });



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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;

        if (id == R.id.nav_news) {
            fragment = new NewsFragment();
        } else if (id == R.id.nav_fav) {

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_logout) {

        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment).commit();

        drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void prepareStuff(){
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    private void Logged() {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("log", MODE_PRIVATE);
        if (!sharedPreferences.contains("token")) {
            Toast.makeText(this, R.string.Fail2log2, Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    private void addCategories(List<CategoryEntity> categories){
        navigationView.getMenu().findItem(R.id.games).getSubMenu().clear();
        for(CategoryEntity category : categories){
            navigationView.getMenu().findItem(R.id.games).getSubMenu()
                    .add(category.getName());
        }
    }

    private String DameToken(){
        SharedPreferences preferences = this.getSharedPreferences("log", MODE_PRIVATE);
        if(preferences.contains("token")){
            return preferences.getString("token", "");
        }
        return "";
    }

    public String capitalizeFirstLetter(String original) {
        if (original == null || original.length() == 0) {
            return original;
        }
        return original.substring(0, 1).toUpperCase() + original.substring(1);
    }
}
