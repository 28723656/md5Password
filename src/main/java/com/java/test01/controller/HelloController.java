package com.java.test01.controller;

import com.java.test01.utils.ArrayUtil;
import com.java.test01.utils.Md5Util;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.Random;

@Controller
public class HelloController {

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        return "Hello World";
    }

    @ResponseBody
    @RequestMapping("/md5/{number}")
    public String testMd5(@PathVariable String number){
        return Md5Util.getMD5(number)+"<hr/>"+Md5Util.getMD5WithSalt(number);
    }


    @ResponseBody
    @RequestMapping("/md5TenTimes/{number}")
    public String md5TenTimes(@PathVariable String number) {
        String toBeMd5Number = number;
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int randomNum = random.nextInt(100);
            if (randomNum > 50) {
                toBeMd5Number = Md5Util.getMD5WithSalt(toBeMd5Number);
            } else {
                toBeMd5Number = Md5Util.getMD5(toBeMd5Number);
            }
        }
        return toBeMd5Number;
    }
    @ResponseBody
    @RequestMapping("/openMd5/{finalPassword}")
    public String openMd5(@PathVariable String finalPassword){

        long startTime = System.currentTimeMillis();

        // 创造1024种可能
        Map<Integer, int[]> mapArr = ArrayUtil.createEveryArray();
        // 最后获得的密文
        String newPassword="";
        // 正确的数字
        String correctNum = "没有答案！";

        //中断警示
        boolean breakDown = false;


        for(int i=0;i<9999;i++){


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

                        if(newPassword.equals(finalPassword)){
                            breakDown = true;
                            break;
                        }
                    }


                }

                // 如果收到中断提示
                if(breakDown){
                    break;
                }




        }
        long endTime = System.currentTimeMillis();
        String costTime ="总共用时："+(endTime-startTime)/1000+"秒";
        return correctNum+"<hr/>"+costTime;
    }





}
