package com.wf.aloha.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import com.wf.aloha.R;
import com.wf.aloha.network.NetApi;
import com.wf.aloha.network.NetService;
import com.wf.aloha.network.UploadEntry;
import com.wf.aloha.utils.FileUtils;
import com.wf.aloha.utils.LogUtils;
import com.wf.aloha.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_pdf);
        ButterKnife.bind(this);
        
        loadPdf();
    }

    private void loadPdf() {
//        Uri pdfUri = Uri.parse("http://www.egmicro.com/download/EG3013_datasheet.pdf");
//        mPdfView.fromUri(pdfUri).load();
        String appRootPath = FileUtils.getAppRootPath(this);
        
        NetApi api = NetApi.getApi();
        NetService service = api.getService();
        File picFile = new File(appRootPath + "xp.jpg");
        if(!picFile.exists()){
            LogUtils.d("-----",picFile.toString());
            ToastUtils.showInstance("pic 不存在");
            return;
        }
        
        HashMap<String, String> paramMap = new HashMap<>();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("token","fd924fc424234f558fff479c5174855e");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        paramMap.put("params", jsonObject.toString());
        paramMap.put("from", "AC");
        
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/otcet-stream"), picFile);
        MultipartBody.Part body = MultipartBody.Part.createFormData("xp.jpg", picFile.getName(), requestBody);
        
        Call<UploadEntry> uploadCall = service.uploadPic(paramMap, body);
        uploadCall.enqueue(new Callback<UploadEntry>() {
            @Override
            public void onResponse(Call<UploadEntry> call, Response<UploadEntry> response) {
                if(response.isSuccessful()){
                    if(response.body().isSuccess()){
                        ToastUtils.showInstance("hahahahhgai touxiangle");
                    }
                }
            }

            @Override
            public void onFailure(Call<UploadEntry> call, Throwable t) {

            }
        });

    }

//    private void uploadFile(String filePath) {
//        // create upload service client
//        UpLoadService service =
//                ServiceGenerator.createService(UpLoadService.class);
//
//        // use the FileUtils to get the actual file by uri
//        File file = new File(filePath);
//
//        // create RequestBody instance from file
//        RequestBody requestFile =
//                RequestBody.create(MediaType.parse("multipart/form-data"), file);
//
//        // MultipartBody.Part is used to send also the actual file name
//        MultipartBody.Part body =
//                MultipartBody.Part.createFormData("file", file.getName(), requestFile);
//
//
//        // finally, execute the request
//        Call<ResponseBody> call = service.upload(body);
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call,
//                                   Response<ResponseBody> response) {
//                try {
//                    Log.e("Upload", "success:"+response.body().string());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Log.e("Upload error:", t.getMessage());
//            }
//        });
//    }
}
