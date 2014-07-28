package com.abay.aml.thread;

import java.lang.ref.WeakReference;

import android.os.AsyncTask;
/**
 * 
 * »ı“˝”√
 * @version 1.0.0 
 * @author Abay Zhuang <br/>
 *		   Create at 2014-7-17
 */
public abstract class WeakAsyncTask<Params, Progress, Result, WeakTarget>
		extends AsyncTask<Params, Progress, Result> {
	protected WeakReference<WeakTarget> mTarget;

	public WeakAsyncTask(WeakTarget target) {
		mTarget = new WeakReference<WeakTarget>(target);
	}

	@Override
	protected final void onPreExecute() {
		final WeakTarget target = mTarget.get();
		if (target != null) {
			this.onPreExecute(target);
		}
	}

	@Override
	protected final Result doInBackground(Params... params) {
		final WeakTarget target = mTarget.get();
		if (target != null) {
			return this.doInBackground(target, params);
		} else {
			return null;
		}
	}

	@Override
	protected final void onPostExecute(Result result) {
		final WeakTarget target = mTarget.get();
		if (target != null) {
			this.onPostExecute(target, result);
		}
	}

	protected void onPreExecute(WeakTarget target) {
		// Nodefaultaction
	}

	protected abstract Result doInBackground(WeakTarget target,
			Params... params);

	protected void onPostExecute(WeakTarget target, Result result) {
		// Nodefaultaction
	}
}
