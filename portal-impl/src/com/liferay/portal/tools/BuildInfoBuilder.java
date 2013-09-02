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
import java.util.Locale;
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
      
      Properties releaseProps = _fileUtil.toProperties("../release." + machineName + ".properties");
      if (releaseProps == null || releaseProps.isEmpty()) {
        releaseProps = _fileUtil.toProperties("../release.properties");
      }

      File file = new File("../portal-service/src/com/liferay/portal/kernel/util/" + "ReleaseInfo.java");

      String content = _fileUtil.read(file);

      String pceVersion = releaseProps.getProperty("pce.version");
      content = setClassProperty("_PCE_VERSION", pceVersion, content);

      DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG, Locale.ENGLISH);
      String date = dateFormat.format(new Date());
      content = setClassProperty("_PCE_BUILD_DATE", date, content);

      _fileUtil.write(file, content);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private String setClassProperty(String propertyName,
                                  String value,
                                  String content) {
    int x = content.indexOf("String " + propertyName +" = \"");
    x = content.indexOf("\"", x) + 1;

    int y = content.indexOf("\"", x);

    content = content.substring(0, x) + value + content.substring(y);
    return content;
  }

  private static FileImpl _fileUtil = FileImpl.getInstance();

}
