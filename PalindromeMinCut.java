package com.examle.mincut;

import java.util.LinkedList;
import java.util.Queue;

public class PalindromeMinCut {

    private int [] solutionVector;


    private Queue<Entry> queue = new LinkedList<Entry>();
    private String string;
    int level=0;
    int endIndex;

    public int[] findMinCut(String str){
        string= str;
        int len=string.length();
        endIndex=len-1;
        solutionVector= new int[len];
        for (int i = 0; i < string.length(); i++) {
            solutionVector[i]=0;
        }
        findNextCut(0);
        return solutionVector;
    }
    private void findNextCut(int begIndex){

        String str=string.substring(begIndex);
        String leftString;
        String rightString;
        Entry element;
        if(isPalindrome(str))
            return;
        else {
            while (true) {
                for (int i = begIndex + 1; i < endIndex + 1; i++) {
                    leftString = str.substring(begIndex, i);
                    rightString = str.substring(i);
                    if (isPalindrome(leftString)) {
                        solutionVector[level] = begIndex;
                        if (isPalindrome(rightString)) {
                            solutionVector[++level] = i;
                            return;

                        } else {
                            Entry entry = new Entry(level + 1, i);
                            queue.add(entry);
                        }
                    }
                }
                element = queue.remove();
                level = element.level();
                begIndex = element.startIndex();
            }
        }
    }
    
        public int findMinCut_Vers1(String str){
        int len=str.length();
        endIndex=len-1;
        int [] possibleCutPositions= new int[len];
        int [] minCuts= new int[len];
        int i,j,k, toReturn =str.length()+1;

        if( len==0 ) return -1;
        if( len==1 ) return 0;
        char c=str.charAt(0);

        //find possible cut positions
        for ( i = 0,j=0; i <len ; ) {
            int index=str.indexOf(c,i);
            if(index==-1) break;
            else{
                possibleCutPositions[j++]=index;
                i=index+1;

            }

        }
        //-1 to indicate no more possible positions
        possibleCutPositions[j]=-1;
        j=0;
        // -2 indicates the cut position is not a palindrome
        while(possibleCutPositions[j]!=-1){
            String substring=str.substring(0,possibleCutPositions[j]+1);
            if(! isPalindrome(substring))
                possibleCutPositions[j]=-2;
            j++;
        }

        //find mincuts of the valid strings
        for ( k = 0; possibleCutPositions[k]!=-1  ; k++) {
            if(possibleCutPositions[k]!=-2)
                minCuts[k]=1+findMinCut_Vers1(str.substring(possibleCutPositions[k]+1));
            else minCuts[k]=str.length()+1;
        }

        for (int l = 0; l <k ; l++) {
            if(minCuts[l]< toReturn) toReturn =minCuts[l];
        }
        return toReturn;





    }
    public boolean isPalindrome(String str){
        int length=str.length();
        int mid=(length+1)/2;
        for (int i=0,j=length-1;i<mid;i++,j--){
            if(str.charAt(i)!=str.charAt(j))
                return false;
        }
        return true;
    }
    public void printMinCut(    ){
        int i;
        for ( i =0; i<string.length()-1 && solutionVector[i+1]!=0  ; i++) {
            System.out.println(string.substring(solutionVector[i],solutionVector[i+1]));
        }
        System.out.println(string.substring(solutionVector[i]));
    }
    public static void  main(String[] args){
        PalindromeMinCut solution=new PalindromeMinCut();

       solution.findMinCut("amaramavdva");
       solution.printMinCut();
    }
}
