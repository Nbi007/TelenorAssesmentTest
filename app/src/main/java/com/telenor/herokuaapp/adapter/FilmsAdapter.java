package com.telenor.herokuaapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.telenor.herokuaapp.R;
import com.telenor.herokuaapp.model.Film;
import com.telenor.herokuaapp.utils.Constants;
import com.telenor.herokuaapp.view.FilmDetailActivity;

import java.util.List;
/**
 * Created by nofal bin idrees on 2/17/2019.
 */
public class FilmsAdapter extends RecyclerView.Adapter {
    private List<Film> films;
    private Context context;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_films_item, parent, false);
        return new ItemTypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final FilmsAdapter.ItemTypeViewHolder itemTypeViewHolder = (FilmsAdapter.ItemTypeViewHolder) holder;
        final Film film = films.get(position);

        //set params on view item
        itemTypeViewHolder.title.setText(film.getTitle());
        itemTypeViewHolder.releaseDate.setText(film.getReleaseDate());

        // loading album cover using Glide library
        RequestOptions requestOptions = new RequestOptions().centerCrop();
        Glide.with(context).load(film.getThumbnail(context)).apply(requestOptions).into(itemTypeViewHolder.thumbnail);

        //add listeners on items
        itemTypeViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDetails(film);
            }
        });
    }

    private void showDetails(Film film) {
        Intent nextActivity = new Intent(context, FilmDetailActivity.class);
        nextActivity.putExtra(Constants.FILM_KEY, film);
        context.startActivity(nextActivity);
    }
    public FilmsAdapter(List<Film> films, Context context) {
        this.films = films;
        this.context = context;
    }
    @Override
    public int getItemCount() { return films.size();}

    private class ItemTypeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private View cardView;
        private ImageView thumbnail;
        private TextView title, releaseDate;

        public ItemTypeViewHolder(View view) {
            super(view);
            cardView = itemView.findViewById(R.id.card_view);
            title = (TextView) itemView.findViewById(R.id.title);
            thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
            releaseDate = (TextView) itemView.findViewById(R.id.release_date);

        }

        @Override
        public void onClick(View view) {

        }
    }
}
