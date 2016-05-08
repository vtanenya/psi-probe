/*
 * Licensed under the GPL License.  You may not use this file except in
 * compliance with the License.  You may obtain a copy of the License at
 *
 *     http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 *
 * THIS PACKAGE IS PROVIDED "AS IS" AND WITHOUT ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, WITHOUT LIMITATION, THE IMPLIED WARRANTIES OF
 * MERCHANTIBILITY AND FITNESS FOR A PARTICULAR PURPOSE.
 */

/*
 Java Script functions for datasourcetest.jsp

 Author: Andy Shapoval, Vlad Ilyushchenko
 */

var recordsetUrl = '';
var sqlOutputDivId = 'outputHolder';
var formId = 'sqlForm';
var ajaxActivityId = 'ajaxActivity';
var metaDataH3Id = 'metaDataH3';
var resultsH3Id = "resultsH3";
var optionsDivId = 'optionsDL';
var optionsVisible = false;
var ajaxActivityTimer;

function setupAjaxActions(aRecordsetUrl) {
    recordsetUrl = aRecordsetUrl;
    var rules = {
        'li#showOptions': function(element) {
            element.onclick = function() {
                showOptions();
                return false;
            }
        },
        'li#hideOptions': function(element) {
            element.onclick = function() {
                hideOptions();
                return false;
            }
        }
    };

    Behaviour.register(rules);
}

function executeSql() {
    Element.show(ajaxActivityId);
    Element.hide(metaDataH3Id);
    Element.show(resultsH3Id);
    var params = Form.serialize(formId);
    new Ajax.Updater(sqlOutputDivId, recordsetUrl, {
        method: 'post',
        postBody: params,
        onComplete: function() {
            setupPaginationLinks();
            if ($('rs_empty') || $('rs_error')) {
                $('left_scroller').hide();
                $('right_scroller').hide();
                $('separator').hide();
            } else {
                $('left_scroller').show();
                $('right_scroller').show();
                $('separator').show();
            }
            $('sqlResultsWrapper').show();
        }
    });
}

function setupPaginationLinks(req, obj) {
    if ($('rs_rowsAffected') && $('rs_pagebanner') && $('rs_pagelinks')) {
        $('rowsAffected').innerHTML = $('rs_rowsAffected').innerHTML;
        $('pagebanner').innerHTML = $('rs_pagebanner').innerHTML;
        $('pagelinks').innerHTML = $('rs_pagelinks').innerHTML;
    } else {
        $('rowsAffected').innerHTML = "";
        $('pagebanner').innerHTML = "";
        $('pagelinks').innerHTML = "";
    }

    var links = $$('#pagelinks a');

    links.each(function(lnk) {
        lnk.onclick = function() {
            Element.show(ajaxActivityId);
            Element.show(resultsH3Id);
            var p = Form.serialize(formId);
            new Ajax.Updater(sqlOutputDivId, lnk.href, {
                method: 'post',
                postBody: p,
                onComplete: setupPaginationLinks
            });
            return false;
        }
    });

    if (ajaxActivityTimer) clearTimeout(ajaxActivityTimer);
    ajaxActivityTimer = setTimeout('Element.hide("' + ajaxActivityId + '")', 250);
}
/*
 event handlers to display an option entry form
 */

function showOptions() {
    Element.hide('showOptions');
    Element.show('hideOptions');
    Effect.Appear(optionsDivId, {
        duration:0.20
    });
    optionsVisible = true;
}

function hideOptions() {
    Element.hide('hideOptions');
    Element.show('showOptions');
    Effect.Fade(optionsDivId, {
        duration:0.20
    });
    optionsVisible = false;
}

function addEvent(element, eventName, fn) {
    if (element.addEventListener)
        element.addEventListener(eventName, fn, false);
    else if (element.attachEvent)
        element.attachEvent('on' + eventName, fn);
}
