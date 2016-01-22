/**
 * 
 */
package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author wjh
 *
 */
public class testJson {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		String pathStr="games";
		String xpathStr="";
		File gameFile=new File(pathStr);
		File[] files=gameFile.listFiles();
		BufferedWriter bw=new BufferedWriter(new FileWriter(new File("config\\gameConfig.txt"),true));
		bw.write("{games:[");
		for (int i = 0; i < files.length-1; i++) {
			pathStr=pathStr+"\\"+files[i].getName();
			BufferedReader br=new BufferedReader(new FileReader(new File(pathStr)));
				bw.append("{\"address\":\""+br.readLine()+"\",");
				bw.append("\"xpath\":\""+br.readLine().replaceAll( "\"", "\'")+"\",");
				bw.append("\"name\":\""+br.readLine()+"\"},");
			pathStr="games";
		}
		pathStr=pathStr+"\\"+files[files.length-1].getName();
		BufferedReader br=new BufferedReader(new FileReader(new File(pathStr)));
			bw.append("{\"address\":\""+br.readLine()+"\",");
			bw.append("\"xpath\":\""+br.readLine().replaceAll( "\"", "\'")+"\",");
			bw.append("\"name\":\""+br.readLine()+"\"}");
		bw.append("]}");
		bw.flush();
		bw.close();
	}

}
