package com.leetcode;

import java.io.*;

public class AddBinary {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(addBinary(br.readLine(), br.readLine()));
        br.close();
    }

    public static String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int aL = a.length() - 1;
        int bL = b.length() - 1;

        int sum = 0;

        while(aL >= 0 || bL >= 0) {
            if(aL >= 0) sum += a.charAt(aL--) - '0';
            if(bL >= 0) sum += b.charAt(bL--) - '0';

            sb.append(sum % 2);
            sum /= 2;
        }

        if(sum == 1) sb.append(sum);

        return sb.reverse().toString();
    }

    /*
    public String addBinary(String a, String b) {
        int aN = Integer.parseInt(a, 2); // 2진수 바이너리 Integer로 변환
        int bN = Integer.parseInt(b, 2);

        return Integer.toBinaryString(aN + bN); // 2진수 스트링으로 변환
    }
    */
}
