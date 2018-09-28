package com.xzw.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @Author: Mr.Lin
 * @Description:
 * @Data: Created in 14:39 2017-10-28
 * @ModifiedBy:
 */
public class RandomUtil {
    /**
     * @Author: Mr.Lin
     * @Description:生成随机数+时间字符串
     * @param
     * @CreateData: 18:19 2017-9-9
     */
    public static String getRandomFileName(){

        SimpleDateFormat simpleDateFormat;

        simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

        Date date = new Date();

        String str = simpleDateFormat.format(date);

        Random random = new Random();

        int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数

        return  str + rannum;// 当前时间
    }
    /**
     * @Author: Mr.Lin
     * @Description:时间字符串
     * @param
     * @CreateData: 18:19 2017-9-9
     */
    public static String GetDataTimeStr(){

        SimpleDateFormat simpleDateFormat;

        simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

        Date date = new Date();

        String str = simpleDateFormat.format(date);

        return str;
    }
}
