package langReco.reco;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import langModel.LanguageModel;
import langModel.MyLaplaceLanguageModel;
import langModel.MyNgramCounts;
import langModel.NgramCounts;

@SuppressWarnings("unused")
public class MyLanguageRecognizer1 extends LanguageRecognizer {

	HashMap<String,LanguageModel> languageModels;

	public MyLanguageRecognizer1(){
		super();
		languageModels = new HashMap<String,LanguageModel>();
	}
	@Override
	public String recognizeSentenceLanguage(String sentence) {
		if (this.languageModels.isEmpty()){
			this.loadNgramCountPath4Lang("lm/fichConfig_bigram-100.txt");
			for (String language: super.getLanguages()){
				LanguageModel model = new MyLaplaceLanguageModel();
				NgramCounts ngramCounts = new MyNgramCounts();
				for (String langueModel: getNgramCountNames(language)){
					ngramCounts.readNgramCountsFile(getLangNgramCountMap().get(language).get(langueModel));
					model.setNgramCounts(ngramCounts);
					this.languageModels.put(language,model);
				}

			}
		}
		double approx = 0.0;
		String bestLang = "";
		for (String l: this.languageModels.keySet()){
			double temp = this.languageModels.get(l).getSentenceProb(sentence);
			if (temp > approx){
				approx = temp;
				bestLang = l;
			}
		}
		return bestLang;
	}
	
	public static void main(String[] argvs){
		MyLanguageRecognizer1 reco = new MyLanguageRecognizer1();
		System.out.println(reco.recognizeSentenceLanguage(""));
	}
}
