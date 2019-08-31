package jp.co.systena.tigerscave.rpgapplication.application.model;

public class Warrior extends Job {

  public Warrior(String characterName){
    super(0, "戦士", characterName);
  }

  @Override
  public String battle() {
    // TODO 自動生成されたメソッド・スタブ
    return getCharacterName() + "は剣で攻撃した！";
  }

}
