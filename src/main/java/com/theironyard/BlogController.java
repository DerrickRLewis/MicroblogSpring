package com.theironyard;
import com.sun.xml.internal.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 */

@Controller
public class BlogController {


@Autowired
    BlogRepository messages;
   // ArrayList<Message> messages = new ArrayList<>();

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, HttpSession session){
        model.addAttribute("name", session.getAttribute("userName"));
        model.addAttribute("messages",session.getAttribute("messages"));
        model.addAttribute("deleteMessage" ,session.getAttribute("deleteMessage"));
        return "home";
    }


    @RequestMapping(path ="/login", method = RequestMethod.POST)
    public String login (HttpSession session,String userName){
        session.setAttribute("userName",userName);
        return "redirect:/";
    }



    @RequestMapping(path = "/add-message", method = RequestMethod.POST)
    public String inputMessage (HttpSession session,String text ){
        messages.add(new Message(messages.size()+1,text));
        session.setAttribute("messages",messages);

        return "redirect:/";
    }

    /**
     * delete button is not working
     * looking for fix to this issue
      */

    @RequestMapping(path ="/delete-message", method = RequestMethod.DELETE)

    public int deleteMessage(HttpSession session , int id){

        session.setAttribute("deleteMessage",messages.get(id));
        messages.remove(id -1);
        return Integer.parseInt("redirect:/");
   }
}