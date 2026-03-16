import java.util.*;

public class PlagiarismDetector{

    public static Set<String> getNGrams(String text,int n){

        String[] words=text.split(" ");
        Set<String> grams=new HashSet<>();

        for(int i=0;i<=words.length-n;i++){

            String gram="";

            for(int j=0;j<n;j++){
                gram+=words[i+j]+" ";
            }

            grams.add(gram.trim());
        }

        return grams;
    }

    public static void main(String[] args){

        String doc1="this is a simple plagiarism example";
        String doc2="this is a simple example";

        Set<String> g1=getNGrams(doc1,3);
        Set<String> g2=getNGrams(doc2,3);

        g1.retainAll(g2);

        System.out.println("Matching ngrams "+g1.size());
    }
}