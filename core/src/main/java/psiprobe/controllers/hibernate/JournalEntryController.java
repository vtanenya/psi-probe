package psiprobe.controllers.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import psiprobe.DAO.JournalEntryDAO;
import psiprobe.controllers.TomcatContainerController;
import psiprobe.service.JournalEntryService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by vt on 08.05.16.
 */

@Controller
public class JournalEntryController extends TomcatContainerController {

    @Autowired
    private JournalEntryService journalService;

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        ModelAndView modelAndView = new ModelAndView(getViewName());
        modelAndView.addObject( "journalList", journalService.listEntrys());

        return modelAndView;
    }
}