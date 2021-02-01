package com.xiaodai.top150;

/**
 * Author ：dai
 * Date   ：2021/2/1 10:46 上午
 * Description：罗马数字转整数
 */
public class P_0013_RomanToInt {

    public static int romanToInt(String s) {
        int nums[] = new int[s.length()];
        // 初始化数据
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case 'M':
                    nums[i] = 1000;
                    break;
                case 'D':
                    nums[i] = 500;
                    break;
                case 'C':
                    nums[i] = 100;
                    break;
                case 'L':
                    nums[i] = 50;
                    break;
                case 'X':
                    nums[i] = 10;
                    break;
                case 'V':
                    nums[i] = 5;
                    break;
                case 'I':
                    nums[i] = 1;
                    break;
            }
        }
        int sum = 0;
        // 大数在小数右边做减法，大数在小数左边做加法
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] < nums[i + 1]) {
                sum -= nums[i];
            } else {
                sum += nums[i];
            }
        }
        // 最后一个位置，右侧没数字，可以认为右侧比自己小，加上
        return sum + nums[nums.length - 1];
    }

}
