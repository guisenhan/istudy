package hise.hznu.istudy.model.course;

import java.io.Serializable;

/**
 * Created by PC on 2016/9/26.
 */
public class SubQuestionEntity implements Serializable{
    /**
     *  id : 小题题目id
     type :  题目类别（基础题型）
     content ： 题目内容
     totalscore：分值
     strandanswer ： 参考答案
     optiona  ： 选项a
     optionb  ： 选项b
     optionc  ： 选项c
     optiond  ： 选项d
     optione  ： 选项e
     optionf  ： 选项f
     optiong  ： 选项g
     optionh  ： 选项h
     optioni  ： 选项i
     optionj  ： 选项j
     blank1   : 填空提示1
     blank2   : 填空提示2
     blank3   : 填空提示3
     blank4   : 填空提示4
     blank5   : 填空提示5
     blank6   : 填空提示6
     blank7   : 填空提示7
     blank8   : 填空提示8
     blank9   : 填空提示9
     blank10   : 填空提示10
     */
    private String id;
    private int type;
    private String content;
    private String totalscore;
    private String strandanswer;
    private String optiona;
    private String optionb;
    private String optionc;
    private String optiond;
    private String optione;
    private String optionf;
    private String optiong;
    private String optionh;
    private String optioni;
    private String optionj;
    private String  blank1;
    private String  blank2;
    private String  blank3;
    private String  blank4;
    private String  blank5;
    private String  blank6;
    private String  blank7;
    private String  blank8;
    private String  blank9;
    private String  blank10;

    public String getBlank10() {
        return blank10;
    }

    public void setBlank10(String blank10) {
        this.blank10 = blank10;
    }

    public String getBlank1() {
        return blank1;
    }

    public void setBlank1(String blank1) {
        this.blank1 = blank1;
    }

    public String getBlank2() {
        return blank2;
    }

    public void setBlank2(String blank2) {
        this.blank2 = blank2;
    }

    public String getBlank3() {
        return blank3;
    }

    public void setBlank3(String blank3) {
        this.blank3 = blank3;
    }

    public String getBlank4() {
        return blank4;
    }

    public void setBlank4(String blank4) {
        this.blank4 = blank4;
    }

    public String getBlank5() {
        return blank5;
    }

    public void setBlank5(String blank5) {
        this.blank5 = blank5;
    }

    public String getBlank6() {
        return blank6;
    }

    public void setBlank6(String blank6) {
        this.blank6 = blank6;
    }

    public String getBlank7() {
        return blank7;
    }

    public void setBlank7(String blank7) {
        this.blank7 = blank7;
    }

    public String getBlank8() {
        return blank8;
    }

    public void setBlank8(String blank8) {
        this.blank8 = blank8;
    }

    public String getBlank9() {
        return blank9;
    }

    public void setBlank9(String blank9) {
        this.blank9 = blank9;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOptiona() {
        return optiona;
    }

    public void setOptiona(String optiona) {
        this.optiona = optiona;
    }

    public String getOptionb() {
        return optionb;
    }

    public void setOptionb(String optionb) {
        this.optionb = optionb;
    }

    public String getOptionc() {
        return optionc;
    }

    public void setOptionc(String optionc) {
        this.optionc = optionc;
    }

    public String getOptiond() {
        return optiond;
    }

    public void setOptiond(String optiond) {
        this.optiond = optiond;
    }

    public String getOptione() {
        return optione;
    }

    public void setOptione(String optione) {
        this.optione = optione;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getStrandanswer() {
        return strandanswer;
    }

    public void setStrandanswer(String strandanswer) {
        this.strandanswer = strandanswer;
    }

    public String getTotalscore() {
        return totalscore;
    }

    public void setTotalscore(String totalscore) {
        this.totalscore = totalscore;
    }

    public String getOptioni() {
        return optioni;
    }

    public void setOptioni(String optioni) {
        this.optioni = optioni;
    }

    public String getOptionj() {
        return optionj;
    }

    public void setOptionj(String optionj) {
        this.optionj = optionj;
    }

    public String getOptionh() {
        return optionh;
    }

    public void setOptionh(String optionh) {
        this.optionh = optionh;
    }

    public String getOptiong() {
        return optiong;
    }

    public void setOptiong(String optiong) {
        this.optiong = optiong;
    }

    public String getOptionf() {
        return optionf;
    }

    public void setOptionf(String optionf) {
        this.optionf = optionf;
    }
}
