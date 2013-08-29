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
			File file = new File(
				"../portal-service/src/com/liferay/portal/kernel/util/" +
					"ReleaseInfo.java");

			String content = _fileUtil.read(file);

			// Get date

			DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG);

			String date = dateFormat.format(new Date());

			int x = content.indexOf("String _DATE = \"");
			x = content.indexOf("\"", x) + 1;

			int y = content.indexOf("\"", x);

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