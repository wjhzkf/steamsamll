package test;

public class StringTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "2955,74 p���.";
		System.out.println(str);
		System.out.println(str.replaceAll(" ", ""));
		System.out.println(str.replaceAll(" ", "").replaceAll(",", ""));
		System.out.println(str.replaceAll(" ", "").replaceAll(",", "").replaceAll("p���.", ""));
	}

}
