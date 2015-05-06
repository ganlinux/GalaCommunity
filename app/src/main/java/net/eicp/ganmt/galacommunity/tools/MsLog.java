package net.eicp.ganmt.galacommunity.tools;
import android.util.Log;

/**
 * 易车卖手日志
 * @author pengqm
 *
 */
public class MsLog{
	private String tag = "# YiCheMsLog #";
	private boolean debug = false;
	public MsLog(Object clazz){
		this(clazz.getClass().getSimpleName());
	}
	
	public MsLog(String tag){
		this.tag = tag;
	}

	public void v(String message) {
		if (debug) {
			Log.v(tag, message);
		}
	}

	public void d(String message) {
		if (debug) {
			Log.d(tag, message);
			//writeLog(message);
		}
	}

	public void i(String message) {
		if (debug) {
			Log.i(tag, message);
			//writeLog(message);
		}
	}

	public void w(String message) {
		if (debug) {
			Log.w(tag, message);
		}
	}

	public void e(String message) {
		if (debug) {
			Log.e(tag, message);
		}
	}


}
