package android.rezkyauliapratama.com.unittesttraining.Event;

import android.annotation.SuppressLint;
import android.rezkyauliapratama.com.unittesttraining.network.NetworkApi;
import android.rezkyauliapratama.com.unittesttraining.schema.Event;
import android.rezkyauliapratama.com.unittesttraining.util.TimeUtil;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Rezky Aulia Pratama on 8/10/18.
 */
public class FetchUseCaseEvent extends ApiObservable<FetchUseCaseEvent.Listener>{

    public interface Listener {
        void onEventFetchSuccess(List<Event> events);
        void onEventFetchFailure();
    }

    private final NetworkApi networkApi;

    public FetchUseCaseEvent(NetworkApi networkApi) {

        this.networkApi = networkApi;
    }


    @SuppressLint("CheckResult")
    public void fetchLastEventAndNotify(String id){
        networkApi.getPastMatchLeague(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    if (response != null){
                        if (response.getEvents() != null){
                            for (Event event : response.getEvents()){
                                TimeUtil timeUtil1 = new TimeUtil(new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()));
                                Date date = timeUtil1.convertStringToDate(event.getDateEvent());

                                TimeUtil timeUtil2 = new TimeUtil(new SimpleDateFormat("dd MMMM yyyy",Locale.getDefault()));
                                String userFriendlyDate = timeUtil2.getUserFriendlyDate(date);

                                if (userFriendlyDate != null){
                                    event.setDateEvent(userFriendlyDate);
                                }
                            }
                            notifySuccess(response.getEvents());
                        }else{
                            notifyFailure();
                        }
                    }else{
                        notifyFailure();
                    }

                }, throwable -> {
                    notifyFailure();
                });
    }

    private void notifyFailure() {
        for (Listener listener : getListeners()) {
            listener.onEventFetchFailure();
        }
    }

    private void notifySuccess(List<Event> events) {

        for (Listener listener : getListeners()) {
            listener.onEventFetchSuccess(events);
        }
    }

}
