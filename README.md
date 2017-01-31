# LangReco

School projec: Detect the language of a text using N Grams. 

# How doees it work

First, a huge collection of data is needed to train our model, the data are text written in only one language.
Then we train our model on those language, for each language the program will compute the probability of a word (1gram) or a combinaision (2gram, 3gram...). 

Once the model is trained we can feed an unknow corpus, for each Ngram in the text the program will choose the one with the highest probability, the final result is the language that got picked the most 
