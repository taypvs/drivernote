package app.camnanglaixe.com.android.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.artifex.mupdfdemo.AsyncTask;
import com.artifex.mupdfdemo.MuPDFCore;
import com.artifex.mupdfdemo.MuPDFPageAdapter;
import com.artifex.mupdfdemo.MuPDFReaderView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import app.camnanglaixe.com.android.Common.CommonUtils;
import app.camnanglaixe.com.android.Common.Constanst;
import app.camnanglaixe.com.android.R;
import app.camnanglaixe.com.android.jsonhandler.JsonParseMachine;
import app.camnanglaixe.com.android.models.ContentDetailRule;

/**
 * Created by taypham on 16/12/2016.
 */
public class ContentDetailMultiTaskActivity extends BaseActivity {
    private RelativeLayout pdfView;
    private LinearLayout contentView;
    private ContentDetailRule currentContent;
    private MuPDFCore core;
    private MuPDFReaderView mDocview;
    private TextView detailText;
    private ImageView imageView;
    private TextView titleTv;
    private String mFilePath;
    private String linkPDF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_multi_task);

        init();
    }

    protected void init() {
        setBackBtnOnclick();
        if (getIntent().hasExtra("KEY_CONTENT")) {
            try {
                String json = getIntent().getStringExtra("KEY_CONTENT");
                JSONObject jsonObject = new JSONObject(json);
                Log.d("TayPVS", "TayPVS - subtopic - jsonObject " + jsonObject.toString());
                currentContent = JsonParseMachine.parseContent(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        ((TextView) findViewById(R.id.title)).setText(currentContent.title);
        pdfView = (RelativeLayout) findViewById(R.id.content_pdf_view);
        contentView = (LinearLayout) findViewById(R.id.content_text_view);

        if(currentContent.detail.equals("")){
            initPDF();
        } else {
            initContentText();
        }

        AdView mAdView = (AdView) findViewById(R.id.adView);
        if (CommonUtils.isOnline(getBaseContext())) {
            mAdView.setVisibility(View.VISIBLE);
            AdRequest adRequest = new AdRequest.Builder()
//                    .addTestDevice(CommonUtils.getDeviceId(getBaseContext()))
                    .build();
            mAdView.loadAd(adRequest);
        }
        else{
            mAdView.setVisibility(View.GONE);
        }
    }

    public void onDestroy() {
        super.onDestroy();
    }

    private MuPDFCore openFile(String path) {
        try {
            core = new MuPDFCore(getBaseContext(), path);
            // New file: drop the old outline data
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return core;
    }

    private class copyPdfFromAsset extends AsyncTask<String, String, String> {
        ProgressDialog processDialog;
        int count;
        InputStream input;
        OutputStream output = null;
        File mPdfFile;
        String filePath;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            processDialog = new ProgressDialog(ContentDetailPDFActivity.this);
//            processDialog.setMessage("Processing...");
//            processDialog.show();

        }


        /**
         * Downloading file in background thread
         */
        @Override
        protected String doInBackground(String... f_url) {
            try {
                try {
                    InputStream is = getAssets().open(currentContent.image + ".pdf");
                    int size = is.available();
                    byte[] buffer = new byte[size];
                    is.read(buffer);
                    is.close();

                    mPdfFile = new File(CommonUtils.createFilePath(currentContent.image+".pdf", Constanst.FILE_DRIVER_DOWNLOAD_MAIN_PDF, false));
                    mPdfFile.mkdirs();
                    FileOutputStream fos = new FileOutputStream(CommonUtils.createFilePath(currentContent.image+".pdf", Constanst.FILE_DRIVER_DOWNLOAD_MAIN_PDF, true));
                    fos.write(buffer);
                    fos.close();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }

            } catch (Exception e) {
                e.printStackTrace();

            }

            return "";
        }

        protected void onProgressUpdate(String... progress) {

        }

        @Override
        protected void onPostExecute(String file_url) {
//            if (processDialog.isShowing())
//                processDialog.dismiss();
            cancel(false);
            try {
                if (output != null)
                    output.close();
                if (input != null)
                    input.close();

                Gson gson = new Gson();
                String json = gson.toJson(currentContent);
                startContentAdvance(json);
                finish();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    private void initPDF(){
        contentView.setVisibility(View.GONE);
        pdfView.setVisibility(View.VISIBLE);
        mFilePath = CommonUtils.createFilePath(currentContent.image+".pdf", Constanst.FILE_DRIVER_DOWNLOAD_MAIN_PDF, true);
        File pdfFile = new File(mFilePath);
        if (!pdfFile.exists()) {
            new copyPdfFromAsset().execute();
        }
        else {
            Log.d("TayPVS", "TayPVS - mFilePath " + mFilePath);
            core = openFile(CommonUtils.createFilePath(currentContent.image+".pdf", Constanst.FILE_DRIVER_DOWNLOAD_MAIN_PDF, true));
            if (core != null && core.countPages() == 0) {
                core = null;
            }
            if (core == null || core.countPages() == 0 || core.countPages() == -1) {
                Log.d("TayPVS", " TayPVSDocument Not Opening");
                return;
            }
            if (core != null) {
                mDocview = new MuPDFReaderView(this) {
                    @Override
                    protected void onMoveToChild(int i) {
                        if (core == null)
                            return;
                        super.onMoveToChild(i);
                    }

                };

                mDocview.setAdapter(new MuPDFPageAdapter(this, core));
                pdfView.addView(mDocview);
            }
        }
    }

    private void initContentText(){
        contentView.setVisibility(View.VISIBLE);
        pdfView.setVisibility(View.GONE);
        detailText = (TextView) findViewById(R.id.content_text);
        titleTv = (TextView) findViewById(R.id.content_title);
        imageView = (ImageView) findViewById(R.id.content_image);
        detailText.setText(Html.fromHtml(currentContent.detail));

        titleTv.setText(currentContent.title);
        if(currentContent.image!=null&&!currentContent.image.equals("")){
            imageView.setVisibility(View.VISIBLE);
            if(CommonUtils.getDrawableResourceByName(getBaseContext(), currentContent.image.trim().toLowerCase())!=null) {
                imageView.setVisibility(View.VISIBLE);
                imageView.setImageDrawable(CommonUtils.getDrawableResourceByName(getBaseContext(), currentContent.image.trim().toLowerCase()));
            }
        }
    }
}
