package com.wf.aloha;

import android.widget.Toast;

import com.wf.aloha.utils.FileUtils;
import com.wf.aloha.utils.LogUtils;
import com.wf.aloha.utils.ToastUtils;

import junit.extensions.TestSetup;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.mock;

/**
 * Created by wangfei on 2018/3/5.
 */

public class FileUtilsTest {

    private AppRoot mContext;
    private Toast mToast;
    private Addt addt;

    @Before
    public void setUp(){
        mContext = mock(AppRoot.class);
        mToast = mock(Toast.class);
        addt = mock(Addt.class);
    }
    
    
    @Test
    public void testFilePath() throws Exception {

//        Assert.assertArrayEquals();

//        String appRootPath = FileUtils.getAppRootPath(AppRoot.getContext());

//        Addt addt = new Addt();
//        int add = addt.add(1, 2);
//        Assert.assertEquals(3,add);
//        System.out.print(mContext.toString());
//        String rootPath = FileUtils.getAppRootPath(mContext);
//        LogUtils.d("-----","ddd");
//        mToast.makeText(mContext,"kk",Toast.LENGTH_SHORT).show();
        int add = addt.add(1, 2);
        Assert.assertEquals(3,add);
        System.out.print(mContext.toString());
    }
    
}

