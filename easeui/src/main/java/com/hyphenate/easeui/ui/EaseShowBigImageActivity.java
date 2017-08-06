/**
 * Copyright (C) 2016 Hyphenate Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hyphenate.easeui.ui;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.R;
import com.hyphenate.easeui.model.EaseImageCache;
import com.hyphenate.easeui.utils.EaseLoadLocalBigImgTask;
import com.hyphenate.easeui.widget.photoview.EasePhotoView;
import com.hyphenate.util.EMLog;
import com.hyphenate.util.ImageUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * download and show original image
 * 
 */
public class EaseShowBigImageActivity extends EaseBaseActivity {
	private static final String TAG = "ShowBigImage"; 
	private ProgressDialog pd;
	private EasePhotoView image;
	private int default_res = R.drawable.ease_default_image;
	private String localFilePath;
	private Bitmap bitmap;
	private boolean isDownloaded;
	private ImageView iv_title_back;
	private TextView tv_title_save;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.ease_activity_show_big_image);
		super.onCreate(savedInstanceState);

		image = (EasePhotoView) findViewById(R.id.image);
		iv_title_back = (ImageView) findViewById(R.id.iv_title_back);
		tv_title_save = (TextView) findViewById(R.id.tv_title_save);
		ProgressBar loadLocalPb = (ProgressBar) findViewById(R.id.pb_load_local);
		default_res = getIntent().getIntExtra("default_image", R.drawable.ease_default_avatar);
		final Uri uri = getIntent().getParcelableExtra("uri");
		localFilePath = getIntent().getExtras().getString("localUrl");
		String msgId = getIntent().getExtras().getString("messageId");
		EMLog.d(TAG, "show big msgId:" + msgId );

		//show the image if it exist in local path
		if (uri != null && new File(uri.getPath()).exists()) {
			EMLog.d(TAG, "showbigimage file exists. directly show it");
			DisplayMetrics metrics = new DisplayMetrics();
			getWindowManager().getDefaultDisplay().getMetrics(metrics);
			// int screenWidth = metrics.widthPixels;
			// int screenHeight =metrics.heightPixels;
			bitmap = EaseImageCache.getInstance().get(uri.getPath());
			if (bitmap == null) {
				EaseLoadLocalBigImgTask task = new EaseLoadLocalBigImgTask(this, uri.getPath(), image, loadLocalPb, ImageUtils.SCALE_IMAGE_WIDTH,
						ImageUtils.SCALE_IMAGE_HEIGHT);
				if (android.os.Build.VERSION.SDK_INT > 10) {
					task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				} else {
					task.execute();
				}
			} else {
				image.setImageBitmap(bitmap);
			}
		} else if(msgId != null) {
		    downloadImage(msgId);
		}else {
			image.setImageResource(default_res);
		}


		iv_title_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				finish();
			}
		});

		tv_title_save.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				if(bitmap!=null){
					String path = Environment.getExternalStorageDirectory().getPath()+"/Atom_Parent/"+ UUID.randomUUID() + ".jpg";
					final File mFile = new File(path);
					if (mFile.exists()) {
						mFile.delete();
					}
					new Thread(new Runnable() {
						@Override
						public void run() {
							try {
								FileOutputStream out = new FileOutputStream(mFile);
								bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
								out.flush();
								out.close();
								handler.sendEmptyMessage(1000);
								sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(mFile)));
							} catch (IOException e) {
								e.printStackTrace();
								handler.sendEmptyMessage(1001);
							}
						}
					}).start();
				}else {
					//获取图片的宽和高，并不把图片加载到内存中
					BitmapFactory.Options options = new BitmapFactory.Options();
					options.inJustDecodeBounds = true;
					if(uri!=null){
						BitmapFactory.decodeFile(uri.getPath(), options);
					}else {
						BitmapFactory.decodeFile(localFilePath, options);
					}
					options.inSampleSize = caculateInSampleSize(options,720);
					//使用获取到的InSampleSize再次解析图片
					options.inJustDecodeBounds = false;
					if(uri!=null){
						bitmap=BitmapFactory.decodeFile(uri.getPath(), options);
					}else {
						bitmap=BitmapFactory.decodeFile(localFilePath, options);
					}
					String path = Environment.getExternalStorageDirectory().getPath()+"/Atom_Parent/"+ System.currentTimeMillis() + ".jpg";
					final File mFile = new File(path);
					new Thread(new Runnable() {
						@Override
						public void run() {
							try {
								FileOutputStream out = new FileOutputStream(mFile);
								bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
								out.flush();
								out.close();
								handler.sendEmptyMessage(1000);
								sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(mFile)));
							} catch (IOException e) {
								e.printStackTrace();
								handler.sendEmptyMessage(1001);
							}
						}
					}).start();
				}
			}
		});
	}

	private Handler handler=new Handler(){
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what){
				case 1000:
					Toast.makeText(EaseShowBigImageActivity.this, "图片保存在Atom_Parent相册中", Toast.LENGTH_SHORT).show();
					break;
				case 1001:
					Toast.makeText(EaseShowBigImageActivity.this, "图片保存失败，请稍后再试", Toast.LENGTH_SHORT).show();
					break;
			}
		}
	};

	private int caculateInSampleSize(BitmapFactory.Options options, int reqwidth) {
		int width = options.outWidth;
		int inSampleSize = 1;
		if (width > reqwidth) {
			inSampleSize = (width/reqwidth)+1;
		}
		return inSampleSize;
	}

	/**
	 * download image
	 * 
	 * @param
	 */
	@SuppressLint("NewApi")
	private void downloadImage(final String msgId) {
        EMLog.e(TAG, "download with messageId: " + msgId);
		String str1 = getResources().getString(R.string.Download_the_pictures);
		pd = new ProgressDialog(this);
		pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		pd.setCanceledOnTouchOutside(false);
		pd.setMessage(str1);
		pd.show();
		File temp = new File(localFilePath);
		final String tempPath = temp.getParent() + "/temp_" + temp.getName();
		final EMCallBack callback = new EMCallBack() {
			public void onSuccess() {
			    EMLog.e(TAG, "onSuccess" );
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
                        new File(tempPath).renameTo(new File(localFilePath));

                        DisplayMetrics metrics = new DisplayMetrics();
						getWindowManager().getDefaultDisplay().getMetrics(metrics);
						int screenWidth = metrics.widthPixels;
						int screenHeight = metrics.heightPixels;

						bitmap = ImageUtils.decodeScaleImage(localFilePath, screenWidth, screenHeight);
						if (bitmap == null) {
							image.setImageResource(default_res);
						} else {
							image.setImageBitmap(bitmap);
							EaseImageCache.getInstance().put(localFilePath, bitmap);
							isDownloaded = true;
						}
						if (isFinishing() || isDestroyed()) {
						    return;
						}
						if (pd != null) {
							pd.dismiss();
						}
					}
				});
			}

			public void onError(int error, String msg) {
				EMLog.e(TAG, "offline file transfer error:" + msg);
				File file = new File(tempPath);
				if (file.exists()&&file.isFile()) {
					file.delete();
				}
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						if (EaseShowBigImageActivity.this.isFinishing() || EaseShowBigImageActivity.this.isDestroyed()) {
						    return;
						}
                        image.setImageResource(default_res);
                        pd.dismiss();
					}
				});
			}

			public void onProgress(final int progress, String status) {
				EMLog.d(TAG, "Progress: " + progress);
				final String str2 = getResources().getString(R.string.Download_the_pictures_new);
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
                        if (EaseShowBigImageActivity.this.isFinishing() || EaseShowBigImageActivity.this.isDestroyed()) {
                            return;
                        }
						pd.setMessage(str2 + progress + "%");
					}
				});
			}
		};
		
		EMMessage msg = EMClient.getInstance().chatManager().getMessage(msgId);
		msg.setMessageStatusCallback(callback);

		EMLog.e(TAG, "downloadAttachement");
		EMClient.getInstance().chatManager().downloadAttachment(msg);
	}

	@Override
	public void onBackPressed() {
		if (isDownloaded)
			setResult(RESULT_OK);
		finish();
	}
}
