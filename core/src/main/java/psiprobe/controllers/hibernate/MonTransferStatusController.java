package psiprobe.controllers.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import psiprobe.Entities.MonTransferStatus;
import psiprobe.controllers.TomcatContainerController;
import psiprobe.service.MonTransferStatusService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by vt on 06.05.16.
 */

@Controller
public class MonTransferStatusController extends TomcatContainerController {

    @Autowired
    private MonTransferStatusService statusService;

    @RequestMapping("/mon")
    public String listContacts(Map<String, Object> map) {

        map.put("status", new MonTransferStatus());
        map.put("statusList", statusService.listStatuses());

        return "status";
    }

//    @RequestMapping("/")
//    public String home() {
//        return "redirect:/index";
//    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addContact(@ModelAttribute("MonTransferStatus") MonTransferStatus status,
                             BindingResult result) {

        statusService.addStatus(status);

        return "redirect:/index";
    }

    @RequestMapping("/delete/{statusUUID}")
    public String deleteContact(@PathVariable("statusUUID") String statusUUID) {

        statusService.removeStatus(statusUUID);

        return "redirect:/mon";
    }

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        ModelAndView modelAndView = new ModelAndView(getViewName());
        modelAndView.addObject( "statusList", statusService.listStatuses());

        return modelAndView;
    }
}