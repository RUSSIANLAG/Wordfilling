import java.util.*;

public class WordFiller {

    public static String getAuthorName() { return "Ivan, Golovine"; }
    public static String getRyersonID() { return "500813431"; }

    // The possible words that we are trying to maximize in the string.
    private static Set<String> wordSet = new HashSet<String>();
    private static ArrayList<String> listofword = new ArrayList<String>();
    // Remember the list of words that we were given.
    public static void processWordList(List<String> words) {
        for(String word: words) {
        	wordSet.add(word);
        	listofword.add(word);
        	}
        }

    //Memo
    private static Map<String, String> memo = new HashMap<>();
    private static Map<String, String> memoaf = new HashMap<>();

    public static String fillPattern(String pattern) {
    	 Random rng = new Random();
    	//result to return
        String result = "";
        int i=0;
        int m=0;
        //String of the letters after the initial
        while (result.length()!=pattern.length()){
        		String re = "";

        		if (i<45)
        		{

            	String before = pattern.substring(i,i+5);
            	String after = pattern.substring(i+3,i+8);
            		i=i+3;
            		m=m+3;
            		 re = guessForward(listofword,before,after);
            		 memo.clear();

        		}
        		else if (i==45)
        		{
        			String rest = "";
        			String before = pattern.substring(i,i+5);
        			for (int j = 0;j<before.length();j++)
        			{
        				if(before.charAt(j) != '*') {
        		            rest += before.charAt(j);
        		            }
        				else
        				{
        					rest += (char)(rng.nextInt(26) + 'a');
        				}
        			}
        			re = rest;
        		}
            	result+=re;
        }
        return result;
    }

    public static String guessForward(ArrayList<String> listofword, String before, String after)
    {
    	Random rng = new Random();
    	String guess="";
    	String word="";
    	String memoCheck="";
    		for (int i=0;i<listofword.size();i++)
    		{
    			word=listofword.get(i);


    	    	//check before
    			String checkA="";

    			//after
    			String checkC="";

    			char[] aArray = word.toCharArray();
    			//after word
    			char[] bArray = after.toCharArray();
    			//before word
    			char[] cArray = before.toCharArray();

    		for(int h=0;h<word.length();h++)
    			{
    				if (cArray[h]=='*')
    				{
    					checkA+=aArray[h];
    				}
    				else
    				{
    					checkA+=cArray[h];
    				}
    			}

    		for(int k=0;k<word.length();k++)
			{
				if (bArray[k]=='*')
				{
					checkC+=aArray[k];
				}
				else
				{
					checkC+=bArray[k];
				}
			}

    		//memo and checking

    		if (checkA.equals(word))
			{
    			String ok ="";
    			ok = maps(memo,memoaf);
				//memoCheck=word.substring(3,5);
    			if (!ok.equals("nope"))
    			{
    				guess=ok;
    				break;
    			//guess = maps(memo,memoaf);
    			}
				guess=word.substring(0, 3);
				memo.put(word,word);
				//break;
			/*	memoCheck=checkA.substring(0,3)+word.substring(3,5);
				if (memo.containsKey(memoCheck))
				{
					String wordMemo=memo.get(memoCheck);
					guess=wordMemo.substring(0,3);
					break;
				}*/
			}

    		if (checkC.equals(word))
			{
    			String ok ="";
    			ok = maps(memo,memoaf);
				//memoCheck=word.substring(3,5);
    			if (!ok.equals("nope"))
    			{
    				guess=ok;
    				break;
    			//guess = maps(memo,memoaf);
    			}
    			memoaf.put(word, word);

    			memoCheck=checkC.substring(0,3)+word.substring(3,5);
				//guess=checkB.charAt(0);
				/*if (memo.containsKey(memoCheck))
				{
					String wordMemo=memo.get(memoCheck);
					guess=wordMemo.substring(0,3);
					break;
				}*/
				//memo.put(memoCheck,word);
				//break;
			}
    		else
			{
    			String rest = "";
    			String b = before.substring(0,3);
    			for (int j = 0;j<b.length();j++)
    			{
    				if(before.charAt(j) != '*') {
    					rest += before.charAt(j);
    				}
    				else
    				{
    					rest += (char)(rng.nextInt(26) + 'a');
    				}
    			}
    			guess = rest;
			}
    	}

    		return guess;
    }



    public static String maps(Map<String, String> mapA, Map<String, String> mapB) {

    	String check = "";
    	String subCheck ="";
    	String next = "";
    	String nextSub="";

    	String guess="";

    	for (String k : mapA.keySet())
    	{
    		check=mapA.get(k);
    		subCheck=check.substring(3,5);
    		for (String y : mapB.keySet())
    		{
    			next=mapB.get(y);
    			nextSub=next.substring(0,2);
    			if (nextSub.equals(subCheck))
    			{
    				guess=check.substring(0,3);
    				break;
    			}
    		}

    	}
    	if (guess.isEmpty())
    	{
    		guess = "nope";
    	}

    	return guess;
    }

}
