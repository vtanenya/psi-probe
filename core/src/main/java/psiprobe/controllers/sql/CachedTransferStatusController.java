package psiprobe.controllers.sql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.ParameterizableViewController;
import psiprobe.model.sql.TransferStatusInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public class CachedTransferStatusController extends ParameterizableViewController {

    /**
     * The Constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(CachedTransferStatusController.class);

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request,
                                                 HttpServletResponse response) throws Exception {

        int rowsPerPage = ServletRequestUtils.getIntParameter(request, "rowsPerPage", 0);
        List<Map<String, String>> results = null;
        int rowsAffected = 0;
        HttpSession sess = request.getSession(false);

        if (sess == null) {
            request
                    .setAttribute(
                            "errorMessage",
                            getMessageSourceAccessor().getMessage(
                                    "probe.src.dataSourceTest.cachedResultSet.failure"));
            logger.error("Cannot retrieve a cached result set. Http session is NULL.");
        } else {
            TransferStatusInfo sessData =
                    (TransferStatusInfo) sess.getAttribute(TransferStatusInfo.TRANSFER_STATUS_SESS_ATTR);

            if (sessData == null) {
                request.setAttribute(
                        "errorMessage",
                        getMessageSourceAccessor().getMessage(
                                "probe.src.dataSourceTest.cachedResultSet.failure"));
                logger.error("Cannot retrieve a cached result set. {} session attribute is NULL.",
                        TransferStatusInfo.TRANSFER_STATUS_SESS_ATTR);
            } else {
                synchronized (sess) {
                    sessData.setRowsPerPage(rowsPerPage);
                }

                results = sessData.getResults();

                if (results == null) {
                    request.setAttribute(
                            "errorMessage",
                            getMessageSourceAccessor().getMessage(
                                    "probe.src.dataSourceTest.cachedResultSet.failure"));
                    logger.error("Cached results set is NULL.");
                } else {
                    rowsAffected = results.size();
                }
            }
        }

        ModelAndView mv = new ModelAndView(getViewName(), "results", results);
        mv.addObject("rowsAffected", String.valueOf(rowsAffected));
        mv.addObject("rowsPerPage", String.valueOf(rowsPerPage));

        return mv;
    }
}