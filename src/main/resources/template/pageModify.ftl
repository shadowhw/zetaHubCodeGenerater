<template>
  <div class="${humpEntityName}Modify">
    <!-- 新增或修改对话框 -->
    <el-dialog :title="titleType" :close-on-click-modal="false" :visible.sync="open" :before-close="handleClose"
               :center="true" :width="'60%'" append-to-body>
      <el-form ref="dataForm" :model="dataForm" label-width="80px" :rules="rules">
        <#if tableFieldData?has_content>
          <#list tableFieldData as field>
            <#if field?index % 2 == 0>
        <el-row>
          <el-col :span="12">
            <el-form-item label="${field.columnComment}" prop="${field.entityFieldName}">
              <el-input v-model="dataForm.${field.entityFieldName}"></el-input>
            </el-form-item>
          </el-col>
              <#else>
          <el-col :span="12">
            <el-form-item label="${field.columnComment}" prop="${field.entityFieldName}">
              <el-input v-model="dataForm.${field.entityFieldName}"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
            </#if>
            <#if field?is_last && field?index % 2 == 0>
        </el-row>
            </#if>
          </#list>
        </#if>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="saveOrUpdate">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "${entityName}Modify",
  props: {
    dict: []
  },
  data() {
    return {
      open: false,
      dataForm: {},
      titleType: "",
      rules: {
      <#if tableFieldData?has_content>
          <#list tableFieldData as field>
        ${field.entityFieldName}: [
          {required: true, message: "请输入字典${field.columnComment}", trigger: "change"},
        ],
          </#list>
      </#if>
      },
    };
  },
  methods: {
    /**
     * 打开新增或修改
     */
    openModify(data) {
      this.open = true;
      // 修改
      if (data) {
        this.titleType = "更新数据";
        this.dataForm = data;
        return;
      }
      this.titleType = "新增数据";
      this.dataForm = {};
    },
    /**
     * 关闭弹出清除数据
     */
    handleClose() {
      Object.assign(this.$data, this.$options.data())
    },
    /**
     * 提交数据
     */
    saveOrUpdate() {
      this.$refs["dataForm"].validate((valid) => {
        if (valid) {
          this.$emit("submitSaveOrUpdate", {...this.dataForm});
          this.open = false;
        } else {
          this.$message.error("有必填项未填写");
          return false;
        }
      });
    }
  },
};
</script>
