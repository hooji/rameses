package javax.lang;

import javax.lang.Assert;

public class StringsTest {

	public void testTruncate() throws Exception {
		Assert.equals("this is a test", Strings.truncate("this is a test of the national broadcast system", 14));
		Assert.equals("this is a t...", Strings.truncate("this is a test of the national broadcast system", 14, "..."));
		Assert.equals("this is a test", Strings.truncate("this is a test", 100));
	}
	
	public void testGenerate() throws Exception {
		Assert.equals("55555", Strings.generate('5', 5));
		Assert.equals("------------------------------", Strings.generate('-', 30));
		Assert.equals("", Strings.generate('%', 0));
		Assert.equals("", Strings.generate('%', -10));
	}
	
	public void testJoin() throws Exception {
		Assert.equals("this is a test", Strings.join(new String[] {"this", "is", "a", "test"}));
		Assert.equals("this,is,a,test", Strings.join(new String[] {"this", "is", "a", "test"}, ","));
		Assert.equals("foo,this,is,a,test,bar", Strings.join(new String[] {"this", "is", "a", "test"}, "foo,", ",", ",bar"));
	}
	
	public void testString() throws Exception {
		Assert.equals("1", Strings.string(1));
		Assert.equals("1", Strings.string("1"));
		Assert.equals("1.0", Strings.string(1.0));
	}
	
	public void testStrings() throws Exception {
		String[] strings = Strings.strings(new Object[] {0,1,2,3,4,5});
		for (int i = 0; i < strings.length; i++)
			Assert.equals(new Integer(i).toString(), strings[i]);
	}
	
	public void testPad() throws Exception {
		Assert.equals("foo   ", Strings.pad("foo", 6));
		Assert.equals("foo---", Strings.pad("foo", '-', 6));
		Assert.equals("foooooo", Strings.pad("foooooo", 3));
		Assert.equals("foooooo", Strings.pad("foooooo", '-', 3));
		Assert.equals("---foo", Strings.pad("foo", '-', 6, "left"));
	}
	
	public void testDivide() throws Exception {
		Assert.equals(3, Strings.divide("this is a test", 5).length);
		Assert.equals("this ", Strings.divide("this is a test", 5)[0]);
		Assert.equals("is a ", Strings.divide("this is a test", 5)[1]);
		Assert.equals("test", Strings.divide("this is a test", 5)[2]);
		Assert.equals(1, Strings.divide("this is a test", 15).length);
		Assert.equals(44, Strings.divide("this is a test of the nation brodcast system", 1).length);
		Assert.equals(0, Strings.divide("sdfaf", 0).length);
		Assert.equals(0, Strings.divide("sdfaf", -1).length);
	}
	
	public void testWrap() throws Exception {
		Assert.equals("this \nis a \ntest", Strings.wrap("this is a test", 5));
		Assert.equals("t\nh\ni\ns", Strings.wrap("this", 1));
		Assert.equals("", Strings.wrap("this", 0));
		Assert.equals("", Strings.wrap("this", -1));
		Assert.equals("this", Strings.wrap("this", 15));
	}
	
	public void testBlock() throws Exception {
		Assert.equals("this \nis a \ntest ", Strings.block("this is a test", 5));
		Assert.equals("test ", Strings.block("test", 5));
		Assert.equals("t\ne\ns\nt", Strings.block("test", 1));
	}
	
	public static void main(String[] arguments) throws Exception {
		StringsTest test = new StringsTest();
		test.testString();
		test.testStrings();
		test.testJoin();
		test.testPad();
		test.testDivide();
		test.testWrap();
		test.testBlock();
		test.testTruncate();
		test.testGenerate();
	}
}