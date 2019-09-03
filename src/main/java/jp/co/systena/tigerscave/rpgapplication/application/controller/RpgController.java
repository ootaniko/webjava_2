package jp.co.systena.tigerscave.rpgapplication.application.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import jp.co.systena.tigerscave.rpgapplication.application.model.CharacterForm;
import jp.co.systena.tigerscave.rpgapplication.application.model.Fighter;
import jp.co.systena.tigerscave.rpgapplication.application.model.Job;
import jp.co.systena.tigerscave.rpgapplication.application.model.Warrior;
import jp.co.systena.tigerscave.rpgapplication.application.model.Wizard;

@Controller // Viewあり。Viewを返却するアノテーション
public class RpgController {

  @Autowired
  HttpSession session;  // セッション管理

  @RequestMapping(value="/charactermake", method = RequestMethod.GET)          // URLとのマッピング
  public ModelAndView index(ModelAndView mav, @Valid CharacterForm characterForm) {

    mav.setViewName("charactermake");
    return mav;
  }

  @RequestMapping(value="/commandselect", method = RequestMethod.GET)          // URLとのマッピング
  public ModelAndView getcommandselect(ModelAndView mav, @Valid CharacterForm characterForm) {

    Job job = null;

    job = (Job)session.getAttribute("job");

    mav.addObject("job", job);

    mav.setViewName("commandselect");
    return mav;
  }

  @RequestMapping(value = "/commandselect", method = RequestMethod.POST) // URLとのマッピング
  private ModelAndView postcommandselect(ModelAndView mav, @Valid CharacterForm characterForm,
      BindingResult bindingResult, HttpServletRequest request) {

    Job job = null;

    if(characterForm.getJobId()==0) {
      job = new Warrior(characterForm.getName());
    }else if(characterForm.getJobId()==1) {
      job = new Wizard(characterForm.getName());
    }else if(characterForm.getJobId()==2) {
      job = new Fighter(characterForm.getName());
    }
    session.setAttribute("job", job);
    return new ModelAndView("redirect:/commandselect"); // リダイレクト
  }

  @RequestMapping(value="/result", method = RequestMethod.GET)          // URLとのマッピング
  public ModelAndView getresult(ModelAndView mav) {

    Job job = null;

    job = (Job)session.getAttribute("job");

    mav.addObject("job", job);

    mav.setViewName("result");
    return mav;
  }

  @RequestMapping(value = "/result", method = RequestMethod.POST) // URLとのマッピング
  private ModelAndView postresult(ModelAndView mav,
      BindingResult bindingResult, HttpServletRequest request) {

    return new ModelAndView("redirect:/result"); // リダイレクト
  }


}
