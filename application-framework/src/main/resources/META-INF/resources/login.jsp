<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="loginMessage" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=11;chrome=1">
<title><c:out value="${name} :: "></c:out><fmt:message key="login.application" /> </title>
<style type="text/css">
html, body {
    height: 100%;
    margin: 0;
}
</style>
<link rel="shortcut icon" type="image/vnd.microsoft.icon"
    href="${icon}">
<link rel="icon" type="image/vnd.microsoft.icon"
    href="${icon}">
<link rel="stylesheet" type="text/css"
    href="${stylesheet}">

<c:url value="/login" var="loginProcessingUrl" />
<script>
	function setSubmitUrl(form) {
		var hash = unescape(self.document.location.hash);
		form.action = "${loginProcessingUrl}" + hash;
		return true;
	}
</script>
</head>
<body class=" v-generated-body v-sa v-ch v-webkit v-lin"
	onLoad="document.getElementById('username').focus();">
	<form name="f" onSubmit="return setSubmitUrl(this);" method="POST"
        style="height: 100%;">
        <div id="ipmwebapplication10SNAPSHOTmaster-2058184743"
            class=" v-app ${themename} loginui">
                <div class="v-customlayout v-layout v-widget v-has-width"
                    style="border-style: none; margin: 0px; padding: 0px; width: 100%; height: 100%">
                    <div id="ipm-login-view-container"
                        class="ipm-login-view-container">
                        <div
                            class="v-panel v-widget ipm-login-panel-caption v-panel-ipm-login-panel-caption v-has-width"
                            style="overflow: hidden; width: 33%; padding-top: 27px; padding-bottom: 0px;">
                            <div class="v-panel-captionwrap"
                                style="margin-top: -27px;">
                                <div
                                    class="v-panel-caption v-panel-caption-ipm-login-panel-caption">
                                    <span class="v-icon FontAwesome"></span><span><fmt:message key="login.registrationin" /> ${name}</span>
                                </div>
                            </div>
                            <div
                                class="v-panel-content v-panel-content-ipm-login-panel-caption v-scrollable"
                                style="position: relative;">
                                <div
                                    class="v-formlayout v-layout v-widget v-has-width"
                                    style="width: 100%;">
                                    <table
                                        class="v-formlayout-margin-top v-formlayout-margin-right v-formlayout-margin-bottom v-formlayout-margin-left v-formlayout-spacing">
                                        <colgroup>
                                            <col>
                                        </colgroup>
                                        <tbody>
                                            <tr
                                                class="v-formlayout-row v-formlayout-firstrow">
                                                <td
                                                    class="v-formlayout-captioncell"><div
                                                        class="v-caption"></div></td>
                                                <td
                                                    class="v-formlayout-errorcell"><div
                                                        class="v-formlayout-error-indicator"></div></td>
                                                <td
                                                    class="v-formlayout-contentcell">
                                                    <c:choose>
                                                        <c:when
                                                            test="${param.error != null}">
                                                            <div
                                                                class="v-label v-widget v-has-width failure v-label-failure"
                                                                style="width: 100%;">
                                                                <fmt:message key="login.loginfailed" />
                                                                <c:if
                                                                    test="${SPRING_SECURITY_LAST_EXCEPTION != null}">
                                                                    <c:out
                                                                        value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
                                                                </c:if>
                                                            </div>
                                                        </c:when>
                                                        <c:when
                                                            test="${param.logout != null}">
                                                            <div
                                                                class="v-label v-widget v-has-width"
                                                                style="width: 100%;">
                                                                <fmt:message key="login.logout" />
                                                            </div>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <div class="v-label v-widget v-has-width" style="width: 100%;">
                                                                <fmt:message key="login.registrationdetails" /></div>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                            </tr>
                                            <tr class="v-formlayout-row">
                                                <td><div
                                                        class="v-caption v-caption-hasdescription">
                                                        <span><fmt:message key="login.username" /></span>
                                                    </div></td>
                                                <td
                                                    class="v-formlayout-errorcell"><div
                                                        class="v-formlayout-error-indicator"></div></td>
                                                <td class="v-formlayout-contentcell">
                                                    <input type="text"
                                                    class="v-textfield v-widget" id="username" name="username" tabindex="0" value="${username}"></td>
                                            </tr>
                                            <tr class="v-formlayout-row">
                                                <td><div
                                                        class="v-caption v-caption-hasdescription">
                                                        <span><fmt:message key="login.password" /></span>
                                                    </div></td>
                                                <td
                                                    class="v-formlayout-errorcell"><div
                                                        class="v-formlayout-error-indicator"></div></td>
                                                <td class="v-formlayout-contentcell">
                                                <input type="password" class="v-textfield v-widget" id="password" name="password" tabindex="0"  value="${password}"></td>
                                            </tr>
                                            <tr
                                                class="v-formlayout-row v-formlayout-lastrow">
                                                <td
                                                    class="v-formlayout-captioncell"><div
                                                        class="gwt-HTML"></div></td>
                                                <td
                                                    class="v-formlayout-errorcell"><div
                                                        class="v-formlayout-error-indicator"></div></td>
                                                <td
                                                    class="v-formlayout-contentcell">
                                                    <input
                                                    class="v-button v-widget v-button-wrap v-button-caption"
                                                    name="submit"
                                                    type="submit"
                                                    value="<fmt:message key="login.login" />">
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
        </div>
    </form>
</body>
</html>