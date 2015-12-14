package langReco.reco;
import langReco.eval.Performance;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

public class MyLanguageRecognizer2Test {

	@Test
	public void testMyLanguageReconizer2() {
		String goldSentPath = "data/gold/test.txt";
		String goldLangPath = "data/gold/test-re.txt";

		
		MyLanguageRecognizer2 myLanguageReco = new MyLanguageRecognizer2();
		// or use the following if you want to consider all the languages
		// LanguageRecognizer baseline = new BaselineLanguageRecognizer();

		String hypLangFilePath = "/tmp/hyp";
		myLanguageReco.recognizeFileLanguage(goldSentPath, hypLangFilePath);
		System.out.printf("System performance = %f\n", Performance.evaluate(goldLangPath, hypLangFilePath));
	}


	@Rule
	public TestName name = new TestName();

	
	@Before
	public void printSeparator()
	{
		System.out.println("\n=== " + name.getMethodName() + " =====================");
	}
}