package jp.co.systena.tigerscave.rpgapplication.application.model;

public abstract class Job {

  private int jobId;
  private String jobName;
  private String characterName;

  public Job(int jobId, String jobName, String characterName) {
    this.setJobId(jobId);
    this.setJobName(jobName);
    this.setCharacterName(characterName);
  }

  public int getJobId() {
    return jobId;
  }

  public void setJobId(int jobId) {
    this.jobId = jobId;
  }

  public String getJobName() {
    return jobName;
  }

  public void setJobName(String jobName) {
    this.jobName = jobName;
  }

  public abstract String battle();

  public String getCharacterName() {
    return characterName;
  }

  public void setCharacterName(String characterName) {
    this.characterName = characterName;
  }
}
