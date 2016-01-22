/**
 * 
 */
package test;

/**
 * @author wjh
 *
 */
public class testStr {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String balanceStr="eee";
		String bs="";
		String string1=(bs=balanceStr.replaceAll(" ", "").replaceAll(",", ".").replaceAll("p§å§Ò.", ""));
		bs=(bs=balanceStr.replaceAll(" ", "").replaceAll(",", ".").replaceAll("p§å§Ò.", "")).equals("")?"0.0":bs;
	}

}
