<template>
  <div class="box-card">
    <!-- 查询面板 -->
    <${entityName}QueryForm @queryPageList="queryPageList"/>

    <!-- 新增或修改 -->
    <${entityName}Modify ref="${humpEntityName}Modify" :dict="dict" @submitSaveOrUpdate="submitSaveOrUpdate"/>

    <!-- 表格列表 -->
    <el-card>
      <el-button type="success" size="small" @click="openAddData" v-has-perm="`${packageConfig.moduleName}:${humpEntityName}:create`">
        {{ this.$globalVar.getButtonNameByPerms("${packageConfig.moduleName}:${humpEntityName}:create") }}
      </el-button>
    </el-card>
    <el-table border :data="tableData" stripe :header-cell-style="tbHeadCellStyle">
      <#if tableFieldData?has_content>
        <#list tableFieldData as field>
      <el-table-column prop="${field.entityFieldName}" label="${field.columnComment}"/>
        </#list>
      </#if>
      <el-table-column label="操作">
        <template v-slot="scope">
          <div :style="optionButtonStyle">
            <el-button size="mini" type="info" @click="queryDetail(scope.row)"
                       v-has-perm="`${packageConfig.moduleName}:${humpEntityName}:detail`">
              {{ $globalVar.getButtonNameByPerms("${packageConfig.moduleName}:${humpEntityName}:detail") }}
            </el-button>
            <el-button size="mini" type="success" @click="openUpdateData(scope.row)"
                       v-has-perm="`${packageConfig.moduleName}:${humpEntityName}:update`">
              {{ $globalVar.getButtonNameByPerms("${packageConfig.moduleName}:${humpEntityName}:update") }}
            </el-button>
            <el-button size="mini" type="danger" @click="handleDelete(scope.row)"
                       v-has-perm="`${packageConfig.moduleName}:${humpEntityName}:delete`">
              {{ $globalVar.getButtonNameByPerms("${packageConfig.moduleName}:${humpEntityName}:delete") }}
            </el-button>
          </div>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <el-pagination background @size-change="handleSizeChange" @current-change="handleCurrentChange"
                   :page-sizes="[10, 30, 50]" :current-page="pageReq.pageIndex"
                   layout="total,sizes,prev, pager, next"
                   :total="total">
    </el-pagination>
  </div>
</template>
<script>
import table from "../../../js/mixins/table";
import {getPageList, createData, updateData, deleteData} from "./stores/${humpEntityName}";
import ${entityName}QueryForm from "./component/${entityName}QueryForm.vue";
import ${entityName}Modify from "./component/${entityName}Modify.vue";

export default {
  name: "${entityName}",
  mixins: [table],
  components: {
    ${entityName}QueryForm, ${entityName}Modify
  },
  data() {
    return {
      dict: {},
    };
  },
  methods: {
    /**
     * 分页查询
     *
     * @param fields 查询字段
     */
    queryPageList(fields = {}) {
      getPageList({...this.pageReq, ...fields}).then((res) => {
        this.tableData = res.data.rows;
        this.total = res.data.total;
      });
    },

    /**
     * 提交请求-更新或新增
     *
     * @param data
     * @returns {Promise<void>}
     */
    async submitSaveOrUpdate(data) {
      // 更新
      if (data.id) {
        await updateData(data).then((res) => {
          if (res.code === 200) {
            this.$message.success(res.msg);
          }
        });
      } else {
        // 新增
        await createData(data).then((res) => {
          if (res.code === 200) {
            this.$message.success(res.msg);
          }
        });
      }
      this.queryPageList();
    },

    /**
     * 打开新增面板
     */
    openAddData() {
      this.$refs.${humpEntityName}Modify.openModify();
    },

    /**
     * 打开修改面板
     *
     * @param data
     */
    openUpdateData(data) {
      this.$refs.${humpEntityName}Modify.openModify(data);
    },

    /**
     * 删除
     *
     * @param row
     */
    handleDelete(row) {
      deleteData({id: row.id}).then((res) => {
        if (res.code === 200) {
          this.$message.success("删除成功");
          this.queryPageList();
        }
      });
    },
    /**
     * 查询详情
     *
     * @param row
     */
    queryDetail(row) {
      this.$router.push({name: "${entityName}Detail", params: {id: row.id}});
    }
  },
  mounted() {
    this.queryPageList();
  },
};
</script>

<style>
</style>


