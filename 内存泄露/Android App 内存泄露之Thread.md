# Thread 内存泄露 #

	线程也是造成内存泄露的一个重要的源头。线程产生内存泄露的主要原因在于线程生命周期的不可控。
## 1.看一下下面是否存在问题 ##
	
	/**
	 * 
	 * @version 1.0.0 
	 * @author Abay Zhuang <br/>
	 *		   Create at 2014-7-17
	 */
	public class ThreadActivity extends Activity {
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);
			new MyThread().start();
		}
	
		private class MyThread extends Thread {
			@Override
			public void run() {
				super.run();
				dosomthing();
			}
		}
		private void dosomthing(){
		
		}
	}

	这段代码很平常也很简单，是我们经常使用的形式。
### 真的没有问题吗 ###
	我们思考一个问题：假设MyThread的run函数是一个很费时的操作，当我们开启该线程后，将设备的横屏变为了竖屏，
	一般情况下当屏幕转换时会重新创建Activity，按照我们的想法，老的Activity应该会被销毁才对，然而事实上并非如此。
    由于我们的线程是Activity的内部类，所以MyThread中保存了Activity的一个引用，当MyThread的run函数没有结束时，
	MyThread是不会被销毁的，因此它所引用的老的Activity也不会被销毁，因此就出现了内存泄露的问题。	 
	

## 2.这种线程导致的内存泄露问题应该如何解决呢？ ##
- 第一、将线程的内部类，改为静态内部类。
- 第二、在线程内部采用弱引用保存Context引用。
  代码如下：

		/**
		 * 
		 * @version 1.0.0
		 * @author Abay Zhuang <br/>
		 *         Create at 2014-7-17
		 */
		
		public class ThreadAvoidActivity extends Activity {
			public void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
				setContentView(R.layout.activity_main);
				new MyThread(this).start();
			}
		
			private void dosomthing() {
		
			}
		
			private static class MyThread extends Thread {
				WeakReference<ThreadAvoidActivity> mThreadActivityRef;
		
				public MyThread(ThreadAvoidActivity activity) {
					mThreadActivityRef = new WeakReference<ThreadAvoidActivity>(
							activity);
				}
		
				@Override
				public void run() {
					super.run();
					if (mThreadActivityRef == null)
						return;
					if (mThreadActivityRef.get() != null)
						mThreadActivityRef.get().dosomthing();
					// dosomthing
				}
			}
		}
	
###   上面的两个步骤其实是切换两个对象的双向强引用链接 ##
1.     静态内部类：切断Activity 对于 MyThread的强引用。
1.     弱引用： 切断MyThread对于Activity 的强引用。

##3.AsynTask 内部类会如何呢？##
	有些人喜欢用Android提供的AsyncTask，但事实上AsyncTask的问题更加严重，
	Thread只有在run函数不结束时才出现这种内存泄露问题，然而AsyncTask内部的实现机制是运用了ThreadPoolExcutor,
	该类产生的Thread对象的生命周期是不确定的，是应用程序无法控制的，
	因此如果AsyncTask作为Activity的内部类，就更容易出现内存泄露的问题。

   代码如下：
    

	/**
	 * 
	 * 弱引用
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