package com.system.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//The metric VS is not considered in the study
@Entity
public class Metriche {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nomeFile;
    private String descrizione;
    private String VS, ACI, PM, EF,
            AZ, LI, IFF, UMA, SB, RLV, GV, NLF, NU, IP, NM, MA,
            EC, BV, NE, DF;

    public Metriche() {
    }

    public Metriche(String nomeFile, String VS, String ACI, String PM, String EF, String AZ, String LI, String IFF, String UMA, String SB, String RLV, String GV, String NLF, String NU, String IP, String NM, String MA, String EC, String BV, String NE, String DF) {
        this.nomeFile = nomeFile;
        this.VS = VS;
        this.ACI = ACI;
        this.PM = PM;
        this.EF = EF;
        this.AZ = AZ;
        this.LI = LI;
        this.IFF = IFF;
        this.UMA = UMA;
        this.SB = SB;
        this.RLV = RLV;
        this.GV = GV;
        this.NLF = NLF;
        this.NU = NU;
        this.IP = IP;
        this.NM = NM;
        this.MA = MA;
        this.EC = EC;
        this.BV = BV;
        this.NE = NE;
        this.DF = DF;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeFile() {
        return nomeFile;
    }

    public void setNomeFile(String nomeFile) {
        this.nomeFile = nomeFile;
    }

    public String getVS() {
        return VS;
    }

    public void setVS(String VS) {
        this.VS = VS;
    }

    public String getACI() {
        return ACI;
    }

    public void setACI(String ACI) {
        this.ACI = ACI;
    }

    public String getPM() {
        return PM;
    }

    public void setPM(String PM) {
        this.PM = PM;
    }

    public String getEF() {
        return EF;
    }

    public void setEF(String EF) {
        this.EF = EF;
    }

    public String getAZ() {
        return AZ;
    }

    public void setAZ(String AZ) {
        this.AZ = AZ;
    }

    public String getLI() {
        return LI;
    }

    public void setLI(String LI) {
        this.LI = LI;
    }

    public String getIFF() {
        return IFF;
    }

    public void setIFF(String IFF) {
        this.IFF = IFF;
    }

    public String getUMA() {
        return UMA;
    }

    public void setUMA(String UMA) {
        this.UMA = UMA;
    }

    public String getSB() {
        return SB;
    }

    public void setSB(String SB) {
        this.SB = SB;
    }

    public String getRLV() {
        return RLV;
    }

    public void setRLV(String RLV) {
        this.RLV = RLV;
    }

    public String getGV() {
        return GV;
    }

    public void setGV(String GV) {
        this.GV = GV;
    }

    public String getNLF() {
        return NLF;
    }

    public void setNLF(String NLF) {
        this.NLF = NLF;
    }

    public String getNU() {
        return NU;
    }

    public void setNU(String NU) {
        this.NU = NU;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getNM() {
        return NM;
    }

    public void setNM(String NM) {
        this.NM = NM;
    }

    public String getMA() {
        return MA;
    }

    public void setMA(String MA) {
        this.MA = MA;
    }

    public String getEC() {
        return EC;
    }

    public void setEC(String EC) {
        this.EC = EC;
    }

    public String getBV() {
        return BV;
    }

    public void setBV(String BV) {
        this.BV = BV;
    }

    public String getNE() {
        return NE;
    }

    public void setNE(String NE) {
        this.NE = NE;
    }

    public String getDF() {
        return DF;
    }

    public void setDF(String DF) {
        this.DF = DF;
    }

}
