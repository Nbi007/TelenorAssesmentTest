package com.telenor.herokuaapp.network;

import com.telenor.herokuaapp.model.Film;
import com.telenor.herokuaapp.model.GetAllFilmsResponseModel;
import com.telenor.herokuaapp.utils.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NetworkApi {
    @GET(Constants.API_END_POINT_FILMS)
    Call<List<Film>> GetAllFilms();
}
