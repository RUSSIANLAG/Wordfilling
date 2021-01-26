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

    //Memoization
    private static Map<String, String> memo = new HashMap<>();
    private static Map<String, String> memoaf = new HashMap<>();
   // private static final String MOST_FREQUENT = "aei";
    //private static final String FREQUENCY_ORDER = "tonsrhldcumfpgwybvkxjqz";
    //private static final String MOST_COMMON_LETTER_PAIRS = "th,he,an,in,er,on,re,nd,ha,at,es,en,ed,of,nt,ea,ti,to,io,le,is,ou,ar,as,de,rt,ve";


    // Greedy algorithm to use each letter to create a word if possible. Only looks
    // into the past when choosing the current character, but not into the future.
    public static String fillPattern(String pattern) {
    	 Random rng = new Random();
    	//result to return
        String result = "";
        int i=0;
        //String of the letters after the initial
        while (result.length()!=pattern.length()){
       // for(int i = 0; i < pattern.length(); i++) {
            // If the pattern contains a character, we have no choice this round.
          //  if(pattern.charAt(i) != '*') {
            //	result += pattern.charAt(i);
           // }
           /*else if (i==0)
            {
            	char c ='\0';
            	String before = result.substring(result.length());
            		String after = pattern.substring(i,i+5);
            		 c = guessForward(listofword,before,after);
            	// c = guessLetter(listofword,before);
            	result+=c;

            }
            else if (i==1)
            {
            	char c ='\0';
            	String before = result.substring(result.length()-1);

            		String after = pattern.substring(i,i+5);
            		 c = guessForward(listofword,before,after);

            	// c = guessLetter(listofword,before);
            	result+=c;

            }
            else if (i==2)
           // else
            {
            	/*char c ='\0';
            	String before = result.substring(result.length()-2);
            	String after = pattern.substring(i,i+5);
            	c = guessForward(listofword,before,after);
            	result +=c;
            }
            	char c ='\0';
            	String before = result.substring(result.length()-2);

            		String after = pattern.substring(i,i+5);
            		 c = guessForward(listofword,before,after);

            	// c = guessLetter(listofword,before);

            	result+=c;

            }
            else if (i==3)
            {
            	char c ='\0';
            	String before = result.substring(result.length()-3);

            		String after = pattern.substring(i,i+5);
            		 c = guessForward(listofword,before,after);

            //	 c = guessLetter(listofword,before);

            	result+=c;

            }
           */ //else
          //  {
            	//char c ='\0';

        		String re = "";
            	//String before = result.substring(result.length()-4);
        		if (i<45)
        		{
            	String before = pattern.substring(i,i+5);
            	//(end >50 || end>45 && end<50)
            	//if (i<45)
            	//{
            		String after = pattern.substring(i+3,i+8);
            		i=i+3;
            		 re = guessForward(listofword,before,after);

            	//}
            	//else {
            	// c = guessLetter(listofword,before);
            	//}
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

            /*	char c ='\0';
            	String before = result.substring(result.length()-4);
            	//String after = pattern.substring(result.length(),result.length()+5);
            	//c = guessForward(listofword,before,after);
            	//result+=c;
            	//char c ='\0';
            	//String before = result.substring(result.length()-4);
            	if (pattern.length()+5 < pattern.length()-1)
            	{
            		//String after = pattern.substring(result.length(),result.length()+5);
            		String after = pattern.substring(i,i+5);
            		c = guessForward(listofword,before,after);
            	}
            	else {
            	 c = guessLetter(listofword,before);*/

         //  }
        }
        return result;
    }
    public static char guessLetter(ArrayList<String> listofword, String before)
    {
    	Random rng = new Random();
    	char guess='\0';
    	String word="";
    	String memoCheck="";
 		//boolean found =false;
    		for (int i=0;i<listofword.size();i++)
    		{
    			String check="";
    			word=listofword.get(i);
    			char[] aArray = word.toCharArray();

    			for(int o=0;o<before.length();o++)
    			{
    			check+=aArray[o];
    			//found = listofword.get(i).indexOf(patern.charAt(j)) >= 0;
    			}
    			//if (memo.get(before) != null)
    			if (memo.containsKey(before))
        		{
        			String wordMemo=memo.get(before);
        			guess=wordMemo.charAt(before.length());
        		//	found=true;
        			break;
        		}
    			else if (check.equals(before))
    			{
    				memoCheck=before+word.charAt(before.length());
    				guess=word.charAt(before.length());
    				memo.put(memoCheck,word);
    			//	found = true;
    				break;
    			}
    			else
    			{
    				//guess='a';
    				guess = (char)(rng.nextInt(26) + 'a');
    			}
    		}
    	return guess;

    }

    public static String guessForward(ArrayList<String> listofword, String before, String after)
    {
    	Random rng = new Random();
    	//char guess='\0';
    	String guess="";
    	String word="";
    	String memoCheck="";
    		for (int i=0;i<listofword.size();i++)
    		{
    			word=listofword.get(i);

    	    	String check="";
    	    	String checkAf="";
    	    	//check before
    			String checkA="";
    			String checkB="";
    			//after
    			String checkC="";

    			char[] aArray = word.toCharArray();
    			//after word
    			char[] bArray = after.toCharArray();
    			//before word
    			char[] cArray = before.toCharArray();
    	/*	for(int o=0;o<before.length();o++)
    			{
    			check+=aArray[o];
    			}*/

    		for(int h=0;h<word.length();h++)
    			{
    				if (cArray[h]=='*')
    				{
    					checkA+=aArray[h];
    					//checkB+=aArray[h];
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
					//checkB+=aArray[k];
				}
				else
				{
					checkC+=bArray[k];
				}
			}

    		//memo and checking

    		if (checkA.equals(word))
			{
				//memoCheck=word.substring(3,5);
				//guess=word.substring(0, 3);
				memo.put(word,word);
				//break;
				memoCheck=checkA.substring(0,3)+word.substring(3,5);
				//guess=checkB.charAt(0);
				if (memoaf.containsKey(memoCheck))
				{
					String wordMemo=memo.get(memoCheck);
					guess=wordMemo.substring(0,3);
					break;
				}
			}

    		if (checkC.equals(word))
			{
    			memoaf.put(word, word);

    			memoCheck=checkC.substring(0,3)+word.substring(0,2);
				//guess=checkB.charAt(0);
				/*if (memo.containsKey(memoCheck))
				{
					String wordMemo=memo.get(memoCheck);
					guess=wordMemo.substring(0,3);
					break;
				}
				//memo.put(memoCheck,word);
				//break;*/
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

    			/*if (memo.containsKey(before))
        		{
        			String wordMemo=memo.get(before);
        			memoCheck=wordMemo.substring(2,4);
        			guess=wordMemo.charAt(before.length()+1);
        			memo.put(memoCheck, wordMemo);
        			break;
        		}

    			else if (check.equals(before) && checkA.equals(word))
    			{
    				memoCheck=word.substring(1,3);
    				guess=word.charAt(before.length());
    				memo.put(memoCheck,word);
    				break;
    			}
    			else if (checkA.equals(word))
    			{
    				memoCheck=word.substring(1,3);
    				guess=checkB.charAt(0);
    				//memo.put(memoCheck,word);
    				//break;
    			}
    			else if (check.equals(before))
    				{
    				memoCheck=word.substring(1,3);
    				guess=word.charAt(before.length());
    				//memo.put(memoCheck,word);
    				}
    			else
    			{
    				guess = (char)(rng.nextInt(26) + 'a');
    				//guess='a';
    			}*/
    		}
    		return guess;
    }

}
