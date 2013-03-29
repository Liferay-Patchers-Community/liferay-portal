<#include "macro.ftl">

insert into DLFileEntry values ('${dlFileEntry.uuid}', ${dlFileEntry.fileEntryId}, ${dlFileEntry.groupId}, ${dlFileEntry.companyId}, ${dlFileEntry.userId}, '${dlFileEntry.userName}', ${dlFileEntry.versionUserId}, '${dlFileEntry.versionUserName}', '${dataFactory.getDateString(dlFileEntry.createDate)}', '${dataFactory.getDateString(dlFileEntry.modifiedDate)}', ${dlFileEntry.classNameId}, ${dlFileEntry.classPK}, ${dlFileEntry.repositoryId}, ${dlFileEntry.folderId}, '${dlFileEntry.name}', '${dlFileEntry.extension}', '${dlFileEntry.mimeType}', '${dlFileEntry.title}','${dlFileEntry.description}', '${dlFileEntry.extraSettings}', ${dlFileEntry.fileEntryTypeId}, '${dlFileEntry.version}', ${dlFileEntry.size}, ${dlFileEntry.readCount}, ${dlFileEntry.smallImageId}, ${dlFileEntry.largeImageId}, ${dlFileEntry.custom1ImageId}, ${dlFileEntry.custom2ImageId}, ${dlFileEntry.manualCheckInRequired?string});

<#assign dlFileVersion = dataFactory.newDLFileVersion(dlFileEntry)>

insert into DLFileVersion values ('${dlFileVersion.uuid}', ${dlFileVersion.fileVersionId}, ${dlFileVersion.groupId}, ${dlFileVersion.companyId}, ${dlFileVersion.userId}, '${dlFileVersion.userName}', '${dataFactory.getDateString(dlFileVersion.createDate)}', '${dataFactory.getDateString(dlFileVersion.modifiedDate)}', ${dlFileVersion.repositoryId}, ${dlFileVersion.folderId}, ${dlFileVersion.fileEntryId}, '${dlFileVersion.extension}', '${dlFileVersion.mimeType}', '${dlFileVersion.title}','${dlFileVersion.description}', '${dlFileVersion.changeLog}', '${dlFileVersion.extraSettings}', ${dlFileVersion.fileEntryTypeId}, '${dlFileVersion.version}', ${dlFileVersion.size}, '${dlFileVersion.checksum}', ${dlFileVersion.status}, ${dlFileVersion.statusByUserId}, '${dlFileVersion.statusByUserName}', ${dlFileVersion.statusDate!'null'});

<@insertDLSync _entry = dlFileEntry/>

<@insertAssetEntry _entry = dlFileEntry/>

<#assign ddmStorageLinkId = counter.get()>

<@insertDDMContent _entry = dlFileEntry _ddmStorageLinkId = ddmStorageLinkId _ddmStructureId = ddmStructureId/>

${sampleSQLBuilder.insertMBDiscussion(dlFileEntry.groupId, dataFactory.DLFileEntryClassNameId, dlFileEntry.fileEntryId, counter.get(), counter.get(), 0)}

<#assign socialActivity = dataFactory.newSocialActivity(dlFileEntry)>

insert into SocialActivity values (${socialActivity.activityId}, ${socialActivity.groupId}, ${socialActivity.companyId}, ${socialActivity.userId}, ${socialActivity.createDate}, ${socialActivity.activitySetId}, ${socialActivity.mirrorActivityId}, ${socialActivity.classNameId}, ${socialActivity.classPK}, ${socialActivity.type}, '${socialActivity.extraData}', ${socialActivity.receiverUserId});

<#assign dlFileEntryMetadata = dataFactory.newDLFileEntryMetadata(ddmStorageLinkId, ddmStructureId, dlFileVersion)>

insert into DLFileEntryMetadata values ('${dlFileEntryMetadata.uuid}', ${dlFileEntryMetadata.fileEntryMetadataId}, ${dlFileEntryMetadata.DDMStorageId}, ${dlFileEntryMetadata.DDMStructureId}, ${dlFileEntryMetadata.fileEntryTypeId}, ${dlFileEntryMetadata.fileEntryId}, ${dlFileEntryMetadata.fileVersionId});

<@insertDDMStructureLink _entry = dlFileEntryMetadata/>