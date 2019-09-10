package jp.co.systena.tigerscave.rpgapplication.application.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import jp.co.systena.tigerscave.rpgapplication.application.model.CommandForm;
import jp.co.systena.tigerscave.rpgapplication.application.model.Fighter;
import jp.co.systena.tigerscave.rpgapplication.application.model.Job;
import jp.co.systena.tigerscave.rpgapplication.application.model.JobForm;
import jp.co.systena.tigerscave.rpgapplication.application.model.Warrior;
import jp.co.systena.tigerscave.rpgapplication.application.model.Wizard;

@Controller // Viewあり。Viewを返却するアノテーション
public class RpgController {

  @Autowired
  HttpSession session; // セッション管理

  @RequestMapping(value = "/charactermake", method = RequestMethod.GET) // URLとのマッピング
  public ModelAndView index(ModelAndView mav, @Valid JobForm jobForm) {

    mav.setViewName("charactermake");
    return mav;
  }

  @RequestMapping(value = "/commandselect", method = RequestMethod.GET) // URLとのマッピング
  public ModelAndView getcommandselect(ModelAndView mav, @Valid CommandForm commandForm) {

    Job job = null;
    int counter = (int) session.getAttribute("counter");

    // ジョブリストから指定の番号の情報を抜き出し、mavに渡す
    List<Job> jobList = new ArrayList<Job>();
    jobList = (List<Job>) session.getAttribute("jobList");
    job = jobList.get(counter);

    mav.addObject("job", job);

    mav.setViewName("commandselect");
    return mav;
  }

  @RequestMapping(value = "/commandselect", method = RequestMethod.POST) // URLとのマッピング
  private ModelAndView postcommandselect(ModelAndView mav, @Valid JobForm jobForm,
      BindingResult bindingResult, HttpServletRequest request) {

    int counter = 0;
    if (session.getAttribute("counter") != null) {
      counter = (int) session.getAttribute("counter");
    }

    List<Job> jobList = (List<Job>) session.getAttribute("jobList");

    // セッションにジョブ情報が存在しないときは新しく作る
    if (jobList == null) {
      jobList = new ArrayList<Job>();
    }

    // jobIdに従ってJobのインスタンス化を行う
    if (jobForm.getJobId() == 0) {
      jobList.add(new Warrior(jobForm.getName()));
    } else if (jobForm.getJobId() == 1) {
      jobList.add(new Wizard(jobForm.getName()));
    } else if (jobForm.getJobId() == 2) {
      jobList.add(new Fighter(jobForm.getName()));
    }

    // ジョブ情報をセッションに保持
    session.setAttribute("jobList", jobList);

    // counterが3未満かつ次キャラ作成がTrueならば再度ジョブ情報の入力を要求する
    if ((counter < 3)&&(jobForm.getGoNext())) {
      counter++;
      session.setAttribute("counter", counter);
      return new ModelAndView("redirect:/charactermake"); // リダイレクト
    }

    int numOfCharacter = counter;
    session.setAttribute("numOfCharacter", numOfCharacter);

    counter = 0;
    session.setAttribute("counter", counter);
    return new ModelAndView("redirect:/commandselect"); // リダイレクト
  }

  @RequestMapping(value = "/result", method = RequestMethod.GET) // URLとのマッピング
  public ModelAndView getresult(ModelAndView mav) {

    // 作成したジョブのリスト
    List<Job> jobList = new ArrayList<Job>();
    jobList = (List<Job>) session.getAttribute("jobList");

    // 敵の体力
    int life = 100;
    if (session.getAttribute("life") != null) {
      life = (int) session.getAttribute("life");
    }

    Job job = null;
    String result = "";
    int damage = 0;

    // コマンドに従い各ジョブの行動の結果をresultに追加する
    for (int i = 0; i < jobList.size(); i++) {
      job = jobList.get(i);
      if (job.getCommandId() == 0) {
        result += job.battle();
        // 攻撃を行ったらダメージを10加算
        damage += 10;
      } else if (job.getCommandId() == 1) {
        result += job.heal();
      }
    }

    // ダメージの総量だけ敵の体力を減少する
    if(life >= damage) {
      life -= damage;
    } else {
      life = 0;
    }

    mav.addObject("result", result);
    mav.addObject("life", life);
    mav.addObject("damage", damage);

    mav.setViewName("result");
    return mav;
  }

  @RequestMapping(value = "/result", method = RequestMethod.POST) // URLとのマッピング
  private ModelAndView postresult(ModelAndView mav, @Valid CommandForm commandForm,
      BindingResult bindingResult, HttpServletRequest request) {

    int command = commandForm.getCommandId();
    int counter = (int) session.getAttribute("counter");
    int numOfCharacter = (int) session.getAttribute("numOfCharacter");

    List<Job> jobList = new ArrayList<Job>();
    jobList = (List<Job>) session.getAttribute("jobList");

    jobList.get(counter).setCommandId(command);
    session.setAttribute("jobList", jobList);

    // counterがキャラクター数より大きければresultを表示する
    if (counter >= numOfCharacter) {
      counter = 0;
      session.setAttribute("counter", counter);
      return new ModelAndView("redirect:/result"); // リダイレクト
    }

    // counterを1進め、再度コマンドの入力を要求する
    counter ++;
    session.setAttribute("counter", counter);
    return new ModelAndView("redirect:/commandselect"); // リダイレクト
  }


}
