package net.eicp.ganmt.galacommunity.dal.db;
import java.io.File;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBService {
	private Context context;

	public DBService(Context context) {
		this.context = context;
	}

	/**
	 * 获取银行卡信息
	 * @param bankcardnumber 银行卡号
	 * @return 0银行名，1卡类型，2卡名
	 */
	public String[] getBankCardInfo(String bankcardnumber) {
		String bankName = "";
		String cardType = "";
		String cardName = "";
		
		String databaseFilename = OutDatabaseManager.DATABASE_PATH + "/" + OutDatabaseManager.DATABASE_FILENAME;
		
		if (!(new File(databaseFilename)).exists()) {
			
		}else {
			DBOpenHelper helper = new DBOpenHelper(context, OutDatabaseManager.DATABASE_FILENAME);
			SQLiteDatabase db = helper.getWritableDatabase();
			DBMethod dbMethod = new DBMethod();
			Cursor c = null;
			if (bankcardnumber.length() > 0) {
				for (int i = bankcardnumber.length(); i > 0; i--) {
					c = dbMethod.getBankCardInfo(i,bankcardnumber,db);
					while (c.moveToNext()) {
						bankName = c.getString(c.getColumnIndex("BankName"));
						cardType = c.getString(c.getColumnIndex("CardType"));
						cardName = c.getString(c.getColumnIndex("CardName"));
					}
				}
				c.close();
			}
			db.close();
		}
		
		return new String[]{bankName,cardType,cardName};
		
	}
	
}
