package com.vidyalankar.yogaspace;

public class YogaInstruction {

    String image, name, instruction1, instruction2, instruction3, instruction4, instruction5, instruction6;

    public YogaInstruction(String image, String name, String instruction1, String instruction2, String instruction3, String instruction4, String instruction5, String instruction6) {
        this.image = image;
        this.name = name;
        this.instruction1 = instruction1;
        this.instruction2 = instruction2;
        this.instruction3 = instruction3;
        this.instruction4 = instruction4;
        this.instruction5 = instruction5;
        this.instruction6 = instruction6;
    }

    public YogaInstruction() {
    }

    public String getInstruction4() {
        return instruction4;
    }

    public void setInstruction4(String instruction4) {
        this.instruction4 = instruction4;
    }

    public String getInstruction5() {
        return instruction5;
    }

    public void setInstruction5(String instruction5) {
        this.instruction5 = instruction5;
    }

    public String getInstruction6() {
        return instruction6;
    }

    public void setInstruction6(String instruction6) {
        this.instruction6 = instruction6;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstruction1() {
        return instruction1;
    }

    public void setInstruction1(String instruction1) {
        this.instruction1 = instruction1;
    }

    public String getInstruction2() {
        return instruction2;
    }

    public void setInstruction2(String instruction2) {
        this.instruction2 = instruction2;
    }

    public String getInstruction3() {
        return instruction3;
    }

    public void setInstruction3(String instruction3) {
        this.instruction3 = instruction3;
    }
}
