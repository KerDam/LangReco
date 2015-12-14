package langReco.reco;
import java.util.HashMap;
import langModel.LanguageModel;
import langModel.MyLaplaceLanguageModel;
import langModel.MyNgramCounts;
import langModel.NgramCounts;


public class MyLanguageRecognizer2 extends LanguageRecognizer {

	HashMap<String,LanguageModel> languageModels;

	public MyLanguageRecognizer2(){
		super();
		languageModels = new HashMap<String,LanguageModel>();
	}
	@Override
	public String recognizeSentenceLanguage(String sentence) {
		
		// this part will instanciate the models once for all to avoid operations each time the method is called
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
		// give the best language 
		double approx = 0.0;
		String bestLang = "";
		for (String l: this.languageModels.keySet()){
			double temp = this.languageModels.get(l).getSentenceProb(sentence);
			if (temp > approx){
				approx = temp;
				bestLang = l;
			}
		}
		// gestion de la langue inconnue 
		if (approx > 1.0 * Math.pow(10,-1789)){
			return bestLang;
		}
		else
			return "unk";
	}

}
