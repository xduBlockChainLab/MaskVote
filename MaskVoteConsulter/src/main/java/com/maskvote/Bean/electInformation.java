package com.maskvote.Bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

public class electInformation {


    /**
     *  这个类主要事对于投票事务的属性
     */
    private String Id;                  //项目的编号
    private String electAffairStr;      //项目的描述
    private String electStartTime;           //开始时间
    private String electEndTime;             //结束时间
    private int candidateNum;           //候选项数目
    private int electNum;               //投票人数
    private String electSearchNum;         //选举事务搜索码



    public electInformation() {
    }

    public electInformation(String id, String electAffairStr, String electStartTime, String electEndTime, int candidateNum,
                            int electNum, String electSearchNum) {
        Id = id;
        this.electAffairStr = electAffairStr;
        this.electStartTime = electStartTime;
        this.electEndTime = electEndTime;
        this.candidateNum = candidateNum;
        this.electNum = electNum;
        this.electSearchNum = electSearchNum;
    }



    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getElectAffairStr() {
        return electAffairStr;
    }

    public void setElectAffairStr(String electAffairStr) {
        this.electAffairStr = electAffairStr;
    }

    public String getElectStartTime() {
        return electStartTime;
    }

    public void setElectStartTime(String electStartTime) {
        this.electStartTime = electStartTime;
    }

    public String getElectEndTime() {
        return electEndTime;
    }

    public void setElectEndTime(String electEndTime) {
        this.electEndTime = electEndTime;
    }

    public int getCandidateNum() {
        return candidateNum;
    }

    public void setCandidateNum(int candidateNum) {
        this.candidateNum = candidateNum;
    }

    public int getElectNum() {
        return electNum;
    }

    public void setElectNum(int electNum) {
        this.electNum = electNum;
    }

    public String getElectSearchNum() {
        return electSearchNum;
    }

    public void setElectSearchNum(String electSearchNum) {
        this.electSearchNum = electSearchNum;
    }


    @Override
    public String toString() {
        return "electInformation{" +
                "Id='" + Id + '\'' +
                ", electAffairStr='" + electAffairStr + '\'' +
                ", electStartTime='" + electStartTime + '\'' +
                ", electEndTime='" + electEndTime + '\'' +
                ", candidateNum=" + candidateNum +
                ", electNum=" + electNum +
                ", electSearchNum='" + electSearchNum + '\'' +
                '}';
    }
}
