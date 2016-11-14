package book.chapter08.mementoresolver;

import java.util.Calendar;
import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Button chooseDate,add,show;
	private EditText date,subject,body;
	private ListView result;
	private LinearLayout title;
	private ContentResolver contentResolver;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		contentResolver = getContentResolver();
		date=(EditText)this.findViewById(R.id.date);
		subject=(EditText)this.findViewById(R.id.subject);
		body=(EditText)this.findViewById(R.id.body);
		result=(ListView)this.findViewById(R.id.result);
		title=(LinearLayout)this.findViewById(R.id.title);
		add=(Button)this.findViewById(R.id.add);
		show=(Button)this.findViewById(R.id.query);
		chooseDate=(Button)this.findViewById(R.id.chooseDate);
		title.setVisibility(View.INVISIBLE);
		chooseDate.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Calendar c=Calendar.getInstance();//获取当前日期
				
				//日期选择器对话框
				//日期改变监听器
				new DatePickerDialog(MainActivity.this,new DatePickerDialog.OnDateSetListener() {
					
					@Override
					public void onDateSet(DatePicker view, int year, int month,int day) {
						// TODO Auto-generated method stub
						//设置文本编辑框的内容为设置的日期，month需要从0开始，所以月份为month+1
						date.setText(year+"-"+(month+1)+"-"+day);}
					
				},c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH)).show();
				}
			});
		add.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ContentValues values=new ContentValues();//创建一个ContentValues对象
				values.put(Mementos.Memento.SUBJECT, subject.getText().toString());
				values.put(Mementos.Memento.BODY, body.getText().toString());
				values.put(Mementos.Memento.DATE, date.getText().toString());//values中存值
				contentResolver.insert(Mementos.Memento.MEMENTOS_CONTENMT_URI, values);
				Toast.makeText(MainActivity.this, "添加生词成功！", 1000).show();
				
			}			
		});
		show.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Cursor cursor = contentResolver.query(
						Mementos.Memento.MEMENTOS_CONTENMT_URI, null, null,
						null, null);
				System.out.println(cursor);
				//查询所有记录
				SimpleCursorAdapter resultAdapter = new SimpleCursorAdapter(
						MainActivity.this, R.layout.result, cursor,
						new String[] { Mementos.Memento._ID,
								Mementos.Memento.SUBJECT,
								Mementos.Memento.BODY, Mementos.Memento.DATE },
						new int[] { R.id.memento_num, R.id.memento_subject,
								R.id.memento_body, R.id.memento_date });
				result.setAdapter(resultAdapter);//设置数据显示方式
			}
		});
	}
}
