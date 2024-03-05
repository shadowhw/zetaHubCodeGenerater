DROP TABLE IF EXISTS `${schemeName}`.`${tableName}`;

CREATE TABLE `${schemeName}`.`${tableName}` (
<#if tableFieldInfos?has_content>
  <#list tableFieldInfos as field>
  `${field.columnName}` ${field.fieldTypeEnum.typeCode()}<#if field.columnLength?has_content>(${field.columnLength})</#if><#if field.isNull == false> NOT NULL</#if><#if field.defaultValue?has_content> DEFAULT ${field.defaultValue}</#if><#if field.extend?has_content> ${field.extend}</#if><#if field.comments?has_content> COMMENT '${field.comments }'</#if>,
  </#list>
  <#if keyList?has_content>
    <#list keyList as key>
  ${key.keyType} <#if key.keyName?has_content>`${key.keyName}`</#if> (`${key.columnNames}`) USING BTREE<#if key?is_last == false>,</#if>
    </#list>
  </#if>
</#if>
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC COMMENT='${tableComments}';