/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.tools;

import java.io.File;
import java.text.DateFormat;
import java.util.Date;
import java.util.Properties;

import com.liferay.portal.util.FileImpl;

/**
 * @author Brian Wing Shun Chan
 * @author Sander Bilo
 */
public class BuildInfoBuilder {

	public static void main(String[] args) {
		new BuildInfoBuilder();
	}

	public BuildInfoBuilder() {
		try {

			String machineName = System.getProperty("user.name");

			Properties releaseProps = _fileUtil.toProperties("../release.properties");
			Properties releaseMachineProps = _fileUtil.toProperties("../release." + machineName + ".properties");
			if (releaseMachineProps != null) {
				releaseProps.putAll(releaseMachineProps);
			}
			
			File file = new File(
				"../portal-service/src/com/liferay/portal/kernel/util/" +
					"ReleaseInfo.java");

			String content = _fileUtil.read(file);
			
			String version = releaseProps.getProperty("lp.version") +"-"+ releaseProps.getProperty("pce.version");
			
			int x = content.indexOf("String _VERSION = \"");

			x = content.indexOf("\"", x) + 1;

			int y = content.indexOf("\"", x);

			content = content.substring(0, x) + version + content.substring(y);
			
			String versionDisplayName = version + " PCE GA2";
			
			x = content.indexOf("String _VERSION_DISPLAY_NAME = \"");

			x = content.indexOf("\"", x) + 1;

			y = content.indexOf("\"", x);

			content = content.substring(0, x) + versionDisplayName + content.substring(y);

			// Get date

			DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG);

			String date = dateFormat.format(new Date());

			x = content.indexOf("String _DATE = \"");
			x = content.indexOf("\"", x) + 1;

			y = content.indexOf("\"", x);

			content = content.substring(0, x) + date + content.substring(y);

			// Update ReleaseInfo.java

			_fileUtil.write(file, content);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static FileImpl _fileUtil = FileImpl.getInstance();

}