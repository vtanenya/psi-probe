<%--
 * Licensed under the GPL License.  You may not use this file except in
 * compliance with the License.  You may obtain a copy of the License at
 *
 *     http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 *
 * THIS PACKAGE IS PROVIDED "AS IS" AND WITHOUT ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, WITHOUT LIMITATION, THE IMPLIED WARRANTIES OF
 * MERCHANTIBILITY AND FITNESS FOR A PARTICULAR PURPOSE.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="https://github.com/psi-probe/psi-probe/jsp/tags" prefix="probe" %>

<html>

<head>
    <title><spring:message code="probe.jsp.menu.journalentry"/></title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}<spring:theme code='datasourcetest.css'/>"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}<spring:theme code='scroller.css'/>"/>
    <script type="text/javascript" src="<c:url value='/js/prototype.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/behaviour.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/scriptaculous.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/func.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/areascroller.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/journalentry.js'/>"></script>
</head>

<%--
    Provides GUI for datasource connectivity testing.
    Allows a user to enter an SQL query and displays results returned by the query.

    Author: Andy Shapoval, Vlad Ilyushchenko
--%>

<body>

<c:set var="navTabJournalEntry" value="active" scope="request"/>

<div class="dataSourceTestMenu">
    <ul class="options">
        <li id="showOptions">
            <a href="#" onclick="showOptions()">
                <spring:message code="probe.jsp.dataSourceTest.menu.showOptions"/>
            </a>
        </li>
        <li id="hideOptions" style="display: none;">
            <a href="#" onclick="hideOptions()">
                <spring:message code="probe.jsp.dataSourceTest.menu.hideOptions"/>
            </a>
        </li>
    </ul>
</div>

<div class="dataSourceTestContent">
    <div id="help" class="helpMessage" style="display: none;">
        <div class="ajax_activity"></div>
    </div>

    <form id="sqlForm" action="" method="post">
        <c:if test="${param.webapp != null}">
            <input type="hidden" name="webapp" value="${param.webapp}"/>
        </c:if>
        <input type="hidden" name="resource" value="${param.resource}"/>
        <dl id="optionsDL" style="display: none;">
            <dt><label for="maxRows"><spring:message code="probe.jsp.dataSourceTest.sqlForm.maxRows.label"/></label></dt>
            <dd><input type="text" id="maxRows" name="maxRows" class="txtInput" value="${maxRows}" size="6"/></dd>
            <dt><label for="rowsPerPage"><spring:message code="probe.jsp.dataSourceTest.sqlForm.rowsPerPage.label"/></label></dt>
            <dd><input type="text" id="rowsPerPage" name="rowsPerPage" class="txtInput" value="${rowsPerPage}" size="6"/></dd>
            <dt><label for="historySize"><spring:message code="probe.jsp.dataSourceTest.sqlForm.historySize.label"/></label></dt>
            <dd><input type="text" id="historySize" name="historySize"  class="txtInput" value="${historySize}" size="6"/></dd>
            <li id="executeSql">
                <a href="#" onclick="executeSql()">
                    <spring:message code="probe.jsp.dataSourceTest.menu.execute"/>
                </a>
            </li>
        </dl>
    </form>

    <div id="sqlResultsHeader">
        <h3 id="metaDataH3" style="display: none;"><spring:message code="probe.jsp.dataSourceTest.h3.metaData"/></h3>

        <h3 id="resultsH3" style="display: none;"><spring:message code="probe.jsp.dataSourceTest.h3.results"/></h3>

        <div id="ajaxActivity" class="ajax_activity" style="display: none;"></div>
    </div>

    <div id="sqlResultsWrapper" style="display: none;">
        <div>
            <span id="rowsAffected"></span><span id="pagebanner"></span><span id="pagelinks"></span>
        </div>
        <table id="resultsTable" cellspacing="0">
            <tr>
                <td id="left_scroller" class="scroller" style="display: none;">&nbsp;</td>
                <td id="separator" width="1%" style="display: none;">&nbsp;</td>
                <td><div id="outputHolder"></div></td>
                <td id="right_scroller" class="scroller" style="display: none;">&nbsp;</td>
            </tr>
        </table>
    </div>

</div>

<script type="text/javascript">
    setupAjaxActions(
            '<c:url value="/sql/journalentryset.ajax"/>');
    setupScrollers('sqlResultsContainer');
    addEvent(window, 'load', executeSql());
    //window.onload = onload + executeSql();
</script>

</body>

</html>
