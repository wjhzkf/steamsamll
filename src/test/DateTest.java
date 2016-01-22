package test;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import taobao.TaobaoController;


public class DateTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Date startDate = new Date(System.currentTimeMillis());
		Date endDate = new Date(System.currentTimeMillis()-86400000);
		System.out.println(startDate);
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		System.out.println(format.format(startDate));
		System.out.println(format.format(endDate));
		
	}

}
