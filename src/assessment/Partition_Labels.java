package assessment;

import java.util.ArrayList;
import java.util.List;

/**
 *  problem statement : https://leetcode.com/problems/partition-labels/
 *  TC : O(N) where N is length of the input String
 *  SC : O(1)
 */
public class Partition_Labels {

    public List<Integer> partitionLabels(String S) {

        int[] last = new int[26];
        int length = S.length();
        for(int i = 0; i < length; i++) {
            last[S.charAt(i) - 'a'] = i;
        }

        int j = 0, pivot = 0;
        List<Integer> response = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            j = Math.max(j, last[S.charAt(i) - 'a']);
            if (i == j) {
                response.add(i - pivot + 1);
                pivot = i + 1;
            }
        }
        return response;
    }
}
