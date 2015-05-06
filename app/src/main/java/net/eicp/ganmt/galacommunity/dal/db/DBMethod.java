package net.eicp.ganmt.galacommunity.dal.db;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBMethod {
	/**
	 * 
	 * 从数据库查询银行卡信息
	 * 
	 */
	public Cursor getBankCardInfo(int i,String bankcardnumber,SQLiteDatabase db){
		Cursor c = db.rawQuery(
				"select * from BankCard where MainNumber=?",
				new String[] { bankcardnumber.substring(0, i - 1) });
		return c;
	}
}
