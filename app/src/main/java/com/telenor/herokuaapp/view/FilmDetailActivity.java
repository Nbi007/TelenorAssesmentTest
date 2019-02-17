package com.telenor.herokuaapp.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.telenor.herokuaapp.R;
import com.telenor.herokuaapp.model.Film;
import com.telenor.herokuaapp.utils.Constants;
import com.telenor.herokuaapp.utils.PixelsOp;
import com.telenor.herokuaapp.utils.ToolbarOp;

import butterknife.BindView;
import butterknife.ButterKnife;
/**
 * Created by nofal bin idrees on 2/17/2019.
 */
public class FilmDetailActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.description)
    TextView description;
    @BindView(R.id.director_name)
    TextView director_name;
    @BindView(R.id.producer_name)
    TextView producer_name;
    @BindView(R.id.film_rating)
    TextView film_rating;
    @BindView(R.id.poster)
    ImageView poster;
    private Context context;
    private String titleMovie="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_detail);
        ButterKnife.bind(this);
        context = FilmDetailActivity.this;
        //Get film from arguments
        Film film = getIntent().getParcelableExtra(Constants.FILM_KEY);
        if (film != null) {
            updateDetailsView(film);
        }
    }

    private void updateDetailsView(Film film) {
        if (film != null) {
            //Insert film data into layout
            description.setText(film.getDescription());
            titleMovie=film.getTitle()+" ("+film.getReleaseDate()+")";

            try {
                RequestOptions requestOptions = new RequestOptions().centerCrop();
                Glide.with(this).load(film.getPoster(FilmDetailActivity.this)).apply(requestOptions).into(poster);
            } catch (Exception e) {
                e.printStackTrace();
            }
            director_name.setText(film.getDirector());
            producer_name.setText(film.getProducer());
            film_rating.setText(film.getScore());
            setToolbar();
        }
    }

    private void addCustomIcons() {

        ToolbarOp.addIcon(toolbar, context, R.drawable.ic_arrow_back_black, Gravity.START, (int) PixelsOp.pxFromDp(context, 15f), 0, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void setToolbar() {

        toolbar.removeAllViews();
        toolbar.setContentInsetsAbsolute(0, 0);

        toolbar.setTitleTextColor(Color.WHITE);
        ToolbarOp.addTitle(toolbar, context, titleMovie, ToolbarOp.Theme.Dark, true);
       // toolbar.setBackgroundColor(getResources().getColor(R.color.transparent));

        addCustomIcons();
    }
}
