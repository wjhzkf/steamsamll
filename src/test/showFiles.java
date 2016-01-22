package test;
import java.io.File;


public class showFiles {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File f = new File("games");
		File[] fileLists = f.listFiles();
		
		for(int i=0;i<fileLists.length;i++){
			System.out.println(fileLists[i].getName());
		}
		
		File file = new File("games\\中文.txt");
//		BufferedWriter bw = new BufferedWriter(new FileWriter(new File("PC正版Dark Souls II Season Pass黑暗之魂2中文版季票DLC.txt")))
//		file.delete();
//		System.out.println(file.renameTo(new File("test.txt")));
	}

}
