package android.rezkyauliapratama.com.unittesttraining;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.rezkyauliapratama.com.unittesttraining.Event.FetchUseCaseEvent;
import android.rezkyauliapratama.com.unittesttraining.databinding.ActivityMainBinding;
import android.rezkyauliapratama.com.unittesttraining.network.NetworkApi;
import android.rezkyauliapratama.com.unittesttraining.schema.Event;
import android.rezkyauliapratama.com.unittesttraining.schema.ListEvent;
import android.rezkyauliapratama.com.unittesttraining.util.TimeUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements FetchUseCaseEvent.Listener{

    NetworkApi mNetworkApi;
    MatchRvAdapter adapter;
    FetchUseCaseEvent fetchUseCaseEvent;

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);


        initRetrofit();
        initSpinner();
        initRv();
    }

    @Override
    protected void onStart() {
        super.onStart();
        fetchUseCaseEvent.registerListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        fetchUseCaseEvent.unregisterListener(this);
    }

    private void initSpinner() {
        String[] leagues = getResources().getStringArray(R.array.league);
        String[] leaguesId = getResources().getStringArray(R.array.league_id);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, leagues);
        binding.spinner.setAdapter(arrayAdapter);

        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                   fetchUseCaseEvent.fetchLastEventAndNotify(leaguesId[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initRv() {
        adapter = new MatchRvAdapter();
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerview.setAdapter(adapter);
    }

    private void initRetrofit(){
        Gson gson = new GsonBuilder().create();

        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("https://www.thesportsdb.com/").build();

        mNetworkApi = retrofit.create(NetworkApi.class);


        fetchUseCaseEvent = new FetchUseCaseEvent(mNetworkApi);
    }


    @Override
    public void onEventFetchSuccess(List<Event> events) {
        adapter.bindData(events);

    }

    @Override
    public void onEventFetchFailure() {
        Toast.makeText(this,"Error", Toast.LENGTH_LONG).show();

    }
}
