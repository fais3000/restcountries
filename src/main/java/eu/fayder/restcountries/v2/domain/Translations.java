package eu.fayder.restcountries.v2.domain;

import io.micronaut.core.annotation.Introspected;

/**
 * Created by fayder on 05/03/2017.
 */
@Introspected
public class Translations {

    private String br;
    private String pt;
    private String nl;
    private String hr;
    private String fa;

    public String getBr() {
        return br;
    }

    public String getPt() {
        return pt;
    }

    public String getNl() {
        return nl;
    }

    public String getHr() {
        return hr;
    }

    public String getFa() {
        return fa;
    }

    private String de;
    private String es;
    private String fr;
    private String ja;
    private String it;

    public String getDe() {
        return de;
    }
    public void setDe(String de) {
        this.de = de;
    }
    public String getEs() {
        return es;
    }
    public void setEs(String es) {
        this.es = es;
    }
    public String getFr() {
        return fr;
    }
    public void setFr(String fr) {
        this.fr = fr;
    }
    public String getJa() {
        return ja;
    }
    public void setJa(String ja) {
        this.ja = ja;
    }
    public String getIt() {
        return it;
    }
    public void setIt(String it) {
        this.it = it;
    }
}
