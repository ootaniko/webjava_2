package jp.co.systena.tigerscave.rpgapplication.application.model;

import java.util.ArrayList;
import java.util.List;

public class Party {

  private List<Job> jobList = new ArrayList<Job>();

  public List<Job> getJobList() {
    return jobList;
  }

  public void setJobList(List<Job> jobList) {
    this.jobList = jobList;
  }

}
