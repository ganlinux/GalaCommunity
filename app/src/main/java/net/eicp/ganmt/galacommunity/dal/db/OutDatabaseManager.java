package net.eicp.ganmt.galacommunity.dal.db;

import android.content.Context;
import android.os.Environment;

import net.eicp.ganmt.galacommunity.tools.Utils;

public class OutDatabaseManager {
	private Context context;
	public static final String DATABASE_FILENAME = Utils.OUT_DATABASE_NAME; // 这个是DB文件名字
	public static final String PACKAGE_NAME = Utils.PACKAGENAME; // 这个是自己项目包路径
	public static final String DATABASE_PATH = "/data"
			+ Environment.getDataDirectory().getAbsolutePath() + "/"
			+ PACKAGE_NAME + "/databases"; // 获取存储位置地址

	public OutDatabaseManager(Context context) {
		this.context = context;
	}

	public void createDatabase() {
		/*
		try {
			String databaseFilename = DATABASE_PATH + "/" + DATABASE_FILENAME;
			File dir = new File(DATABASE_PATH);
			if (!dir.exists()) {
				dir.mkdir();
			}
			if (!(new File(databaseFilename)).exists()) {
				InputStream is = context.getResources().openRawResource(
						R.raw.bankcardinfo);
				FileOutputStream fos = new FileOutputStream(databaseFilename);
				byte[] buffer = new byte[1024];
				int count = 0;
				while ((count = is.read(buffer)) > 0) {
					fos.write(buffer, 0, count);
				}
				fos.flush();
				fos.close();
				is.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		 */
	}
}
