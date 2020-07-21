/*
 * Copyright (c) 2012, 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0, which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the
 * Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
 * version 2 with the GNU Classpath Exception, which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 */

package org.jakartaee8.servlet.security.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.security.DeclareRoles;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.HttpMethodConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.ServletSecurity.EmptyRoleSemantic;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * This should allow the Administrator role permission to access any of the Servlet methods.
 *
 * <p>
 * This is an annotation based equivalent of ServletSecTestServlet with the main difference
 * being that this uses Servlet based annotations whereas ServletSecTestServlet uses a
 * deployment descriptor for configuring its security constraints.
 */
@DeclareRoles({ "Administrator", "Manager", "Employee" })
@ServletSecurity(
    value = @HttpConstraint(EmptyRoleSemantic.DENY),
    httpMethodConstraints = {
        @HttpMethodConstraint(value = "GET", rolesAllowed = "Administrator"),
        @HttpMethodConstraint(value = "POST", rolesAllowed = "Administrator") })
@WebServlet(name = "ServletSecAnnoTestLogicalName", urlPatterns = { "/ServletSecAnnoTest" })
public class ServletSecAnnoTestServlet extends HttpServlet {

    private static final long serialVersionUID = -3316626375207623503L;

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        System.out.println("Inside  ServletSecAnnoTestServlet ....." + "<BR>");
        System.out.println("The user principal is: " + request.getUserPrincipal().getName() + "<BR>");
        System.err.println("The user principal is: " + request.getUserPrincipal().getName() + "<BR>");

        out.println("The user principal is: " + request.getUserPrincipal().getName() + "<BR>");
        out.println("getRemoteUser(): " + request.getRemoteUser() + "<BR>");

        // Used for validating test7
        out.println("Inside  ServletSecAnnoTestServlet ....." + "<BR>");

        // Surround these with !'s so they are easier to search for.
        // (i.e. we can search for !true! or !false!)
        out.println("isUserInRole(\"ADM\"): !" + request.isUserInRole("ADM") + "!<BR>");
        out.println("isUserInRole(\"MGR\"): !" + request.isUserInRole("MGR") + "!<BR>");
        out.println("isUserInRole(\"VP\"): !" + request.isUserInRole("VP") + "!<BR>");
        out.println("isUserInRole(\"EMP\"): !" + request.isUserInRole("EMP") + "!<BR>");
        out.println("isUserInRole(\"Administrator\"): !" + request.isUserInRole("Administrator") + "!<BR>");
    }
}
