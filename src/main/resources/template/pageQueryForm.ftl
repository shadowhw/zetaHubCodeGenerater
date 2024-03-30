<template>
  <div class="${entityName}QueryForm">
    <!-- 条件查询面板 -->
    <el-card :body-style="{ padding: '10px' }">
      <el-form :inline="true" size="mini" :model="searchForm" style="margin-top: 0">
        <#if tableFieldData?has_content>
          <#list tableFieldData as field>
        <el-form-item label="${field.columnComment}">
          <el-input v-model="searchForm.${field.entityFieldName}" placeholder="输入${field.columnComment}"></el-input>
        </el-form-item>
          </#list>
        </#if>
        <el-form-item>
          <el-button type="primary" @click="submitSearch">查询</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "${entityName}QueryForm",
  data() {
    return {
      searchForm: {}
    };
  },
  methods: {
    /**
     * 条件查询
     */
    submitSearch() {
      this.$emit("queryPageList", {...this.searchForm});
    },
  },
};
</script>



