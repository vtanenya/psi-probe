package psiprobe.controllers.hibernate;

import org.apache.catalina.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import psiprobe.Entities.JournalEntry;
import psiprobe.controllers.ContextHandlerController;
import psiprobe.model.sql.JournalEntryInfo;
import psiprobe.service.JournalEntryService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Vladimir tanenya
 * 01.05.16.
 */

@Controller
public class JournalEntryController extends ContextHandlerController {

    @Autowired
    private JournalEntryService journalService;

    @Override
    protected ModelAndView handleContext(String contextName, Context context, HttpServletRequest request, HttpServletResponse response) throws Exception {

        int maxRows = ServletRequestUtils.getIntParameter(request, "maxRows", 100);
        int rowsPerPage = ServletRequestUtils.getIntParameter(request, "rowsPerPage", 20);
        String senderLastname = ServletRequestUtils.getStringParameter(request, "senderLastname");
        String dateFrom = ServletRequestUtils.getStringParameter(request, "dateFrom");
        String dateTo = ServletRequestUtils.getStringParameter(request, "dateTo");
        String eventMessage = ServletRequestUtils.getStringParameter(request, "eventMessage");
        String subjectUUID = ServletRequestUtils.getStringParameter(request, "subjectUUID");

        // store current option values and query history in a session attribute

        HttpSession sess = request.getSession();
        JournalEntryInfo sessData =
                (JournalEntryInfo) sess.getAttribute(JournalEntryInfo.JOURNAL_ENTRY_SESS_ATTR);

        synchronized (sess) {
            if (sessData == null) {
                sessData = new JournalEntryInfo();
                sess.setAttribute(JournalEntryInfo.JOURNAL_ENTRY_SESS_ATTR, sessData);
            }

            sessData.setMaxRows(maxRows);
            sessData.setRowsPerPage(rowsPerPage);
            sessData.setSenderLastname(senderLastname);
            sessData.setDateFrom(dateFrom);
            sessData.setDateTo(dateTo);
            sessData.setEventMessage(eventMessage);
            sessData.setSubjectUUID(subjectUUID);
        }

        List<JournalEntry> entries = null;
        int rowsAffected = 0;
        entries = journalService.listEntrys(maxRows, senderLastname, dateFrom, dateTo, eventMessage, subjectUUID);
        rowsAffected = entries.size();

        // store the query results in the session attribute in order
        // to support a result set pagination feature without re-executing the query

        List<Map<String, String>> results = new ArrayList<>();

        for (JournalEntry entry : entries)
        {
            Map<String, String> record = new LinkedHashMap<>();

            record.put("Sender name", entry.getSender().getLastName() + " "
                    + entry.getSender().getFirstName().substring(0,1) + ". "
                    + entry.getSender().getMiddleName().substring(0,1) + ". ");
            record.put("Date", entry.getDate().toString());
            record.put("Category", entry.getCategory());
            record.put("Message", entry.getMessage());
            record.put("Subject UUID", entry.getSubject());
            record.put("Entry UUID", entry.getUuid());

            results.add(record);
        }

        synchronized (sess) {
            sessData.setResults(results);
        }

        ModelAndView mv = new ModelAndView(getViewName(), "results", results);
        mv.addObject("rowsAffected", String.valueOf(rowsAffected));
        mv.addObject("rowsPerPage", String.valueOf(rowsPerPage));
        mv.addObject("maxRows", String.valueOf(maxRows));

        return mv;
    }

    @Override
    protected boolean isContextOptional() {
        return true;
    }
}