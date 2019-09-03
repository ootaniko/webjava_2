package jp.co.systena.tigerscave.rpgapplication.application.model;

public class Wizard extends Job {

  public Wizard(String characterName){
    super(1, "魔法使い", characterName);
  }

  @Override
  public String battle() {
    // TODO 自動生成されたメソッド・スタブ
    return getCharacterName() + "は魔法で攻撃した！";
  }

  @Override
  public String heal() {
    // TODO 自動生成されたメソッド・スタブ
    return getCharacterName() + "はまほうで回復した！";
  }


}
