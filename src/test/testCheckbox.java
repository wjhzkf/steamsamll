/**
 * 
 */
package test;

/**
 * @author wjh
 *
 */
public class testCheckbox {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MyComboBox jComboBox1=new MyComboBox();
		for (int i = 0; i < 10; i++) {
			CheckValue cValue = new CheckValue();
			cValue.value = "²âÊÔ_" + i;
			if (i % 3 == 0) {
				cValue.bolValue = true;
			}
			jComboBox1.addItem(cValue);
		}
		jComboBox1.setRenderer(new CheckListCellRenderer());
	}
}
