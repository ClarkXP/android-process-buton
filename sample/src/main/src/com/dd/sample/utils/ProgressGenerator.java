package com.dd.sample.utils;

import com.dd.processbutton.ProcessButton;

import android.os.Handler;

import java.util.Random;

public class ProgressGenerator {

	public interface OnCompleteListener {

		public void onComplete();

		public void onError();
	}

	private OnCompleteListener mListener;
	private int mProgress;
	private boolean mError;

	public ProgressGenerator(OnCompleteListener listener) {
		mListener = listener;

	}

	public void setError(boolean b) {
		mError = b;
	}

	public void start(final ProcessButton button) {
		final Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				button.setErrorStatus(mError);
				if (button.isErrorStatus()) {
					mListener.onError();
				} else {
					mProgress += 10;
					button.setProgress(mProgress);

					if (mProgress <= 110) {
						handler.postDelayed(this, generateDelay());
					} else {
						mListener.onComplete();
					}
				}
			}
		}, generateDelay());
	}

	private Random random = new Random();

	private int generateDelay() {
		return random.nextInt(1000);
	}
}
