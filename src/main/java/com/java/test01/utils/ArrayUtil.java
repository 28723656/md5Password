package com.java.test01.utils;

import java.util.HashMap;
import java.util.Map;

public class ArrayUtil {


    // 创造所有可能性
    public static Map<Integer, int[]> createEveryArray(){

        Map<Integer, int[]> map = new HashMap<>();
        int i=0;

        for(int t1=0;t1<2;t1++){
            for(int t2=0;t2<2;t2++){
                for(int t3=0;t3<2;t3++){
                    for(int t4=0;t4<2;t4++){
                        for(int t5=0;t5<2;t5++){
                            for(int t6=0;t6<2;t6++){
                                for(int t7=0;t7<2;t7++){
                                    for(int t8=0;t8<2;t8++){
                                        for(int t9=0;t9<2;t9++){
                                            for(int t10=0;t10<2;t10++){
                                                    map.put(i++,new int[]{t1,t2,t3,t4,t5,t6,t7,t8,t9,t10});
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return map;
    }
}
