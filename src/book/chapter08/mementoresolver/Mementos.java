package book.chapter08.mementoresolver;

import android.net.Uri;
import android.provider.BaseColumns;

public class Mementos {

	public static final String AUTHORITY="book.providers.memento";
	public static final class Memento implements BaseColumns{
		public static final String _ID="_id";//memento_tb����_id�ֶ�
		public static final String SUBJECT="subject";//memento_tb����subject�ֶ�
		public static final String BODY="subject";//memento_tb����body�ֶ�
		public static final String DATE="subject";//memento_tb����date�ֶ�
		public static final Uri MEMENTOS_CONTENMT_URI=Uri.parse("content://"+AUTHORITY+"/mementos");//�ṩ����memento����URI
		public static final Uri MEMENTO_CONTENT_URI=Uri.parse("content://"+AUTHORITY+"/memento");//�ṩ��������mementoURI
		
	}
}
