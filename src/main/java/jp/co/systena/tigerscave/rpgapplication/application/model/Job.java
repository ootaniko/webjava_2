package jp.co.systena.tigerscave.rpgapplication.application.model;

public abstract class Job {

  private int jobId;
  private String jobName;
  private String characterName;
  private int commandId;

  public Job(int jobId, String jobName, String characterName) {
    this.setJobId(jobId);
    this.setJobName(jobName);
    this.setCharacterName(characterName);
    this.commandId = -1;
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

  public String getCharacterName() {
    return characterName;
  }

  public void setCharacterName(String characterName) {
    this.characterName = characterName;
  }

  public int getCommandId() {
    return commandId;
  }

  public void setCommandId(int commandId) {
    this.commandId = commandId;
  }

  public abstract String battle();

  public abstract String heal();

}
