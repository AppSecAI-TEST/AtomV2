package com.tongxun.atongmu.parent.dialog;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.application.ParentApplication;
import com.tongxun.atongmu.parent.ui.homework.IAudioRecordListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Anro on 2017/7/6.
 */

public class AudioDialog extends BaseDialog implements View.OnTouchListener {

    @BindView(R.id.tv_record_status)
    TextView tvRecordStatus;
    @BindView(R.id.ib_audio_record)
    ImageButton ibAudioRecord;
    @BindView(R.id.iv_audio_delete)
    ImageView ivAudioDelete;

    private IAudioRecordListener mlistener;

    public AudioDialog(Context context, IAudioRecordListener listener) {
        super(context, View.inflate(context, R.layout.dialog_audio_layout, null), new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        ButterKnife.bind(this);
        mlistener = listener;
        init();

    }


    private void init() {
        ibAudioRecord.setOnTouchListener(this);
        ivAudioDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                tvRecordStatus.setText(ParentApplication.getContext().getResources().getString(R.string.stop_record));
                if (mlistener != null) {
                    mlistener.startRecordAudio();
                }
                break;
            case MotionEvent.ACTION_UP:
                tvRecordStatus.setText(ParentApplication.getContext().getResources().getString(R.string.start_record));
                if (mlistener != null) {
                    mlistener.stopRecordAudio();
                }
                break;
        }
        return false;
    }


}
