<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/html/portlet/asset_browser/init.jsp" %>

<%
long groupId = ParamUtil.getLong(request, "groupId");
long[] selectedGroupIds = StringUtil.split(ParamUtil.getString(request, "selectedGroupIds"), 0L);
long refererAssetEntryId = ParamUtil.getLong(request, "refererAssetEntryId");
String typeSelection = ParamUtil.getString(request, "typeSelection");
long subtypeSelectionId = ParamUtil.getLong(request, "subtypeSelectionId");
String eventName = ParamUtil.getString(request, "eventName", liferayPortletResponse.getNamespace() + "selectAsset");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("struts_action", "/asset_browser/view");
portletURL.setParameter("selectedGroupIds", StringUtil.merge(selectedGroupIds));
portletURL.setParameter("refererAssetEntryId", String.valueOf(refererAssetEntryId));
portletURL.setParameter("typeSelection", typeSelection);
portletURL.setParameter("subtypeSelectionId", String.valueOf(subtypeSelectionId));
portletURL.setParameter("eventName", eventName);

request.setAttribute("view.jsp-portletURL", portletURL);
%>

<div class="asset-search">
	<aui:form action="<%= portletURL %>" method="post" name="selectAssetFm">
		<aui:input name="typeSelection" type="hidden" value="<%= typeSelection %>" />

		<liferay-ui:search-container
			searchContainer="<%= new AssetSearch(renderRequest, portletURL) %>"
		>
			<aui:nav-bar>
				<aui:nav cssClass="navbar-nav" searchContainer="<%= searchContainer %>">
					<liferay-util:include page="/html/portlet/asset_browser/toolbar.jsp">
						<liferay-util:param name="groupId" value="<%= String.valueOf(groupId) %>" />
						<liferay-util:param name="typeSelection" value="<%= typeSelection %>" />
						<liferay-util:param name="subtypeSelectionId" value="<%= String.valueOf(subtypeSelectionId) %>" />
					</liferay-util:include>
				</aui:nav>

				<aui:nav-bar-search cssClass="navbar-search-advanced" file="/html/portlet/asset_publisher/asset_search.jsp" searchContainer="<%= searchContainer %>" />
			</aui:nav-bar>

			<%
			AssetSearchTerms searchTerms = (AssetSearchTerms)searchContainer.getSearchTerms();

			long[] groupIds = selectedGroupIds;

			AssetRendererFactory assetRendererFactory = AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassName(typeSelection);
			%>

			<liferay-ui:search-container-results>
				<%@ include file="/html/portlet/asset_publisher/asset_search_results.jspf" %>
			</liferay-ui:search-container-results>

			<liferay-ui:search-container-row
				className="com.liferay.portlet.asset.model.AssetEntry"
				escapedModel="<%= true %>"
				modelVar="assetEntry"
			>

				<%
				Group group = GroupLocalServiceUtil.getGroup(assetEntry.getGroupId());
				%>

				<liferay-ui:search-container-column-text
					name="title"
					value="<%= HtmlUtil.escape(assetEntry.getTitle(locale)) %>"
				/>

				<liferay-ui:search-container-column-text
					name="description"
					value="<%= HtmlUtil.stripHtml(assetEntry.getDescription(locale)) %>"
				/>

				<liferay-ui:search-container-column-text
					name="user-name"
					value="<%= HtmlUtil.escape(PortalUtil.getUserName(assetEntry)) %>"
				/>

				<liferay-ui:search-container-column-date
					name="modified-date"
					value="<%= assetEntry.getModifiedDate() %>"
				/>

				<liferay-ui:search-container-column-text
					name="site"
					value="<%= HtmlUtil.escape(group.getDescriptiveName(locale)) %>"
				/>

				<liferay-ui:search-container-column-text>
					<c:if test="<%= assetEntry.getEntryId() != refererAssetEntryId %>">

						<%
						Map<String, Object> data = new HashMap<String, Object>();

						data.put("assetentryid", assetEntry.getEntryId());
						data.put("assetclassname", assetEntry.getClassName());
						data.put("assettype", assetRendererFactory.getTypeName(locale, subtypeSelectionId));
						data.put("assettitle", assetEntry.getTitle(locale));
						data.put("groupdescriptivename", group.getDescriptiveName(locale));
						%>

						<aui:button cssClass="selector-button" data="<%= data %>" value="choose" />
					</c:if>
				</liferay-ui:search-container-column-text>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator />
		</liferay-ui:search-container>
	</aui:form>
</div>

<aui:script use="aui-base">
	Liferay.Util.selectEntityHandler('#<portlet:namespace />selectAssetFm', '<%= HtmlUtil.escapeJS(eventName) %>');
</aui:script>