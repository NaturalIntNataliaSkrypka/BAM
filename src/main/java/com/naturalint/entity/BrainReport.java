package com.naturalint.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by yka on 12/10/2017.
 */
@Entity
@Table(name = "brain_report")
public class BrainReport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "report_date")
    @Temporal(TemporalType.DATE)
    private Date reportDate;

    @ManyToOne
    @JoinColumn(name = "site_id", nullable = false)
    private Site site;

    @Column
    private String platform;

    @Column
    private Integer visits;

    @Column
    private Float expected;

    @Column
    private Float CTR;

    @Column(name = "site_CTR")
    private Float siteCTR;

    @Column(name = "diff_from_site")
    private Float diffFromSite;

    @Column(name = "diff_from_expected")
    private Float diffFromExpected;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Integer getVisits() {
        return visits;
    }

    public void setVisits(Integer visits) {
        this.visits = visits;
    }

    public Float getExpected() {
        return expected;
    }

    public void setExpected(Float expected) {
        this.expected = expected;
    }

    public Float getCTR() {
        return CTR;
    }

    public void setCTR(Float CTR) {
        this.CTR = CTR;
    }

    public Float getSiteCTR() {
        return siteCTR;
    }

    public void setSiteCTR(Float siteCTR) {
        this.siteCTR = siteCTR;
    }

    public Float getDiffFromSite() {
        return diffFromSite;
    }

    public void setDiffFromSite(Float diffFromSite) {
        this.diffFromSite = diffFromSite;
    }

    public Float getDiffFromExpected() {
        return diffFromExpected;
    }

    public void setDiffFromExpected(Float diffFromExpected) {
        this.diffFromExpected = diffFromExpected;
    }
}
