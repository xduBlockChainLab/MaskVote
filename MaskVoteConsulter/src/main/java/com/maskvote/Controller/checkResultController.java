package com.maskvote.Controller;


import com.alibaba.fastjson.JSON;
import com.maskvote.Connection.Connection;
import com.maskvote.Connection.QueryResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;

@Controller
public class checkResultController {


    @RequestMapping("electResult")
    public String electResultCheck(@RequestParam("electSearchNum") String num, Model model){


        /**
         * 从链上获取选举信息
         */
        String string = QueryResult.queryResult(num, Connection.getContract(Connection.getNetwork()));

        String voteSearchNum = num;

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(string);
        string = string.replaceAll("\\\\\"", "\"");
        String str1 = JSON.parseObject(string).getString(num);
        String electAffairStr = JSON.parseObject(str1).getString("electAffairStr");
        //投票开始时间
        String electStartTime = JSON.parseObject(str1).getString("electStartTime");
        //结束时间
        String electEndTime = JSON.parseObject(str1).getString("electEndTime");
        //选举人数
        String electNum = JSON.parseObject(str1).getString("electNum");

        /**
         * 吧- 替换成 /
         */
        String newEndTime1 =  electEndTime.replaceAll("-", "/");
//        System.out.println(newEndTime1);
        HashMap<String, String> voteMap = new HashMap<>();

        voteMap.put("electAffairStr", electAffairStr);
        voteMap.put("electStartTime", electStartTime);
        voteMap.put("electEndTime", electEndTime);
        voteMap.put("electNum", electNum);

        String result = QueryResult.queryResult(num+"counter1VoteResult", Connection.getContract(Connection.getNetwork()));
//        String result = "{\"vote1Result\":1700665966400988042079266337689,\"vote2Result\":1,\"vote3Result\":1}";
        String Nstr1 = JSON.parseObject(result).getString("vote1Result");
        String Nstr2 = JSON.parseObject(result).getString("vote2Result");
        String Nstr3 = JSON.parseObject(result).getString("vote3Result");
        HashMap<String, Integer> map = querySet();
        int a = map.get(Nstr1);
        int b = map.get(Nstr2);
        int c = map.get(Nstr3);


        model.addAttribute("electAffairStr", electAffairStr);
        model.addAttribute("electStartTime", electEndTime);
        model.addAttribute("electEndTime", electEndTime);
        model.addAttribute("electNum", electNum);

        model.addAttribute("vote1Result",a);
        model.addAttribute("vote2Result",b);
        model.addAttribute("vote3Result",c);

        return "electResultPage";

    }



    /**
     * 结果集查询
     */
    public static HashMap<String, Integer> querySet(){
        HashMap<String, Integer> map = new HashMap<>();
        map.put("1",0);
        map.put("1413725201043655687806426909828",1);
        map.put("1750742576262600654506941253804",2);
        map.put("310339300967179069297448514413",3);
        map.put("1700665966400988042079266337689",4);
        map.put("1421245184312183264223578839220",5);
        map.put("1732533456276251810196998069242",6);
        map.put("1060610491901052232220807333510",7);
        map.put("1487352758104605009351999760849",8);
        map.put("758763866668915015114369703483",9);
        map.put("2359819723432380752679491018907",10);

        return map;
    }



    @GetMapping("electResultSearch")
    public String electResultSearch(){

        return "electResultSearch";
    }

    @GetMapping("voteResultSearch")
    public String voteResultSearch(){

        return "voteResultSearch";
    }

}
