package org.naarad.muni.backtracking;


import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.com/problems/restore-ip-addresses/">Restore IP address</a>
 * <p>
 * 1 <= s.length <= 20
 * s consists of digits only.
 */
public class RestoreIPAddress {
    public static void main(String[] args) {
        RestoreIPAddress ip = new RestoreIPAddress();
        for (String ipAddress : ip.restoreIpAddresses("0")) {
            System.out.println(ipAddress);
        }
    }

    public List<String> restoreIpAddresses(String s) {
        List<String> temp = getAllIP(4, s);
        if (temp == null || temp.isEmpty()) {
            return new ArrayList<>();
        }
        return temp;
    }

    private List<String> getAllIP(int digitLeft, String stringLeft) {
        if (digitLeft > stringLeft.length() || digitLeft == 0 || digitLeft * 3 < stringLeft.length()) {
            return null;
        }

        final List<String> returnList = new ArrayList<>();
        if (digitLeft == 1) {
            if ((stringLeft.length() > 1 && stringLeft.charAt((0)) == '0') || Integer.parseInt(stringLeft) > 255) {
                return null;
            } else {
                returnList.add(stringLeft);
            }
        } else if (stringLeft.charAt(0) == '0') {
            final List<String> temp = getAllIP(digitLeft - 1, stringLeft.substring(1));
            if (temp != null && !temp.isEmpty()) {
                for (String ip : temp) {
                    returnList.add("0." + ip);
                }
            }
        } else {
            for (int i = 0; i < 3 && i < stringLeft.length(); i++) {
                String substring = stringLeft.substring(0, i + 1);
                if (Integer.parseInt(substring) <= 255) {
                    final List<String> temp = getAllIP(digitLeft - 1, stringLeft.substring(i + 1));
                    if (temp != null && !temp.isEmpty()) {
                        for (String ip : temp) {
                            returnList.add(substring + "." + ip);
                        }
                    }
                }
            }
        }

        return returnList;
    }

}
