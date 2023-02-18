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
