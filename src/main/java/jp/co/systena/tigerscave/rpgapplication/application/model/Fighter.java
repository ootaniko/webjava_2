package jp.co.systena.tigerscave.rpgapplication.application.model;

public class Fighter extends Job {

  public Fighter(String characterName){
    super(2, "武闘家", characterName);
  }

  @Override
  public String battle() {
    // TODO 自動生成されたメソッド・スタブ
    return getCharacterName() + "は拳で攻撃した！";
  }

  @Override
  public String heal() {
    // TODO 自動生成されたメソッド・スタブ
    return getCharacterName() + "はやくそうで回復した！";
  }

}
