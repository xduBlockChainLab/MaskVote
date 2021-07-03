package com.maskvote.Bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@ToString
public class voteInformation {


    /**
     *  这个类主要事对于投票事务的属性
     */
    private String Id;             //项目的编号
    private String voteAffairStr;         //项目的描述
    private String voteStartTime;     //开始时间
    private String voteEndTime;     //倒计时
    private int voteNum;      //投票人数
    private String voteSearchNum;       //表决事物搜索码



    public voteInformation() {
    }


    public voteInformation(String id, String voteAffairStr, String voteStartTime, String voteEndTime, int voteNum, String voteSearchNum) {
        Id = id;
        this.voteAffairStr = voteAffairStr;
        this.voteStartTime = voteStartTime;
        this.voteEndTime = voteEndTime;
        this.voteNum = voteNum;
        this.voteSearchNum = voteSearchNum;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getVoteAffairStr() {
        return voteAffairStr;
    }

    public void setVoteAffairStr(String voteAffairStr) {
        this.voteAffairStr = voteAffairStr;
    }

    public String getVoteStartTime() {
        return voteStartTime;
    }

    public void setVoteStartTime(String voteStartTime) {
        this.voteStartTime = voteStartTime;
    }

    public String getVoteEndTime() {
        return voteEndTime;
    }

    public void setVoteEndTime(String voteEndTime) {
        this.voteEndTime = voteEndTime;
    }

    public int getVoteNum() {
        return voteNum;
    }

    public void setVoteNum(int voteNum) {
        this.voteNum = voteNum;
    }

    public String getVoteSearchNum() {
        return voteSearchNum;
    }

    public void setVoteSearchNum(String voteSearchNum) {
        this.voteSearchNum = voteSearchNum;
    }


    @Override
    public String toString() {
        return "voteInformation{" +
                "Id='" + Id + '\'' +
                ", voteAffairStr='" + voteAffairStr + '\'' +
                ", voteStartTime='" + voteStartTime + '\'' +
                ", voteEndTime='" + voteEndTime + '\'' +
                ", voteNum=" + voteNum +
                ", voteSearchNum='" + voteSearchNum + '\'' +
                '}';
    }
}