package assessment;

import java.util.Arrays;

/**
 * problem statement : https://leetcode.com/problems/reorder-data-in-log-files/
 * TC : O(N*log(N))
 * SC : O(1), as in-built sorting algorithm is double pivoted quick sort
 */
public class Reorder_Data_in_Log_Files {

    public String[] reorderLogFiles(String[] logs) {

        Arrays.sort(logs, (log1, log2) -> {

            String[] split1 = log1.split("\\s", 2);
            String[] split2 = log2.split("\\s", 2);
            boolean isDigit1 = isDigit(split1[1].charAt(0));
            boolean isDigit2 = isDigit(split2[1].charAt(0));
            if (!isDigit1 && !isDigit2) {
                int cmp = split1[1].compareTo(split2[1]);
                if (cmp != 0) {
                    return cmp;
                }
                return split1[0].compareTo(split2[0]);
            }
            return isDigit1 ? (isDigit2 ? 0 : 1) : -1;
        });
        return logs;
    }

    private boolean isDigit(char ch) {
        return (ch >= 48 && ch <= 57);
    }
}
