package com.wf.aloha;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoadPdfActivity extends AppCompatActivity {

    @BindView(R.id.pdf_view)
    PDFView mPdfView;

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

    }
}
