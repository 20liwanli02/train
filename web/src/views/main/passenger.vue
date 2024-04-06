<template>
  <p>
    <a-space>
  <a-button type="primary" @click="handleQuery()">刷新</a-button>
  <a-button type="primary" @click="onAdd">新增</a-button>
    </a-space>
  </p>
  <a-table :dataSource="passengers" :columns="columns" :pagination="pagination" @change="handleTableChange" :loading="loading">
<!--    增加普通的属性列，直接再columns里加就行了，对应好name，type等自动会加上
        但增加编辑删除等按钮需要用到下面的特殊的写法
        -->
    <template #bodyCell="{ column, record }">
      <template v-if="column.dataIndex === 'operation'">
        <a-space>
          <a-popconfirm
              title="删除后不可恢复，确认删除?"
              @confirm="onDelete(record)"
              ok-text="确认" cancel-text="取消">
            <a style="color: red">删除</a>
          </a-popconfirm>
          <a @click="onEdit(record)">编辑</a>
        </a-space>
      </template>
<!--      将type写成我自定义的东西-->
      <template v-else-if="column.dataIndex === 'type'">
        <span v-for="item in PASSENGER_TYPE_ARRAY" :key="item.key">
          <span v-if="item.key === record.type">
            {{item.value}}
          </span>
        </span>
      </template>
    </template>
  </a-table>
  <a-modal v-model:visible="visible" title="乘车人" @ok="handleOk" ok-text="确认" cancel-text="取消">
    <a-form :model="passenger" :label-col="{span: 4}" :wrapper-col="{ span: 20 }">
      <a-form-item label="姓名">
        <a-input v-model:value="passenger.name" />
      </a-form-item>
      <a-form-item label="身份证">
        <a-input v-model:value="passenger.idCard" />
      </a-form-item>
      <a-form-item label="旅客类型">
        <a-select v-model:value="passenger.type">
          <a-select-option v-for="item in PASSENGER_TYPE_ARRAY" :key="item.key" :value="item.key">
            {{item.value}}
          </a-select-option>
        </a-select>
      </a-form-item>
    </a-form>
  </a-modal>
</template>


<script>
import {defineComponent,ref,onMounted} from "vue";
import {notification} from "ant-design-vue";
import axios from "axios";


export default defineComponent({
  name: "passenger-view",
  setup() {
    const PASSENGER_TYPE_ARRAY = window.PASSENGER_TYPE_ARRAY;
    const visible = ref(false);
    let passenger = ref({
      id: undefined,
      memberId: undefined,
      name: undefined,
      idCard: undefined,
      type: undefined,
      createTime: undefined,
      updateTime: undefined,
    });

    const passengers = ref([]);

        const columns = [
          {
            title: '会员id',
            dataIndex: 'memberId',
            key: 'memberId',
          },
          {
            title: '姓名',
            dataIndex: 'name',
            key: 'name',
          },
          {
            title: '身份证',
            dataIndex: 'idCard',
            key: 'idCard',
          },
          {
            title: '旅客类型',
            dataIndex: 'type',
            key: 'type',
          },
          {
            title: '操作',
            dataIndex: 'operation',
          },
        ];
    // 分页的三个属性名是固定的
    const pagination = ref({
      total: 0,
      current: 1,
      pageSize: 10,
    });

    /**
     *删除乘车信息
     * */
    const onDelete = (record) => {
          axios.delete("/member/passenger/delete/" + record.id).then((response) => {
            const data = response.data;
            if (data.success) {
              notification.success({description: "删除成功！"});
              handleQuery({
                page: pagination.value.current,
                size: pagination.value.pageSize,
              });
            } else {
              notification.error({description: data.message});
            }
          });
    };

    let loading = ref(false)
    const handleQuery = (param) => {
      if (!param) {
        param = {
          page: 1,
          size: pagination.value.pageSize
        };
      }
      loading.value = true;
      axios.get("/member/passenger/query-list", {
        params: {
          page: param.page,
          size: param.size
        }
      }).then((response) => {
        loading.value = false;
        let data = response.data;
        if (data.success) {
          passengers.value = data.content.list;
          pagination.value.total = data.content.total;
          // 设置分页控件的值
          pagination.value.current = param.page;
          // pagination.value.total = data.content.total;
        } else {
          notification.error({description: data.message});
        }
      });
    };

    const handleTableChange = (pagination) => {
      // console.log("看看自带的分页参数都有啥：" + pagination);
      handleQuery({
        page: pagination.current,
        size: pagination.pageSize
      });
    };

    const onAdd = () => {
      passenger.value = {};
      visible.value = true;
    };

    const onEdit = (record) => {
      // record还是双向绑定的？原来类似于针织的赋值，现在咋先copy一份再赋值
      passenger.value = window.Tool.copy(record);
      visible.value = true;
    }

    const handleOk = () => {
      axios.post("/member/passenger/save", passenger.value).then((response) => {
        let data = response.data;
        if (data.success) {
          notification.success({description: "保存成功！"});
          visible.value = false;
          handleQuery({
            page: pagination.value.current,
            size: pagination.value.pageSize
          });
        } else {
          notification.error({description: data.message});
        }
      });
    };

    onMounted(() => {
      handleQuery({
        page: 1,
        size: pagination.value.pageSize
        // size: 2
      });
    });

    return {
      visible,
      onAdd,
      handleOk,
      passenger,
      passengers,
      columns,
      pagination,
      handleTableChange,
      handleQuery,
      loading,
      onEdit,
      onDelete,
      PASSENGER_TYPE_ARRAY,
    };
  },
})
</script>


<style>

</style>

