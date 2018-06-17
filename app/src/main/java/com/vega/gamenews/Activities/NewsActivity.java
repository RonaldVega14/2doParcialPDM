package com.vega.gamenews.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vega.gamenews.R;

public class NewsActivity extends AppCompatActivity{

    private String image, title, body;
    private ImageView imageView;
    private ImageButton fav;
    private TextView Ntitle, Nbody;
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_activity);
        prepareStuff();

        getData();

        Picasso.with(this).load(image).error(R.drawable.hola).into(imageView);
        Ntitle.setText(title);
        Nbody.setText(body);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == 16908332) {
            this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void prepareStuff(){
        imageView = findViewById(R.id.news_image);
        fav = findViewById(R.id.fav);
        Ntitle = findViewById(R.id.news_title);
        Nbody = findViewById(R.id.news_body);
        toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.returnlogo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void getData(){

        Intent intent = getIntent();
        image = intent.getExtras().getString("image");
        title = intent.getExtras().getString("title");
        body = intent.getExtras().getString("body");

    }


}
