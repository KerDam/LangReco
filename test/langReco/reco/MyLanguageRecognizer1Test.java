package langReco.reco;
import langReco.eval.Performance;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

public class MyLanguageRecognizer1Test {

	@Test
	public void testMyLanguageReconizer1() {
		String goldSentPath = "data/gold/test.txt";
		String goldLangPath = "data/gold/test-re.txt";

		
		MyLanguageRecognizer1 myLanguageReco = new MyLanguageRecognizer1();
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


