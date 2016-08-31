package com.suzualpha.media;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaRecorder;
import android.media.audiofx.Visualizer;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.Toast;

import com.suzualpha.templateapk.R;

public class MediaRecorderTest extends Activity {
	
	private MediaRecorder mRecorder;
	private volatile boolean isRecording = false;
	private ImageView mRecordButton;
	private ImageView mPlayButton;
	// 録音した音声ファイルのパス
	private String voiceMediaPath;
	private MediaPlayer mMediaPlayer;
	private Visualizer mVisualizer;
	private File dir;
	private Button startBtn;
	private Button stopBtn;
	private Button playBtn;
	
	private Chronometer mChronometer;
	private ProgressDialog pDialog;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mediarecordertest);
		
		// アプリケーション全体で共有するクラス
		
//		
//		mRecordButton = (ImageView) findViewById(R.id.record_button);
//		mPlayButton = (ImageView) findViewById(R.id.play_button);
//		mChronometer = (Chronometer) findViewById(R.id.chronometer);
		// プレイヤー準備
		
		startBtn = (Button)findViewById(R.id.madeiarecorder_start);
		startBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startRecording();
				
			}
		});
		stopBtn = (Button)findViewById(R.id.mediarecorder_stop);
		stopBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				stopRecording();
				
			}
		});
		
		playBtn = (Button)findViewById(R.id.mediarecorder_play);
	/*	playBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自動生成されたメソッド・スタブ
				
			}
		});*/
		mMediaPlayer = new MediaPlayer();
		mMediaPlayer.setOnPreparedListener(new OnPreparedListener() {
			@Override
			public void onPrepared(MediaPlayer player) {
//				mPlayButton.setVisibility(View.GONE);
				mMediaPlayer.start();
			}
		});
		mMediaPlayer.setOnCompletionListener(new OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer player) {
//				mPlayButton.setVisibility(View.VISIBLE);
				player.reset();
			}
		});
	
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		if (isRecording) {
			// 録音中だったら録音停止　
			stopRecording();
		}
		if (dir != null)
			delete(dir);
		
		mRecorder.stop();
		// 解放
		mRecorder.release();
		
		mVisualizer.release();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		
	}
	
	private void delete(File f) {
		if (f.exists() == false) {
			return;
		}
		
		if (f.isFile()) {
			f.delete();
		}
		
		if (f.isDirectory()) {
			File[] files = f.listFiles();
			for (int i = 0; i < files.length; i++) {
				delete(files[i]);
			}
			f.delete();
		}
	}
	
	public void onRecordingClick(View view) {
		if (isRecording) {
			// 録音中だったら録音停止
			stopRecording();
		} else {
			// 録音中じゃなかったら録音スタート！
			startRecording();
		}
	}
	
	public void onPlayClick(View view) {
		if (voiceMediaPath == null) {
			showToast("録音してから再生してください。");
			return;
		}

		try {
			// ファイルパスをセットして
			mMediaPlayer.setDataSource(voiceMediaPath);
			// 準備 -> onPreparedで再生される
			mMediaPlayer.prepare();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private int getSecond(String chrono) {
		String[] strs = chrono.split(":");
		int minutes = Integer.valueOf(strs[0]) * 60;
		int second = Integer.valueOf(strs[1]);
		return minutes + second;
	}
	
	private void showProgressDialog(String message) {
		pDialog = new ProgressDialog(this);
		pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		pDialog.setMessage(message);
		pDialog.show();
	}
	
	private String dateformat() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy'_'MM'_'dd'_'HHmmss", Locale.JAPAN);
		return sdf.format(date);
	}
	
	private void startRecording() {
		// 録音中フラグをセット
		isRecording = true;
		
		// 保存先のディレクトリのFileオブジェクトを生成
		dir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES), "templreAPP");
		// ディレクトリがなければ作成する
		if (!dir.exists()) {
			if (!dir.mkdirs()) {
				showToast("SDカードにディレクトリが作成できませんでした。");
			}
		}
		// ファイル名を時間から生成
		String fileName = dateformat() + ".mp4";
		// ディレクトリとファイル名を繋げてFileオブジェクトを作る
		File file = new File(dir, fileName);
		// 出力ファイルのパス
		voiceMediaPath = file.getAbsolutePath();
		
		mRecorder = new MediaRecorder();
		// 入力ソースにマイクを指定
		mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		// 出力フォーマットに3gpを指定
		mRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
		// 音声エンコーダにAMRを指定
		mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
		// 出力ファイルのパスを指定
		mRecorder.setOutputFile(voiceMediaPath);
		
		try {
			// 準備して
			mRecorder.prepare();
			// 録音スタート！
			mRecorder.start();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void stopRecording() {
		// 録音中フラグを解除
		isRecording = false;
//		mRecordButton.setImageResource(R.drawable.recorder);
		// 録音を停止して
		mRecorder.stop();
		// カウントアップストップ
//		mChronometer.stop();
		// カウントクリア
		// mChronometer.setBase(SystemClock.elapsedRealtime());
		// 解放
		mRecorder.release();
		// トースト表示
		System.out.println(voiceMediaPath + "に保存しました。");
		
	}
	
	private void showToast(String text) {
		Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
	}
}
