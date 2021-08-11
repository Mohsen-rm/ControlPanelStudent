package com.ajiashi.controlpanelstudent.mode;

public class ModeResult {

    public String id;
    public String name_mahfatl;
    public boolean chake_result;
    public String link_result;
    public String src_result;
    public int numberclass;
    public int numberadverb ;


    public ModeResult(String name_mahfatl,boolean chake_result , String link_result, String src_result,int numberclass,int numberadverb){
        this.name_mahfatl = name_mahfatl;
        this.chake_result = chake_result;
        this.link_result = link_result;
        this.src_result = src_result;
        this.numberclass = numberclass;
        this.numberadverb = numberadverb;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName_mahfatl() {
        return name_mahfatl;
    }

    public void setName_mahfatl(String name_mahfatl) {
        this.name_mahfatl = name_mahfatl;
    }

    public boolean getChake_result() {
        return chake_result;
    }

    public void setChake_result(boolean chake_result) {
        this.chake_result = chake_result;
    }

    public String getLink_result() {
        return link_result;
    }

    public void setLink_result(String link_result) {
        this.link_result = link_result;
    }

    public String getSrc_result() {
        return src_result;
    }

    public void setSrc_result(String src_result) {
        this.src_result = src_result;
    }

    public int getNumberclass() {
        return numberclass;
    }

    public void setNumberclass(int numberclass) {
        this.numberclass = numberclass;
    }

    public int getNumberadverb() {
        return numberadverb;
    }

    public void setNumberadverb(int numberadverb) {
        this.numberadverb = numberadverb;
    }

}
