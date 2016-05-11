package psiprobe.controllers.hibernate;

import org.apache.catalina.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import psiprobe.Entities.MonTransferStatus;
import psiprobe.controllers.ContextHandlerController;
import psiprobe.model.sql.TransferStatusInfo;
import psiprobe.service.MonTransferStatusService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MonTransferStatusController extends ContextHandlerController {

    @Autowired
    private MonTransferStatusService statusService;

    @Override
    protected ModelAndView handleContext(String contextName, Context context, HttpServletRequest request, HttpServletResponse response) throws Exception {

        int maxRows = ServletRequestUtils.getIntParameter(request, "maxRows", 100);
        int rowsPerPage = ServletRequestUtils.getIntParameter(request, "rowsPerPage", 20);
        String objectUUID = ServletRequestUtils.getStringParameter(request, "objectUUID");
        String dateFrom = ServletRequestUtils.getStringParameter(request, "dateFrom");
        String dateTo = ServletRequestUtils.getStringParameter(request, "dateTo");
        String transferStatus = ServletRequestUtils.getStringParameter(request, "transferStatus");
        String comment = ServletRequestUtils.getStringParameter(request, "comment");
        String fileName = ServletRequestUtils.getStringParameter(request, "fileName");

        // store current option values and query history in a session attribute

        HttpSession sess = request.getSession();
        TransferStatusInfo sessData =
                (TransferStatusInfo) sess.getAttribute(TransferStatusInfo.TRANSFER_STATUS_SESS_ATTR);

        synchronized (sess) {
            if (sessData == null) {
                sessData = new TransferStatusInfo();
                sess.setAttribute(TransferStatusInfo.TRANSFER_STATUS_SESS_ATTR, sessData);
            }

            sessData.setMaxRows(maxRows);
            sessData.setRowsPerPage(rowsPerPage);
            sessData.setObjectUUID(objectUUID);
            sessData.setDateFrom(dateFrom);
            sessData.setDateTo(dateTo);
            sessData.setTransferStatus(transferStatus);
            sessData.setComment(comment);
            sessData.setFileName(fileName);
        }

        List<MonTransferStatus> statuses = null;
        int rowsAffected = 0;
        statuses = statusService.listStatuses(maxRows, objectUUID, dateFrom, dateTo, transferStatus, comment, fileName);
        rowsAffected = statuses.size();

        // store the query results in the session attribute in order
        // to support a result set pagination feature without re-executing the query

        List<Map<String, String>> results = new ArrayList<>();

        for (MonTransferStatus status : statuses)
        {
            Map<String, String> record = new LinkedHashMap<>();

            record.put("Object UUID", status.getObjectUUID());
            record.put("Edit Date", status.getEditDate().toString());
            record.put("Status", status.getTransferStatus());
            record.put("Comment", status.getComment());
            record.put("File Name", status.getFile() != null ? status.getFile().getTitle() : "");
            record.put("Status UUID", status.getUuid());

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