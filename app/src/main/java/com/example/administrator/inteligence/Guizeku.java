package com.example.administrator.inteligence;

/**
 * Created by Administrator on 2016/9/4.
 */
public class Guizeku {
    private String tite;
    private String guizeName;
    private String conclusion;

    public Guizeku(String title,String guizeName,String conclusion) {
        this.guizeName = guizeName;
        this.tite = title;
        this.conclusion = conclusion;
    }
    public void setGuizeName(String guizeName) {
        this.guizeName = guizeName;
    }

    public String getGuizeName() {
        return guizeName;
    }

    public void setTite(String tite) {
        this.tite = tite;
    }

    public String getTite() {
        return tite;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public String getConclusion() {
        return conclusion;
    }
}
