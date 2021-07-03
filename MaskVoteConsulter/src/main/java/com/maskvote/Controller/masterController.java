package com.maskvote.Controller;


import com.alibaba.fastjson.JSON;
import com.maskvote.Bean.electInformation;
import com.maskvote.Bean.voteInformation;
import com.maskvote.Connection.Connection;
import com.maskvote.Connection.Invoke;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


@Controller
public class masterController {


    /**
     * 主页面
     * @return
     */
    @GetMapping(value = {"/", "login"})
    public String loginPage(){

        return "index";
    }


    /**
     * 选举事务页面
     * @return
     */
    @RequestMapping("electAffair.html")
    public String submitElectAffair(){


        return "electAffair";
    }

    /**
     * 表决事物页面
     * @return
     */
    @RequestMapping("voteAffair.html")
    public String submitVoteAffair(){


        return "voteAffair";
    }

    @GetMapping("date")
    public String dateTest(){
        return "date";
    }


    /**
     * 接受选举事务结果
     * @param Id
     * @param electAffairStr
     * @param
     * @param electNum
     * @param candidateNum
     * @param model
     * @return
     */
    @RequestMapping("electAffairSubmit")
    public String electAffairSubmit(@RequestParam("Id") String Id,
                                    @RequestParam("electAffairStr") String electAffairStr,
                                    @RequestParam("electStartTime") String electStartTime,
                                    @RequestParam("electEndTime") String electEndTime,
                                    @RequestParam("electNum") int electNum,
                                    @RequestParam("candidateNum") int candidateNum,
                                    Model model){

        electInformation electInfor = new electInformation();

        electInfor.setId(Id);
        electInfor.setElectAffairStr(electAffairStr);
        electInfor.setElectStartTime(electStartTime);
        electInfor.setElectEndTime(electEndTime);
        electInfor.setElectNum(electNum);
        electInfor.setCandidateNum(candidateNum);


        String electSearchNum = HashFunction.getSHA256StrJava(electInfor.toString());

        electInfor.setElectSearchNum(electSearchNum);
        model.addAttribute("Id", Id);
        model.addAttribute("electAffairStr", electAffairStr);
        model.addAttribute("electStartTime", electStartTime);
        model.addAttribute("electEndTime", electEndTime);
        model.addAttribute("electNum", electNum);
        model.addAttribute("candidateNum", candidateNum);
        model.addAttribute("electSearchNum", electSearchNum);


        HashMap<String, electInformation> hashMap = new HashMap<>();
        hashMap.put(electSearchNum, electInfor);

//        System.out.println(hashMap);
        String strTemp = JSON.toJSONString(hashMap);
//        System.out.println(strTemp);
        strTemp = strTemp.replaceAll("\"", "\\\\\"");
        System.out.println(strTemp);

        Invoke.invoke(Connection.getNetwork(), electSearchNum, strTemp);

        System.out.println(electSearchNum);
        System.out.println(strTemp);

        return "electAffairSubmit";

    }


    @RequestMapping("voteAffairSubmit")
    public String voteAffairSubmit(@RequestParam("Id") String Id,
                                    @RequestParam("voteAffairStr") String voteAffairStr,
                                    @RequestParam("voteStartTime") String voteStartTime,
                                    @RequestParam("voteEndTime") String voteEndTime,
                                    @RequestParam("voteNum") int voteNum,
                                   Model model){

        voteInformation voteInfor = new voteInformation();
        voteInfor.setId(Id);
        voteInfor.setVoteAffairStr(voteAffairStr);
        voteInfor.setVoteStartTime(voteStartTime);
        voteInfor.setVoteEndTime(voteEndTime);
        voteInfor.setVoteNum(voteNum);

        String voteSearchNum = HashFunction.getSHA256StrJava(voteInfor.toString());
//        System.out.println(searchNmu);
        voteInfor.setVoteSearchNum(voteSearchNum);
        model.addAttribute("Id", Id);
        model.addAttribute("voteAffairStr", voteAffairStr);
        model.addAttribute("voteStartTime", voteStartTime);
        model.addAttribute("voteEndTime", voteEndTime);
        model.addAttribute("voteNum", voteNum);
        model.addAttribute("voteSearchNum", voteSearchNum);


        HashMap<String, voteInformation> hashMap = new HashMap<>();
        hashMap.put(voteSearchNum, voteInfor);

        String strTemp = JSON.toJSONString(hashMap);
        strTemp = strTemp.replaceAll("\"", "\\\\\"");

        Invoke.invoke(Connection.getNetwork(), voteSearchNum, strTemp);
        System.out.println(voteSearchNum);
        System.out.println(strTemp);

        return "voteAffairSubmit";

    }
}
