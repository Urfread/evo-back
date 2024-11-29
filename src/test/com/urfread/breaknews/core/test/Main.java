package com.urfread.breaknews.core.test;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static String solution(int[] nums, int k) {
        Map<Integer,Integer>map=new HashMap<>();

        for(int num:nums){
           map.put(num,map.getOrDefault(num,0)+1);
        }
        Integer[] topKElements = new Integer[k];
        Integer[] topKCounts = new Integer[k];
        for(Map.Entry<Integer,Integer> entry:map.entrySet()){
            insertOrIgnore(topKElements,topKCounts,entry.getKey(),entry.getValue(),k);
        }
        Arrays.sort(topKElements);
        List<String>numberStrings=Arrays.stream(topKElements).map(String::valueOf).collect(Collectors.toList());
        System.out.println(String.join(",",numberStrings));
        return String.join(",",numberStrings);
    }
    private static void insertOrIgnore(Integer[] topKElements,Integer[] topKCounts,int currElement,int currCount,int k){
        for(int i=k-1;i>=0;i--){
            if(topKElements[i]==null){
                topKElements[i]=currElement;
                topKCounts[i]=currCount;
                return;
            }
            // 如果频率高于当前元素的频率
            if(topKCounts[i]<currCount){
                // 往左挪
                for(int j=0;j<i;j++){
                    topKElements[j]=topKElements[j+1];
                    topKCounts[j]=topKCounts[j+1];
                }
                topKElements[i]=currElement;
                topKCounts[i]=currCount;
                break;
            }
        }
    }

    public static void main(String[] args) {
        //  You can add more test cases here
        int[] nums1 = {0,0,0,0,0,0,0,1, 1, 1, 2, 2, 3,3,3,3};
        int[] nums2 = {1};

        System.out.println(solution(nums1, 2).equals("1,3"));
        System.out.println(solution(nums2, 1).equals("1"));
    }
}