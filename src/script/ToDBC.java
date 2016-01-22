/*
 * 全角转半角的函数测试
 */
package script;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ToDBC {
	
	public static String ToDBC(String input) {
	      char c[] = input.toCharArray();
	           for (int i = 0; i < c.length; i++) {
	             if (c[i] == '\u3000') {
	               c[i] = ' ';
	             } else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
	               c[i] = (char) (c[i] - 65248);
	            }
	             }
       String returnString = new String(c);
       return returnString.replaceAll(" ", "");
       }
	
	/*
	 * 提取邮箱的函数
	 */
	public static String ExEmail(String str){
		String email = "";
		int atCount = 0;				//记录@符号的变量，大于一则说明邮箱多余一个
		int atLoc = 0;						//记录@符号的位置
		int beg = str.length()-1;						//记录邮箱字符串的开始位置
		int end = 0;						//记录邮箱字符串的结束位置
		for(int i=0;i<str.length();i++){
			String atChar = str.substring(i, i+1);
			if("@".equals(atChar)){
				atCount++;
				atLoc = i;
			}
		}
		if(atCount!=1){
			return "error";
		}
		
		//判断@符号之前的字符串是否由字母、数字和下滑线组成，注意首字符的判断
		String pat = "\\w";
		Pattern p = Pattern.compile(pat);
		Matcher m;
		for(int i=atLoc-1;i>=0;i--){
			String atPre = str.substring(i, i+1);
			m = p.matcher(atPre);
			if(!m.matches()&&!".".equals(atPre)&&!"-".equals(atPre)){
				beg = i+1;
				break;
			}else if(i==0&&m.matches()){
				beg = 0;
			}
		}
		
		//判断@符号之后的字符串是否是由数字、字母和"."组成
		pat = "[a-zA-Z0-9]";
		p  = Pattern.compile(pat);
		for(int i=atLoc+2;i<=str.length();i++){
			String atSuf = str.substring(i-1,i);
			m = p.matcher(atSuf);
			if(!m.matches()&&!".".equals(atSuf)){
				end = i-1;
				break;
			}else if(i==str.length()){
				end = i;
			}
		}
		
		
//		for(int i=atLoc+2;i<=str.length();i++){
//			String atSuf = str.substring(atLoc+1, i);
//			if(atSuf.contains(".com")||atSuf.contains(".cn")||atSuf.contains(".pro")){
//				end = i;
//				break;
//			}
//		}
		
		if(end==0||beg==str.length()-1){
			return "error";
		}
		
		//判断@之后如果没有"."的话则错误
		if(!str.substring(atLoc, end).contains(".")){
			return "error";
		}
		return str.substring(beg,end);
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader(new File("C:\\Users\\Black\\Desktop\\taobao\\另外一个自动购买流程\\买家留言邮箱.txt")));
		String temp;
		while((temp=br.readLine())!=null){
			String new_temp = ToDBC(temp);
			System.out.println(new_temp);
			System.out.println(ExEmail(new_temp)+"\n");
			
		}
//		String email = ExEmail("邮箱:lostago@qq.com,谢谢!");
//		System.out.println(email);
	}

}
