<template>
  <div class="box-card-padding">
    <el-page-header @back="goBack" style="margin-bottom: 10px"/>
    <el-tabs v-model="tabActiveName" @tab-click="handleClick">
      <el-tab-pane label="详情信息" name="baseInfo">
        <el-collapse v-model="activeNames">
          <!-- 基础信息 -->
          <el-collapse-item title="基础信息" name="1">
            <el-descriptions class="margin-top" :column="2" border>
              <#if tableFieldData?has_content>
                <#list tableFieldData as field>
              <el-descriptions-item>
                <template slot="label">
                  ${field.columnComment}
                </template>
                <#if field.entityFieldName == 'createTime' || field.entityFieldName == 'updateTime'>
                {{ formatDateTime(new Date(baseData.${field.entityFieldName}), "yyyy-MM-dd HH:mm:ss") }}
                  <#else>
                {{ baseData.${field.entityFieldName} }}
                </#if>
              </el-descriptions-item>
                </#list>
              </#if>
            </el-descriptions>
          </el-collapse-item>
        </el-collapse>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>

import {detail} from "./stores/${humpEntityName}";
import {formatDateTime} from "@/js/utils";

export default {
  name: "${entityName}Detail",
  data() {
    return {
      baseData: {},
      tabActiveName: "baseInfo",
      activeNames: ['1']
    };
  },
  methods: {
    formatDateTime,
    /**
     * 切换tab页
     *
     * @param tab
     * @param event
     */
    handleClick(tab, event) {

    },
    /**
     * 返回上一页
     */
    goBack() {
      this.$router.go(-1);
    }
  },
  async mounted() {
    if (!this.$route.params.id) {
      this.$router.go(-1);
    }
    await detail({id: this.$route.params.id}).then(res => {
      if (res.code === 200) {
        this.baseData = res.data;
      }
    })
  },
};
</script>

<style>

</style>




