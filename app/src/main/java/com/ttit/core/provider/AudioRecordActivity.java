package com.ttit.core.provider;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.ttit.helloworld.R;

import java.io.File;

public class AudioRecordActivity extends AppCompatActivity implements View.OnClickListener {
    private MediaPlayer mediaPlayer;
    private MediaRecorder mediaRecorder = new MediaRecorder();
    private File audioFile;
    private String msg;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audio_recorder);
        Button btnStart = (Button) findViewById(R.id.btnStart);
        Button btnStop = (Button) findViewById(R.id.btnStop);
        Button btnPlay = (Button) findViewById(R.id.btnPlay);
        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        btnPlay.setOnClickListener(this);

        //从6.0系统开始,需要动态获取权限
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);
        // 试图拿到 联系人 读取权限
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, 0);
        }
    }

    @Override
    public void onClick(View view) {
        try {

            switch (view.getId()) {
                case R.id.btnStart:
                    // 设置音频来源(一般为麦克风)
                    mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                    // 设置音频输出格式（默认的输出格式）
                    mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
                    // 设置音频编码方式（默认的编码方式）
                    mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
                    // 创建一个临时的音频输出文件
                    audioFile = File.createTempFile("record_", ".amr");
                    Log.e("ttit", audioFile.getName());
                    mediaRecorder.setOutputFile(audioFile.getAbsolutePath());
                    mediaRecorder.prepare();
                    mediaRecorder.start();
                    msg = "正在录音...";
                    break;
                case R.id.btnStop:
                    if (audioFile != null) {
                        mediaRecorder.stop();
                    }
                    msg = "已经停止录音.";
                    break;
                case R.id.btnPlay:
                    if (audioFile != null) {
                        mediaPlayer = new MediaPlayer();
                        mediaPlayer.setDataSource(audioFile.getAbsolutePath());
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                setTitle("录音播放完毕.");
                            }
                        });
                        msg = "正在播放录音...";
                    }
                    break;
            }
            setTitle(audioFile.getName() + msg);
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            setTitle(e.getMessage());
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.e("ttit", String.valueOf(requestCode));
        boolean flag = (grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED);
        switch (requestCode) {
            case 0:
                if (flag) {
                    Toast.makeText(AudioRecordActivity.this, "音频记录权限授权成功", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
}
