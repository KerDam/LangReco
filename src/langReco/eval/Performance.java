package langReco.eval;


import java.util.List;

import langModel.MiscUtil;



/**
 * Class Performance: class to compute the performance of a recognition system.
 * @author N. Hernandez and S. Quiniou (2015)
 *
 */
public class Performance {

	/**
	 * Method using the sentences given in two files to compute the accuracy of a recognition system, i.e. the number
	 * of sentences whose language is correctly recognized by the system.
	 * The first file contains the language annotation of each sentence of a reference file and the second
	 * file contains the hypothesis language of each reference sentence as given by the recognition system whose
	 * performance is evaluated. 
	 * 
	 * @param goldLangPath: the path of the file containing the gold annotation of a sentences of the reference file.
	 * @param hypLangPath: the path of the file containing the hypothesis sentences given by a recognition system. 
	 * @return the accuracy of the recognition system.
	 */
	public static double evaluate (String goldLangPath, String hypLangPath) {
		List<String> gold = MiscUtil.readTextFileAsStringList(goldLangPath);
		List<String> hyp = MiscUtil.readTextFileAsStringList(hypLangPath);
		
		return evaluate(gold,hyp);	
	}
	
	
	/**
	 * Method using the sentences given in two lists to compute the accuracy of a recognition system, i.e. the number
	 * of sentences whose language is correctly recognized by the system.
	 * The first list contains the language annotation of each sentence of a reference list and the second
	 * listr contains the hypothesis language of each reference sentence as given by the recognition system whose
	 * performance is evaluated. 
	 * 
	 * @param goldLang: the list containing the gold annotation of the sentences of a reference list.
	 * @param hypLang: the list containing the hypothesis sentences given by a recognition system. 
	 * @return the accuracy of the recognition system.
	 */
	public static double evaluate (List<String> goldLang, List<String> hypLang) {
		if (goldLang.size() != hypLang.size()) {
			System.err.println("Error: gold and hyp lists must have the same number of sentences");
			return -1.0;
		}
		
		Double correct = 0.0;
		for (int i = 0 ; i < goldLang.size() ; i++ ) 
			if (goldLang.get(i).equalsIgnoreCase(hypLang.get(i))) 
				correct++;

		return (double) correct/ goldLang.size();	
	}
	
}
