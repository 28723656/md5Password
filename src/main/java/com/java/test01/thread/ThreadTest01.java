package com.java.test01.thread;

import com.java.test01.utils.ArrayUtil;
import com.java.test01.utils.Md5Util;

import java.util.Map;

public class ThreadTest01 implements Runnable{

    String finalPassword = "3c3319e05bddd54454f6c6abef94b24f";
    private int startNum = 0;
    private int  endNum =0;

    public ThreadTest01(int startNum, int endNum) {
        this.startNum = startNum;
        this.endNum = endNum;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();

        // 创造1024种可能
        Map<Integer, int[]> mapArr = ArrayUtil.createEveryArray();
        // 最后获得的密文
        String newPassword="";
        // 正确的数字
        String correctNum = "没有答案！";

        //中断警示
        boolean breakDown = false;

        for(int i=startNum;i<endNum;i++){
            // 1.数字处理
            if(i<10){
                correctNum = "000"+i;
            }else if(i<100){
                correctNum= "00"+i;
            }else if(i<1000){
                correctNum= "0"+i;
            }else{
                correctNum =""+i;
            }

            System.out.println("当前数字："+correctNum);

            // 首先对密码进行一次普通的md5加密
            newPassword = Md5Util.getMD5(correctNum);

            // 2.尝试2^10可能性
            for(int m=0;m<1024;m++){
                int[] tenPossible = mapArr.get(m);

                // 3.每个进行加密10次
                for(int timesNum =0;timesNum <10;timesNum ++){
                    if(tenPossible[timesNum]==0){
                        newPassword =  Md5Util.getMD5(newPassword);
                    }else {
                        newPassword =  Md5Util.getMD5WithSalt(newPassword);
                    }
                }
                if(newPassword.equals(finalPassword)){
                    breakDown = true;
                    break;
                }
            }
            // 如果收到中断提示
            if(breakDown){
                break;
            }
        }
        long endTime = System.currentTimeMillis();
        String costTime ="总共用时："+(endTime-startTime)/1000+"秒";
        if(breakDown){
            System.out.println("-----------------------------"+costTime+"\n正确密码为："+correctNum+"-----------------------------");
        }
    }
}
