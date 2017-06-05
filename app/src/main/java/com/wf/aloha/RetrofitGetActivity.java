package com.wf.aloha;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.wf.aloha.network.NetApi;
import com.wf.aloha.network.NetService;
import com.wf.aloha.network.PhoneNumEntry;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitGetActivity extends AppCompatActivity {

    private static final String API_KEY = "2b91055ec3a8619ae4421a4ddd97fe54";
    private static final String BASE_URL = "http://apis.baidu.com";
    @BindView(R.id.button)
    Button mBt;
    @BindView(R.id.editText)
    EditText mEnterNum;
    @BindView(R.id.tv_show)
    TextView mTvShow;
    private NetService mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_get);
        ButterKnife.bind(this);

        NetApi api = NetApi.getApi();
        mService = api.getService();
    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        String num = mEnterNum.getText().toString().trim();
        query(num);
//        rxJavaQuery(num);
    }

    //TODO
    //rxJava方式请求数据 未成功 
    private void rxJavaQuery(String num) {
        mService.getResult(API_KEY, num)
                .subscribeOn(io.reactivex.schedulers.Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new io.reactivex.Observer<PhoneNumEntry>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PhoneNumEntry phoneNumEntry) {
                        mTvShow.setText(phoneNumEntry.getResult().getProvince() + phoneNumEntry.getResult().getCompany() + phoneNumEntry.getResult().getCardtype());

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    //retrofit方式请求数据
    private void query(String num) {
//        //1 创建retrofit对象
//        Retrofit retrofit = new Retrofit.Builder()
//                .addConverterFactory(GsonConverterFactory.create())
//                .baseUrl(BASE_URL)
//                .build();
//        //2 创建API请求
//        PhoneService service = retrofit.create(PhoneService.class);
//        Call<PhoneNumEntry> call = service.getResult(API_KEY, num);
        
        
        Call<PhoneNumEntry> call = mService.getPhoneLbs(API_KEY, num);
        //3 发送请求
        call.enqueue(new Callback<PhoneNumEntry>() {
            @Override
            public void onResponse(Call<PhoneNumEntry> call, Response<PhoneNumEntry> response) {
                PhoneNumEntry result = response.body();
                if (result != null) {
                    mTvShow.setText(result.getResult().getProvince() + result.getResult().getCompany() + result.getResult().getCardtype());
                }
            }

            @Override
            public void onFailure(Call<PhoneNumEntry> call, Throwable t) {

            }
        });
    }
}
