/*
 * Copyright (c) 2007, 2018 Oracle and/or its affiliates. All rights reserved.
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

/*
 * $Id$
 */

package org.jakartaee8.servlet.servletrequest.filter.on.forward;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public final class WrapResponseFilter implements Filter {

    // The filter configuration object we are associated with. If this value
    // is null, this filter instance is not currently configured.
    @SuppressWarnings("unused")
    private FilterConfig filterConfig = null;

    // initialize the filter configuration object for this filter.

    @Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ServletResponse wrappedResponse = new CTSResponseWrapper(response);
        response.getWriter().print("in WrapResponseFilter");

        chain.doFilter(request, wrappedResponse);
    }

    // Remove the filter configuration object for this filter.
    @Override
    public void destroy() {
        this.filterConfig = null;
    }

}
