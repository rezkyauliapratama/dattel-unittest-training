package android.rezkyauliapratama.com.unittesttraining.network;

import android.rezkyauliapratama.com.unittesttraining.schema.ListEvent;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NetworkApi {

    @GET("api/v1/json/1/eventspastleague.php")
    Single<ListEvent> getPastMatchLeague(
            @Query("id") String leagueId
    );
}
